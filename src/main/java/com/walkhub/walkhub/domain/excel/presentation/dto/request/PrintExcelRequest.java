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
    private LocalDate startAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endAt;

    @NotNull(message = "user_type은 Null일 수 없습니다.")
    private UserType userType;

    private Integer grade;

    private Integer classNum;

}
