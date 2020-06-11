package org.zy.rpc.interfaces;

import org.zy.rpc.ServerTransPort;
import org.zy.rpc.ServiceDescription;

public interface Service {

    Object invoke(Object ...args);

    ServiceDescription description();


}
