# message-process-and-patch-processing
<img src="https://github.com/TcPaulyue/message-process-and-patch-processing/blob/master/%E6%88%AA%E5%B1%8F2020-03-21%E4%B8%8A%E5%8D%8810.30.46.png" alt="截屏2020-03-21上午10.30.46" style="zoom:50%;" />

### Message processing

+ 访问地址:http://localhost:8081
+ 用户发送消息和接收消息都对应简单的mongodb内容修改
+ 利用`quartz`进行简单的系统消息发送和定时消息发送
+ 利用`spring reactive`和mongodb的reactive特性提供批处理访问接口

### Batch processing

+ 访问地址:http://localhost:8082
+ 采用`webClient`从**message processing**服务提供的`webflux`接口中异步获取数据并进行处理。

### Admin Server

+ 访问地址:http://localhost:8080

+ 利用`spring boot admin`对上述服务进行监控


