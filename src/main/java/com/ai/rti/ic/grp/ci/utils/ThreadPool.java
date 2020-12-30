package com.ai.rti.ic.grp.ci.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.ai.rti.ic.grp.utils.Config;
import com.ai.rti.ic.grp.utils.StringUtil;

public class ThreadPool {
	private Logger log = Logger.getLogger(ThreadPool.class);

	private final ExecutorService service;

	private final ExecutorService singleThreadService;

	private final Map<String, ExecutorService> threadMap;

	static class ThreadPoolHolder {
		static final ThreadPool instance = new ThreadPool();
	}

	public static ThreadPool getInstance() {
		return ThreadPoolHolder.instance;
	}

	private ThreadPool() {
		int threadNum = Runtime.getRuntime().availableProcessors() * 2;

		try {
			threadNum = NumberUtils.toInt(Config.getObject("THREAD_NUM"), threadNum);
		} catch (Exception exception) {
		}

		Map<String, ExecutorService> threadPoolMap = new HashMap<>();

		try {
			String useCityThreadPool = Config.getObject("USE_CITY_THREAD_POOL");
			if (StringUtil.isNotEmpty(useCityThreadPool) && "true".equals(useCityThreadPool)) {
				CacheBase cache = CacheBase.getInstance();
				Map<Object, Object> cityThreadConfigMap = cache.getCityThreadConfigMap();
				for (Object key : cityThreadConfigMap.keySet()) {
					Object value = cityThreadConfigMap.get(key);
					ExecutorService eachCityThreadService = Executors.newFixedThreadPool(((Integer) value).intValue());
					threadPoolMap.put(key.toString(), eachCityThreadService);
					this.log.info("地市ID：" + key.toString() + "================线程数：" + value);
				}
			}
		} catch (Exception e) {

			this.log.error("从缓存中获取地市线程池配置信息出错", e);
		}
		this.threadMap = threadPoolMap;
		this.singleThreadService = Executors.newFixedThreadPool(1);
		this.service = Executors.newFixedThreadPool(threadNum);
	}

	public int getSize() {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) this.service;
		return pool.getQueue().size();
	}

	public void execute(Runnable r) {
		try {
			this.service.execute(r);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		this.log.info("add task [" + r.toString() + "] ThreadPool status:" + showStatus());
	}

	public void execute(Runnable r, boolean isSingleThread) {
		if (!isSingleThread) {
			execute(r);
		} else {
			try {
				this.singleThreadService.execute(r);
			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}

		this.log.info("add task [" + r.toString() + "] to SingleThread ,ThreadPool status:" + showStatus());
	}

	public void execute(Runnable r, boolean isSingleThread, String cityId) {
		if (!isSingleThread) {
			try {
				if (StringUtil.isNotEmpty(cityId) && this.threadMap != null && this.threadMap.size() > 0) {
					ExecutorService eachCityPool = this.threadMap.get(cityId);
					if (eachCityPool != null) {
						eachCityPool.execute(r);
						this.log.debug("使用的线程的cityId为：" + cityId + ",该线程池的线程数为"
								+ ((ThreadPoolExecutor) eachCityPool).getMaximumPoolSize());
					} else {
						execute(r, true);
					}
				} else {
					execute(r, true);
				}
			} catch (Exception ex) {

				ex.printStackTrace();
			}
		} else {

			try {
				this.singleThreadService.execute(r);
			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}

		this.log.info("add task [" + r.toString() + "] to SingleThread ,ThreadPool status:" + showStatus());
	}

	public String showStatus() {
		StringBuffer info = new StringBuffer();
		info.append("FixedThreadPool:");
		ThreadPoolExecutor executor = (ThreadPoolExecutor) this.service;
		info.append("ActiveCount:").append(executor.getActiveCount()).append(",");
		info.append("CompletedTaskCount:").append(executor.getCompletedTaskCount()).append(",");
		info.append("TaskCount:").append(executor.getTaskCount()).append(",");
		info.append("PoolSize:").append(executor.getPoolSize()).append(",");
		info.append("CorePoolSize:").append(executor.getCorePoolSize()).append(",");
		info.append("LargestPoolSize:").append(executor.getLargestPoolSize()).append(",");
		BlockingQueue<Runnable> queue = executor.getQueue();
		info.append("QueueSize:").append(queue.size()).append(",");
		info.append("BlockingQueue:");
		if (queue != null) {
			info.append("[");
			for (Runnable r : queue) {
				info.append(r);
			}
			info.append("]");
		}
		info.append("\nSingleThreadPool:");
		ThreadPoolExecutor singleExecutor = (ThreadPoolExecutor) this.singleThreadService;
		info.append("ActiveCount:").append(singleExecutor.getActiveCount()).append(",");
		info.append("CompletedTaskCount:").append(singleExecutor.getCompletedTaskCount()).append(",");
		info.append("TaskCount:").append(singleExecutor.getTaskCount()).append(",");
		info.append("PoolSize:").append(singleExecutor.getPoolSize()).append(",");
		info.append("CorePoolSize:").append(singleExecutor.getCorePoolSize()).append(",");
		info.append("LargestPoolSize:").append(singleExecutor.getLargestPoolSize()).append(",");
		queue = singleExecutor.getQueue();
		info.append("QueueSize:").append(queue.size()).append(",");
		info.append("BlockingQueue:");
		if (queue != null) {
			info.append("[");
			for (Runnable r : queue) {
				info.append(r);
			}
			info.append("]");
		}
		return info.toString();
	}

	public void execute(Callable<?> r) throws Exception {
		try {
			this.service.submit(r);
		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;
		}

		this.log.info("add task [" + r.toString() + "] to ThreadPool status:" + showStatus());
	}

	public void execute(Callable<?> r, boolean isSingleThread) throws Exception {
		if (!isSingleThread) {
			execute(r);
		} else {
			try {
				this.singleThreadService.submit(r);
			} catch (Exception ex) {

				ex.printStackTrace();
				throw ex;
			}

			this.log.info("add task [" + r.toString() + "] to SingleThread ,ThreadPool status:" + showStatus());
		}
	}

	public void execute(Callable<?> r, boolean isSingleThread, String cityId) throws Exception {
		if (!isSingleThread) {
			try {
				if (StringUtil.isNotEmpty(cityId) && this.threadMap != null && this.threadMap.size() > 0) {
					ExecutorService eachCityPool = this.threadMap.get(cityId);
					if (eachCityPool != null) {
						eachCityPool.submit(r);
						this.log.info("使用的线程的cityId为：" + cityId + ",该线程池的线程数为"
								+ ((ThreadPoolExecutor) eachCityPool).getMaximumPoolSize());
					} else {
						execute(r, true);
					}
				} else {
					execute(r, true);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				this.singleThreadService.submit(r);
			} catch (Exception ex) {

				ex.printStackTrace();
				throw ex;
			}

			this.log.info("add task [" + r.toString() + "] to SingleThread ,ThreadPool status:" + showStatus());
		}
	}

	public List<Runnable> shutdownNow() {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) this.service;
		return pool.shutdownNow();
	}
}
