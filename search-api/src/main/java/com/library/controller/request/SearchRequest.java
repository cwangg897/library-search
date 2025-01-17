package com.library.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@ToString
public class SearchRequest {

    @NotBlank(message = "입력은 비어 있을 수 없습니다.")
    @Size(max = 50, message = "입력은 최대 50자를 초과할 수 없습니다.",min = 1)
    private String query;

    @NotNull(message = "페이지 번호는 필수 입니다")
    @Min(value = 1, message = "페이지 번호는 1이상이야 합니다")
    @Max(value = 10000, message = "페이지 번호는 10000이하이어야 합니다")
    private Integer page;

    @NotNull(message = "페이지 사이즈는 필수입니다")
    @Min(value = 1, message = "페이지크기는 1이상여야 합니다")
    @Max(value = 50, message = "페이지크기는 50이하여야 합니다")
    private Integer size;
}
