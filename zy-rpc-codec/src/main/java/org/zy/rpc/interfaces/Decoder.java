package org.zy.rpc.interfaces;

public interface Decoder {
    <T>T decoder(byte[] bytes, Class<T> clazz);
}
