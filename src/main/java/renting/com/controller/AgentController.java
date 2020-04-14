package renting.com.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Category;
import renting.com.entities.Agent;
import renting.com.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
@RestController
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private FonctionService fonctionService;

    @Autowired
    private DirectionService directionService;

    @Autowired
    private StatutService statutService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CivilityService civilityService;
    


    @RequestMapping(value = "/listAgent", method = RequestMethod.GET)
    public ResponseData getAllAgent(){
        List<Agent> newComList = new ArrayList<Agent>();
        List<Agent> listCom = agentService.getAll();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date();
        String dateForm = sdf.format(d);
        String[] dateTab = dateForm.split("-");
        for(Agent co : listCom){
            Agent c = new Agent();
            c.setId(co.getId());
            c.setFirstName(co.getFirstName());
            c.setLastName(co.getLastName());
            c.setDate(co.getDate());
            c.setDateTransient(df.format(co.getDate()));
            c.setCivilityTransient(co.getCivility().getName());
            c.setEmail(co.getEmail());
            c.setPhone(co.getPhone());
            c.setMatricule(co.getMatricule());
            c.setStartJobDate(co.getStartJobDate());
            c.setBirthDate(co.getBirthDate());
            c.setFonctionTransient(co.getFonction().getName());
            c.setStatutTransient(co.getSatut().getName());
            c.setDirectionTransient(co.getDirection().getName());
            c.setCategoryTransient(co.getCategory().getName());
            String[] ageTab = co.getBirthDate().split("/");
            c.setAge(Integer.parseInt(dateTab[2])-Integer.parseInt(ageTab[2]));


            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editAgentModal\" onclick=\"editAgent("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editAgentModal\" class=\"link-underlined margin-right-50 btn btn-success btn-sm\" data-original-title=\"Editer\" onclick=\"editAgent("+c.getId()+")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeAgentModal\" class=\"link-underlined btn btn-danger btn-sm\" data-original-title=\"Supprimer\" onclick=\"removeAgent("+c.getId()+")\"><i class=\"fa fa-trash font-14\"><!-- --></i></a>\n" +
                    "</td>";
            c.setAction(act);
            /*String checkboxes ="<input name=\"select_id\" id=\"tabId\" value=\""+c.getId()+"\" type=\"checkbox\">";
            c.setCheckboxe(checkboxes);*/

            if(co.getImage() != null){
                byte[] encodeBase64 = Base64.encodeBase64(co.getImage());
                String base64Encoded = null;
                try {
                    base64Encoded = new String(encodeBase64, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //String img = "<img width=\"64\" height=\"64\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
                String img = "<img class=\"hvr-shrinks\" style=\"width:45px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
                c.setImageTransient(img);

                String checkboxes="<a href=\"javascript: void(0);\" class=\"btn btn-info\" data-toggle=\"modal\" data-target=\"#ModalAgent"+c.getId()+"\"><i class=\"fa fa-eye\"></i></a>\n" +
                        "<!-- Modal -->\n" +
                        "  <div class=\"modal fade\" id=\"ModalAgent"+c.getId()+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabelts\" aria-hidden=\"true\">\n" +
                        " <div class=\"modal-dialog\" role=\"document\">\n" +
                        " <div class=\"modal-content\">\n" +
                        "  <div class=\"modal-body\">\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                        " <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        //" class="img img-fluid" <img src=\"..\\files\\assets\\images\\modal\\overflow.jpg\" alt=\"\" class=\"img img-fluid\">\n" +
                        "<img style=\"width:900px\" alt=\"img\" class=\"img img-fluid\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>\n"+
                        "  </div>\n" +
                        "  </div>\n" +
                        " </div>\n" +
                        " </div>";
                c.setCheckboxe(checkboxes);
            }else{
                // String img = "<img class=\"user_picture_small\" style=\"width:200px\" alt=\"Image\" src=\"../assets/common/img/upload_img.png\">";
                c.setImageTransient(null);
                c.setCheckboxe(null);
            }
            


          /*String  displayFile="<td>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#displayFileModal\" class=\"link-underlined margin-right-5\" onclick=\"displayFile("+c.getId()+")\"><i class=\"fa fa-file-image-o\"><!-- --></i></a>\n" +
                    "</td>";
            c.setDisplayFile(displayFile);*/
            newComList.add(c);
        }



        return new ResponseData(true,newComList);
    }


    @RequestMapping(value = "/saveAgent", method = RequestMethod.POST,headers="Accept=*/*",produces="application/json;charset=UTF-8")
    public ResponseData addAgent(Locale locale,@ModelAttribute Agent agent, BindingResult result,@RequestParam("picture")MultipartFile file,HttpServletRequest request)throws Exception{
        ResponseData json=null;
        try {
            if (file.getSize() > 0) {
                String fileName = file.getOriginalFilename();
                System.out.println("name"+fileName);
                byte[] bytes = file.getBytes();
                agent.setImageName(fileName);
                agent.setImage(bytes);
            }
            agent.setMatricule(request.getParameter("matricule"));
            agent.setFirstName(request.getParameter("firstName"));
            agent.setLastName(request.getParameter("lastName"));
            agent.setEmail(request.getParameter("email"));
            agent.setCivility(civilityService.findById(Integer.parseInt(request.getParameter("civility"))));
            agent.setPhone(request.getParameter("phone"));
            agent.setFonction(fonctionService.findById(Integer.parseInt(request.getParameter("fonction"))));
            agent.setCategory(categoryService.findById(Integer.parseInt(request.getParameter("category"))));
            agent.setSatut(statutService.findById(Integer.parseInt(request.getParameter("statut"))));
            agent.setDirection(directionService.findById(Integer.parseInt(request.getParameter("direction"))));
            agent.setBirthDate(request.getParameter("birthDate"));
            agent.setStartJobDate(request.getParameter("startJobDate"));
            String mb = request.getParameter("member");
            if(Integer.parseInt(mb) ==1){
                agent.setBoolMember(true);
            }else{
                agent.setBoolMember(false);
            }
            Agent c = agentService.add(agent);
            json = new ResponseData(true, c);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getMessage());
        }
        return json;
    }

    @RequestMapping(value = "/updateAgent", method = RequestMethod.POST,headers="Accept=*/*",produces="application/json;charset=UTF-8")
    public ResponseData updateAgent(Locale locale,@ModelAttribute Agent pro, BindingResult result,@RequestParam("editPicture")MultipartFile file,HttpServletRequest request){
        ResponseData json=null;
        try {
            Agent agent = agentService.findById(Integer.parseInt(request.getParameter("id")));
            if(file.getSize()>0 && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                agent.setImageName(fileName);
                agent.setImage(bytes);
            }else{
                agent.setImage(agent.getImage());
                agent.setImageName(agent.getImageName());
            }

            agent.setFirstName(request.getParameter("firstName"));
            agent.setLastName(request.getParameter("lastName"));
            agent.setEmail(request.getParameter("email"));
            agent.setCivility(civilityService.findById(Integer.parseInt(request.getParameter("civility"))));
            agent.setPhone(request.getParameter("phone"));
            agent.setFonction(fonctionService.findById(Integer.parseInt(request.getParameter("fonction"))));
            agent.setCategory(categoryService.findById(Integer.parseInt(request.getParameter("category"))));
            agent.setSatut(statutService.findById(Integer.parseInt(request.getParameter("statut"))));
            agent.setDirection(directionService.findById(Integer.parseInt(request.getParameter("direction"))));
            agent.setBirthDate(request.getParameter("birthDate"));
            agent.setStartJobDate(request.getParameter("startJobDate"));
            String mb = request.getParameter("member");
            if(Integer.parseInt(mb) ==1){
                agent.setBoolMember(true);
            }else{
                agent.setBoolMember(false);
            }
            Agent c = agentService.update(agent);
            json = new ResponseData(true, c);
            }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/findAgent/{id}", method = RequestMethod.GET)
    public ResponseData findAgent(Locale locale,@ModelAttribute Agent agent,@PathVariable int id, BindingResult result,HttpServletRequest request){
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Agent ci = agentService.findById(id);

        if(ci.getImage() != null){
            byte[] encodeBase64 = Base64.encodeBase64(ci.getImage());
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //String img = "<img width=\"64\" height=\"64\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
            String img = "<img style=\"width:200px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
            ci.setImageTransient(img);
        }else{
            // String img = "<img class=\"user_picture_small\" style=\"width:200px\" alt=\"Image\" src=\"../assets/common/img/upload_img.png\">";
            ci.setImageTransient(null);
        }
       return new ResponseData(true,ci);
    }

    @RequestMapping(value = "/deleteAgent/{typeId}", method = RequestMethod.DELETE)
    public ResponseData deleteAgent(@PathVariable int typeId,HttpServletRequest request){
        ResponseData json=null;
        try{

      agentService.delete(typeId);
            json =new ResponseData(true,null);
    }catch (Exception ex){
        json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
    }
    return json;
    }

    @RequestMapping(value = "/countAgent", method = RequestMethod.POST)
    public ResponseData countAgent(HttpServletRequest request){
        ResponseData json=null;
        try {
            int count = 0;//agentService.countAgent();
            json = new ResponseData(true, count);
        }catch (Exception ex){
            json = new ResponseData(false,"erreur serveur",ex.getCause());
        }
        return json;
    }








   /* @RequestMapping(value = "/agent/export", method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public void exportCustomer(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        response.setContentType("application/pdf");

        int id =Integer.parseInt(request.getParameter("cpt"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Agent> customers = agentService.export(id, request);
        // System.out.println("userList "+customers);
        try {
            OutputStream out = response.getOutputStream();
            AgentReporting p = new AgentReporting(customers,authentication.getName());
            p.build(request).toPdf(out);
        } catch (IOException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (DRException ex) {
            Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
