package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class StartedCooking extends AbstractEvent {

    private Long id;
    private String storeOrderStatus;

    public StartedCooking(StoreOrder aggregate) {
        super(aggregate);
    }

    public StartedCooking() {
        super();
    }
}
