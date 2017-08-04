package shuaicj.example.simplerpc.server;

/**
 * A customized rpc exception .
 *
 * @author shuaicj 2017/08/04
 */
@SuppressWarnings("serial")
public class RpcException extends Exception {

    public RpcException() {
        super();
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(Throwable e) {
        super(e);
    }
}
