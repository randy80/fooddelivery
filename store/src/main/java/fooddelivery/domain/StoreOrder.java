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

    }

    public static StoreOrderRepository repository() {
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(
            StoreOrderRepository.class
        );
        return storeOrderRepository;
    }

    public void orderAccept() {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.setStoreOrderStatus("OrderAccepted");
        orderAccepted.publishAfterCommit();
    }

    public void orderReject() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.setStoreOrderStatus("OrderRejected");
        orderRejected.publishAfterCommit();
    }

    public void startCooking() {
        StartedCooking startedCooking = new StartedCooking(this);
        startedCooking.setStoreOrderStatus("StartedCooking");
        startedCooking.publishAfterCommit();
    }

    public void finishCooking() {
        FinishedCooking finishedCooking = new FinishedCooking(this);
        finishedCooking.setStoreOrderStatus("FinishedCooking");
        finishedCooking.publishAfterCommit();
    }

    public static void orderInformationReceive(Paid paid) {
        /** Example 1:  new item */
        StoreOrder storeOrder = new StoreOrder();
        storeOrder.setOrderId(paid.getId());
        storeOrder.setUserId(paid.getUserId());
        storeOrder.setUserName(paid.getUserName());
        storeOrder.setUserAddress(paid.getUserAddress());
        storeOrder.setProductId(paid.getProductId());
        storeOrder.setProductName(paid.getProductName());
        storeOrder.setQty(paid.getQty());
        storeOrder.setStoreOrderStatus("StoreOrderPlaced");
        repository().save(storeOrder);

        OrderInformationReceived orderInformationReceived = new OrderInformationReceived(storeOrder);
        orderInformationReceived.publishAfterCommit();
    }

    public static void storeOrderCancel(PaymentCancelled paymentCancelled) {
        /** Example 2:  finding and process */
        
        repository().findById(paymentCancelled.getId()).ifPresent(storeOrder->{
            
            storeOrder.setStoreOrderStatus("StoreOrderCancelled");
            repository().save(storeOrder);

            StoreOrderCancelled storeOrderCancelled = new StoreOrderCancelled(storeOrder);
            storeOrderCancelled.publishAfterCommit();

         });

    }
}
