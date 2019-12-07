package renting.com.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
@Entity
@Table(name = "spent")
public class Spent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate = new Date();


    @Column(name = "commentary")
    private String commentary;


    @Transient
    private String action;
    @Transient
    private String checkboxe;

    @Transient
    private String dateTransient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCheckboxe() {
        return checkboxe;
    }

    public void setCheckboxe(String checkboxe) {
        this.checkboxe = checkboxe;
    }

    public String getDateTransient() {
        return dateTransient;
    }

    public void setDateTransient(String dateTransient) {
        this.dateTransient = dateTransient;
    }

    @Override
    public String toString() {
        return "Civility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
