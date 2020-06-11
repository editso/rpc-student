package org.zy.rpc;

public class Request {
    private ServiceDescription description;
    private Object[] params;

    public Request(ServiceDescription description, Object[] params) {
        this.description = description;
        this.params = params;
    }

    public Request() {

    }

    public ServiceDescription getDescription() {
        return description;
    }

    public void setDescription(ServiceDescription description) {
        this.description = description;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
