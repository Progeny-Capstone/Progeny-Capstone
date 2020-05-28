package com.progeny.repositories;

import com.progeny.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> { // < What we are dealing with, How it will be identified >
}
