package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private String userName;
    private String userAddress;
    private Long productId;
    private String productName;
    private Integer qty;
    private String orderStatus;
    private Integer orderAmt;
    private Integer paymentAmt;
    private Integer refundAmt;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
