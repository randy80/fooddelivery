package fooddelivery.infra;

import fooddelivery.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class DeliveryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Delivery>> {

    @Override
    public EntityModel<Delivery> process(EntityModel<Delivery> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/deliverycomplete"
                )
                .withRel("deliverycomplete")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deliverystart")
                .withRel("deliverystart")
        );

        return model;
    }
}
