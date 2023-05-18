package fooddelivery.domain;

import fooddelivery.FrontApplication;
import fooddelivery.domain.OrderPlaced;
import fooddelivery.domain.PaymentCancelled;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.setOrderStatus("OrderPlaced");
        orderPlaced.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
//        PaymentCancelled paymentCancelled = new PaymentCancelled(this);
//        paymentCancelled.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = FrontApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void payments() {
        Paid paid = new Paid(this);
        paid.publishAfterCommit();
    }

    public void orderCancel() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public static void paymentCancel(OrderCancelled orderCancelled) {
        /** Example 2:  finding and process */
        
        repository().findById(orderCancelled.getId()).ifPresent(order->{
            
            order.setOrderStatus("OrderCancelled");
            repository().save(order);

            PaymentCancelled paymentCancelled = new PaymentCancelled(order);
            paymentCancelled.publishAfterCommit();

         });
    }
}
