package com.walkhub.walkhub.domain.image.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ImageUrlResponse {

    private final List<String> imageUrl;
}
