package com.bside.BSIDE.contents.domain;

import java.util.List;

import lombok.Data;

@Data
public class PagedResponse<T> {
	private List<T> content;
    private int page;
    private int size;
    private long totalElements;

    public PagedResponse(List<T> content, int page, int size, long totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
    }
}
