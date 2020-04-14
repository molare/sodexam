package renting.com.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
@Entity
@Table(name = "agent")
public class Agent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "matricule" , unique = true)
    private String matricule;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    @Email
    private String email;
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "start_job_date")
    private String startJobDate;


    @Column(name = "bool_member")
    private boolean boolMember;

    @Column(name="created_date", updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "civility_id")
    private Civility civility;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private Statut satut;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @Lob
    @Column(name="image", unique =true, nullable = true,length = 80000000)
    private byte[] image;

    @Column(name="image_name", nullable = true )
    private String imageName;

    @Transient
    private String imageTransient;

    @Transient
    private String dateTransient;

    @Transient
    private String civilityTransient;

    @Transient
    private String categoryTransient;

    @Transient
    private String statutTransient;

    @Transient
    private String directionTransient;

    @Transient
    private String fonctionTransient;

    @Transient
    private int age;

    @Transient
    private int numberYearJob;

    @Transient
    private String action;

    @Transient
    private String checkboxe;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public String getCheckboxe() {
        return checkboxe;
    }

    public void setCheckboxe(String checkboxe) {
        this.checkboxe = checkboxe;
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

    public String getCivilityTransient() {
        return civilityTransient;
    }

    public void setCivilityTransient(String civilityTransient) {
        this.civilityTransient = civilityTransient;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getStartJobDate() {
        return startJobDate;
    }

    public void setStartJobDate(String startJobDate) {
        this.startJobDate = startJobDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryTransient() {
        return categoryTransient;
    }

    public void setCategoryTransient(String categoryTransient) {
        this.categoryTransient = categoryTransient;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public String getFonctionTransient() {
        return fonctionTransient;
    }

    public void setFonctionTransient(String fonctionTransient) {
        this.fonctionTransient = fonctionTransient;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberYearJob() {
        return numberYearJob;
    }

    public void setNumberYearJob(int numberYearJob) {
        this.numberYearJob = numberYearJob;
    }

    public boolean isBoolMember() {
        return boolMember;
    }

    public void setBoolMember(boolean boolMember) {
        this.boolMember = boolMember;
    }

    public Statut getSatut() {
        return satut;
    }

    public void setSatut(Statut satut) {
        this.satut = satut;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getStatutTransient() {
        return statutTransient;
    }

    public void setStatutTransient(String statutTransient) {
        this.statutTransient = statutTransient;
    }

    public String getDirectionTransient() {
        return directionTransient;
    }

    public void setDirectionTransient(String directionTransient) {
        this.directionTransient = directionTransient;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                ", civility=" + civility +
                ", image=" + Arrays.toString(image) +
                ", imageName='" + imageName + '\'' +
                ", imageTransient='" + imageTransient + '\'' +
                ", dateTransient='" + dateTransient + '\'' +
                ", civilityTransient='" + civilityTransient + '\'' +
                ", action='" + action + '\'' +
                ", checkboxe='" + checkboxe + '\'' +
                '}';
    }
}
