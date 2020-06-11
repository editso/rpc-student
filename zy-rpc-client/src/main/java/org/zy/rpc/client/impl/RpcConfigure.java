package org.zy.rpc.client.impl;

import org.zy.rpc.Configure;
import org.zy.rpc.Peer;
import org.zy.rpc.client.interfaces.RemoteServer;
import org.zy.rpc.impl.JsonDecoder;
import org.zy.rpc.impl.JsonEnCoder;
import org.zy.rpc.interfaces.Decoder;
import org.zy.rpc.interfaces.Encoder;

public class RpcConfigure implements Configure {

    @Override
    public Peer peer() {
        return null;
    }

    public Class<? extends Decoder> decoderClass() {
        return JsonDecoder.class;
    }

    public Class<? extends Encoder> encoderClass(){
        return JsonEnCoder.class;
    }

    public Class<? extends RemoteServer> remoteServerClass(){
        return RandomRemoteServer.class;
    }

    public void remoteServer(RemoteServer remoteServer){

    }

}
