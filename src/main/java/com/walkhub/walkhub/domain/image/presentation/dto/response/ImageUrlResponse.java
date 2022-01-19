package com.walkhub.walkhub.domain.image.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ImageUrlResponse {

    List<String> imageUrl;
}
