package dev.hoang.homehotel.room.controller;

import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.RoomResponse;
import dev.hoang.homehotel.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    ResponseEntity<List<RoomResponse>> getAllRooms() {
        List<RoomResponse> roomResponses = roomService.getAllRooms();
        return ResponseEntity.ok(roomResponses);
    }

    @GetMapping("/{id}")
    ResponseEntity<RoomResponse> getRoomById(@PathVariable long id) {
        RoomResponse roomResponse = roomService.getRoomById(id);
        return ResponseEntity.ok(roomResponse);
    }

    @PostMapping
    ResponseEntity<RoomResponse> createNewRoom(@RequestBody RoomRequest roomRequestBody) {
        RoomResponse roomResponse = roomService.createRoom(roomRequestBody);
        return ResponseEntity.ok(roomResponse);
    }

    @PutMapping("/{id}")
    ResponseEntity<RoomResponse> updateRoom(@PathVariable long id, @RequestBody RoomRequest roomRequestBody) {
        RoomResponse roomResponse = roomService.updateRoom(id, roomRequestBody);
        return ResponseEntity.ok(roomResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRoom(@PathVariable long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
