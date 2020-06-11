package org.zy.rpc.interfaces;


import org.zy.rpc.Configure;
import org.zy.rpc.impl.RpcServerConfigure;

public interface Server<S, C extends Configure> {
    S init(C config);
    void start();
    void stop();
}
