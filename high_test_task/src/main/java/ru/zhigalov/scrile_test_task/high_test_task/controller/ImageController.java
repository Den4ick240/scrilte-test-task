package ru.zhigalov.scrile_test_task.high_test_task.controller;


import io.swagger.api.ImageApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import ru.zhigalov.scrile_test_task.high_test_task.model.ImageEntity;
import ru.zhigalov.scrile_test_task.high_test_task.repository.ImageRepository;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class ImageController implements ImageApi {

    @Autowired
    private final Environment environment;

    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<Resource> imageImageIdGet(String imageId) {
        var optionalImage = imageRepository.findById(imageId);
        if (optionalImage.isEmpty())
            return ResponseEntity.badRequest().build();

        byte[] content = optionalImage.get().getImageBytes();
        return ResponseEntity.ok(new ByteArrayResource(content));
    }

    @Override
    public ResponseEntity<String> uploadPicture(MultipartFile image) {
        final String host = environment.getProperty("HOST") + ":" + environment.getProperty("server.port");
        final String url = host + "/image/";

        try {
            var entity = imageRepository.save(new ImageEntity(null, image.getBytes()));
            var imageUrl = url + entity.getId();
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
