package org.zy.rpc.client.impl;

import org.apache.log4j.Logger;
import org.zy.rpc.Peer;
import org.zy.rpc.client.interfaces.Connect;
import org.zy.rpc.interfaces.Decoder;
import org.zy.rpc.interfaces.Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcConnect implements Connect {
    private final Logger logger = Logger.getLogger(RpcConnect.class);
    private Peer peer;
    private Socket socket;


    @Override
    public void peer(Peer peer) {
        this.peer = peer;
    }

    @Override
    public Peer peer() {
        return peer;
    }

    @Override
    public Connect connect() {
        try {
            if (socket != null){
                logger.warn("尝试重复连接");
                return this;
            }
            socket = new Socket();
            socket.connect(new InetSocketAddress(peer.getHost(), peer.getPort()));
        }catch (Exception e){
            logger.error(peer, e);
        }
        return this;
    }

    @Override
    public int localPort() {
        return socket.getLocalPort();
    }

    @Override
    public boolean isConnect() {
        return socket.isConnected();
    }

    @Override
    public boolean write(byte[] bytes) {
        if (socket.isClosed() || socket.isOutputShutdown())
            return false;
        try {
            OutputStream stream = getOutStream();
            stream.write(bytes);
            stream.flush();
            socket.shutdownOutput();
            return true;
        }catch (Exception e){
            logger.error(e);
        }
        return false;
    }

    @Override
    public <T> T get(Class<T> clazz, Decoder decoder) {
        if (socket.isClosed())
            return null;
        try {
            InputStream stream = getInputStream();
            return decoder.decoder(stream.readAllBytes(), clazz);
        }catch (Exception e){
            logger.error(peer, e);
        }
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    public OutputStream getOutStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public void close() throws IOException{
        if (socket.isClosed())
            return;
        if (!socket.isInputShutdown())
            socket.shutdownInput();
        if (!socket.isOutputShutdown())
            socket.shutdownOutput();
        socket.close();
        socket = null;
    }

    @Override
    public String toString() {
        return "RpcConnect{"+ socket + '}';
    }
}
