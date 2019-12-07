/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package renting.com.entities;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.*;

/**
 *
 * @author olivier
 */
@Entity
@Table(name = "bien_attachment")
public class BienAttachment implements Serializable{
     
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   
   @Column(name = "name_file", unique = false, nullable = true)
   private String nameFile;

   @ManyToOne
   @JoinColumn(name="bien_id",nullable=false)
   private Bien bien;

   @Lob
   @Column(name = "file_attachment", unique = true, nullable = true,  length = 500000000)
   private byte[] fileAttachment;

    @Transient
    private String bienName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public byte[] getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(byte[] fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public String getBienName() {
        return bienName;
    }

    public void setBienName(String bienName) {
        this.bienName = bienName;
    }

    @Override
    public String toString() {
        return "BienAttachment{" +
                "id=" + id +
                ", nameFile='" + nameFile + '\'' +
                ", bien=" + bien +
                ", fileAttachment=" + Arrays.toString(fileAttachment) +
                ", bienName='" + bienName + '\'' +
                '}';
    }
}
