package com.library.service;


import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.repository.BookRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookQueryService {

    @Qualifier("naverBookRepository")
    private final BookRepository naverBookRepository;

    @Qualifier("kakaoBookRepository")
    private final BookRepository kakaoBookRepository;

    @CircuitBreaker(name = "naverSearch", fallbackMethod = "searchFallBack")
    public PageResult<SearchResponse> search(String query, int page, int size) {
        return naverBookRepository.search(query, page, size);
    }

    public PageResult<SearchResponse> searchFallBack(String query, int page, int size, Throwable throwable){
        if(throwable instanceof CallNotPermittedException){
            return handleOpenCircuit(query, page, size);
        }
        return handleException(query, page, size, throwable);
    }

    // 에러가 섞이는 문제가 발생하기 때문에

    private PageResult<SearchResponse> handleOpenCircuit(String query, int page, int size) {
        log.warn("BookQueryService Circuit Breaker is open! Fallback to kakao search.");
        return kakaoBookRepository.search(query, page, size);
    }

    // 일반적인 에러
    private PageResult<SearchResponse> handleException(String query, int page, int size,
        Throwable throwable) {
        log.error("BookQueryService An error occurred! Fallback to kakao search. errorMessage={}", throwable.getMessage());
        return kakaoBookRepository.search(query, page, size);
    }
}
