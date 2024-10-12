package com.example.Note_Collecter_V2.service.impl;

import com.example.Note_Collecter_V2.customeStatesCode.SelectedUserAndNoteErrorStatus;
import com.example.Note_Collecter_V2.dao.NoteDAO;
import com.example.Note_Collecter_V2.dto.NoteStates;
import com.example.Note_Collecter_V2.dto.impl.NoteDTO;
import com.example.Note_Collecter_V2.entity.impl.NoteEntity;
import com.example.Note_Collecter_V2.exception.DataPersistsExseption;
import com.example.Note_Collecter_V2.exception.NoteNotFoundException;
import com.example.Note_Collecter_V2.exception.UserNoteFoundException;
import com.example.Note_Collecter_V2.service.NotService;
import com.example.Note_Collecter_V2.util.AppUtil;
import com.example.Note_Collecter_V2.util.Mapping;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class NoteServiceImpl implements NotService {

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());
        NoteEntity savedNote =
                noteDAO.save(mapping.toNoteEntity(noteDTO));
        if(savedNote == null){
            throw new DataPersistsExseption("Note not saved");
        }

    }

    @Override
    public List<NoteDTO> getAllNote() {
        return mapping.asNoteDTOList( noteDAO.findAll());
    }

    @Override
    public NoteStates getNote(String noteId) {
        if(noteDAO.existsById(noteId)){
            var selectedUser = noteDAO.getReferenceById(noteId);
            return mapping.toNoteDTO(selectedUser);
        }else {
            return new SelectedUserAndNoteErrorStatus(2,"Selected note not found");
        }
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> foundNote = noteDAO.findById(noteId);
        if (!foundNote.isPresent()) {
            throw new NoteNotFoundException("Note not found");
        }else {
            noteDAO.deleteById(noteId);
        }
    }

    @Override
    public void updateNote(String noteId, NoteDTO noteDTO) {
        Optional<NoteEntity> findNote=noteDAO.findById(noteId);
        if (!findNote.isPresent()) {
            throw new UserNoteFoundException("Note not found");
        } else {
            findNote.get().setNoteTittle(noteDTO.getNoteTittle());
            findNote.get().setNoteDesc(noteDTO.getNoteDesc());
            findNote.get().setPriorityLevel(noteDTO.getPriorityLevel());
            noteDAO.save(findNote.get());
        }
    }
}
