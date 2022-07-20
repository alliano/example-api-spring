package com.example.api.models.entities.tryning;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users_table")
public class Users implements Serializable {

    private static final long serialVersionUID = 5L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "name can't be empety")
    @Column(length = 255,nullable = false)
    private String name;

    @NotEmpty(message = "email can't be empety")
    @Column(unique = true)
    private String email;

    private boolean isAdmin;

    @NotEmpty(message = "password can't be empty")
    private String password;

    public Users(){}
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Users [email=" + email + ", id=" + id + ", isAdmin=" + isAdmin + ", name=" + name + ", password="
                + password + "]";
    }
    
    
}
