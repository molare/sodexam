package renting.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Direction;
import renting.com.service.DirectionService;

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
public class DirectionController {

    @Autowired
    private DirectionService directionService;
    @RequestMapping(value = "/listDirection", method = RequestMethod.GET)
    public ResponseData getAllDirection(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        List<Direction> newComList = new ArrayList<Direction>();
        List<Direction> listCom = directionService.getAll();
        for(Direction co : listCom){
            Direction c = new Direction();
            c.setId(co.getId());
            c.setName(co.getName());
            c.setDescription(co.getDescription());
            c.setCreatedDate(co.getCreatedDate());
            c.setDateTransient(df.format(co.getCreatedDate()));
            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editDirectionModal\" onclick=\"editDirection("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editDirectionModal\" class=\"link-underlined margin-right-50 btn btn-success btn-sm\" data-original-title=\"Editer\" onclick=\"editDirection("+c.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeDirectionModal\" class=\"link-underlined btn btn-danger btn-sm\" data-original-title=\"Supprimer\" onclick=\"removeDirection("+c.getId()+")\"><i class=\"fa fa-trash\"><!-- --></i></a>\n" +
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


    @RequestMapping(value = "/saveDirection", method = RequestMethod.POST)
    public ResponseData addDirection(Locale locale,@ModelAttribute Direction devis, BindingResult result,HttpServletRequest request){
        devis.setName(request.getParameter("name"));
        devis.setDescription(request.getParameter("description"));
        Direction c =  directionService.add(devis);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/updateDirection/{id}", method = RequestMethod.POST)
    public ResponseData updateDirection(Locale locale,@ModelAttribute Direction devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Direction ci = directionService.findById(id);
        ci.setName(request.getParameter("name"));
        ci.setDescription(request.getParameter("description"));
        Direction c =  directionService.add(ci);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/findDirection/{id}", method = RequestMethod.GET)
    public ResponseData findDirection(Locale locale,@ModelAttribute Direction devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Direction ci = directionService.findById(id);
       return new ResponseData(true, ci);
    }

    @RequestMapping(value = "/deleteDirection/{devisId}", method = RequestMethod.DELETE)
    public ResponseData deleteDirection(@PathVariable int devisId,HttpServletRequest request){
        ResponseData json=null;
        try {
            directionService.delete(devisId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/countDirection", method = RequestMethod.POST)
    public ResponseData countDirection(HttpServletRequest request){
        ResponseData json=null;
        try {
           int counter = directionService.countDirection();
            json = new ResponseData(true, counter);
        }catch (Exception ex){
            json = new ResponseData(false,"null",ex.getCause());
        }
        return json;
    }


}
