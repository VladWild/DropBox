package com.dropbox.test.dropbox.test.controller;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import com.dropbox.test.dropbox.test.dto.dropbox.DropBoxInfoDto;
import com.dropbox.test.dropbox.test.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Api(tags  = "Drop Box Test")
public class DropBoxTestController {

    private final ConversionService conversionService;
    private final PhotoService photoService;
    private final DbxClientV2 client;

    public DropBoxTestController(ConversionService conversionService,
                                 PhotoService photoService,
                                 DbxClientV2 client) {
        this.conversionService = conversionService;
        this.photoService = photoService;
        this.client = client;
    }

    @GetMapping("/drop-box-account-info")
    @ApiOperation("Получение инфы об аккаунте dropbox")
    public DropBoxInfoDto getDropBoxInfoDto() throws DbxException {
        FullAccount currentAccount = client.users().getCurrentAccount();
        return conversionService.convert(currentAccount, DropBoxInfoDto.class);
    }

    @PostMapping("/user/photo")
    @ApiOperation("Загрузка фотографии пользователя в dropbox")
    public ResponseEntity<HttpStatus> changeUserPhoto(
            @RequestParam("userId") String userId,
            @RequestParam("photo") MultipartFile photo) throws IOException, DbxException {
        photoService.changeUserPhoto(userId, photo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/user/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation("Получение фотографии по id пользователя")
    public byte[] getPhoto(@RequestParam String userId) {
        return photoService.getPhotoById(userId);
    }
}
