package renting.com.controller;

import net.sf.dynamicreports.report.exception.DRException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Role;
import renting.com.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olivier on 16/09/2019.
 */
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseData getAllRole(){
        List<Role> prds = new ArrayList<Role>();
        List<Role> listRole = roleService.getAll();
        for(Role p: listRole){
            Role rol = new Role();
            rol.setId(p.getId());
            rol.setName(p.getName());
            rol.setDescription(p.getDescription());
            String act="<td>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editRoleModal\" class=\"link-underlined margin-right-5 btn btn-success btn-sm\" onclick=\"editRole("+rol.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeRoleModal\" class=\"link-underlined btn btn-danger btn-sm\" onclick=\"removeRole("+rol.getId()+")\"><i class=\"fa fa-trash\"><!-- --></i></a>\n" +
                    "</td>";
            rol.setAction(act);

            prds.add(rol);
        }
        return new ResponseData(true, prds);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public ResponseData addRole(Locale locale, @ModelAttribute Role role,BindingResult result,HttpServletRequest request)throws Exception{
        ResponseData json;
        try {

            String name = request.getParameter("name");
            String description = request.getParameter("description");

            role.setName(name.toUpperCase());
            role.setDescription(description);
            Role p = roleService.add(role);
            json = new ResponseData(true,p);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a été dupliquée",null);
        }
        return json;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.POST)
    public ResponseData updateRole(Locale locale, @ModelAttribute Role role,BindingResult result, @PathVariable int id,HttpServletRequest request)throws Exception{
        ResponseData json;
        try {
            role.setId(id);

            String name = request.getParameter("name");
            String description = request.getParameter("description");

            role.setName(name.toUpperCase());
            role.setDescription(description);
            Role p = roleService.update(role);
            json = new ResponseData(true,p);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a été dupliquée",null);
        }
        return json;
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public ResponseData getRole(@PathVariable int id){
        Role p = roleService.findById(id);
        return new ResponseData(true, p);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteRole(@PathVariable int id){
        ResponseData json;
        try {
            roleService.delete(id);
            json = new ResponseData(true,null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer car cette donnée est utilisée ailleurs",null);
        }
        return json;
    }

    /*@RequestMapping(value = "role/export", method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public void exportCustomer(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        response.setContentType("application/pdf");

        int id =Integer.parseInt(request.getParameter("cpt"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Role> roles = roleService.export(id, request);
        // System.out.println("userList "+customers);
        try {
            OutputStream out = response.getOutputStream();
            RoleExport p = new RoleExport(roles,authentication.getName());
            p.build(request).toPdf(out);
        } catch (IOException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (DRException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}
