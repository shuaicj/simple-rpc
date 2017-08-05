package shuaicj.example.simplerpc.server;

import java.io.IOException;

import shuaicj.example.simplerpc.common.HelloService;

/**
 * The application entry.
 *
 * @author shuaicj 2017/08/04
 */
public class Application {

    public static void main(String[] args) throws IOException {
        RpcServer server = new RpcServerImpl(8080, 3);
        server.export(HelloService.class, HelloServiceImpl.class);
        server.start();
    }
}
