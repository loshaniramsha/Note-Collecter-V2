package com.example.Note_Collecter_V2.customeStatesCode;
import com.example.Note_Collecter_V2.dto.NoteStates;
import com.example.Note_Collecter_V2.dto.UserStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserAndNoteErrorStatus implements UserStates, NoteStates {
    private int statusCode;
    private String statusMessage;
}
