package com.library.repository;

import com.library.Item;
import com.library.NaverBookResponse;
import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.feign.NaverClient;
import com.library.util.DateUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NaverBookRepository implements BookRepository{

    private final NaverClient naverClient;

    @Override
    public PageResult<SearchResponse> search(String query, int page, int size) {
        NaverBookResponse response = naverClient.search(query, page, size);
        List<SearchResponse> searchResponses = response.getItems().stream()
            .map(this::createResponse)
            .collect(Collectors.toList());
        return new PageResult<>(page, size, response.getTotal(),searchResponses);
    }

    private SearchResponse createResponse(Item item){
        return SearchResponse.builder()
            .title(item.getTitle())
            .author(item.getAuthor())
            .publisher(item.getPublisher())
            .pubDate(DateUtils.parseYYYYMMDD(item.getPudDate()))
            .isbn(item.getIsbn())
            .build();
    }
}