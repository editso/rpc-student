package org.zy.rpc.factory;


import org.zy.rpc.ServiceDescription;
import org.zy.rpc.interfaces.Service;
import org.zy.rpc.util.ReflectionUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;


public class Builder {

    public static<T> Service[] builderService(Class<T> interfaces, T impl){
        ArrayList<Service> services = new ArrayList<>();
        for (Method publicMethod : ReflectionUtil.getPublicMethods(interfaces)) {
            services.add(new ServiceImpl(interfaces, impl, publicMethod));
        }
        return services.toArray(Service[]::new);
    }

    public static class ServiceImpl implements Service{
        private Class<?> clazz;
        private Object impl;
        private Method method;

        public ServiceImpl(Class<?> clazz, Object impl, Method method) {
            this.clazz = clazz;
            this.impl = impl;
            this.method = method;
        }

        public Object getImpl() {
            return impl;
        }

        public void setImpl(Object impl) {
            this.impl = impl;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        @Override
        public Object invoke(Object ...args) {
            return ReflectionUtil.invoke(impl, method, args);
        }

        @Override
        public ServiceDescription description() {
            return ServiceDescription.from(
                    clazz,
                    method);
        }

        @Override
        public String toString() {
            return "ServiceImpl{" +
                    "o=" + clazz.getName() +
                    ", method=" + method.getName() +
                    ", params=" + Arrays.toString(method.getParameterTypes())+
                    '}';
        }


    }

}
