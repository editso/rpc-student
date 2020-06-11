package org.zy.rpc.impl;

import org.apache.log4j.Logger;
import org.zy.rpc.ServerTransPort;
import org.zy.rpc.ServiceDescription;
import org.zy.rpc.factory.Builder;
import org.zy.rpc.interfaces.Service;
import org.zy.rpc.interfaces.ServiceContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class RpcServiceContainer implements ServiceContainer<Service>{
    private Logger logger = Logger.getLogger(RpcServiceContainer.class);
    private final Map<ServiceDescription, Service> services;

    public RpcServiceContainer(){
        services = new HashMap<>();
    }

    @Override
    public ServiceContainer<Service> register(Service ...service) {
        for (Service s : service) {
            services.put(s.description(), s);
            logger.info(s);
        }
        return this;
    }

    @Override
    public Service lookup(ServiceDescription description) {
        logger.info(description);
        return services.get(description);
    }

    @Override
    public String toString() {
        return "RpcServiceContainer{" +
                "\nservices=" + Arrays.toString(services.keySet().stream().map((Function<ServiceDescription, Object>) transPort -> "\n\t"+transPort.toString()+"\n").toArray()).replaceAll("\n,", "") + "\n}";
    }
}
