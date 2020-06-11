package org.zy.rpc.client.interfaces;

import org.zy.rpc.Peer;


public interface RemoteServer {
    int count();
    Connect select();
    RemoteServer recycle(Connect connect);
    RemoteServer register(Peer connect);
}
