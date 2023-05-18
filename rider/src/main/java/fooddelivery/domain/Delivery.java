package fooddelivery.domain;

import fooddelivery.RiderApplication;
import fooddelivery.domain.DeliveryPrepared;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long storeOrderId;

    private Long productId;

    private String productName;

    private String deliveryStatus;

    private String userId;

    private String userName;

    private String userAddress;

    @PostPersist
    public void onPostPersist() {
//        DeliveryPrepared deliveryPrepared = new DeliveryPrepared(this);
//        deliveryPrepared.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void deliveryComplete() {
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.setDeliveryStatus("DeliveryCompleted");
        deliveryCompleted.publishAfterCommit();
    }

    public void deliveryStart() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.setDeliveryStatus("DeliveryStarted");
        deliveryStarted.publishAfterCommit();
    }

    public static void deliveryPrepare(FinishedCooking finishedCooking) {

        /** Example 2:  finding and process */

        repository().findById(finishedCooking.getId()).ifPresent(delivery->{

            delivery.orderId = finishedCooking.getOrderId();
            delivery.productId = finishedCooking.getProductId();
            delivery.productName = finishedCooking.getProductName();
            delivery.deliveryStatus = "DeliveryPrepared";
            delivery.userId = finishedCooking.getUserId();
            delivery.userName = finishedCooking.getUserName();
            delivery.userAddress = finishedCooking.getUserAddress();
            repository().save(delivery);

            DeliveryPrepared deliveryPrepared = new DeliveryPrepared(delivery);
            deliveryPrepared.publishAfterCommit();

         });

    }
}
