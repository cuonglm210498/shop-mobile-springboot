package com.lecuong.controller.upload;

import com.lecuong.modal.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadImageController {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");

    @GetMapping("/image-user")
    public ResponseEntity<BaseResponse<String>> uploadImageUser(@RequestParam MultipartFile image) throws IOException {
        //Set up where to save photos
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images/user");

        //If the user does not select an image, return null
        if (image.isEmpty()) {
            return null;
        }

        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }

        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }

        //get url
        String urlImage = imagePath.resolve(image.getOriginalFilename()).toString();

        return ResponseEntity.ok(BaseResponse.ofSuccess(urlImage));
    }

    @GetMapping("/image-product")
    public ResponseEntity<BaseResponse<String>> uploadImageProduct(@RequestParam MultipartFile image) throws IOException {
        //Set up where to save photos
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images/products");

        //If the user does not select an image, return null
        if (image.isEmpty()) {
            return null;
        }

        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }

        Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }

        //get url
        String urlImage = imagePath.resolve(image.getOriginalFilename()).toString();

        return ResponseEntity.ok(BaseResponse.ofSuccess(urlImage));
    }
}
