package  renting.com.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
@Entity
@Table(name = "bien")
public class Bien implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "amortized_cost")
    private double amortizedCost;

    @Column(name = "acquisition_cost")
    private double acquisitionCost;

    @Column(name = "transport")
    private double transport;

    @Column(name = "etat")
    private String etat;

    @Column(name = "buy_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date buyDate;

    @Column(name = "statut")
    private boolean status;

    @Transient
    private String statusTransient;

    @Column(name="created_date", updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date = new Date();


    @Lob
    @Column(name="image", unique =true, nullable = true,length = 80000000)
    private byte[] image;

    @Column(name="image_name", nullable = true)
    private String imageName;

    @Column(name = "commentary")
    private String commentary;

    @Transient
    private String amortizedCostTransient;

    @Transient
    private String imageTransient;

    @Transient
    private String dateTransient;

    @Transient
    private String buyDateTransient;

    @Transient
    private String typeTransient;

    @Transient
    private String cityTransient;

    @Transient
    private String propertyTransient;

    @Transient
    private String action;

    @Transient
    private String checkboxe;

    @Transient
    private String communeTransient;

    @Transient
    private String  displayFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageTransient() {
        return imageTransient;
    }

    public void setImageTransient(String imageTransient) {
        this.imageTransient = imageTransient;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDateTransient() {
        return dateTransient;
    }

    public void setDateTransient(String dateTransient) {
        this.dateTransient = dateTransient;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public double getAcquisitionCost() {
        return acquisitionCost;
    }

    public void setAcquisitionCost(double acquisitionCost) {
        this.acquisitionCost = acquisitionCost;
    }


    public String getTypeTransient() {
        return typeTransient;
    }

    public void setTypeTransient(String typeTransient) {
        this.typeTransient = typeTransient;
    }

    public String getCityTransient() {
        return cityTransient;
    }

    public void setCityTransient(String cityTransient) {
        this.cityTransient = cityTransient;
    }

    public String getPropertyTransient() {
        return propertyTransient;
    }

    public void setPropertyTransient(String propertyTransient) {
        this.propertyTransient = propertyTransient;
    }


    public String getCheckboxe() {
        return checkboxe;
    }

    public void setCheckboxe(String checkboxe) {
        this.checkboxe = checkboxe;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getCommuneTransient() {
        return communeTransient;
    }

    public void setCommuneTransient(String communeTransient) {
        this.communeTransient = communeTransient;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusTransient() {
        return statusTransient;
    }

    public void setStatusTransient(String statusTransient) {
        this.statusTransient = statusTransient;
    }

    public double getAmortizedCost() {
        return amortizedCost;
    }

    public void setAmortizedCost(double amortizedCost) {
        this.amortizedCost = amortizedCost;
    }

    public String getAmortizedCostTransient() {
        return amortizedCostTransient;
    }

    public void setAmortizedCostTransient(String amortizedCostTransient) {
        this.amortizedCostTransient = amortizedCostTransient;
    }

    public String getDisplayFile() {
        return displayFile;
    }

    public void setDisplayFile(String displayFile) {
        this.displayFile = displayFile;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getBuyDateTransient() {
        return buyDateTransient;
    }

    public void setBuyDateTransient(String buyDateTransient) {
        this.buyDateTransient = buyDateTransient;
    }

    @Override
    public String toString() {
        return "Bien{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", designation='" + designation + '\'' +
                ", acquisitionCost=" + acquisitionCost +
                ", date=" + date +
                ", image=" + Arrays.toString(image) +
                ", imageName='" + imageName + '\'' +
                ", imageTransient='" + imageTransient + '\'' +
                ", dateTransient='" + dateTransient + '\'' +
                ", typeTransient='" + typeTransient + '\'' +
                ", cityTransient='" + cityTransient + '\'' +
                ", propertyTransient='" + propertyTransient + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
