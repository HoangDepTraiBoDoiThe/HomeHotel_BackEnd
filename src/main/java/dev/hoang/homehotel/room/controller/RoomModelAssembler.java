package dev.hoang.homehotel.room.controller;

import dev.hoang.homehotel.room.dto.res.RoomResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RoomModelAssembler implements RepresentationModelAssembler<RoomResponse, EntityModel<RoomResponse>> {

    @Override
    public EntityModel<RoomResponse> toModel(RoomResponse entity) {
        EntityModel<RoomResponse> entityModel = EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).getRoomById(entity.getId())).withSelfRel().withType("GET"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).deleteRoom(entity.getId())).withRel("deleteRoom").withType("DELETE"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).getAllRooms()).withRel("getAllRooms").withType("GET")
                );

        if (!entity.isBooked()) {
            entityModel.add(
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookRoomController.class).bookARoom(null, entity.getId())).withRel("bookRoom").withType("POST")
            );
        }

        return entityModel;
    }
}
