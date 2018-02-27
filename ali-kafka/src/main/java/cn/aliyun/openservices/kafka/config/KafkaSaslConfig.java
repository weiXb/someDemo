package cn.ykc.echarge.base.aliyun.openservices.kafka.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

/**
 * 阿里kafka消息队列配置
 */
public class KafkaSaslConfig {
    @Value("${consumerID}")
    private String consumerID;

    public KafkaSaslConfig() {
        if (System.getProperty("java.security.auth.login.config") == null) {
            System.setProperty("java.security.auth.login.config", KafkaSaslConfig.class.getClassLoader().getResource("kafka_client_jaas.conf").getPath());
        }
    }

    private HashMap<String, String> clientConfigMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("bootstrap.servers", "kafka-ons-internet.aliyun.com:8080");
        map.put("ssl.truststore.location", KafkaSaslConfig.class.getClassLoader().getResource("kafka.client.truststore.jks").getPath());
        map.put("ssl.truststore.password", "KafkaOnsClient");
        map.put("security.protocol", "SASL_SSL");
        map.put("sasl.mechanism", "ONS");
        return map;
    }

    public HashMap<String, String> producerProperties() {
        HashMap<String, String> map = new HashMap<>();
        map.putAll(clientConfigMap());

        map.put("retries", "3");
        map.put("batch.size", "1000");
        map.put("linger.ms", "1");
        map.put("buffer.memory", "3554432");
        map.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        map.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return map;
    }


    public HashMap<String, String> consumerProperties() {
        HashMap<String, String> map = new HashMap<>();
        map.putAll(clientConfigMap());

        map.put("group.id",consumerID);
        map.put("enable.auto.commit","true");
        map.put("auto.commit.interval.ms","1000");
        map.put("session.timeout.ms","15000");
        map.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        map.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return map;
    }

}
