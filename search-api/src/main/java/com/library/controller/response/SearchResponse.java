package com.library.controller.response;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record SearchResponse(String title, String author, String publisher, LocalDate pubDate, String isbn) {

}
