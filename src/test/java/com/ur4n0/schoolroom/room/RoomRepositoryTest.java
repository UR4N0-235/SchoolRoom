package com.ur4n0.schoolroom.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;
 
    @BeforeEach
    public void setUp() {
        roomRepository.save(new RoomModel(1L, 101));
    }

    @Test
    public void testFindAll() {
        List<RoomModel> rooms = roomRepository.findAll();
        assertThat(rooms).isNotEmpty();
    }
    
    @Test
    public void testFindByNumber() {
        Optional<RoomModel> room = roomRepository.findByNumber(101);
        assertThat(room).isPresent();
        assertThat(room.get().getNumber()).isEqualTo(101);
    }

    @Test
    public void testFindByNumberNotFound() {
        Optional<RoomModel> room = roomRepository.findByNumber(102);
        assertThat(room).isEmpty();
    }

    @Test
    public void testSave() {
        RoomModel room = new RoomModel();
        room.setNumber(102);
        RoomModel savedRoom = roomRepository.save(room);

        assertThat(savedRoom).isNotNull();
        assertThat(savedRoom.getId()).isNotNull();
        assertThat(savedRoom.getNumber()).isEqualTo(102);
    }

    @Test
    public void testDelete() {
        roomRepository.deleteById(1L);
        Optional<RoomModel> room = roomRepository.findById(1L);
        assertThat(room).isEmpty();
    }
}
