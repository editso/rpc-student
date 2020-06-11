package org.zy.rpc.example;

import org.zy.rpc.example.service.Math;
import org.zy.rpc.example.service.MathImpl;
import org.zy.rpc.factory.Builder;
import org.zy.rpc.impl.RpcServer;
import org.zy.rpc.impl.RpcServerConfigure;
import org.zy.rpc.interfaces.Service;
import org.zy.rpc.interfaces.ServiceContainer;

public class Server {
    public static void main(String[] args) {
     new RpcServer().init(new RpcServerConfigure(){
         @Override
         public void register(ServiceContainer<Service> container) {
            container.register(Builder.builderService(Math.class, new MathImpl()));
         }
     }).start();

    }
}
