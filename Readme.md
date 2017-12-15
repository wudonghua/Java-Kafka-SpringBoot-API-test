java-kafka-SpringBoot-API


该工程是为java-kafka-API的测试用例
测试三个方面,发送消息,接收消息,以及接收消息时bean的注入.把刚才的项目打包到本地仓库
# 依赖
```xml
 <dependency>
     <groupId>Riven.kafka</groupId>
     <artifactId>Java-SpringBoot-Kafka-API</artifactId>
     <version>1.1.0-SNAPSHOT</version>
 </dependency>
```
# 发送消息:

# 配置yml文件
```java
Riven:
  kafka:
   producer:
    bootstrapServers: 服务器列表 #必填
    retries: 99
    acks: 接受策略
    batchSize: 99
    lingerMs: 99
    bufferMemory: 99
    keySerializer: org.apache.kafka.common.serialization.StringSerializer
    valueSerializer: org.apache.kafka.common.serialization.StringSerializer
```


# Usage
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author dw07-Riven770[wudonghua@gznb.com]
 * @Date 2017/12/1417:37
 */
@Component
@EnableScheduling
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(fixedDelay = 6000)
    private void sendMsg(){
        kafkaTemplate.send("topic-test", "hello"+ LocalDateTime.now().toString());
    }
}
    
```







# 接收消息及Bean注入
## yml 配置
```xml
Riven:
  kafka:
    consumer:
    bootstrapServers: 服务器列表 #必填
    enableAutoCommit: true
    autoCommitIntervalMs: 99
    sessionTimeoutMs: 99
    fetchMinBytes: 99
    maxPollRecords: 99
    groupId: java #必填
    autoOffseReset: latest
    keySerializer: org.apache.kafka.common.serialization.StringDeserializer
    valueSerializer: org.apache.kafka.common.serialization.StringDeserializer
    consumerAmount: 99
    PollTimeout: 99
    topics[0]: test_group #必填
```
## User
```java
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import riven.kafka.api.listener.IKafkaListener;

import java.util.Optional;

/**
 * @Author dw07-Riven770[wudonghua@gznb.com]
 * @Date 2017/12/1417:37
 */
@Component
public class Consumer implements IKafkaListener{

    @Autowired
    private TestBean testBean;

    @Override
    public void listener(ConsumerRecord<?, ?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        String s = (String) value.get();
        System.out.println("消费者"+s);
        testBean.test();
    }
}
```
# 建议和完善
问题、BUG可以在issue中提问，feature可以pull request。

