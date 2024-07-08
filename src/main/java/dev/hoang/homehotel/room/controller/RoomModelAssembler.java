package dev.hoang.homehotel.room.controller;

import dev.hoang.homehotel.room.dto.res.RoomResponse;
import dev.hoang.homehotel.room.model.Room;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RoomModelAssembler implements RepresentationModelAssembler<Room, EntityModel<Room>> {

    @Override
    public EntityModel<Room> toModel(Room entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).getRoomById(entity.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).getAllRooms()).withRel("getAllRooms")
        );
    }
}
