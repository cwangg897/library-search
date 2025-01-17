package com.library.controller;

import com.library.controller.request.SearchRequest;
import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.controller.response.StatResponse;
import com.library.service.BookApplicationService;
import com.library.service.BookQueryService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookSearchController {

    private final BookApplicationService bookApplicationService;

    @GetMapping
    public PageResult<SearchResponse> search(@Valid SearchRequest request) {
        log.info("[BookController] search= {}", request);
        return bookApplicationService.search(request.getQuery(), request.getPage(),
            request.getSize());
    }

    @GetMapping("/stats")
    public StatResponse findQueryStats(@RequestParam("query") String query, @RequestParam("date")
    LocalDate date) {
        log.info("[BookController] find stats query = {} date= {}", query, date);
        return bookApplicationService.findQueryCount(query, date);
    }

    @GetMapping("/stats/ranking")
    public List<StatResponse> findTop5Stats(@RequestParam("query") String query){
        return  bookApplicationService.findTop5Query(query);
    }

}
