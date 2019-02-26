package com.xzst.modi.app.dModel;

import java.util.Map;

/**
 * Created by LHT on 2018/7/5.
 */
public class ConsumerMessageBean {

	//结果集编号
	private String resultSetId;
	//模板列名与其对应值
	private Map<String,String> dataMap;



	public String getResultSetId() {
		return resultSetId;
	}

	public void setResultSetId(String resultSetId) {
		this.resultSetId = resultSetId;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		return "ConsumerMessageBean:    {" +
				"resultSetId='" + resultSetId + '\'' +
				", dataMap=" + dataMap +
				'}';
	}
}
