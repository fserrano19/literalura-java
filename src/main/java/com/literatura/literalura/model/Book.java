package com.literatura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String title;

    @Column(length = 50)
    private String language;

    private Integer downloadCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {}

    public Book(String title, String language, Integer downloadCount, Author author) {
        this.title = title;
        this.language = language;
        this.downloadCount = downloadCount;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "\nTitle: " + title +
                "\nAuthor: " + (author != null ? author.getName() : "Unknown") +
                "\nLanguage: " + language +
                "\nDownloads: " + downloadCount;
    }
}