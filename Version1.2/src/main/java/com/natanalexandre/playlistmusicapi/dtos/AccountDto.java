package com.natanalexandre.playlistmusicapi.dtos;

import jakarta.validation.constraints.NotBlank;

public class AccountDto {
    @NotBlank
    private String usernameAccount;
    @NotBlank
    private String emailAccount;
    @NotBlank
    private String numberAccount;
    @NotBlank
    private String passwordAccount;

    public AccountDto(String usernameAccount, String emailAccount, String numberAccount, String passwordAccount){
        this.usernameAccount = usernameAccount;
        this.emailAccount = emailAccount;
        this.numberAccount = numberAccount;
        this.passwordAccount = passwordAccount;
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

    public void setPasswordAccount(String passwordaccount) {
        this.passwordAccount = passwordaccount;
    }
}
