package com.walkhub.walkhub.domain.image.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ImageUrlResponse {

    List<String> imageUrl;
}
