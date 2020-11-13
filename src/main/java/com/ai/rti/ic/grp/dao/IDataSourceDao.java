package  com.ai.rti.ic.grp.dao;

import com.ai.rti.ic.grp.entity.DataSource;
import com.ai.rti.ic.grp.entity.DataSourceAuthConf;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IDataSourceDao {
  List<DataSource> getDataSourceList(DataSource paramDataSource);
  
  List<DataSourceAuthConf> getDataSourceAuthConf(DataSourceAuthConf paramDataSourceAuthConf);
}

 