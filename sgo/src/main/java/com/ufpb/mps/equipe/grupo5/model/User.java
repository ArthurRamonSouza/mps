package com.ufpb.mps.equipe.grupo5.model;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor()
@NoArgsConstructor()
@Getter @Setter
@Entity
@Table(name="users")
public class User {

    @NotNull(message = "O CPF não pode ser nulo.")
    @NotBlank(message = "O CPF não pode estar em branco.")
    @Size(max = 11, message = "O CPF deve ter no máximo 11 caracteres.")
    @Pattern(regexp = "\\d{11}", message = "Apenas números. Deve conter 11 dígitos.")
    @Id
    private String cpf;

    @NotNull(message = "O nome não pode ser nulo.")
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 200, message = "O nome deve ter no máximo 200 caracteres.")
    private String name;

    @Email(message = "Lembre-se de inserir um e-mail válido.")
    @NotNull(message = "O e-mail não pode ser nulo.")
    @NotBlank(message = "O e-mail não pode estar em branco.")
    @Size(max = 255, message = "O e-mail deve ter no máximo 255 caracteres.")
    private String email;

    @NotNull(message = "A matrícula não pode ser nula.")
    @NotBlank(message = "A matrícula não pode estar em branco.")
    @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres.")
    @Pattern(regexp = "\\d{5,}", message = "Apenas números. Deve conter ao menos 5 dígitos.")
    @Column(name = "company_id")
    private String companyId;

    @NotNull(message = "O setor não pode ser nulo.")
    @NotBlank(message = "O setor não pode estar em branco.")
    @Size(max = 100, message = "O setor deve ter no máximo 100 caracteres.")
    private String sector;

    @NotNull(message = "A data de cadastro não pode ser nula.")
    @Column(name = "entry_date")
    private Date entryDate;

    @NotNull(message = "O nível de acesso não pode ser nulo.")
    @NotBlank(message = "O nível de acesso não pode estar em branco.")
    @Size(max = 100, message = "O nível de acesso deve ter no máximo 100 caracteres.")
    @Column(name = "access_level")
    private String accessLevel;

    @Column(name = "is_active")
    private boolean isActive;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", companyId='" + companyId + '\'' +
                ", sector='" + sector + '\'' +
                ", entryDate=" + entryDate +
                ", accessLevel='" + accessLevel + '\'' +
                ", isActive=" + isActive +
                '}';
    }

}
