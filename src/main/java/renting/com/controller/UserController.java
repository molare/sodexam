package renting.com.controller;

import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.User;
import renting.com.service.RoleService;
import renting.com.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MORY on 19/03/2019.
 */
@RestController
//@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public ResponseData getAlls(){
        List<User> users = new ArrayList<User>();
        List<User> listUsers = userService.getAll();
        for(User user: listUsers){
            User u = new User();
            u.setId(user.getId());
            u.setDate(user.getDate());
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            u.setRoleTransient(user.getRole().getName());
            if(user.isActive()){
                u.setActiveTransient("Oui");
            }else{
                u.setActiveTransient("Non");

            }
            //u.setActive(user.isActive());
            String act="<td>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editUserModal\" class=\"link-underlined margin-right-5\" onclick=\"editUser("+u.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeUserModal\" class=\"link-underlined\" onclick=\"removeUser("+u.getId()+")\"><i class=\"fa fa-trash\"><!-- --></i></a>\n" +
                    "</td>";

            /*if(p.getImage() != null){
                byte[] encodeBase64 = Base64.encodeBase64(p.getImage());
                String base64Encoded = null;
                try {
                    base64Encoded = new String(encodeBase64, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //String img = "<img width=\"64\" height=\"64\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
                String img = "<img style=\"width:60px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
                pro.setImageTransient(img);
            }else{
                // String img = "<img class=\"user_picture_small\" style=\"width:200px\" alt=\"Image\" src=\"../assets/common/img/upload_img.png\">";
                pro.setImageTransient(null);
            }*/
            u.setAction(act);
            users.add(u);
        }
        return new ResponseData(true, users);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/saveUser/{role}", method = RequestMethod.POST)
    public ResponseData addUser(Locale locale, @ModelAttribute User user,BindingResult result,@PathVariable("role") int roleId,/*@RequestParam("picture")MultipartFile file,*/HttpServletRequest request)throws Exception{
        ResponseData json;
        try {
            /*if(file.getSize()>0){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                User.setImageName(fileName);
                User.setImage(bytes);
            }*/
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user.setUsername(username);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            String active = request.getParameter("active");
            if(Integer.parseInt(active)==1){
                user.setActive(true);
            }else {
                user.setActive(false);
            }
            user.setRole(roleService.findById(roleId));
            User p = userService.add(user);
            json = new ResponseData(true,p);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a été dupliquée",null);
        }
        return json;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public ResponseData updateUser(Locale locale, @ModelAttribute User users,BindingResult result, @PathVariable int id,/*@RequestParam("editPicture")MultipartFile file,*/HttpServletRequest request)throws Exception{
        ResponseData json;
        try {
            User user = userService.findById(id);
            /*if(file.getSize()>0 && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                user.setImageName(fileName);
                user.setImage(bytes);
            }else{
                user.setImage(UserService.findById(id).getImage());
                user.setImageName(UserService.findById(id).getImageName());
            }*/
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user.setUsername(username);
            if(!password.isEmpty()) {
                user.setPassword(new BCryptPasswordEncoder().encode(password));
            }else{
                user.setPassword(user.getPassword());
            }
            String active = request.getParameter("active");
            if(Integer.parseInt(active)==1){
                user.setActive(true);
            }else {
                user.setActive(false);
            }
            String role = request.getParameter("role");
            user.setRole(roleService.findById(Integer.parseInt(role)));

                User p = userService.update(user);
            json = new ResponseData(true,p);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a été dupliquée",null);
        }
        return json;
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public ResponseData getUser(@PathVariable int id){
        User p = userService.findById(id);
        if(p.isActive()){
            p.setActiveTransient("Oui");
        }else{
            p.setActiveTransient("Non");
        }
       /* if(p.getImage() != null){
            byte[] encodeBase64 = Base64.encodeBase64(p.getImage());
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //String img = "<img width=\"64\" height=\"64\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
            String img = "<img style=\"width:200px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
            p.setImageTransient(img);
        }else{
            // String img = "<img class=\"user_picture_small\" style=\"width:200px\" alt=\"Image\" src=\"../assets/common/img/upload_img.png\">";
            p.setImageTransient(null);
        }*/
        return new ResponseData(true, p);
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteUser(@PathVariable int id){
        ResponseData json;
        try {
            userService.delete(id);
            json = new ResponseData(true,null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer car cette donnée est utilisée ailleurs",null);
        }
        return json;
    }
/*
    @RequestMapping(value = "count/users", method = RequestMethod.POST)
    public ResponseData countAllProviders(){
        int cpt = UserService.counterProvider();
        return new ResponseData(true,cpt);
    }*/

   /* @RequestMapping(value = "user/export", method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public void exportCustomer(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        response.setContentType("application/pdf");

        int id =Integer.parseInt(request.getParameter("cpt"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<User> users = userService.export(id, request);
        // System.out.println("userList "+customers);
        try {
            OutputStream out = response.getOutputStream();
            UserExport p = new UserExport(users,authentication.getName());
            p.build(request).toPdf(out);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (DRException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
