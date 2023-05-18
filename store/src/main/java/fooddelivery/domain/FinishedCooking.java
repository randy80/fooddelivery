package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class FinishedCooking extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private String userId;
    private String userName;
    private String userAddress;
    private String storeOrderStatus;

    public FinishedCooking(StoreOrder aggregate) {
        super(aggregate);
    }

    public FinishedCooking() {
        super();
    }
}
