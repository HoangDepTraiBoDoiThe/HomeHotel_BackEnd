package dev.hoang.homehotel.room.controller;

import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.RoomResponse;
import dev.hoang.homehotel.room.model.Room;
import dev.hoang.homehotel.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomModelAssembler roomModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Room>>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        CollectionModel<EntityModel<Room>> roomEntityModelCollection = roomModelAssembler.toCollectionModel(rooms);
        roomEntityModelCollection.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).getAllRooms()).withSelfRel());
        return ResponseEntity.ok(roomEntityModelCollection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Room>> getRoomById(@PathVariable long id) {
        EntityModel<Room> roomEntityModel = roomModelAssembler.toModel(roomService.getRoomById(id));
        return ResponseEntity.ok(roomEntityModel);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EntityModel<Room>> createNewRoom(
            @RequestParam BigDecimal roomPrice, @RequestParam MultipartFile roomPic, @RequestParam String roomType, @RequestParam int roomNumber) {
        RoomRequest roomRequest = new RoomRequest(roomPrice, roomPic, roomType, roomNumber);
        Room room = roomService.createRoom(roomRequest);
        EntityModel<Room> roomEntityModel = roomModelAssembler.toModel(room);
        return ResponseEntity.ok(roomEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Room>> updateRoom(@PathVariable long id, @RequestBody RoomRequest roomRequestBody) {
        Room room = roomService.updateRoom(id, roomRequestBody);
        EntityModel<Room> roomEntityModel = roomModelAssembler.toModel(room);
        return ResponseEntity.ok(roomEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
