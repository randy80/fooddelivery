package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.OrderInformationReceived;
import fooddelivery.domain.StoreOrderCancelled;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "StoreOrder_table")
@Data
public class StoreOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long productId;

    private String productName;

    private Integer qty;

    private String storeOrderStatus;

    private String userId;

    private String userName;

    private String userAddress;

    @PostPersist
    public void onPostPersist() {
        OrderInformationReceived orderInformationReceived = new OrderInformationReceived(
            this
        );
        orderInformationReceived.publishAfterCommit();

        StoreOrderCancelled storeOrderCancelled = new StoreOrderCancelled(this);
        storeOrderCancelled.publishAfterCommit();
    }

    public static StoreOrderRepository repository() {
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(
            StoreOrderRepository.class
        );
        return storeOrderRepository;
    }

    public void orderAccept() {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    public void orderReject() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    public void startCooking() {
        StartedCooking startedCooking = new StartedCooking(this);
        startedCooking.publishAfterCommit();
    }

    public void finishCooking() {
        FinishedCooking finishedCooking = new FinishedCooking(this);
        finishedCooking.publishAfterCommit();
    }

    public static void orderInformationReceive(Paid paid) {
        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        OrderInformationReceived orderInformationReceived = new OrderInformationReceived(storeOrder);
        orderInformationReceived.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);

            OrderInformationReceived orderInformationReceived = new OrderInformationReceived(storeOrder);
            orderInformationReceived.publishAfterCommit();

         });
        */

    }

    public static void storeOrderCancel(PaymentCancelled paymentCancelled) {
        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        StoreOrderCancelled storeOrderCancelled = new StoreOrderCancelled(storeOrder);
        storeOrderCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCancelled.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);

            StoreOrderCancelled storeOrderCancelled = new StoreOrderCancelled(storeOrder);
            storeOrderCancelled.publishAfterCommit();

         });
        */

    }
}
