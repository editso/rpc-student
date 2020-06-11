package org.zy.rpc;


import java.util.Objects;

public class ServerTransPort {
    private final Class<?> clazz;
    private final String methodName;
    private final Object returnType;

    public ServerTransPort(Class<?> clazz,
                           String methodName,
                           Object returnType) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.returnType = returnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)return true;
        ServerTransPort transPort = (ServerTransPort)o;
        return transPort.getClazz().equals(getClazz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClazz(), getMethodName(), getReturnType());
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        return "ServerTransPort{" +
                "clazz=" + clazz +
                ", methodName='" + methodName + '\'' +
                ", returnType=" + returnType +
                '}';
    }


}
