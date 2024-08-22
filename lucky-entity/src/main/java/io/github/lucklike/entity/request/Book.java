package io.github.lucklike.entity.request;

import lombok.Data;

@Data
public class Book {

    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private Double bookPrice;
}
