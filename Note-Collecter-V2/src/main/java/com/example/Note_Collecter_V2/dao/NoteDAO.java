package com.example.Note_Collecter_V2.dao;
import com.example.Note_Collecter_V2.entity.impl.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDAO extends JpaRepository<NoteEntity, String> {
}
