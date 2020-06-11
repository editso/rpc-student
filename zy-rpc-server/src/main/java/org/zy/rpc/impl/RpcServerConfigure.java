package org.zy.rpc.impl;

import org.zy.rpc.Configure;
import org.zy.rpc.Peer;
import org.zy.rpc.interfaces.*;

import java.net.Socket;


public class RpcServerConfigure implements Configure {

    public void register(ServiceContainer<Service> container){

    }

    public void onStart(){

    }

    public void onStop(){

    }

    public void onAccept(Socket socket){

    }

    public Class<? extends Decoder> decoderClass() {
        return JsonDecoder.class;
    }

    public Class<? extends Encoder> encoderClass(){
        return JsonEnCoder.class;
    }

    public Class<? extends ItemHandler> handlerClass(){
        return ThreadHandler.class;
    }

    public Class<? extends ServiceContainer<Service>> containerClass(){
        return RpcServiceContainer.class;
    }


    @Override
    public Peer peer() {
        return new Peer("0.0.0.0", 8000);
    }
}
