package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.University;
import com.example.PlateformeMobilite.Interfaces.IUniversityService;
import com.example.PlateformeMobilite.Repository.UniversityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UniversityService implements IUniversityService {

    private final UniversityRepository universityRepository;
    @Override
    public List<University> retrieveAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public University retrieveUniversityById(Long UniversityId) {
        return universityRepository.findById(UniversityId).get();
    }

    @Override
    public University addUniversity(University u) {
        return universityRepository.save(u);
    }

    @Override
    public University updateUniversity(Long id,University u) {
        return universityRepository.findById(id)
                .map(p->{
                    p.setLocation(u.getLocation());
                    p.setName((u.getName()));
                    return universityRepository.save(u);
                }).orElseThrow(()-> new RuntimeException("user not found!"));
    }

    @Override
    public void removeUniversity(Long UniversityId) {
        universityRepository.deleteById(UniversityId);

    }
}
