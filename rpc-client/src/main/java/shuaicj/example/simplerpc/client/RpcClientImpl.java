package shuaicj.example.simplerpc.client;

import java.io.IOException;
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
            Socket socket = null;
            ObjectInputStream in = null;
            ObjectOutputStream out = null;
            try {
                socket = new Socket(host, port);
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeUTF(serviceInterface.getName());
                out.writeUTF(method.getName());
                out.writeObject(method.getParameterTypes());
                out.writeObject(params);

                in = new ObjectInputStream(socket.getInputStream());
                return in.readObject();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
