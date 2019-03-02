package com.xzst.modi.app.hConfig;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "other.websocketinformtype")
public class WebsocketInformTypeConifg {
    private Map<String,String > informType;

    public Map<String, String> getInformType() {
        return informType;
    }

    public void setInformType(Map<String, String> informType) {
        this.informType = informType;
    }
}
