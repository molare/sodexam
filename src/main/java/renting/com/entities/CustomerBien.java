package renting.com.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
@Entity
@Table(name = "locater_bien")
public class CustomerBien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate = new Date();

    @ManyToOne
    @JoinColumn(name = "locater_id")
    private Locater locater;

    @ManyToOne
    @JoinColumn(name = "bien_id")
    private Bien bien;

    @Column(name="quantity")
    private int quantity;

    @Column(name = "remaining_amount")
    private double remainingAmount;

    @Column(name = "payroll_amount")
    private double payrollAmount;

    @Column(name = "amount")
    private double amount;

    @Transient
    private String action;


    @Transient
    private String dateTransient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Locater getLocater() {
        return locater;
    }

    public void setLocater(Locater locater) {
        this.locater = locater;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public double getPayrollAmount() {
        return payrollAmount;
    }

    public void setPayrollAmount(double payrollAmount) {
        this.payrollAmount = payrollAmount;
    }
}
