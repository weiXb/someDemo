package cn.ykc.echarge.dev.test.controller;

import cn.ykc.echarge.dev.platauth.domain.StationStatusReq;
import cn.ykc.echarge.dev.platauth.pojo.ConnectorStatusInfo;
import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducerDemo {
    public static void main(String args[]) {
        ConnectorStatusInfo connectorStatusInfo = new ConnectorStatusInfo();
        connectorStatusInfo.setConnectorID("1201020000003907");

        StationStatusReq stationStatusReq = new StationStatusReq();
        stationStatusReq.setConnectorStatusInfo(connectorStatusInfo);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-core.xml");
        KafkaTemplate kafkaTemplate = context.getBean("kafkaTemplate", KafkaTemplate.class);
        kafkaTemplate.sendDefault(String.valueOf(System.currentTimeMillis()), JSON.toJSONString(stationStatusReq));

    }

}
