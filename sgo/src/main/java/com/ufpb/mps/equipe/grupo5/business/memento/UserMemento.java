package com.ufpb.mps.equipe.grupo5.business.memento;

import java.util.Date;

import com.ufpb.mps.equipe.grupo5.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserMemento implements Memento<User> {
    private final String cpf;
    private final String name;
    private final String email;
    private final String login;
    private final String password;
    private final String companyId;
    private final String sector;
    private final Date entryDate;
    private final String accessLevel;
    private final boolean isActive;

    public UserMemento(User user) {
        this.cpf = user.getCpf();
        this.name = user.getName();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.companyId = user.getCompanyId();
        this.sector = user.getSector();
        this.entryDate = new Date(user.getEntryDate().getTime());
        this.accessLevel = user.getAccessLevel();
        this.isActive = user.isActive();
    }

    @Override
    public User getState() {
        User user = new User();
        user.setCpf(this.cpf);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setCompanyId(this.companyId);
        user.setSector(this.sector);
        user.setEntryDate(new Date(this.entryDate.getTime()));
        user.setAccessLevel(this.accessLevel);
        user.setActive(this.isActive);
        return user;
    }
}
