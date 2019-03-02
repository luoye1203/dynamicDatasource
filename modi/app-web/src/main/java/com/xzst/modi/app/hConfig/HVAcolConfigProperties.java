package com.xzst.modi.app.hConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "other.hav-config")
public class HVAcolConfigProperties {
    private List<Map<String, String>> focusCol;


    public List<Map<String, String>> getFocusCol() {
        return focusCol;
    }

    public void setFocusCol(List<Map<String, String>> focusCol) {
        this.focusCol = focusCol;
    }
}
