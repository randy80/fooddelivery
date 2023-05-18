package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryPrepared extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long storeOrderId;
    private Long productId;
    private String productName;
    private String deliveryStatus;
    private String userId;
    private String userName;
    private String userAddress;

    public DeliveryPrepared(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryPrepared() {
        super();
    }
}
