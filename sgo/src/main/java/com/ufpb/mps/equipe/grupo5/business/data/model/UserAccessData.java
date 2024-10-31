package com.ufpb.mps.equipe.grupo5.business.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "user_access_data")
public class UserAccessData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "access_count")
    private int accessCount;

    @NotNull
    @Column(name = "login_date", nullable = false)
    private Date loginDate;

    public UserAccessData(String userName, int accessCount, Date loginDate) {
        this.userName = userName;
        this.accessCount = accessCount;
        this.loginDate = loginDate;
    }

    public String getUserName() {
        return userName;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public Date getLoginDate() {
        return loginDate;
    }
}
