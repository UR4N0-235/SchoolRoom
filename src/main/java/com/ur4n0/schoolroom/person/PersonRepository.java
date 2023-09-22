package com.ur4n0.schoolroom.person;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonModel, Long> {
 Optional<PersonModel> findByRm(String rm);   
}