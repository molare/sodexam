package renting.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.FileCopyUtils;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Bien;
import renting.com.entities.BienAttachment;
import renting.com.service.BienAttachmentService;
import renting.com.service.CityService;
import renting.com.service.BienService;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olivier on 02/10/2019.
 */
@RestController
public class BienController {

    @Autowired
    private BienService bienService;

    @Autowired
    private CityService cityService;

    @Autowired
    private BienAttachmentService bienAttachmentService;



    @RequestMapping(value = "/listBien", method = RequestMethod.GET)
    public ResponseData getAllBien(){
        List<Bien> newComList = new ArrayList<Bien>();
        List<Bien> listCom = bienService.getAll();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for(Bien co : listCom){
            Bien c = new Bien();
            c.setId(co.getId());
            c.setCode(co.getCode());
            c.setDesignation(co.getDesignation());
            c.setAcquisitionCost(co.getAcquisitionCost());
            c.setTransport(co.getTransport());
            c.setEtat(co.getEtat());
            c.setBuyDateTransient(sdf.format(co.getBuyDate()));

            c.setDate(co.getDate());
            c.setDateTransient(df.format(co.getDate()));
            String act="<td>\n" +
                    //"<button  class=\"btn btn-success btn-xs m-r-5\"  data-toggle=\"modal\" data-target=\"#editBienModal\" onclick=\"editBien("+c.getId()+") data-original-title=\"Edit\"><i class=\"fa fa-pencil font-14\"></i></button>\n"+
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editBienModal\" class=\"link-underlined margin-right-50 btn btn-success\" data-original-title=\"Editer\" onclick=\"editBien("+c.getId()+")\"><i class=\"fa fa-pencil font-14\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeBienModal\" class=\"link-underlined btn btn-danger\" data-original-title=\"Supprimer\" onclick=\"removeBien("+c.getId()+")\"><i class=\"fa fa-trash font-14\"><!-- --></i></a>\n" +
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
                String img = "<img style=\"width:60px\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
                c.setImageTransient(img);

                String checkboxes="<a href=\"javascript: void(0);\" class=\"btn btn-info\" data-toggle=\"modal\" data-target=\"#ModalBien"+c.getId()+"\"><i class=\"fa fa-eye\"></i></a>\n" +
                        "<!-- Modal -->\n" +
                        "  <div class=\"modal fade\" id=\"ModalBien"+c.getId()+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabelts\" aria-hidden=\"true\">\n" +
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

            if(!c.isStatus()){
                c.setStatusTransient("<td>\n" +
                        "<span class=\"badge badge-success\"><h7>Disponible</h7></span>\n" +
                        "  </td>");
            }else{

                c.setStatusTransient("<td>\n" +
                        "<span class=\"badge badge-danger\"><h7>Occup&eacute;e</h7></span>\n" +
                        "  </td>");
            }

          String  displayFile="<td>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#displayFileModal\" class=\"link-underlined margin-right-5\" onclick=\"displayFile("+c.getId()+")\"><i class=\"fa fa-file-image-o\"><!-- --></i></a>\n" +
                    "</td>";
            c.setDisplayFile(displayFile);
            newComList.add(c);
        }



        return new ResponseData(true,newComList);
    }


    @RequestMapping(value = "/saveBien", method = RequestMethod.POST,headers="Accept=*/*",produces="application/json;charset=UTF-8")
    public ResponseData addBien(Locale locale,@ModelAttribute Bien bien, BindingResult result,@RequestParam("picture")MultipartFile file,@RequestParam ( "file" )MultipartFile[] multipartFiles,HttpServletRequest request)throws Exception{
        ResponseData json=null;
        try {
           /* if (file.getSize() > 0) {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                bien.setImageName(fileName);
                bien.setImage(bytes);
            }

        bien.setDesignation(request.getParameter("code"));
        bien.setDesignation(request.getParameter("designation").toUpperCase());
        bien.setAcquisitionCost(Double.parseDouble(request.getParameter("cost")));
        bien.setCommentary(request.getParameter("commentary"));
        bien.setStatus(true);

         Bien c =  bienService.add(bien);
            json = new ResponseData(true, c);*/
            json = bienService.addBien(bien, result, file,multipartFiles, request);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/updateBien/{idBien}", method = RequestMethod.POST,headers="Accept=*/*",produces="application/json;charset=UTF-8")
    public ResponseData updateBien(Locale locale,@ModelAttribute Bien bien,@PathVariable int idBien, BindingResult result,@RequestParam("editPicture")MultipartFile file, @RequestParam ( "file" )MultipartFile[] multipartFiles,HttpServletRequest request){
        ResponseData json=null;
        try {
          /*Bien  b =bienService.findById(id);
            if(file.getSize()>0 && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                b.setImageName(fileName);
                b.setImage(bytes);
            }else{
                b.setImage(bienService.findById(id).getImage());
                b.setImageName(bienService.findById(id).getImageName());
            }

            b.setDesignation(request.getParameter("code"));
            b.setDesignation(request.getParameter("designation").toUpperCase());
            b.setAcquisitionCost(Double.parseDouble(request.getParameter("cost")));
            b.setCommentary(request.getParameter("commentary"));
            b.setStatus(bien.isStatus());
            Bien c =  bienService.update(bien);
            json = new ResponseData(true, c);*/
            json= bienService.updateBien(bien, idBien, result, file, multipartFiles, request);

            }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/findBien/{id}", method = RequestMethod.GET)
    public ResponseData findBien(Locale locale,@ModelAttribute Bien bien,@PathVariable int id, BindingResult result,HttpServletRequest request){
        Bien ci = bienService.findById(id);
        List<BienAttachment> bienAttachmentList = bienAttachmentService.findByBien(ci);
       // System.out.println("bienAttachmentList "+bienAttachmentList);

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
       return new ResponseData(true,ci, null, bienAttachmentList);
    }

    @RequestMapping(value = "/deleteBien/{typeId}", method = RequestMethod.DELETE)
    public ResponseData deleteBien(@PathVariable int typeId,HttpServletRequest request){
        ResponseData json=null;
        try {
        bienService.delete(typeId);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer cette donnée car elle est liée ailleurs",ex.getCause());
        }
        return json;
    }

    @RequestMapping(value = "/countBien", method = RequestMethod.POST)
    public ResponseData countBien(HttpServletRequest request){
        ResponseData json=null;
        try {
            int count = bienService.countBien();
            json = new ResponseData(true, count);
        }catch (Exception ex){
            json = new ResponseData(false,"erreur serveur",ex.getCause());
        }
        return json;
    }


    @RequestMapping(value = "/bien/download.do", method = RequestMethod.GET)
    public void dowload(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("bienAttachment_id"));

        try {
            BienAttachment harvestAttachment = bienAttachmentService.findById(id);
            //voir le fichier
            response.setHeader("Content-Disposition", "inline; filename=\""+ harvestAttachment.getNameFile()+"\"");
            //telechargement du fichier
           // response.setHeader("Content-Disposition", "attachment; filename=" + harvestAttachment.getNameFile());
            response.setContentType("application/pdf");
            response.setContentLength(harvestAttachment.getFileAttachment().length);
            FileCopyUtils.copy(harvestAttachment.getFileAttachment(), response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/bien/findBienAttachmentByBien/{id}",  method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseData findBienAttachmentByBien(@PathVariable int id, HttpServletRequest request){
        ResponseData json = null;
        try {
           Bien bien =bienService.findById(id);
            List<BienAttachment> bienAttachmentList = bienAttachmentService.findByBien(bien);
           if(!bienAttachmentList.isEmpty()){
                json = new ResponseData(true,bienAttachmentList.get(0).getBien(), bienAttachmentList);
            }else{
               json = new ResponseData(false,"erreur serveur");
           }

        }catch (Exception e) {
            System.out.println("Exception : ");
            json = new ResponseData(false,"erreur serveur",e.getMessage());
            System.out.println("Exception : "+e.getMessage());
        }

        return json;
    }

   /* @RequestMapping(value = "/bien/export", method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public void exportCustomer(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        response.setContentType("application/pdf");

        int id =Integer.parseInt(request.getParameter("cpt"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Bien> customers = bienService.export(id, request);
        // System.out.println("userList "+customers);
        try {
            OutputStream out = response.getOutputStream();
            BienReporting p = new BienReporting(customers,authentication.getName());
            p.build(request).toPdf(out);
        } catch (IOException ex) {
            Logger.getLogger(BienController.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (DRException ex) {
            Logger.getLogger(BienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
