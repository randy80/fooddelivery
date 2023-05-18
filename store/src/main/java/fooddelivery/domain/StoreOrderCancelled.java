package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class StoreOrderCancelled extends AbstractEvent {

    private Long id;
    private String storeOrderStatus;

    public StoreOrderCancelled(StoreOrder aggregate) {
        super(aggregate);
    }

    public StoreOrderCancelled() {
        super();
    }
}
