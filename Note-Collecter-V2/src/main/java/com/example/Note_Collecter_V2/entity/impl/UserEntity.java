package com.example.Note_Collecter_V2.entity.impl;

import com.example.Note_Collecter_V2.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")

public class UserEntity implements SuperEntity {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "LONGTEXT")
    private String profilepic;
    @OneToMany(mappedBy = "user")
    private List<NoteEntity> notes;
}
