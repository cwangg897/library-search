package com.library.feign

import feign.RequestInterceptor
import feign.RequestTemplate
import spock.lang.Specification

class NaverClientConfigurationTest extends Specification {
    NaverClientConfiguration configuration;

    // 헤더값이 제대로 적용되는지

    void setup(){
        configuration = new NaverClientConfiguration();
    }

    def "requestInterceptor의 header에 key값들이 적용된다."(){
        given:
        def template = new RequestTemplate()
        def clientId = "id"
        def clientSecret = "secret";

        and: "interceptor를 타기전에 header가 존재하지 않는다."
        template.headers()["X-Naver-Client-Id"] == null
        template.headers()["X-Naver-Client-Secret"] == null

        when: "interceptor를 탄다"
        def interceptor = configuration.requestInterceptor(clientId, clientSecret)
        interceptor.apply(template) // 우리가 만든 interceptor에 template적용하면 그대로 나오는지

        then:
        template.headers()["X-Naver-Client-Id"].contains(clientId)
        template.headers()["X-Naver-Client-Secret"].contains(clientSecret)
    }


}
