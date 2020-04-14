package renting.com.controller;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Civility;
import renting.com.service.CivilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by olivier on 02/10/2019.
 */
@RestController
public class CivilityController {

    @Autowired
    private CivilityService civilityService;


    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @RequestMapping(value = "/listCivility", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public ResponseData getAllCivility(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        List<Civility> newComList = new ArrayList<Civility>();
        List<Civility> listCom = civilityService.getAll();
        for(Civility co : listCom){
            Civility c = new Civility();
            c.setId(co.getId());
            c.setName(co.getName());
            c.setDescription(co.getDescription());
            c.setCreatedDate(co.getCreatedDate());
            c.setDateTransient(df.format(co.getCreatedDate()));
            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editCivilityModal\" onclick=\"editCivility("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editCivilityModal\" class=\"link-underlined margin-right-50 btn btn-success btn-sm\" data-original-title=\"Editer\" onclick=\"editCivility("+c.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeCivilityModal\" class=\"link-underlined btn btn-danger btn-sm\" data-original-title=\"Supprimer\" onclick=\"removeCivility("+c.getId()+")\"><i class=\"fa fa-trash\"><!-- --></i></a>\n" +
                    "</td>";
            c.setAction(act);
            String checkboxes ="<input name=\"select_id\" id=\"tabId\" value=\""+c.getId()+"\" type=\"checkbox\">";
            c.setCheckboxe(checkboxes);
            newComList.add(c);
        }



        return new ResponseData(true,newComList);
    }


    @RequestMapping(value = "/saveCivility", method = RequestMethod.POST)
    public ResponseData addCivility(Locale locale,@ModelAttribute Civility civility, BindingResult result,HttpServletRequest request){
        ResponseData json=null;
        /*MimeMessage message =  this.mailSender.createMimeMessage();
        MimeMessageHelper mimeHelper;*/
        try {
            civility.setName(request.getParameter("name"));
            civility.setDescription(request.getParameter("description"));
            Civility c = civilityService.add(civility);
            /*mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("morysangare67@gmail.com");
            mailSender.setPassword("baba1993");
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            mailSender.setDefaultEncoding("UTF-8");
            mailSender.setJavaMailProperties(props);
            mimeHelper = new MimeMessageHelper(message,true);
            mimeHelper.setTo("morysangare67@gmail.com");
            mimeHelper.setFrom("morysangare67@gmail.com");
            mimeHelper.setSubject("Initialisation du mot de passe");
            mimeHelper.setText("<html><body>Bonjour,<br/>Merci de cliquer ici pour r&eacute;initialiser le mot de passe</a></body></html>",true);
            mailSender.send(message);*/

            json= new ResponseData(true, c);
        }catch (Exception ex){
            System.out.println("errrrrrrrrrrrrrrrrrrrr "+ex.getMessage());
            System.out.println("errrrrrrrrrrrrrrrrrrrr "+ex.getCause());
            json= new ResponseData(false, ex.getMessage());
        }
        return json;
    }

    @RequestMapping(value = "/updateCivility/{id}", method = RequestMethod.POST)
    public ResponseData updateCivility(Locale locale,@ModelAttribute Civility civility,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Civility ci = civilityService.findById(id);
        ci.setName(request.getParameter("name"));
        ci.setDescription(request.getParameter("description"));
        Civility c =  civilityService.add(ci);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/findCivility/{id}", method = RequestMethod.GET)
    public ResponseData findCivility(Locale locale,@ModelAttribute Civility civility,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Civility ci = civilityService.findById(id);
       return new ResponseData(true, ci);
    }

    @RequestMapping(value = "/deleteCivility/{civilityId}", method = RequestMethod.DELETE)
    public ResponseData deleteCivility(@PathVariable int civilityId,HttpServletRequest request){

        ResponseData json=null;
        try {
            civilityService.delete(civilityId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }
}
