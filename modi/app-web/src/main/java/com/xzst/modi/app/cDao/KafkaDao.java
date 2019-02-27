package com.xzst.modi.app.cDao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface KafkaDao {

    public List<Map<String,String>> getAllConfigedResultSetIds();
}
