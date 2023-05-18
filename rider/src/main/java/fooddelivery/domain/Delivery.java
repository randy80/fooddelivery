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
        DeliveryPrepared deliveryPrepared = new DeliveryPrepared(this);
        deliveryPrepared.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void deliveryComplete() {
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public void deliveryStart() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    public static void deliveryPrepare(FinishedCooking finishedCooking) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryPrepared deliveryPrepared = new DeliveryPrepared(delivery);
        deliveryPrepared.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(finishedCooking.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryPrepared deliveryPrepared = new DeliveryPrepared(delivery);
            deliveryPrepared.publishAfterCommit();

         });
        */

    }
}
