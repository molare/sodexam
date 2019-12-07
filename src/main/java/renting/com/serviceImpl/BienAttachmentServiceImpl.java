package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import renting.com.entities.Bien;
import renting.com.entities.BienAttachment;
import renting.com.repositories.BienAttachmentRepository;
import renting.com.repositories.BienRepository;
import renting.com.service.BienAttachmentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 04/12/2019.
 */
@Service("bienAttachmentService")
@Transactional
public class BienAttachmentServiceImpl implements BienAttachmentService {

    @Autowired
    private BienAttachmentRepository bienAttachmentRepository;

    @Autowired
    private BienAttachmentService bienAttachmentService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public BienAttachment addFile(BienAttachment bienAttachment) {
        return bienAttachmentRepository.save(bienAttachment);
    }

    @Override
    public List<BienAttachment> getAll() {
        return bienAttachmentRepository.findAll();
    }

    @Override
    public void updateFile(BienAttachment bienAttachment) {
        if(bienAttachment.getId() ==0){
          bienAttachmentRepository.save(bienAttachment);
        }
        bienAttachmentRepository.saveAndFlush(bienAttachment);

    }

    @Override
    public void delete(int id) {
        bienAttachmentRepository.deleteById(id);
    }

    @Override
    public List<BienAttachment> findByBien(Bien bien) {
        return bienAttachmentRepository.findByBien(bien);
    }

    @Override
    public BienAttachment findById(int id) {
        return bienAttachmentRepository.findById(id);
    }

    @Override
    public List<BienAttachment> findAllByBien(int bienId) {
        String sql ="select ba.name_file, ba.file_attachment, b.id, b.designation from bien_attachment ba, bien b\n" +
                "where ba.bien_id = b.id\n" +
                "AND b.id = "+bienId;

        Query query  = em.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        List<BienAttachment> list = new ArrayList<BienAttachment>();
        for(Object[] rs : results){
            BienAttachment ba = new BienAttachment();
            byte bytes = Byte.parseByte(rs[0]+"");
           // ba.setFileAttachment(bytes);
            ba.setNameFile(rs[0]+"");
            ba.setBienName(rs[3]+"");
            list.add(ba);
        }

        return list;
    }
}
