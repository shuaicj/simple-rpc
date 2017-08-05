package shuaicj.example.simplerpc.server;

import java.io.IOException;

/**
 * The interface for rpc server.
 *
 * @author shuaicj 2017/08/04
 */
public interface RpcServer {

    void start() throws IOException;

    <T> void export(Class<T> serviceInterface, Class<? extends T> serviceImpl);
}
