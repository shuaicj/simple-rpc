package shuaicj.example.simplerpc.server;

import shuaicj.example.simplerpc.common.HelloService;

/**
 * An implementation of HelloService.
 *
 * @author shuaicj 2017/08/04
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String greet(String name, String country) {
        return "Hello " + name + "@" + country + "!!!";
    }
}
