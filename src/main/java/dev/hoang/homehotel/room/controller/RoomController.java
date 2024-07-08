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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomModelAssembler roomModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RoomResponse>>> getAllRooms() {
        List<RoomResponse> roomResponses = roomService.getAllRooms().stream().map(RoomResponse::new).collect(Collectors.toList());
        CollectionModel<EntityModel<RoomResponse>> roomResponseModelCollection = roomModelAssembler.toCollectionModel(roomResponses);
        roomResponseModelCollection.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoomController.class).getAllRooms()).withSelfRel());
        return ResponseEntity.ok(roomResponseModelCollection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RoomResponse>> getRoomById(@PathVariable long id) {
        RoomResponse roomResponse = new RoomResponse(roomService.getRoomById(id));
        EntityModel<RoomResponse> roomResponseModel = roomModelAssembler.toModel(roomResponse);
        return ResponseEntity.ok(roomResponseModel);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EntityModel<RoomResponse>> createNewRoom(
            @RequestParam BigDecimal roomPrice, @RequestParam MultipartFile roomPic, @RequestParam String roomType, @RequestParam int roomNumber) {
        RoomRequest roomRequest = new RoomRequest(roomPrice, roomPic, roomType, roomNumber);
        RoomResponse roomResponse = new RoomResponse(roomService.createRoom(roomRequest));
        EntityModel<RoomResponse> roomResponseModel = roomModelAssembler.toModel(roomResponse);
        return ResponseEntity.ok(roomResponseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RoomResponse>> updateRoom(@PathVariable long id, @RequestBody RoomRequest roomRequestBody) {
        RoomResponse roomResponse = new RoomResponse(roomService.updateRoom(id, roomRequestBody));
        EntityModel<RoomResponse> roomResponseModel = roomModelAssembler.toModel(roomResponse);
        return ResponseEntity.ok(roomResponseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
