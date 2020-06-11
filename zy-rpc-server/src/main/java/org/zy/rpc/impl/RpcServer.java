package org.zy.rpc.impl;

import org.apache.log4j.Logger;
import org.zy.rpc.Peer;
import org.zy.rpc.Request;
import org.zy.rpc.Response;
import org.zy.rpc.ServerTransPort;
import org.zy.rpc.interfaces.*;
import org.zy.rpc.util.ReflectionUtil;


import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class RpcServer implements Server<RpcServer, RpcServerConfigure> {
    private Logger logger = Logger.getLogger(RpcServer.class);
    private RpcServerConfigure config;
    private ServerSocket serverSocket;
    private Encoder encoder;
    private Decoder decoder;
    private Map<ServerTransPort, Request> register;
    private ServiceContainer<Service> container;
    private Peer peer;
    private Class<? extends ItemHandler> handlerClass;


    @Override
    public RpcServer init(RpcServerConfigure config) {
        logger.info("初始化配置文件");
        this.config = config;
        encoder = ReflectionUtil.newInstance(config.encoderClass());
        decoder = ReflectionUtil.newInstance(config.decoderClass());
        container = ReflectionUtil.newInstance(config.containerClass());
        handlerClass = config.handlerClass();
        peer = config.peer();
        config.register(container);
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(peer.getHost(), peer.getPort()));
            serverSocket.setReuseAddress(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public void start() {
        config.onStart();
        while (true){
            try {
                Socket socket = serverSocket.accept();
                config.onAccept(socket);
                new Thread(()->{
                    ItemHandler handler = ReflectionUtil.newInstance(handlerClass);
                    try {
                        handler.handler(
                                socket,
                                decoder,
                                encoder,
                                container);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                }).start();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }finally {
            config.onStop();
        }
    }




}
