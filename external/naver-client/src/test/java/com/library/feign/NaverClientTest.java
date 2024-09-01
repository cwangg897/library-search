package com.library.feign;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;

// 여기모듈은 spring boot가 없음
@SpringBootTest(classes = NaverClientTest.TestConfiguration.class)
@ActiveProfiles("test")
class NaverClientTest {

    // feignclient는 빈이아니라서
    // @EnableFeignClients는 Spring의 자동 구성 기능을 활성화하여 Feign 클라이언트를 인식하고, 스프링 컨텍스트에 Feign 클라이언트를 빈으로 등록합니다.
    @EnableAutoConfiguration
    @EnableFeignClients(clients = NaverClient.class)
    static class TestConfiguration{ }

    @Autowired
    NaverClient naverClient;

    @Test
    public void callNaver() throws Exception {
        // given
        String http = naverClient.search("HTTP", 1, 10);
        System.out.println(http);
        Assertions.assertFalse(http.isEmpty());
    }
}