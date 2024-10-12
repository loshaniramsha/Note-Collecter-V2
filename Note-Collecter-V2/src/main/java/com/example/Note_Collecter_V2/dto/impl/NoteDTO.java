package com.example.Note_Collecter_V2.dto.impl;

import com.example.Note_Collecter_V2.dto.NoteStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class NoteDTO implements NoteStates {
    private String noteId;
    private String noteTittle;
    private String noteDesc;
    private String createDate;
    private String priorityLevel;
    private String userId;
}
