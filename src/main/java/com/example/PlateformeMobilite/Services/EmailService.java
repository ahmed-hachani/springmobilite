package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.DTO.EmailDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.Moyenne;
import com.example.PlateformeMobilite.Entity.User;
import com.example.PlateformeMobilite.Interfaces.IEmailService;
import com.example.PlateformeMobilite.Repository.FormRepository;
import com.example.PlateformeMobilite.Repository.MoyenneRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {


    private final JavaMailSender javaMailSender;
    private final MoyenneRepository ms;
    private final FormRepository fs;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    @Override
    public List<EmailDTO> getAcceptedUsers(Long formId) {
        Form form = fs.findByFormId(formId);
        int placesDisp = form.getPlacesDisp();
        //List<Moyenne> moyennes = form.getMoyennes();// Assuming you have a getter for moyennes
        //Long formId1 = form.getFormId();
        List<Moyenne> moyennes1= ms.findAllByForm_FormId(formId);



        // Sort Moyennes by descending order of moyenne
        moyennes1.sort(Comparator.comparingDouble(Moyenne::getMoyenne).reversed());

        // Get the first 'placesDisp' number of users
        List<EmailDTO> acceptedUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(placesDisp, moyennes1.size()); i++) {
            User user = moyennes1.get(i).getUser();
            acceptedUsers.add(new EmailDTO(user.getUserId(), user.getUsername(), form.getFormId(), form.getFormName(), user.getEmail()));
        }

        return acceptedUsers;
    }

    @Override
    public List<EmailDTO> getRejectedUsers(Long formId) {
        Form form = fs.findByFormId(formId);
        int placesDisp = form.getPlacesDisp();
        List<Moyenne> moyennes1 = ms.findAllByForm_FormId(formId);

        // Sort Moyennes by descending order of moyenne
        moyennes1.sort(Comparator.comparingDouble(Moyenne::getMoyenne).reversed());

        // Get the first 'placesDisp' number of users as accepted users
        List<EmailDTO> acceptedUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(placesDisp, moyennes1.size()); i++) {
            User user = moyennes1.get(i).getUser();
            acceptedUsers.add(new EmailDTO(user.getUserId(), user.getUsername(), form.getFormId(), form.getFormName(), user.getEmail()));
        }

        List<EmailDTO> rejectedUsers = moyennes1.stream()
                .filter(moyenne -> !acceptedUsers.stream().anyMatch(
                        acceptedUser -> acceptedUser.getUserId().equals(moyenne.getUser().getUserId())))
                .map(moyenne -> {
                    User user = moyenne.getUser();
                    // Pass the 'email' parameter when creating EmailDTO
                    return new EmailDTO(user.getUserId(), user.getUsername(), form.getFormId(), form.getFormName(), user.getEmail());
                })
                .collect(Collectors.toList());

        return rejectedUsers;
    }
    public void sendAcceptanceEmail(EmailDTO emailDTO) {
        String subject = "Congratulations!";
        String message = "Dear " + emailDTO.getUsername() + ",\n\n"
                + "You have been accepted for the Form: " + emailDTO.getFormName() + ".\n"
                + "Congratulations and best wishes!";
        sendEmail(emailDTO.getEmail(), subject, message);
    }

    public void sendRejectionEmail(EmailDTO emailDTO) {
        String subject = "Application Update";
        String message = "Dear " + emailDTO.getUsername() + ",\n\n"
                + "We regret to inform you that your application for the Form: " + emailDTO.getFormName() + " has been rejected.\n"
                + "Thank you for applying, and we appreciate your interest.";
        sendEmail(emailDTO.getEmail(), subject, message);
    }
    @Override
    public void sendEmailsToAcceptedAndRejectedUsers(Long formId) {
        List<EmailDTO> acceptedUsers = this.getAcceptedUsers(formId);
        List<EmailDTO> rejectedUsers = this.getRejectedUsers(formId);

        for (EmailDTO user : acceptedUsers) {
            this.sendAcceptanceEmail(user);
        }

        for (EmailDTO user : rejectedUsers) {
            this.sendRejectionEmail(user);
        }
    }
}
