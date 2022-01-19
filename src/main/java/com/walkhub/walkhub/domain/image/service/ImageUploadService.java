package com.walkhub.walkhub.domain.image.service;

import com.walkhub.walkhub.domain.image.presentation.dto.response.ImageUrlResponse;
import com.walkhub.walkhub.infrastructure.image.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageUploadService {

    private final S3Facade s3Facade;

    public ImageUrlResponse execute(List<MultipartFile> files) {
        List<String> imageUrls = files.stream()
                .map(s3Facade::uploadImage)
                .collect(Collectors.toList());

        return ImageUrlResponse.builder()
                .imageUrl(imageUrls)
                .build();
    }
}
