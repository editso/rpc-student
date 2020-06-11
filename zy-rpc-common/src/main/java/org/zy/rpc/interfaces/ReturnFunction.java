package org.zy.rpc.interfaces;

public interface ReturnFunction<R, P> {
    public R invoke(P params);
}
