package shuaicj.example.simplerpc.server;

/**
 * The interface for rpc server.
 *
 * @author shuaicj 2017/08/04
 */
public interface RpcServer {

    void start() throws RpcException;

    <T> void export(Class<T> serviceInterface, Class<? extends T> serviceImpl);
}
