package com.example.PlateformeMobilite.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @ManyToOne
    private Form form;

    private String fieldName;
    private String fieldLabel;
    private String fieldType;/* my own type mech HTML  selon type hedha bech yekhou input Lform b switch case*/
    private String configuration;
    private int coefficient;



}
