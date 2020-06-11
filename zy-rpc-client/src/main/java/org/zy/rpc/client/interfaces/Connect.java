package org.zy.rpc.client.interfaces;

import org.zy.rpc.Peer;
import org.zy.rpc.interfaces.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Connect {

    void peer(Peer peer);

    Peer peer();

    Connect connect();

    int localPort();

    boolean isConnect();

    boolean write(byte[] bytes);

    <T>T get(Class<T> clazz, Decoder decoder);

    InputStream getInputStream() throws IOException;

    OutputStream getOutStream() throws IOException;

    void close() throws IOException;

}
