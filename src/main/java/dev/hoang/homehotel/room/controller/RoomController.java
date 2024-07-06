package dev.hoang.homehotel.room.controller;

import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.RoomResponse;
import dev.hoang.homehotel.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomModelAssembler roomModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RoomResponse>>> getAllRooms() {
        List<RoomResponse> roomResponses = roomService.getAllRooms();
        CollectionModel<EntityModel<RoomResponse>> roomResponseEntityModelCollection = roomModelAssembler.toCollectionModel(roomResponses);
        roomResponseEntityModelCollection.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).getAllRooms()).withSelfRel());
        return ResponseEntity.ok(roomResponseEntityModelCollection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RoomResponse>> getRoomById(@PathVariable long id) {
        EntityModel<RoomResponse> roomResponseEntityModel = roomModelAssembler.toModel(roomService.getRoomById(id));
        return ResponseEntity.ok(roomResponseEntityModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<RoomResponse>> createNewRoom(@RequestBody RoomRequest roomRequestBody) {
        RoomResponse roomResponse = roomService.createRoom(roomRequestBody);
        EntityModel<RoomResponse> roomResponseEntityModel = roomModelAssembler.toModel(roomResponse);
        return ResponseEntity.ok(roomResponseEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RoomResponse>> updateRoom(@PathVariable long id, @RequestBody RoomRequest roomRequestBody) {
        RoomResponse roomResponse = roomService.updateRoom(id, roomRequestBody);
        EntityModel<RoomResponse> roomResponseEntityModel = roomModelAssembler.toModel(roomResponse);
        return ResponseEntity.ok(roomResponseEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
