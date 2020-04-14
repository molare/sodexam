package renting.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Category;
import renting.com.service.CategoryService;

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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/listCategory", method = RequestMethod.GET)
    public ResponseData getAllCategory(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        List<Category> newComList = new ArrayList<Category>();
        List<Category> listCom = categoryService.getAll();
        for(Category co : listCom){
            Category c = new Category();
            c.setId(co.getId());
            c.setName(co.getName());
            c.setDescription(co.getDescription());
            c.setCreatedDate(co.getCreatedDate());
            c.setDateTransient(df.format(co.getCreatedDate()));
            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editCategoryModal\" onclick=\"editCategory("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editCategoryModal\" class=\"link-underlined margin-right-50 btn btn-success btn-sm\" data-original-title=\"Editer\" onclick=\"editCategory("+c.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeCategoryModal\" class=\"link-underlined btn btn-danger btn-sm\" data-original-title=\"Supprimer\" onclick=\"removeCategory("+c.getId()+")\"><i class=\"fa fa-trash\"><!-- --></i></a>\n" +
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


    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public ResponseData addCategory(Locale locale,@ModelAttribute Category devis, BindingResult result,HttpServletRequest request){
        devis.setName(request.getParameter("name"));
        devis.setDescription(request.getParameter("description"));
        Category c =  categoryService.add(devis);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.POST)
    public ResponseData updateCategory(Locale locale,@ModelAttribute Category devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Category ci = categoryService.findById(id);
        ci.setName(request.getParameter("name"));
        ci.setDescription(request.getParameter("description"));
        Category c =  categoryService.add(ci);
        return new ResponseData(true, c);
    }

    @RequestMapping(value = "/findCategory/{id}", method = RequestMethod.GET)
    public ResponseData findCategory(Locale locale,@ModelAttribute Category devis,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Category ci = categoryService.findById(id);
       return new ResponseData(true, ci);
    }

    @RequestMapping(value = "/deleteCategory/{devisId}", method = RequestMethod.DELETE)
    public ResponseData deleteCategory(@PathVariable int devisId,HttpServletRequest request){
        ResponseData json=null;
        try {
            categoryService.delete(devisId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/countCategory", method = RequestMethod.POST)
    public ResponseData countCategory(HttpServletRequest request){
        ResponseData json=null;
        try {
           int counter = categoryService.countCategory();
            json = new ResponseData(true, counter);
        }catch (Exception ex){
            json = new ResponseData(false,"null",ex.getCause());
        }
        return json;
    }


}
