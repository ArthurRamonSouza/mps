package com.ufpb.mps.equipe.grupo5.models;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserModel {
    @Email(message = "Lembre-se de inserir um e-mail válido.")
    @NotNull(message = "O e-mail não pode ser nulo.")
    @NotBlank(message = "O e-mail não pode estar em branco.")
    @Size(max = 255, message = "O e-mail deve ter no máximo 255 caracteres.")
    private String email;

    @NotNull(message = "O name não pode ser nulo.")
    @NotBlank(message = "O name não pode estar em branco.")
    @Size(max = 200, message = "O name deve ter no máximo 200 caracteres.")
    private String name;

    @NotNull(message = "A matrícula não pode ser nula.")
    @NotBlank(message = "A matrícula não pode estar em branco.")
    @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres.")
    @Pattern(regexp = "\\d{5,}", message = "Apenas números. Deve conter ao menos 5 dígitos.")
    private String matricula;

    private boolean isStaff;

    private boolean isActive;

    @NotNull(message = "A data de cadastro não pode ser nula.")
    private Date dateJoined;

    @NotNull(message = "O setor não pode ser nulo.")
    @NotBlank(message = "O setor não pode estar em branco.")
    @Size(max = 100, message = "O setor deve ter no máximo 100 caracteres.")
    private String setor;

    @NotNull(message = "O nível de acesso não pode ser nulo.")
    @NotBlank(message = "O nível de acesso não pode estar em branco.")
    @Size(max = 100, message = "O nível de acesso deve ter no máximo 100 caracteres.")
    private String accessLevel; // Enum para representar os níveis de acesso

    @NotNull(message = "O CPF não pode ser nulo.")
    @NotBlank(message = "O CPF não pode estar em branco.")
    @Size(max = 11, message = "O CPF deve ter no máximo 11 caracteres.")
    @Pattern(regexp = "\\d{11}", message = "Apenas números. Deve conter 11 dígitos.")
    private String cpf;

    public UserModel() {
    }

    public UserModel(String email, String name, String matricula, boolean isStaff, boolean isActive, Date dateJoined, String setor, String accessLevel, String cpf) {
        this.email = email;
        this.name = name;
        this.matricula = matricula;
        this.isStaff = isStaff;
        this.isActive = isActive;
        this.dateJoined = dateJoined;
        this.setor = setor;
        this.accessLevel = accessLevel;
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}