package fooddelivery.infra;

import fooddelivery.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class StoreOrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<StoreOrder>> {

    @Override
    public EntityModel<StoreOrder> process(EntityModel<StoreOrder> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/orderaccept")
                .withRel("orderaccept")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/orderreject")
                .withRel("orderreject")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/startcooking")
                .withRel("startcooking")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/finishcooking")
                .withRel("finishcooking")
        );

        return model;
    }
}
