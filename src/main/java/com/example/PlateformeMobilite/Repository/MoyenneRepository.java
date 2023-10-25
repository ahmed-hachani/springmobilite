package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.Moyenne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoyenneRepository extends JpaRepository<Moyenne,Long> {
    @Query("SELECT m FROM Moyenne m JOIN FETCH m.user JOIN FETCH m.form")
    List<Moyenne> findAllWithUserAndForm();
    Boolean existsByUser_UserIdAndForm_FormId(Long userId, Long formId);
    @Query("SELECT m FROM Moyenne m JOIN FETCH m.user JOIN FETCH m.form f WHERE f.formName = :formName")
    List<Moyenne> findAllByFormName(@Param("formName") String formName);
    List<Moyenne> findAllByForm_FormId(Long FormId);

}
