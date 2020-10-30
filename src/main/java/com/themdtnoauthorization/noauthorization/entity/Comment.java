package com.themdtnoauthorization.noauthorization.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=1000)
    private String text;
    private LocalDateTime date;
    private LocalDateTime dateOfEditing;

    @ManyToOne
    private User author;

    @ManyToOne
    private Disease disease;

    @ManyToOne
    private Mdt mdt;

}
