package renting.com.serviceImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import renting.com.entities.Civility;
import renting.com.repositories.CivilityRepository;
import renting.com.service.CivilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("civilityService")
public class CivilityServiceImpl implements CivilityService {
    @Autowired
    private CivilityRepository civilityRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<Civility> getAll() {
        return civilityRepository.findAll();
    }

    @Override
    public Civility add(Civility civility) {
        return civilityRepository.save(civility);
    }

    @Override
    public Civility update(Civility civility) {
        if(civility.getId() ==0){
            return civilityRepository.save(civility);
        }
        return civilityRepository.saveAndFlush(civility);
    }

    @Override
    public Civility findById(int id) {
        return civilityRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        civilityRepository.deleteById(id);
    }



    @Override
    public void sendmail(String toEmail, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("morysangare67@gmail.com");

        javaMailSender.send(mailMessage);
    }
}
