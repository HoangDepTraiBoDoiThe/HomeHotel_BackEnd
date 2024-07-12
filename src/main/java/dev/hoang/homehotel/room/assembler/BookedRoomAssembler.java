package dev.hoang.homehotel.room.assembler;

import dev.hoang.homehotel.room.controller.BookRoomController;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookedRoomAssembler implements RepresentationModelAssembler<BookedRoomResponse, EntityModel<BookedRoomResponse>> {
    @Override
    public EntityModel<BookedRoomResponse> toModel(BookedRoomResponse entity) {
        EntityModel<BookedRoomResponse> entityModel = EntityModel.of(entity, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookRoomController.class).getBookedRoomByConfirmationCode(entity.getConfirmationCode())).withSelfRel());
        return entityModel;
    }
}
