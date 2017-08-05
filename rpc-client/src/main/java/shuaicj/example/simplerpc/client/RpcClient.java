package shuaicj.example.simplerpc.client;

/**
 * The interface for rpc client.
 *
 * @author shuaicj 2017/08/05
 */
public interface RpcClient {

    <T> T proxy(Class<T> serviceInterface, String host, int port);
}
