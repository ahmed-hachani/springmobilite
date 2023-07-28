package com.example.testspring11.Services;

import com.example.testspring11.Entity.University;

import java.util.List;

public interface IUniversityService {
    public List<University> retrieveAllUniversities();
    public University retrieveUniversityById(Long UniversityId);
    public University addUniversity(University u);
    public University updateUniversity (University u);
    public void removeUniversity(Long UniversityId);
}
