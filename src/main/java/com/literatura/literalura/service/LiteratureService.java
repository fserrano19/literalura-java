package com.literatura.literalura.service;

import com.literatura.literalura.dto.BookData;
import com.literatura.literalura.dto.AuthorData;
import com.literatura.literalura.model.Author;
import com.literatura.literalura.model.Book;
import com.literatura.literalura.repository.AuthorRepository;
import com.literatura.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class LiteratureService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public LiteratureService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Book saveBookWithAuthor(BookData bookData) {

        Author author = null;

        if (bookData.getAuthors() != null && !bookData.getAuthors().isEmpty()) {
            AuthorData authorData = bookData.getAuthors().get(0);

            author = new Author(
                    authorData.getName(),
                    authorData.getBirthYear(),
                    authorData.getDeathYear()
            );

            author = authorRepository.save(author);
        }

        String language = null;
        if (bookData.getLanguages() != null && !bookData.getLanguages().isEmpty()) {
            language = bookData.getLanguages().get(0);
        }

        Book book = new Book(
                bookData.getTitle(),
                language,
                bookData.getDownloadCount(),
                author
        );

        return bookRepository.save(book);
    }
}