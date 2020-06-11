package org.zy.rpc.interfaces;

import java.io.IOException;
import java.net.Socket;

public interface ItemHandler {


    void handler(Socket socket,
                 Decoder decoder,
                 Encoder encoder,
                 ServiceContainer<Service> serviceContainer) throws IOException;


    void close() throws IOException;

}
