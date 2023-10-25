package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormData;
import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Entity.Moyenne;
import com.example.PlateformeMobilite.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MoyenneService {

    private final MoyenneRepository moyenneRepository;
    private final FormRepository formRepository;
    private final FormFieldRepository formFieldRepository;
    private final FormDataRepository formDataRepository;
    private final UserRepository userRepository;

    public Moyenne retrieveMoyenneById (Long id ){
        return moyenneRepository.findById(id).orElse(null);
    }
    public List<Moyenne> getAllMoyennesWithUserAndForm() {
        List<Moyenne> moyennes = moyenneRepository.findAllWithUserAndForm();
        System.out.println(moyennes);

        for (Moyenne moyenne : moyennes) {
            Long userId = moyenne.getUser().getUserId();
            Long formId = moyenne.getForm().getFormId();
            double calculatedMoyenne = calculateAverageForUser(formId, userId);
            moyenne.setMoyenne(calculatedMoyenne);
        }

        return moyennes;
    }

    public List<Moyenne> getAllMoyennesByFormName(String formName) {
        List<Moyenne> moyennes = moyenneRepository.findAllByFormName(formName);

        for (Moyenne moyenne : moyennes) {
            Long formId = moyenne.getForm().getFormId();
            Long userId = moyenne.getUser().getUserId();
            double calculatedMoyenne = calculateAverageForUser(formId, userId);
                moyenne.setMoyenne(calculatedMoyenne);

        }
        return moyennes;
    }
    public void createMoyenneEntitiesFromUsersAndForms() {
        List<Object[]> usersAndForms = userRepository.findUsersAndForms();


        for (Object[] userAndForm : usersAndForms) {


            Long userId = (Long) userAndForm[0];
            Long formId = (Long) userAndForm[1];
            boolean moyenneExists = moyenneRepository.existsByUser_UserIdAndForm_FormId(userId, formId);
            if (!moyenneExists) {

            double calculatedMoyenne = calculateAverageForUser(formId, userId);

            // Create a new Moyenne entity and set the user, form, and moyenne values
            Moyenne moyenne = new Moyenne();
            moyenne.setUser(userRepository.findById(userId).orElse(null));
            moyenne.setForm(formRepository.findById(formId).orElse(null));
            moyenne.setMoyenne(calculatedMoyenne);

            // Save the Moyenne entity to the database
            moyenneRepository.save(moyenne);
        }}
    }
    public double calculateAverageForUser(Long formId, Long userId) {
        Form form = formRepository.findById(formId).orElse(null);

//        if (form == null) {
//            throw new FormNotFoundException("Form not found with ID: " + formId);
//        }

        double sumOfProducts = 0.0;
        double sumOfCoefficients = 0.0;
        List<FormField> formFields = formFieldRepository.findByForm_FormId(formId);


        for (FormField field : formFields) {
            if ( field.getCoefficient() != 0) {

                // Retrieve the FormData for the specific user and field
                Long fieldId= field.getFieldId();
                FormData formData2 = formDataRepository.findByUser_UserIdAndField_FieldId(userId,fieldId );


                if (formData2 != null) {
                    Integer coefficient = field.getCoefficient();
                    Integer fieldValue = Integer.parseInt(formData2.getValue());
                    double product = coefficient * fieldValue;
                    sumOfProducts += product;
                    sumOfCoefficients += coefficient;
                }
            }
        }

        if (sumOfCoefficients != 0) {
            return sumOfProducts / sumOfCoefficients;
        } else {
            return 0.0;
        }
    }
}
