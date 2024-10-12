package com.example.Note_Collecter_V2.service;



import com.example.Note_Collecter_V2.dto.NoteStates;
import com.example.Note_Collecter_V2.dto.impl.NoteDTO;

import java.util.List;

public interface NotService {
    void saveNote(NoteDTO noteDTO);
    List<NoteDTO> getAllNote();
    NoteStates getNote(String noteId);
    void deleteNote(String noteId);
    void updateNote(String noteId, NoteDTO noteDTO);
}
