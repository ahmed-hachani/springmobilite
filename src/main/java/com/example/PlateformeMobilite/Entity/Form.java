package com.example.PlateformeMobilite.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @ManyToOne
    private University university;
    @OneToMany(mappedBy = "form")
    @OrderBy("moyenne DESC ")
    private List<Moyenne> moyennes;

    private String formName;
    private String description;

    private String equation;


}
