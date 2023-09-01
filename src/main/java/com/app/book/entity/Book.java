package com.app.book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @Column(name = "PUBLISHED_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

}
