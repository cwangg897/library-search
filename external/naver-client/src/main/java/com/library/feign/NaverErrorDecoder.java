package com.library.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.ApiException;
import com.library.ErrorType;
import com.library.NaverErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class NaverErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public NaverErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        // 여기에 로직 적어두면 에러발생하면 여기서 처리하게 된다.
        try {
            //이 코드는 HTTP 응답의 본문을 InputStream으로 읽고, 이 스트림의 모든 바이트를 byte[] 배열로 변환한 후,
            // UTF-8 인코딩을 사용하여 이 바이트 배열을 문자열로 변환하는 작업을 수행합니다.
            String body = new String(response.body().asInputStream().readAllBytes(),
                StandardCharsets.UTF_8);
            NaverErrorResponse naverErrorResponse = objectMapper.readValue(body,
                NaverErrorResponse.class);// 여기서 이렇게 매핑하면 NaverErrorResponse이 나온다
            throw new ApiException(naverErrorResponse.getErrorMessage(), ErrorType.EXTERNAL_API_ERROR,
                HttpStatus.valueOf(response.status()));
        } catch (IOException e) {
            log.error("[Naver] 에러 메시지 파싱 에러 code={}, request={}, errorMessage={}",
                response.status(), response.request(), e.getMessage());
            throw new ApiException("네이버 메시지 파싱에러", ErrorType.EXTERNAL_API_ERROR,
                HttpStatus.valueOf(response.status()));
        }
    }
}
