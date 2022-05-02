package com.walkhub.walkhub.domain.excel.presentation.dto.request;

import com.walkhub.walkhub.domain.excel.domain.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PrintExcelRequest {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate startAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endAt;

    @NotNull(message = "user_type은 Null일 수 없습니다.")
    private final UserType userType;

    private final Integer grade;

    private final Integer classNum;
}
