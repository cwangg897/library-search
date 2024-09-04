package com.library.service;

import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.entity.DailyStat;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookApplicationService {

    private final BookQueryService bookQueryService;
    private final DailyStatCommandService dailyStatCommandService;


    // 이거 트랜잭션 안걸어도 되나?
    public PageResult<SearchResponse> search(String query, int page, int size){
        // 외부 api 호출 -> 통계데이터 저장 -> api호출 값 응답
        PageResult<SearchResponse> response = bookQueryService.search(query, page, size);
        DailyStat dailyStat = new DailyStat(query, LocalDateTime.now());
        dailyStatCommandService.save(dailyStat);
        return response;
    }

}
