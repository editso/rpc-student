package org.zy.rpc.client.interfaces;

import org.zy.rpc.Configure;


public interface Client<C extends Configure>{
    Client init(C configure);
    <T>T proxy(Class<T> clazz);
}
