package shuaicj.example.simplerpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The implementation of RpcServer.
 *
 * @author shuaicj 2017/08/04
 */
public class RpcServerImpl implements RpcServer {

    private final int port;
    private final ExecutorService pool;
    private final Map<String, Class> services;

    public RpcServerImpl(int port, int parallel) {
        this.port = port;
        pool = Executors.newFixedThreadPool(parallel);
        services = new HashMap<>();
    }

    @Override
    @SuppressWarnings({"unchecked", "InfiniteLoopStatement"})
    public void start() throws RpcException {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = server.accept()) {
                    pool.submit(() -> {
                        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                            String serviceName = in.readUTF();
                            String methodName = in.readUTF();
                            Class[] paramTypes = (Class[]) in.readObject();
                            Object[] params = (Object[]) in.readObject();

                            Class serviceClass = services.get(serviceName);
                            Method method = serviceClass.getMethod(methodName, paramTypes);
                            Object result = method.invoke(serviceClass.newInstance(), params);

                            out.writeObject(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        } catch (IOException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public <T> void export(Class<T> serviceInterface, Class<? extends T> serviceImpl) {
        services.put(serviceInterface.getName(), serviceImpl.getClass());
    }
}
