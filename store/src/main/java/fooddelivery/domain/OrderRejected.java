package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderRejected extends AbstractEvent {

    private Long id;
    private String storeOrderStatus;

    public OrderRejected(StoreOrder aggregate) {
        super(aggregate);
    }

    public OrderRejected() {
        super();
    }
}
