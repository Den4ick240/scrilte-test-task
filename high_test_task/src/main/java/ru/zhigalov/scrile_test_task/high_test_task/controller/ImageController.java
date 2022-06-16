package ru.zhigalov.scrile_test_task.high_test_task.controller;


import io.swagger.api.ImageApi;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class ImageController implements ImageApi {

    @Override
    public ResponseEntity<Resource> imageImageIdGet(Long imageId) {
        return null;
    }

    @Override
    public ResponseEntity<String> uploadPicture(MultipartFile image) {
        return null;
    }
}
