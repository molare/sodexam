package renting.com.service;

import renting.com.entities.Bien;
import renting.com.entities.BienAttachment;

import java.util.List;

/**
 * Created by olivier on 04/12/2019.
 */
public interface BienAttachmentService {
    public BienAttachment addFile(BienAttachment bienAttachment);
    public List<BienAttachment> getAll();
    public void updateFile(BienAttachment bienAttachment);
    public void delete(int id);
    public List<BienAttachment> findByBien( Bien bien);
    public BienAttachment findById(int id);
    public List<BienAttachment> findAllByBien(int bienId);
}
