package com.ur4n0.schoolroom.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        RoomModel room = new RoomModel(1L, 101);

        when(roomRepository.findAll()).thenReturn(Arrays.asList(room));
        when(roomRepository.existsById(1L)).thenReturn(true);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(roomRepository.findByNumber(101)).thenReturn(Optional.of(room));
    }

    @Test
    public void testGetAllRooms() {
        List<RoomModel> rooms = roomService.getAllRooms();
        assertThat(rooms).hasSize(1);
    }

    @Test
    public void testGetRoomById() {
        Optional<RoomModel> room = roomService.getRoomById(1L);
        assertThat(room).isPresent();
    }

    @Test
    public void testGetRoomByNumber() {
        Optional<RoomModel> room = roomService.getRoomByNumber(101);
        assertThat(room).isPresent();
    }

    @Test
    public void testCreateRoom() {
        RoomModel newRoom = new RoomModel(2L, 102);
        roomService.createRoom(newRoom);

        verify(roomRepository, times(1)).save(newRoom);
    }

    @Test
    public void testUpdateRoom() {
        RoomModel updatedRoom = new RoomModel(1L, 103);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(updatedRoom));

        roomService.updateRoom(1L, updatedRoom);

        verify(roomRepository, times(1)).save(updatedRoom);
    }

    @Test
    public void testDeleteRoom() {
        roomService.deleteRoom(1L);

        verify(roomRepository, times(1)).deleteById(1L);
    }
}
