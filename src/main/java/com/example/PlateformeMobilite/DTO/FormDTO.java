package com.example.PlateformeMobilite.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class FormDTO {
    private Long universityId;

    private String formName;
    private String description;
    private int placesDisp;
    private Date datelimite;
}
