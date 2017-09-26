package com.mike.util;

/**
 * 
 * 类名称：Response<br>
 * 类描述：Response 类<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年10月26日 下午12:07:05<br>
 * @version v1.0
 *
 */
public class Response {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    private Object data;

    public Response success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public Response success(String message) {
        this.meta = new Meta(true, message);
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public Response success(Object data, String message) {
        this.meta = new Meta(true, message);
        this.data = data;
        return this;
    }

    public Response failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public Response failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }
    
    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
