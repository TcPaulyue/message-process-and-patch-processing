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

### Monitoring with graphite and Grafana

+ 访问地址: http://localhost:3000

#### Configure Graphite as the data source

- Pull Graphite docker image

```
docker pull graphiteapp/graphite-statsd
```

- Start the Graphite

```
docker run -d --name graphite-server -p 80:80 -p 2003-2004:2003-2004 -p 2023-2024:2023-2024 -p 8125:8125/udp -p 8126:8126 graphiteapp/graphite-statsd
```

####Configure Grafana to display metrics

- Pull Grafana docker image

```
docker pull grafana/grafana:6.4.5
```

- Start Grafana

```
docker run -d --name graphana-server -p 3000:3000 grafana/grafana
```



### Reference

+ https://medium.com/@eranda/monitoring-springboot-with-graphite-and-grafana-part-i-71c8dc90b0ab

+ https://medium.com/@ankesh.kapil/real-time-event-streaming-using-spring-webflux-745e8885c8bd
+ spring webflux:https://spring.io/blog/2016/06/13/notes-on-reactive-programming-part-ii-writing-some-code
+ reactive Mongodb:https://www.baeldung.com/spring-data-mongodb-reactive
+ webclient: https://www.baeldung.com/spring-5-webclient
+ Reactor:https://projectreactor.io/docs/core/milestone/reference/#getting-started-introducing-reactor
+ demo: https://www.youtube.com/watch?v=1F10gr2pbvQ&t=2425s