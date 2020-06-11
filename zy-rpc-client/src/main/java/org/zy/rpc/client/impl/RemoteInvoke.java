package org.zy.rpc.client.impl;

import org.apache.log4j.Logger;
import org.zy.rpc.Request;
import org.zy.rpc.Response;
import org.zy.rpc.ServiceDescription;
import org.zy.rpc.client.interfaces.Connect;
import org.zy.rpc.client.interfaces.RemoteServer;
import org.zy.rpc.interfaces.Decoder;
import org.zy.rpc.interfaces.Encoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RemoteInvoke implements InvocationHandler {
    private Logger logger = Logger.getLogger(RemoteInvoke.class);
    private Class<?> clazz;
    private Encoder encoder;
    private Decoder decoder;
    private RemoteServer server;

    public RemoteInvoke(Class<?> clazz, Encoder encoder, Decoder decoder, RemoteServer server) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.server = server;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        Request request = new Request();
        request.setDescription(ServiceDescription.from(clazz, method));
        request.setParams(objects);
        Connect connect =  server.select();
        if (connect == null)return null;
        logger.debug(connect + ">" + method.getName()
                + Arrays.toString(objects).replaceAll("\\[(.*)\\]", "($1)"));
        connect.write(encoder.encoder(request));
        Response response = connect.get(Response.class, decoder);
        server.recycle(connect);
        if (response == null)
            return null;
        if (response.getCode() == -1){
            logger.info("Rpc调用失败: "+ response.getMessage());
        }
        return response.getData();
    }
}
