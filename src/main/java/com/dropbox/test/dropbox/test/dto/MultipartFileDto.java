package com.dropbox.test.dropbox.test.dto;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileDto {
    private MultipartFile photo;

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
