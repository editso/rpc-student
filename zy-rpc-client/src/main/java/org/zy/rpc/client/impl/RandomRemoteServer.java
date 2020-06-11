package org.zy.rpc.client.impl;

import org.apache.log4j.Logger;
import org.zy.rpc.Peer;
import org.zy.rpc.client.interfaces.Connect;
import org.zy.rpc.client.interfaces.RemoteServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomRemoteServer implements RemoteServer {
    private final static Logger logger = Logger.getLogger(RandomRemoteServer.class);
    private List<Connect> list;

    public RandomRemoteServer(){
        list = new ArrayList<>();
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public Connect select() {
        if (list.size() <= 0)
            throw new RuntimeException("rpc服务调用失败,没有可用的远程服务器");
        Connect connect = list.remove(new Random().nextInt(list.size()));
        logger.debug(connect);
        return connect;
    }

    @Override
    public RemoteServer recycle(Connect connect) {
        if (connect.isConnect()){
            try {
                connect.close();
            }catch (Exception e){
//                logger.error(e.getMessage());
            }
        }
        connect.connect();
        list.add(connect);
        return this;
    }

    @Override
    public RemoteServer register(Peer connect) {
        logger.info("Remote[" + connect + "]");
        RpcConnect rpcConnect = new RpcConnect();
        rpcConnect.peer(connect);
        rpcConnect.connect();
        list.add(rpcConnect);
        return this;
    }


}
