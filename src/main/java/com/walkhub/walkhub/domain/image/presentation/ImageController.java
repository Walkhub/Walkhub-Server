package com.walkhub.walkhub.domain.image.presentation;

import com.walkhub.walkhub.domain.image.presentation.dto.response.ImageUrlResponse;
import com.walkhub.walkhub.domain.image.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/images")
@RestController
public class ImageController {

    private final ImageUploadService imageUploadService;

    @PostMapping
    public ImageUrlResponse saveImage(List<MultipartFile> images) {
        return imageUploadService.execute(images);
    }
}
