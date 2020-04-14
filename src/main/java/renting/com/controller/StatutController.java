package renting.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Statut;
import renting.com.service.StatutService;

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
public class StatutController {

    @Autowired
    private StatutService statutService;
    @RequestMapping(value = "/listStatut", method = RequestMethod.GET)
    public ResponseData getAllStatut(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        List<Statut> newComList = new ArrayList<Statut>();
        List<Statut> listCom = statutService.getAll();
        for(Statut co : listCom){
            Statut c = new Statut();
            c.setId(co.getId());
            c.setName(co.getName());
            c.setDescription(co.getDescription());
            c.setCreatedDate(co.getCreatedDate());
            c.setDateTransient(df.format(co.getCreatedDate()));
            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editStatutModal\" onclick=\"editStatut("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editStatutModal\" class=\"link-underlined margin-right-50 btn btn-success btn-sm\" data-original-title=\"Editer\" onclick=\"editStatut("+c.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeStatutModal\" class=\"link-underlined btn btn-danger btn-sm\" data-original-title=\"Supprimer\" onclick=\"removeStatut("+c.getId()+")\"><i class=\"fa fa-trash\"><!-- --></i></a>\n" +
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


    @RequestMapping(value = "/saveStatut", method = RequestMethod.POST)
    public ResponseData addStatut(Locale locale,@ModelAttribute Statut devis, BindingResult result,HttpServletRequest request){
        devis.setName(request.getParameter("name"));
        devis.setDescription(request.getParameter("description"));
        Statut c =  statutService.add(devis);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/updateStatut/{id}", method = RequestMethod.POST)
    public ResponseData updateStatut(Locale locale,@ModelAttribute Statut devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Statut ci = statutService.findById(id);
        ci.setName(request.getParameter("name"));
        ci.setDescription(request.getParameter("description"));
        Statut c =  statutService.add(ci);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/findStatut/{id}", method = RequestMethod.GET)
    public ResponseData findStatut(Locale locale,@ModelAttribute Statut devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Statut ci = statutService.findById(id);
       return new ResponseData(true, ci);
    }

    @RequestMapping(value = "/deleteStatut/{devisId}", method = RequestMethod.DELETE)
    public ResponseData deleteStatut(@PathVariable int devisId,HttpServletRequest request){
        ResponseData json=null;
        try {
            statutService.delete(devisId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/countStatut", method = RequestMethod.POST)
    public ResponseData countStatut(HttpServletRequest request){
        ResponseData json=null;
        try {
           int counter = statutService.countStatut();
            json = new ResponseData(true, counter);
        }catch (Exception ex){
            json = new ResponseData(false,"null",ex.getCause());
        }
        return json;
    }


}
