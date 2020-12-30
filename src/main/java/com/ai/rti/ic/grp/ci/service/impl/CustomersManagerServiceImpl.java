package com.ai.rti.ic.grp.ci.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.rti.ic.grp.ci.dao.ICiCustomGroupInfoHDao;
import com.ai.rti.ic.grp.ci.dao.ICiCustomListInfoHDao;
import com.ai.rti.ic.grp.ci.dao.ICiCustomPushReqHDao;
import com.ai.rti.ic.grp.ci.dao.ICiCustomSceneHDao;
import com.ai.rti.ic.grp.ci.dao.ICiSysInfoHDao;
import com.ai.rti.ic.grp.ci.dao.ICustomGroupInfoJDao;
import com.ai.rti.ic.grp.ci.dao.IMarketManageSubDomainJDao;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomGroupPushCycle;
import com.ai.rti.ic.grp.ci.entity.CiCustomListInfo;
import com.ai.rti.ic.grp.ci.entity.CiCustomPushReq;
import com.ai.rti.ic.grp.ci.entity.CiCustomSceneRel;
import com.ai.rti.ic.grp.ci.entity.CiSysInfo;
import com.ai.rti.ic.grp.ci.entity.IdToName;
import com.ai.rti.ic.grp.ci.exception.CIServiceException;
import com.ai.rti.ic.grp.ci.service.ICiCustomListInfoJDao;
import com.ai.rti.ic.grp.ci.service.ICustomersManagerService;
import com.ai.rti.ic.grp.ci.utils.CIAlarmServiceUtil;
import com.ai.rti.ic.grp.ci.utils.CacheBase;
import com.ai.rti.ic.grp.ci.utils.CiUtil;
import com.ai.rti.ic.grp.ci.utils.ThreadPool;
import com.ai.rti.ic.grp.constant.CommonConstants;
import com.ai.rti.ic.grp.constant.ServiceConstants;
import com.ai.rti.ic.grp.ci.job.CustomerPublishThread;
import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.SpringContextUtil;


/**
 * 客户群接口实现类
 * 
 * @author DENGGANG
 * 
 */
@Service
@Transactional
public class CustomersManagerServiceImpl implements ICustomersManagerService {
	private Logger log = Logger.getLogger(CustomersManagerServiceImpl.class);
	@Autowired
	private ICustomGroupInfoJDao customersJdbcDao;
	@Autowired
	private ICiCustomListInfoHDao ciCustomListInfoHDao;
	@Autowired
	private ICiCustomGroupInfoHDao ciCustomGroupInfoHDao;
	@Autowired
	private ICiSysInfoHDao ciSysInfoHDao;
	@Autowired
	private ICiCustomPushReqHDao ciCustomPushReqHDao;
	@Autowired
	private ICiCustomListInfoJDao ciCustomListInfoJDao;
//	@Autowired
//	private ICiLabelRuleHDao ciLabelRuleHDao;
//	@Autowired
//	private ICiLabelRuleJDao ciLabelRuleJDao;
	@Autowired
	private IMarketManageSubDomainJDao marketManageSubDomainJDao;
	@Autowired
	private ICiCustomSceneHDao ciCustomSceneHDao;
	
	public static Lock customLock = new ReentrantLock();// 客户群锁

	public static Lock listLock = new ReentrantLock();// 清单锁

	public static Lock labelRuleLock = new ReentrantLock();// 清单锁



	@Override
	public void pushCustomerGroup(String customGroupId, String userId) {

		log.info("客户群:"+customGroupId+"推送线程 start");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //等待事务提交
            }
			
            List<CiCustomListInfo> customListInfoList = this.queryCiCustomListInfoByCGroupId(customGroupId);
            if(customListInfoList != null && customListInfoList.size()>0) {
            	log.info("推送客户群 有清单!");
                //for (CiCustomListInfo list : createdListInfo) {
                CiCustomListInfo list = customListInfoList.get(0);
                //不论同时生成几个清单，只推送最新月份和最新日期的清单
                if (list.getDataStatus() == ServiceConstants.CUSTOM_LIST_STATUS_SUCCESS) {
					log.info("推送客户群 状态正常!");
                    String cgId = list.getCustomGroupId();
                    List<CiCustomGroupPushCycle> pushCycleList = this.queryAllPushCycleByGroupId(cgId);

                    if (pushCycleList != null && pushCycleList.size()>0) {
                        for (CiCustomGroupPushCycle item : pushCycleList) {
							log.info("推送客户群 有推送记录!");
                            //判断周期性客户群一次性推送，已经推送过之后就不在进行推送
//                            if(item.getPushCycle() == ServiceConstants.CUSTOM_CYCLE_TYPE_ONE
//                                    && item.getIsPushed() == ServiceConstants.CUSTOM_IS_PUSHED) {
//                                continue;
//                            }
                            CiCustomPushReq ciCustomPushReq = new CiCustomPushReq();
                            ciCustomPushReq.setUserId(userId);
                            ciCustomPushReq.setSysId(item.getSysId());
                            ciCustomPushReq.setReqTime(new Date());
                            ciCustomPushReq.setStatus(ServiceConstants.PUSH_CUSOMER_STATUS_WAIT);
                            ciCustomPushReq.setListTableName(list.getListTableName());
                            ciCustomPushReq.setReqId("COC" + CiUtil.convertLongMillsToYYYYMMDDHHMMSS(-1));
                            try {
                                List<String> sysIds = new ArrayList<>();
                                sysIds.add(item.getSysId());
                                ciCustomPushReq.setSysIds(sysIds);
                                List<CiCustomPushReq> customPushRegList = new ArrayList<>();
                                customPushRegList.add(ciCustomPushReq);
                                this.pushCustomers(customPushRegList, String.valueOf(item.getPushCycle()));
                                //一次性推送完毕之后，将状态改为已推送
                                if(item.getPushCycle() == ServiceConstants.CUSTOM_CYCLE_TYPE_ONE) {
                                    item.setIsPushed(ServiceConstants.CUSTOM_IS_PUSHED);
                                    this.saveCiCustomGroupPushCycle(item);
                                }
                            } catch (Exception e) {
                                log.error("processAutoPush error", e);
                            }
                        }
                    }else{
                        log.info("客户群没有设置推送!");
                    }
                }else{
					log.info("最新账期的客户群清单没有生成!");
                }
            }else{
            	log.info("没有清单数据");
			}

	}

	/**
	 * 客户群推送
	 */
	public void pushCustomers(List<CiCustomPushReq> customPushReqList,
			String pushCycle) {

		boolean validateParameter = true;

		if (customPushReqList != null) {
			for (int i = 0; i < customPushReqList.size(); i++) {
				CiCustomPushReq ciCustomPushReq = customPushReqList.get(i);
				List<String> ids = ciCustomPushReq.getSysIds();
				if (ids != null) {
					for (String id : ids) {
						CiSysInfo sysInfo = ciSysInfoHDao.selectById(id);
						if (sysInfo == null) {
							validateParameter = false;// 系统信息不存在
							break;
						}
					}
				}
				if (!validateParameter) {
					break;
				}
				CiCustomListInfo ciCustomListInfo = ciCustomListInfoHDao.selectById(ciCustomPushReq.getListTableName());
				if (ciCustomListInfo == null
						|| ciCustomListInfo.getDataStatus() != ServiceConstants.CUSTOM_LIST_STATUS_SUCCESS) {
					validateParameter = false;// 清单状态不正确
					break;
				}
			}
		} else {
			validateParameter = false;
		}

		if (!validateParameter) {
			return;
		}

		try {
			CustomerPublishThread pub = (CustomerPublishThread) SpringContextUtil.getBean("customerPublishThread");
			pub.initParamter(customPushReqList, pushCycle);
			ThreadPool.getInstance().execute(pub);
		} catch (Exception e) {
			log.error("创建推送请求失败", e);
			throw new CIServiceException("创建推送请求失败");
		}

	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<CiCustomListInfo> queryCiCustomListInfoByCGroupId(
			String customGroupId) throws CIServiceException {
		return ciCustomListInfoHDao.selectByCustomGroupId(customGroupId);
	}
	
	/**
	 * 根据客户群ID查询已经周期推送过的平台
	 */
	public List<CiCustomGroupPushCycle> queryAllPushCycleByGroupId(
			String customGroupId) {
		return ciCustomGroupInfoHDao.selectPushCycleByGroupId(customGroupId);
	}


	/**
	 * 保存周期性推送到某些平台的客户群
	 */
	public void saveCiCustomGroupPushCycle(CiCustomGroupPushCycle pushCycle) {
		ciCustomGroupInfoHDao.insertCustomGroupPushCycle(pushCycle);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public CiCustomListInfo queryCiCustomListInfoById(String listTableName)
			throws CIServiceException {
		return ciCustomListInfoHDao.selectById(listTableName);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<CiCustomGroupInfo> queryCiCustomGroupInfoListByName(
			String customGroupName, String userId, String createCityId)
			throws CIServiceException {
		List<CiCustomGroupInfo> list = null;

		try {
			list = ciCustomGroupInfoHDao.selectCiCustomGroupInfoListByName(
					customGroupName, userId, createCityId);
		} catch (Exception e) {
			String message = "根据名称及用户Id查询客户群错误";
			log.error(message, e);
			throw new CIServiceException(message, e);
		}
		return list;
	}

	public void saveCiCustomPushReq(CiCustomPushReq req) {
		if (req.getReqId() == null) {
			req.setReqId("COC" + CiUtil.convertLongMillsToYYYYMMDDHHMMSS(-1L));
			ciCustomPushReqHDao.insertCiCustomPushReq(req);
		}else {
			ciCustomPushReqHDao.updateCiCustomPushReqById(req);
		}
		
	}

	public void updateCiCustomPushReqById(CiCustomPushReq req) {
		if (req.getReqId() == null) {
			req.setReqId("COC" + CiUtil.convertLongMillsToYYYYMMDDHHMMSS(-1L));
		}
		ciCustomPushReqHDao.updateCiCustomPushReqById(req);
	}
	
	public void executeInBackDataBase(String sql) {
		ciCustomListInfoJDao.executeInBackDataBase(sql);
	}


	@Override
	public String queryOtherSysCustomGroupId(String customGroupId, String sysId) {
		log.info("===================》》根据系统ID和客户群ID查询外部系统对应的客户群ID，SYSID：" + sysId);
		return customersJdbcDao.selectOtherSysCustomGroupId(customGroupId, sysId);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<CiCustomPushReq> queryCiCustomPushReq(
			CiCustomPushReq ciCustomPushReq) {
		return ciCustomPushReqHDao.select(ciCustomPushReq);
	}

	/**
	 * 查询客户群
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public CiCustomGroupInfo queryCiCustomGroupInfo(String ciCustomGroupInfoId) {
		try {
			CiCustomGroupInfo ciCustomGroupInfo = ciCustomGroupInfoHDao.selectCustomGroupById(ciCustomGroupInfoId);
            if (null != ciCustomGroupInfo) {
            	
            	List<Map<String,Object>> dataSourceList = marketManageSubDomainJDao.queryDimList("CAM-C-0032");
            	for(Map<String,Object> dataSourceMap:dataSourceList){
            		if(dataSourceMap.get("CODE_VALUE").equals(ciCustomGroupInfo.getDataSource())){
            			ciCustomGroupInfo.setDataSourceStr((String)dataSourceMap.get("VALUE"));
            		}
            	}
            	
                // 客户群地市名称赋值
                if (ciCustomGroupInfo.getCreateCityId() != null) {
                    ciCustomGroupInfo.setCreateCityName(IdToName.getName(
                        CommonConstants.TABLE_DIM_CITY,
                        Integer.valueOf(ciCustomGroupInfo.getCreateCityId())));
                }
                CacheBase cache = CacheBase.getInstance();
                // 设置客户群场景名称
                StringBuilder customSceneNames = new StringBuilder();
                List<CiCustomSceneRel> ciCustomSceneRels = ciCustomSceneHDao
                        .getCustomScenesByCustomId(ciCustomGroupInfo
                            .getCustomGroupId());
                if (ciCustomSceneRels != null && ciCustomSceneRels.size() != 0) {
                    for (int i = ciCustomSceneRels.size() - 1; i >= 0; i--) {
                        if (ciCustomSceneRels.get(i) != null) {
                            String dimSceneName = cache
                                    .getDimScene(ciCustomSceneRels.get(i)
                                        .getSceneId());
                            customSceneNames.append(dimSceneName).append(
                                i > 0 ? "&nbsp;&nbsp;" : "");
                        }
                    }
                    ciCustomGroupInfo.setCustomSceneNames(customSceneNames
                        .toString());
                }
                // 设置是否是系统最热客户群
                CiCustomGroupInfo info = cache.getHotCustomByKey(
                    CommonConstants.HOT_CUSTOMS,
                    ciCustomGroupInfo.getCustomGroupId());
                if (info != null) {
                    ciCustomGroupInfo.setIsHotCustom("true");
                }
                // 创建人赋值 TODO
//                String iuser = PrivilegeServiceUtil.getUserById(ciCustomGroupInfo
//                    .getCreateUserId());
//                if (iuser != null) {
//                    ciCustomGroupInfo.setCreateUserName(iuser);
//                }
                // 创建方式
                if (ciCustomGroupInfo.getCreateTypeId() != null) {
                    ciCustomGroupInfo.setCreateType(IdToName.getName(
                        CommonConstants.TABLE_DIM_CUSTOM_CREATE_TYPE,
                        ciCustomGroupInfo.getCreateTypeId()));
                }
                //判断是否已经生成预警
                String alarm_menu = Config.getObject("AlARM_MENU");
                if(alarm_menu.equals("true")){
                    ciCustomGroupInfo
                    .setAlarm(CIAlarmServiceUtil.haveAlarmRecords(
                        ciCustomGroupInfo.getDataDate(),
                        ciCustomGroupInfo.getCreateUserId(),
                        ServiceConstants.ALARM_THRESHOLD_TYPE_CUSTOMERS,
                        ciCustomGroupInfo.getCustomGroupId()));
                }
            }
			return ciCustomGroupInfo;
		} catch (Exception e) {
			log.error("查询客户群失败," + ciCustomGroupInfoId);
			e.printStackTrace();
			throw new CIServiceException("查询客户群分页失败");
		}
	}


}