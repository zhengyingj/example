# example
kafka测试程序：

kafka mac os 安装配置：
```
brew install kafka
```
配置文件位置
```
/usr/local/etc/kafka/server.properties
/usr/local/etc/kafka/zookeeper.properties
```
启动zookeeper和kafka
```
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties
```
配置：
```
kafka.consumer.zookper.connect=127.0.0.1:2181
kafka.consumer.servers=127.0.0.1:9092
kafka.consumer.enable.auto.commit=true
kafka.consumer.session.timeout=6000
kafka.consumer.auto.commit.interval=100
kafka.consumer.auto.offset.reset=latest
kafka.consumer.topic=zhengyj-kafka
kafka.consumer.group.id=zhengyj
kafka.consumer.concurrency=10
kafka.producer.servers=127.0.0.1:9092
kafka.producer.retries=0
kafka.producer.batch.size=4096
kafka.producer.linger=1
kafka.producer.buffer.memory=40960

kafka.topic.default=zhengyj-kafka
```

