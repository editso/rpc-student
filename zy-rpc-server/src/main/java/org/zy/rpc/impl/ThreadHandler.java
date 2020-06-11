package org.zy.rpc.impl;

import org.apache.log4j.Logger;
import org.zy.rpc.Request;
import org.zy.rpc.Response;
import org.zy.rpc.interfaces.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ThreadHandler implements ItemHandler {
    private Logger logger = Logger.getLogger(ThreadHandler.class);
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    @Override
    public void handler(Socket socket,
                        Decoder decoder,
                        Encoder encoder,
                        ServiceContainer<Service> serviceContainer) throws IOException {
        this.socket = socket;
        logger.info(socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        input = socket.getInputStream();
        output = socket.getOutputStream();
        Response response = new Response();
        Request request = decoder.decoder(readAll(), Request.class);
        Service service;
        if (request == null ||
                (service = serviceContainer.lookup(request.getDescription())) == null){
            response.setCode(-1);
            response.setMessage("找不到服务");
        }else{
            response.setMessage("成功");
            response.setData(service.invoke(request.getParams()));
        }
        write(encoder.encoder(response));
        close();
    }

    public byte[] readAll(){
        try {
            byte[] bytes = new byte[4];
            byte[] allBytes = null;
            while (input.read(bytes) != -1){
                allBytes =  merge(bytes, input.readAllBytes());
            }
            socket.shutdownInput();
            return allBytes == null?new byte[0]:allBytes;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new byte[0];
        }
    }

    public byte[] merge(byte[] ...bytes){
        int size = 0;
        for (byte[] aByte : bytes) {
            size += aByte.length;
        }
        byte[] array = new byte[size];
        int cur = 0;
        for (byte[] aByte : bytes) {
            for (byte b : aByte) {
                array[cur++] = b;
            }
        }
        return array;
    }

    public void write(byte[] bytes){
        try {
            output.write(bytes);
            output.flush();
            socket.shutdownOutput();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
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
}
