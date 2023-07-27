package com.example.testspring11.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @ManyToOne
    private University university;

    private String formName;
    private String description;

}
