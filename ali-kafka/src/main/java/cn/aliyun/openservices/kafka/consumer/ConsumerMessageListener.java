package cn.ykc.echarge.base.aliyun.openservices.kafka.consumer;

import cn.ykc.echarge.base.common.utils.MtcpContext;
import cn.ykc.echarge.base.rediscache.service.OperatorInfoService;
import cn.ykc.echarge.dev.platauth.domain.StationStatusReq;
import cn.ykc.echarge.dev.postoperator.service.StationInfoService;
import cn.ykc.echarge.dev.postoperator.service.StationStatusInfoService;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import java.util.List;

public class ConsumerMessageListener implements MessageListener<String, String> {
    private static final Logger log = LoggerFactory.getLogger(ConsumerMessageListener.class);

    @Autowired
    private OperatorInfoService operatorInfoService;
    @Autowired
    private StationInfoService stationInfoService;
    @Autowired
    private StationStatusInfoService stationStatusInfoService;

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {
        StationStatusReq stationStatusReq = JSON.parseObject(consumerRecord.value(), StationStatusReq.class);
        String connectorID = stationStatusReq.getConnectorStatusInfo().getConnectorID();
        log.info("当前推送桩状态：key-{},value-{}", consumerRecord.key(), consumerRecord.value());
        List<String> stationExtOperators = stationStatusInfoService.queryStationExtWithCId(connectorID);
        List<String> pileExtOperators = stationStatusInfoService.queryPileExtWithCId(connectorID);
        List<String> oids = operatorInfoService.getOIDs();

        for (String oid : oids) {
            MtcpContext.setOperatorId(oid);

            try {
                if (stationExtOperators.contains(oid) && pileExtOperators.contains(oid)) {
                    log.info("向运营商：{}推送桩状态：key-{},value-{}", oid, consumerRecord.key(), consumerRecord.value());
                    stationStatusInfoService.notificationStationStatus(stationStatusReq);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        stationInfoService.updateStation(connectorID);
    }

}
