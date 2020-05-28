package com.progeny.repositories;

import com.progeny.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long>{ // < What we are dealing with, How it will be identified >

}
