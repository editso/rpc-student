package org.zy.rpc.impl;

import com.alibaba.fastjson.JSON;
import org.zy.rpc.interfaces.Encoder;

public class JsonEnCoder implements Encoder {

    @Override
    public byte[] encoder(Object o) {
        return JSON.toJSONBytes(o);
    }
}
