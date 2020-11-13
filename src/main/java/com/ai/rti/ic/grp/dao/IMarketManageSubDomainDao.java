package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.MarketManageSubDomain;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMarketManageSubDomainDao {
  List<MarketManageSubDomain> getMarketManageSubDomain(MarketManageSubDomain paramMarketManageSubDomain);
}

 