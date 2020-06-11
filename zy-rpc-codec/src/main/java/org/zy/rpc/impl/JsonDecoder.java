package org.zy.rpc.impl;

import com.alibaba.fastjson.JSON;
import org.zy.rpc.interfaces.Decoder;

public class JsonDecoder implements Decoder {
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
