package org.zy.rpc;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 服务描述
 */
public class ServiceDescription {
    private String className;
    private String method;
    private String returnType;
    private Integer paramsLength;

    public ServiceDescription() {
    }

    public ServiceDescription(String className, String method, String returnType, Integer paramsLength) {
        this.className = className;
        this.method = method;
        this.returnType = returnType;
        this.paramsLength = paramsLength;
    }

    public static ServiceDescription from(Class<?> clazz, Method method){
        return new ServiceDescription(
                clazz.getName(),
                method.getName(),
                method.getReturnType().getName(),
                method.getParameterCount());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Integer getParamsLength() {
        return paramsLength;
    }

    public void setParamsLength(Integer paramsLength) {
        this.paramsLength = paramsLength;
    }


    @Override
    public String toString() {
        return "ServiceDescription{" +
                "className='" + className + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", paramsLength=" + paramsLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescription that = (ServiceDescription) o;
        return Objects.equals(getClassName(), that.getClassName()) &&
                Objects.equals(getMethod(), that.getMethod()) &&
                Objects.equals(getReturnType(), that.getReturnType()) &&
                Objects.equals(getParamsLength(), that.getParamsLength());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClassName(), getMethod(), getReturnType(), getParamsLength());
    }
}
