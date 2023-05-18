package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderInformationReceived extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer qty;
    private String storeOrderStatus;
    private String userId;
    private String userName;
    private String userAddress;

    public OrderInformationReceived(StoreOrder aggregate) {
        super(aggregate);
    }

    public OrderInformationReceived() {
        super();
    }
}
