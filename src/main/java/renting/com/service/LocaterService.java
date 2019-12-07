package renting.com.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Locater;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by olivier on 09/10/2019.
 */
public interface LocaterService {
    public List<Locater> getAll();
    public List<Locater> findAllLocater();
    public Locater add(Locater locater);
    public Locater update(Locater locater);
    public Locater findById(int id);
    public void delete(int id);
    public int countLocater();
    ResponseData deleteLocater(int id);
    public List<Locater> export(int cpt, HttpServletRequest request);
    public ResponseData addCustomer( Locater locater,String customerBien,BindingResult result,MultipartFile file,HttpServletRequest request)throws IOException;


    }
