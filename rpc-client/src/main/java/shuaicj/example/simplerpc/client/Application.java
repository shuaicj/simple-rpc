package shuaicj.example.simplerpc.client;

import shuaicj.example.simplerpc.common.HelloService;

/**
 * The application entry.
 *
 * @author shuaicj 2017/08/05
 */
public class Application {

    public static void main(String[] args) {
        RpcClient client = new RpcClientImpl();
        HelloService service = client.proxy(HelloService.class, "localhost", 8080);
        String greeting = service.greet("shuaicj", "china");
        System.out.println("RpcServer returns: " + greeting);
    }
}
