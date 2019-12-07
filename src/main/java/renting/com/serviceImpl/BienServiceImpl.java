package renting.com.serviceImpl;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Bien;
import renting.com.entities.BienAttachment;
import renting.com.repositories.BienRepository;
import renting.com.service.BienAttachmentService;
import renting.com.service.BienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("bienService")
public class BienServiceImpl implements BienService {
    @Autowired
    private BienRepository bienRepository;

    @Autowired
    private BienAttachmentService bienAttachmentService;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bien> getAll() {
        return bienRepository.findAll();
    }

    @Override
    public Bien add(Bien bien) {
        return bienRepository.save(bien);
    }

    @Override
    public Bien update(Bien bien) {
        if(bien.getId() ==0){
            return bienRepository.save(bien);
        }
        return bienRepository.saveAndFlush(bien);
    }

    @Override
    public Bien findById(int id) {
        return bienRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        bienRepository.deleteById(id);
    }


    @Override
    public int countBien() {
        String sql="SELECT COUNT(DISTINCT b.id) AS nb FROM bien b";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }

    }


    @Override
    @Transactional
    public ResponseData addBien(Bien bien, BindingResult result, MultipartFile file, MultipartFile[] multipartFiles, HttpServletRequest request) {
        ResponseData json=null;
        String fileNames =null;
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (file.getSize() > 0) {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                bien.setImageName(fileName);
                bien.setImage(bytes);
            }

            bien.setCode(request.getParameter("code"));
            bien.setDesignation(request.getParameter("designation").toUpperCase());
            bien.setAcquisitionCost(Double.parseDouble(request.getParameter("cost")));
            bien.setCommentary(request.getParameter("commentary"));
            bien.setEtat(request.getParameter("etat"));
            bien.setBuyDate(sdf.parse(request.getParameter("buyDate")));
            bien.setTransport(Double.parseDouble(request.getParameter("transport")));
            bien.setStatus(true);
            Bien c = add(bien);
            if(c != null){
                if (multipartFiles != null && multipartFiles.length >0) {
                    for(int i =0 ;i< multipartFiles.length; i++){
                        BienAttachment bienAttachment = new BienAttachment();
                        try {
                            if(multipartFiles[i].getSize() > 0){
                                fileNames = multipartFiles[i].getOriginalFilename();
                                byte[] bytes = multipartFiles[i].getBytes();
                                bienAttachment.setBien(c);
                                bienAttachment.setFileAttachment(bytes);
                                bienAttachment.setNameFile(fileNames);
                                bienAttachmentService.addFile(bienAttachment);
                            }
                        } catch (Exception e) {
                            System.out.println("You failed to upload " + fileNames + ": " + e.getMessage());
                        }
                    }

                }
            }
            json = new ResponseData(true, c);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
            throw new UnsupportedOperationException();
        }
        return json;
    }


    @Override
    public List<Bien> export(int cpt, HttpServletRequest request) {
        List<Bien> list = new ArrayList<Bien>();
        Bien us = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            us = bienRepository.findById(Integer.parseInt(request.getParameter("keyid"+i)));
            us.setDateTransient(df.format(us.getDate()));
            list.add(us);
        }
        return list;
    }

    @Override
    @Transactional
    public ResponseData updateBien(Bien bien, int id, BindingResult result, MultipartFile file, MultipartFile[] multipartFiles, HttpServletRequest request) {
        ResponseData json=null;
        String fileNames ="";
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");


        try {
            Bien  b =findById(id);
            if(file.getSize()>0 && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                b.setImageName(fileName);
                b.setImage(bytes);
            }else{
                b.setImage(b.getImage());
                b.setImageName(b.getImageName());
            }

            b.setCode(request.getParameter("code"));
            b.setDesignation(request.getParameter("designation").toUpperCase());
            b.setAcquisitionCost(Double.parseDouble(request.getParameter("cost")));
            b.setCommentary(request.getParameter("commentary"));
            b.setEtat(request.getParameter("etat"));
            b.setBuyDate(sdf.parse(request.getParameter("buyDate")));
            b.setTransport(Double.parseDouble(request.getParameter("transport")));
            b.setStatus(b.isStatus());
            Bien c = update(b);


           /* if (multipartFiles != null && cptAttachment > 0) {

                System.out.println("cptAttachment "+cptAttachment);


                for(int i =0 ;i< cptAttachment; i++){
                    BienAttachment bienAttachment= new BienAttachment();

                    try {

                        if(request.getParameter("bienAttachmentName-"+i) != null){
                            if(request.getParameter("fileId-"+i) != null){
                                bienAttachmentService.delete(Integer.parseInt(request.getParameter("bienAttachment-"+i)));
                            }
                        }
                            if(multipartFiles[i].getSize() > 0){
                                fileNames = multipartFiles[i].getOriginalFilename();
                                byte[] bytes = multipartFiles[i].getBytes();
                                bienAttachment.setBien(c);
                                bienAttachment.setFileAttachment(bytes);
                                bienAttachment.setNameFile(fileNames);
                                if(request.getParameter("bienAttachment-"+i) != null){
                                    bienAttachment.setId(Integer.parseInt(request.getParameter("bienAttachment-"+i)));
                                }
                                bienAttachmentService.addFile(bienAttachment);
                            }
                        json = new ResponseData(true, c);


                    } catch (Exception e) {
                        System.out.println("You failed to upload " + fileNames + ": " + e.getMessage());
                    }
                }

            }else{*/

                json = new ResponseData(true, c);

            //}



            //json = new ResponseData(true, c);
        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
            throw new UnsupportedOperationException();
        }
        return json;
    }


}
