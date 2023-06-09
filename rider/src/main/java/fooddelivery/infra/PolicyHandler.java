package fooddelivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FinishedCooking'"
    )
    public void wheneverFinishedCooking_DeliveryPrepare(
        @Payload FinishedCooking finishedCooking
    ) {
        FinishedCooking event = finishedCooking;
        System.out.println(
            "\n\n##### listener DeliveryPrepare : " + finishedCooking + "\n\n"
        );

        // Sample Logic //
        Delivery.deliveryPrepare(event);
    }
}
