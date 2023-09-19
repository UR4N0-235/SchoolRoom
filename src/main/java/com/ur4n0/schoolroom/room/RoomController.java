package com.ur4n0.schoolroom.room;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomModel>> getAllRooms() {
        List<RoomModel> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomModel> getRoomById(@PathVariable Long id) {
        Optional<RoomModel> room = roomService.getRoomById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RoomModel> createRoom(@RequestBody RoomModel room) {
        RoomModel createdRoom = roomService.createRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomModel> updateRoom(@PathVariable Long id, @RequestBody RoomModel updatedRoom) {
        Optional<RoomModel> existingRoom = roomService.getRoomById(id);
        if (existingRoom.isPresent()) {
            RoomModel room = existingRoom.get();
            room.setNumber(updatedRoom.getNumber());

            RoomModel savedRoom = roomService.updateRoom(id, room);
            return ResponseEntity.ok(savedRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        Optional<RoomModel> existingRoom = roomService.getRoomById(id);
        if (existingRoom.isPresent()) {
            roomService.deleteRoom(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
