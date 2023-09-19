package com.ur4n0.schoolroom.room;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomModel> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<RoomModel> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Optional<RoomModel> getRoomByNumber(int number) {
        return roomRepository.findByNumber(number);
    }

    public RoomModel createRoom(RoomModel room) {
        return roomRepository.save(room);
    }

    public RoomModel updateRoom(Long id, RoomModel updatedRoom) {
        if (roomRepository.existsById(id)) {
            updatedRoom.setId(id);
            return roomRepository.save(updatedRoom);
        } else {
            throw new IllegalArgumentException("Sala não encontrada com ID: " + id);
        }
    }

    public void deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Sala não encontrada com ID: " + id);
        }
    }
}
