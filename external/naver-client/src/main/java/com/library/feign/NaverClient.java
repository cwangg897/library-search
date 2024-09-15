package com.library.feign;

import com.library.NaverBookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// ErrorDecoder도 여기서 시작
@FeignClient(name = "naverClient", url = "${external.naver.url}", configuration = NaverClientConfiguration.class)
public interface NaverClient {

    @GetMapping("/v1/search/book.json")
    NaverBookResponse search(@RequestParam("query") String query,
        @RequestParam("start") int start,
        @RequestParam("display") int display);

}
