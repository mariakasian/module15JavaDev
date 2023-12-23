package com.maria.module15.repository;

import com.maria.module15.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {
}
