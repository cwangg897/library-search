package com.library.feign;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverClientConfiguration {

    // 인터페이스라서 람다식으로 추상메서드 구현할 때 사용가능
    //  Java 8에서 도입된 기능으로, 간단한 메서드를 한 줄로 표현할 수 있게 해줍니다.
    @Bean
    public RequestInterceptor requestInterceptor(
        @Value("${external.naver.headers.client-id}") String clientId,
        @Value("${external.naver.headers.client-secret}") String clientSecret
    ) {
        // apply를 구현한거임
        return requestTemplate -> requestTemplate.header("X-Naver-Client-Id", clientId)
            .header("X-Naver-Client-Secret", clientSecret);
    }


    // 주입받는거
    @Bean
    public NaverErrorDecoder naverErrorDecoder(ObjectMapper objectMapper){
        return new NaverErrorDecoder(objectMapper);
    }

}
