package renting.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Fonction;
import renting.com.service.FonctionService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
@RestController
public class FonctionController {

    @Autowired
    private FonctionService fonctionService;
    @RequestMapping(value = "/listFonction", method = RequestMethod.GET)
    public ResponseData getAllFonction(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        List<Fonction> newComList = new ArrayList<Fonction>();
        List<Fonction> listCom = fonctionService.getAll();
        for(Fonction co : listCom){
            Fonction c = new Fonction();
            c.setId(co.getId());
            c.setName(co.getName());
            c.setDescription(co.getDescription());
            c.setCreatedDate(co.getCreatedDate());
            c.setDateTransient(df.format(co.getCreatedDate()));
            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editFonctionModal\" onclick=\"editFonction("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editFonctionModal\" class=\"link-underlined margin-right-50 btn btn-success btn-sm\" data-original-title=\"Editer\" onclick=\"editFonction("+c.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeFonctionModal\" class=\"link-underlined btn btn-danger btn-sm\" data-original-title=\"Supprimer\" onclick=\"removeFonction("+c.getId()+")\"><i class=\"fa fa-trash\"><!-- --></i></a>\n" +
                    "</td>";
            c.setAction(act);
      /*      String checkboxe ="  <label class=\"ui-checkbox\">\n" +
                    "  <input type=\"checkbox\" id=\""+c.getId()+"\">\n" +
                    "  <span class=\"input-span\"></span>\n" +
                    "  </label>";*/
            String checkboxe ="<input name=\"select_id\" id=\"tabId\" value=\""+c.getId()+"\" type=\"checkbox\">";
            c.setCheckboxe(checkboxe);
            newComList.add(c);
        }



        return new ResponseData(true,newComList);
    }


    @RequestMapping(value = "/saveFonction", method = RequestMethod.POST)
    public ResponseData addFonction(Locale locale,@ModelAttribute Fonction devis, BindingResult result,HttpServletRequest request){
        devis.setName(request.getParameter("name"));
        devis.setDescription(request.getParameter("description"));
        Fonction c =  fonctionService.add(devis);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/updateFonction/{id}", method = RequestMethod.POST)
    public ResponseData updateFonction(Locale locale,@ModelAttribute Fonction devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Fonction ci = fonctionService.findById(id);
        ci.setName(request.getParameter("name"));
        ci.setDescription(request.getParameter("description"));
        Fonction c =  fonctionService.add(ci);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/findFonction/{id}", method = RequestMethod.GET)
    public ResponseData findFonction(Locale locale,@ModelAttribute Fonction devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Fonction ci = fonctionService.findById(id);
       return new ResponseData(true, ci);
    }

    @RequestMapping(value = "/deleteFonction/{devisId}", method = RequestMethod.DELETE)
    public ResponseData deleteFonction(@PathVariable int devisId,HttpServletRequest request){
        ResponseData json=null;
        try {
            fonctionService.delete(devisId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/countFonction", method = RequestMethod.POST)
    public ResponseData countFonction(HttpServletRequest request){
        ResponseData json=null;
        try {
           int counter = fonctionService.countFonction();
            json = new ResponseData(true, counter);
        }catch (Exception ex){
            json = new ResponseData(false,"null",ex.getCause());
        }
        return json;
    }


}
