package com.xzst.modi.app.dModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by li on 2017/7/19.
 * 返回数据的模型
 */
public class BaseResponse {
    /**
     * 成功时返回的数据
     */
    private  Object obj;
    /**
     * 返回状态
     */
    private  int code;
    /**
     * 返回msg
     */
    private  String message;
    /**
     * 其余参数
     */
    private  Map<String, String> params;

    private BaseResponse(Object obj, int code, String message,
                         Map<String, String> params) {
        this.obj = obj;
        this.code = code;
        this.message = message;
        this.params = params;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public static Builder buildResponse() {
        return new Builder();
    }
    /**
     *
     */
    public static final class Builder {
        private static final Map<Object, Object> NULL_OBJ = new HashMap<>();
        /**
         * 返回值
         */
        private  int retCode;
        /**
         * msg
         */
        private  String message;
        /**
         * 其他参数
         */
        private  Map<String, String> params = new HashMap<>();
        /**
         * 数据对象
         */
        private Object obj;

        private Builder() { }

        /**
         * 添加参数信息
         *
         * @param key
         * @param value
         * @return
         */
        public Builder addParam(final String key, final String value) {
            this.params.put(key, value);
            return this;
        }

        public Builder setCode(int code) {
            this.retCode=code;
            return this;
        }
        public Builder setMessage(String message) {
            this.message=message;
            return this;
        }

        /**
         * 设置result obj
         *
         * @param obj
         * @return
         */
        public Builder setObj(final Object obj) {
            this.obj = obj;
            return this;
        }
        /**
         * build BaseResponse
         *
         * @return
         */
        public BaseResponse build() {
            return new BaseResponse(this.obj == null ? NULL_OBJ : this.obj,this.retCode, this.message, this.params);
        }
    }
}
