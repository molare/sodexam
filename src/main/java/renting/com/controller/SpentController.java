package renting.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Spent;
import renting.com.service.SpentService;

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
public class SpentController {

    @Autowired
    private SpentService spentService;
    @RequestMapping(value = "/listSpent", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public ResponseData getAllSpent(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        List<Spent> newComList = new ArrayList<Spent>();
        List<Spent> listCom = spentService.getAll();
        for(Spent co : listCom){
            Spent c = new Spent();
            c.setId(co.getId());
            c.setName(co.getName());
            c.setAmount(co.getAmount());
            c.setCreatedDate(co.getCreatedDate());
            c.setDateTransient(df.format(co.getCreatedDate()));
            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editSpentModal\" onclick=\"editSpent("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editSpentModal\" class=\"link-underlined margin-right-50 btn btn-success\" data-original-title=\"Editer\" onclick=\"editSpent("+c.getId()+")\"><i class=\"fa fa-pencil font-14\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeSpentModal\" class=\"link-underlined btn btn-danger\" data-original-title=\"Supprimer\" onclick=\"removeSpent("+c.getId()+")\"><i class=\"fa fa-trash font-14\"><!-- --></i></a>\n" +
                    "</td>";
            c.setAction(act);
            String checkboxes ="<input name=\"select_id\" id=\"tabId\" value=\""+c.getId()+"\" type=\"checkbox\">";
            c.setCheckboxe(checkboxes);
            newComList.add(c);
        }



        return new ResponseData(true,newComList);
    }


    @RequestMapping(value = "/saveSpent", method = RequestMethod.POST)
    public ResponseData addSpent(Locale locale,@ModelAttribute Spent spent, BindingResult result,HttpServletRequest request){
        spent.setName(request.getParameter("name"));
        spent.setAmount(Double.parseDouble(request.getParameter("amount")));
        spent.setCommentary(request.getParameter("commentary"));
        Spent c =  spentService.add(spent);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/updateSpent/{id}", method = RequestMethod.POST)
    public ResponseData updateSpent(Locale locale,@ModelAttribute Spent spent,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Spent ci = spentService.findById(id);
        ci.setName(request.getParameter("name"));
        ci.setAmount(Double.parseDouble(request.getParameter("amount")));
        ci.setCommentary(request.getParameter("commentary"));
        Spent c =  spentService.add(ci);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/findSpent/{id}", method = RequestMethod.GET)
    public ResponseData findSpent(Locale locale,@ModelAttribute Spent spent,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Spent ci = spentService.findById(id);
       return new ResponseData(true, ci);
    }

    @RequestMapping(value = "/deleteSpent/{spentId}", method = RequestMethod.DELETE)
    public ResponseData deleteSpent(@PathVariable int spentId,HttpServletRequest request){

        ResponseData json=null;
        try {
            spentService.delete(spentId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }
}
