package com.dropbox.test.dropbox.test.dto.dropbox;

public class DropBoxInfoDto {
    private String email;
    private String accountId;
    private String country;
    private String profilePhotoUrl;
    private DropBoxNameDto nameInfo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public DropBoxNameDto getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(DropBoxNameDto nameInfo) {
        this.nameInfo = nameInfo;
    }
}
