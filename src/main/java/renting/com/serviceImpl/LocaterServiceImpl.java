package renting.com.serviceImpl;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.CustomerBien;
import renting.com.entities.Locater;
import renting.com.repositories.LocaterRepository;
import renting.com.service.BienService;
import renting.com.service.CivilityService;
import renting.com.service.CustomerBienService;
import renting.com.service.LocaterService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 09/10/2019.
 */
@Service("locaterService")
public class LocaterServiceImpl implements LocaterService {

    @Autowired
    private LocaterRepository locaterRepository;

    @Autowired
    private CivilityService civilityService;

    @Autowired
    private CustomerBienService customerBienService;

    @Autowired
    private BienService bienService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Locater> getAll() {
        return locaterRepository.findAll();
    }



    @Override
    public Locater add(Locater locater) {
        return locaterRepository.save(locater);
    }

    @Override
    public Locater update(Locater locater) {
        if(locater.getId()==0){
            return locaterRepository.save(locater);
        }
        return locaterRepository.saveAndFlush(locater);
    }

    @Override
    public Locater findById(int id) {
        return locaterRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        locaterRepository.deleteById(id);
    }

    @Override
    public int countLocater() {
        String sql="SELECT COUNT(DISTINCT l.id) AS nb FROM locater l";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }
    }



    @Override
    public List<Locater> export(int cpt, HttpServletRequest request) {
        List<Locater> list = new ArrayList<Locater>();
        Locater us = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            us = locaterRepository.findById(Integer.parseInt(request.getParameter("keyid"+i)));

            us.setDateTransient(df.format(us.getDate()));
            list.add(us);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ResponseData addCustomer(Locater locater,String customerBien, BindingResult result, MultipartFile file, HttpServletRequest request)throws IOException{
        ResponseData json=null;
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");

        try {
            if(file.getSize()>0){
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                locater.setImageName(fileName);
                locater.setImage(bytes);
            }
            String code = request.getParameter("code");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String profession = request.getParameter("profession");
            String civility = request.getParameter("civility");
            String dwellingPlace = request.getParameter("dwellingPlace");
            locater.setDwellingPlace(dwellingPlace);
            locater.setCode(code);
            locater.setFirstName(firstName);
            locater.setLastName(lastName);
            if(email.isEmpty()){
                locater.setEmail(firstName+"."+lastName+"@gmail.com");
            }else {
                locater.setEmail(email);
            }

            locater.setPhone(phone);
            locater.setProfession(profession);
            locater.setCivility(civilityService.findById(Integer.parseInt(civility)));
            Locater p = add(locater);

            //champs dynamic
            if (p != null) {
                String[] tabProduct = customerBien.split(",");
                for(String val : tabProduct){

                    CustomerBien cusb = new CustomerBien();
                    cusb.setLocater(p);
                    cusb.setBien(bienService.findById(Integer.parseInt(val)));

                    if(request.getParameter("quantity"+val) != null){
                        cusb.setQuantity(Integer.parseInt(request.getParameter("quantity"+ val)));
                    }

                    if(request.getParameter("amount"+val) != null){
                        cusb.setAmount(Double.parseDouble(request.getParameter("amount"+ val)));
                    }
                    if(request.getParameter("payrollAmount"+val) != null){
                        cusb.setPayrollAmount(Double.parseDouble(request.getParameter("payrollAmount"+ val)));
                    }

                    if(request.getParameter("remainingAmount"+val) != null){
                        cusb.setRemainingAmount(Double.parseDouble(request.getParameter("remainingAmount"+ val)));
                    }

                    CustomerBien sd = customerBienService.add(cusb);

                }

                json = new ResponseData(true,p);
            }



        }catch (Exception ex){
            json = new ResponseData(false,"une valeur a &eacute;t&eacute; dupliqu&eacute;e ou erron&eacute;e",ex.getCause());
            throw new UnsupportedOperationException();


        }
        return json;
    }


    @Override
    public List<Locater> findAllLocater() {
        List<Locater> listLo = new ArrayList<Locater>();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy : HH:mm");
        String sql ="SELECT DISTINCT l.first_name AS firstname, l.last_name lastname, l.email AS email, l.phone AS phone, l.created_date AS date_location,\n" +
                "string_agg(b.designation||'='||lb.quantity,',')AS detail_quantity, \n" +
                "string_agg(b.designation||'='||lb.amount,',')AS detail_amount, \n" +
                "string_agg(b.designation||'='||lb.payroll_amount,',')AS detail_payroll,\n" +
                "string_agg(b.designation||'='||lb.remaining_amount,',')AS detail_remaining, l.id AS bienId               \n" +
                "FROM bien b, locater l, locater_bien lb \n" +
                "WHERE lb.bien_id = b.id\n" +
                "AND lb.locater_id = l.id\n" +
                "GROUP BY l.first_name, l.last_name, l.created_date, l.email, l.phone,l.id \n" +
                "ORDER BY l.created_date DESC";
        Query query = em.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();

        for(Object[] rs: results) {
            Locater locater = findById(Integer.parseInt(rs[9] + ""));
            locater.setFirstName(rs[0] + "");
            locater.setLastName(rs[1] + "");
            locater.setEmail(rs[2] + "");
            locater.setPhone(rs[3] + "");
            locater.setDateTransient(df.format(rs[4]));
            locater.setQuantityDetail(rs[5] + "");
            locater.setAmountDetail(rs[6] + "");
            locater.setPayrollDetail(rs[7] + "");
            locater.setRemainingDetail(rs[8] + "");
            locater.setId(Integer.parseInt(rs[9] + ""));


            String act = "<td>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#editLocaterModal\" class=\"link-underlined margin-right-50 btn btn-success\" onclick=\"editLocater(" + Integer.parseInt(rs[9] + "") + ")\"><i class=\"fa fa-edit\"><!-- --></i></a>\n" +
                    "	<a href=\"javascript: void(0);\" data-toggle=\"modal\" data-target=\"#removeLocaterModal\" class=\"link-underlined btn btn-danger\" onclick=\"removeLocater(" + Integer.parseInt(rs[9] + "")+ ")\"><i class=\"fa fa-trash-o\"><!-- --></i></a>\n" +
                    "</td>";
            locater.setAction(act);


            if (locater.getImage() != null) {
                byte[] encodeBase64 = Base64.encodeBase64(locater.getImage());
                String base64Encoded = null;
                try {
                    base64Encoded = new String(encodeBase64, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //String img = "<img width=\"64\" height=\"64\" alt=\"img\" src=\"data:image/jpeg;base64,"+base64Encoded+"\"/>";
                String img = "<img style=\"width:60px\" alt=\"img\" src=\"data:image/jpeg;base64," + base64Encoded + "\"/>";
                locater.setImageTransient(img);

                String checkboxes = "<a href=\"javascript: void(0);\" class=\"btn btn-info\" data-toggle=\"modal\" data-target=\"#Modal-lightbox" + locater.getId() + "\"><i class=\"fa fa-eye\"></i></a>\n" +
                        "<!-- Modal -->\n" +
                        "  <div class=\"modal fade\" id=\"Modal-lightbox" + locater.getId() + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n" +
                        " <div class=\"modal-dialog\" role=\"document\">\n" +
                        " <div class=\"modal-content\">\n" +
                        "  <div class=\"modal-body\">\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                        " <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        //" class="img img-fluid" <img src=\"..\\files\\assets\\images\\modal\\overflow.jpg\" alt=\"\" class=\"img img-fluid\">\n" +
                        "<img style=\"width:900px\" alt=\"img\" class=\"img img-fluid\" src=\"data:image/jpeg;base64," + base64Encoded + "\"/>\n" +
                        "  </div>\n" +
                        "  </div>\n" +
                        " </div>\n" +
                        " </div>";
                locater.setCheckboxe(checkboxes);


            }
            listLo.add(locater);
        }
        return listLo;
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ResponseData deleteLocater(int id) {
        ResponseData json;
        try {
            Locater locat = findById(id);
            List<CustomerBien> customerBienList = customerBienService.findByLocater(locat);
            for(CustomerBien cb : customerBienList){
                customerBienService.delete(cb.getId());
            }
            delete(id);
            json = new ResponseData(true, null);
        }catch (Exception ex){
            json = new ResponseData(false,"Impossible de supprimer car cette donn&eacute;e est utilis&eacute;e ailleurs",null);
            throw new UnsupportedOperationException();
        }
        return json;
    }

}
