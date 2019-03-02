package com.xzst.modi.app.cDao;

import com.xzst.modi.app.iannotation.DataSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@DataSource("ajfl")
public interface OtherDataSourceTestDao {

    public List<Map<String,String>> getTest();

}
