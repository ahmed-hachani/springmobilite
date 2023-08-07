package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.Moyenne;
import com.example.PlateformeMobilite.Repository.MoyenneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MoyenneService {

    private final MoyenneRepository moyenneRepository;

    public Moyenne retrieveMoyenneById (Long id ){
        return moyenneRepository.findById(id).orElse(null);
    }
}
