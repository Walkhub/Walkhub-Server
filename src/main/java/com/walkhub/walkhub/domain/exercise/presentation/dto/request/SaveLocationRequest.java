package com.walkhub.walkhub.domain.exercise.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class SaveLocationRequest {

    private List<LocationInfo> locationList;

    @Getter
    public static class LocationInfo {

        @NotNull(message = "sequence는 null, 공백을 허용하지 않습니다.")
        private Integer sequence;

        @NotNull(message = "latitude는 null, 공백을 허용하지 않습니다.")
        private BigDecimal latitude;

        @NotNull(message = "longitude는 null, 공백을 허용하지 않습니다.")
        private BigDecimal longitude;
    }

}
