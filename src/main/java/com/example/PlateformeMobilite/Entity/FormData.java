package com.example.PlateformeMobilite.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormData implements Serializable {
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

    public FormData( User user, FormField field, String value) {
        this.user = user;
        this.field = field;
        this.value = value;
    }


}
