package com.dropbox.test.dropbox.test.service;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {
        private static final String DEFAULT_USER_FOLDER = "/users";
    private static final String DEFAULT_USER_AVATAR_FILE_NAME = "avatar";

    private final DbxClientV2 client;

    public PhotoService(DbxClientV2 client) {
        this.client = client;
    }

    public void changeUserPhoto(String userId, MultipartFile photo) throws IOException, DbxException {
        client.files()
                .uploadBuilder(DEFAULT_USER_FOLDER + "/" + userId + "/" +
                            DEFAULT_USER_AVATAR_FILE_NAME)
                .withMode(WriteMode.OVERWRITE)
                .uploadAndFinish(photo.getInputStream());
    }

    public byte[] getPhotoById(String userId) {
        try {
            DbxDownloader<FileMetadata> download = client.files()
                    .download(DEFAULT_USER_FOLDER + "/" + userId + "/" + DEFAULT_USER_AVATAR_FILE_NAME);
            return download.getInputStream().readAllBytes();
        } catch (DbxException | IOException e) {
            return new byte[0];
        }
    }

    /**
     * Проверка, есть ли у пользователя папка
     */
    private boolean checkFolder(String userId) {
        try {
            client.files().getMetadata(DEFAULT_USER_FOLDER + "/" + userId);
            return true;
        } catch (DbxException e) {
            return false;
        }
    }
}
