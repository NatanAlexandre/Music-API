package com.natanalexandre.playlistmusicapi.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
public class AccountModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAccount;
    @Column(nullable = false, unique = true, length = 70)
    private String usernameAccount;
    @Column(nullable = false, unique = true, length = 80)
    private String emailAccount;
    @Column(nullable = false, length = 20)
    private String numberAccount;
    @Column(nullable = false, length = 70)
    private String passwordAccount;

    public UUID getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
    }

    public String getUsernameAccount() {
        return usernameAccount;
    }

    public void setUsernameAccount(String usernameAccount) {
        this.usernameAccount = usernameAccount;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }
}
