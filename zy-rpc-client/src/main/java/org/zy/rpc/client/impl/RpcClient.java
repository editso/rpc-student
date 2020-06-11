package org.zy.rpc.client.impl;

import org.apache.log4j.Logger;
import org.zy.rpc.Configure;
import org.zy.rpc.Peer;
import org.zy.rpc.client.interfaces.*;
import org.zy.rpc.interfaces.Decoder;
import org.zy.rpc.interfaces.Encoder;
import org.zy.rpc.interfaces.ReturnFunction;
import org.zy.rpc.util.ReflectionUtil;

import java.lang.reflect.Proxy;


public class RpcClient implements Client<RpcConfigure> {
    private Logger logger = Logger.getLogger(RpcClient.class);
    private RpcConfigure configure;
    private Encoder encoder;
    private Decoder decoder;
    private RemoteServer remoteServer;


    @Override
    public RpcClient init(RpcConfigure configure) {
        logger.info("初始化配置文件");
        this.configure = configure;
        encoder = ReflectionUtil.newInstance(configure.encoderClass());
        decoder = ReflectionUtil.newInstance(configure.decoderClass());
        remoteServer = ReflectionUtil.newInstance(configure.remoteServerClass());
        configure.remoteServer(remoteServer);

        return this;
    }

    @Override
    public <T> T proxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoke(clazz, encoder, decoder, remoteServer));
    }


}
