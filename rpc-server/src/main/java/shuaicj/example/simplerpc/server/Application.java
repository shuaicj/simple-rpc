package shuaicj.example.simplerpc.server;

import shuaicj.example.simplerpc.common.HelloService;

/**
 * The application entry.
 *
 * @author shuaicj 2017/08/04
 */
public class Application {

    public static void main(String[] args) throws RpcException {
        RpcServer server = new RpcServerImpl(8080, 10);
        server.export(HelloService.class, HelloServiceImpl.class);
        server.start();
    }
}
