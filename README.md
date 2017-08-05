# Simple RPC

A simple RPC implementation in Java.

#### Modules
- **rpc-common** - definition of common `HelloService`
- **rpc-server** - the rpc server which provides an implementation of `HelloService`
- **rpc-client** - the rpc client which calls the rpc server

#### How to run
- `$ mvn package`
- `$ java -jar rpc-server/target/rpc-server-1.0.0.jar`
- `$ java -jar rpc-client/target/rpc-client-1.0.0.jar`
