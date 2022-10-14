package com.devmountain.noteApp.entities;

import com.devmountain.noteApp.dtos.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;
//many to one creates association within Hibernate
// jsonback ref prevents infinite recursion when you deliver the resource up as Json using Restful APi endpoints
    @ManyToOne
    @JsonBackReference
    private User user;

    public Note(NoteDto noteDto){
        if(noteDto.getBody() !=null){
            this.body = noteDto.getBody();
        }
    }
}
