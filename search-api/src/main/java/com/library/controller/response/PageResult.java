package com.library.controller.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public record PageResult<T>(int page, int size, int totalElements, List<T> items) {

}
