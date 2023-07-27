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
public class FormData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataId;

    @ManyToOne
    private Form form;

    @ManyToOne
    private User user;

    @ManyToOne
    private FormField field;

    private String value;

}
