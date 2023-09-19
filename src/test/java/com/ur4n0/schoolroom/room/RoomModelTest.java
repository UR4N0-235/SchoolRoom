package com.ur4n0.schoolroom.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomModelTest {

    private RoomModel room;

    @BeforeEach
    public void setUp() {
        room = new RoomModel(1L, 101);
    }

    @Test
    public void testRoomModelFields() {
        assertThat(room.getId()).isEqualTo(1L);
        assertThat(room.getNumber()).isEqualTo(101);
    }

    @Test
    public void testCreateRoomModel() {
        assertThat(room).isNotNull();
    }

    @Test
    public void testRoomModelEquality() {
        RoomModel sameRoom = new RoomModel(1L, 101);
        RoomModel differentRoom = new RoomModel(2L, 102);

        assertThat(room).isEqualTo(sameRoom);
        assertThat(room).isNotEqualTo(differentRoom);
    }
}
