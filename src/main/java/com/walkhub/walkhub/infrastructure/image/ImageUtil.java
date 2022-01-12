package com.walkhub.walkhub.infrastructure.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUtil {
    String uploadImage(MultipartFile image);
}
