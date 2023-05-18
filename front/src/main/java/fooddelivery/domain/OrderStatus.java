package fooddelivery.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderStatus_table")
@Data
public class OrderStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long orderId;
    private String productName;
    private Integer qty;
    private String orderStatus;
    private String storeOrderStatus;
    private String deliveryStatus;
    private String userName;
    private String userAddress;
    private Integer orderAmt;
}
