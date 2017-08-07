package shuaicj.example.simplerpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * The implementation of RpcClient.
 *
 * @author shuaicj 2017/08/05
 */
public class RpcClientImpl implements RpcClient {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T proxy(Class<T> serviceInterface, String host, int port) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[]{serviceInterface}, (proxy, method, params) -> {
            try (Socket socket = new Socket(host, port);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                out.writeUTF(serviceInterface.getName());
                out.writeUTF(method.getName());
                out.writeObject(method.getParameterTypes());
                out.writeObject(params);

                return in.readObject();
            }
        });
    }
}
