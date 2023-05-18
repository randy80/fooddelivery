package fooddelivery.infra;

import fooddelivery.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/storeOrders")
@Transactional
public class StoreOrderController {

    @Autowired
    StoreOrderRepository storeOrderRepository;

    @RequestMapping(
        value = "storeOrders/{id}/orderaccept",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder orderAccept(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/orderAccept  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.orderAccept();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }

    @RequestMapping(
        value = "storeOrders/{id}/orderreject",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder orderReject(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/orderReject  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.orderReject();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }

    @RequestMapping(
        value = "storeOrders/{id}/startcooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder startCooking(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/startCooking  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.startCooking();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }

    @RequestMapping(
        value = "storeOrders/{id}/finishcooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder finishCooking(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/finishCooking  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.finishCooking();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }
}
