## 自实现简单RPC框架模板

通过该框架，可以实现跨应用间交互，通过抽象接口，预期实现以下扩展点：

- 允许自定义启动方式，如netty、rsocket、websocket等，只需通过基础启动类配置即可；
- 允许自定义加解码方式，例如jdk、kryo、protobuf、等加解码器；

### 1.功能预览
#### 1.1 SPI加载编解码实现方案
#### 1.2 简单命令的同步/异步传输

### 2.设计方案

- `LoadingStrategy`作为`SPI`加载入口，决定扫描路径，可以重新`CodingLoadStrategy`进行覆盖；
- `AbstractProtocol`加解码抽象类，如需自实现编码方式，必须实现该类的`encode`和`decode`方法；
- `SpiInstance`实现工厂，从此类中获取代理接口，将接口重定向实现放到此类中；
- `AbstractBootstrapServer`服务端启动基础类；

### 3.包结构

- `remote-common` 基础公共功能以及抽象实现
- `remote-core` 核心能力
- `remote-netty` 服务端启动方式
- `remote-protobuf` 编解码实现
- `remote-test` 测试类