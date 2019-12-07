package renting.com.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import renting.com.dataTableResponse.ResponseData;
import renting.com.entities.Bien;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * Created by olivier on 02/10/2019.
 */
public interface BienService {
    public List<Bien> getAll();
    public Bien add(Bien bien);
    public Bien update(Bien bien);
    public Bien findById(int id);
    public void delete(int id);
    public int countBien();
    public ResponseData addBien(Bien bien, BindingResult result,MultipartFile file,MultipartFile[] multipartFiles,HttpServletRequest request);
    public List<Bien> export(int cpt, HttpServletRequest request);
    public ResponseData updateBien( Bien bien, int id, BindingResult result,MultipartFile file,MultipartFile[] multipartFiles,HttpServletRequest request);

    }
