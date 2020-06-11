package org.zy.rpc.interfaces;

import org.zy.rpc.Request;
import org.zy.rpc.ServerTransPort;
import org.zy.rpc.ServiceDescription;

/**
 * 服务容器
 */
public interface ServiceContainer<S> {

    /**
     * 注册服务
     */
    ServiceContainer<S> register(S ...service);


    /**
     * 查找服务
     */
    S lookup(ServiceDescription description);

}
