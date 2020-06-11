package org.zy.rpc.example;

import org.zy.rpc.Peer;
import org.zy.rpc.client.impl.RpcClient;
import org.zy.rpc.client.impl.RpcConfigure;
import org.zy.rpc.client.interfaces.RemoteServer;

public class Client {
    public static void main(String[] args){
        RpcClient client =  new RpcClient().init(new RpcConfigure(){
            @Override
            public void remoteServer(RemoteServer remoteServer) {
                remoteServer.register(new Peer("0.0.0.0", 8000));
            }
        });


    }
}
