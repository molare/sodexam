package renting.com.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by olivier on 16/09/2019.
 */
@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username",unique = true, nullable = false)
    private String username;
    @Column(name="password",unique = true, nullable = false)
    private String password;
    @Column(name="active")
    private boolean active;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    @Transient
    private String roleTransient;

    @Transient
    private String dateTransient;
    @Transient
    private String action;

    @Transient
    private String activeTransient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRoleTransient() {
        return roleTransient;
    }

    public void setRoleTransient(String roleTransient) {
        this.roleTransient = roleTransient;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getActiveTransient() {
        return activeTransient;
    }

    public void setActiveTransient(String activeTransient) {
        this.activeTransient = activeTransient;
    }

    public String getDateTransient() {
        return dateTransient;
    }

    public void setDateTransient(String dateTransient) {
        this.dateTransient = dateTransient;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
