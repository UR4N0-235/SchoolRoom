package com.ur4n0.schoolroom.room;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomModel, Long>{
    Optional<RoomModel> findByNumber(int number);
}
