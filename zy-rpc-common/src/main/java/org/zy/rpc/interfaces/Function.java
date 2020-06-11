package org.zy.rpc.interfaces;

public interface Function<P> {
    void invoke(P params);
}
