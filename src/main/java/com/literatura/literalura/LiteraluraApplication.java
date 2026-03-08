package com.literatura.literalura;

import com.literatura.literalura.client.ApiClient;
import com.literatura.literalura.dto.AuthorData;
import com.literatura.literalura.dto.BookData;
import com.literatura.literalura.dto.GutendexResponse;
import com.literatura.literalura.model.Author;
import com.literatura.literalura.model.Book;
import com.literatura.literalura.repository.AuthorRepository;
import com.literatura.literalura.repository.BookRepository;
import com.literatura.literalura.service.JsonParserService;
import com.literatura.literalura.service.LiteratureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	private final ApiClient client = new ApiClient();
	private final JsonParserService parser = new JsonParserService();
	private final LiteratureService literatureService;

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	private final Scanner scanner = new Scanner(System.in);

	public LiteraluraApplication(LiteratureService literatureService,
								 BookRepository bookRepository,
								 AuthorRepository authorRepository) {
		this.literatureService = literatureService;
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@Override
	public void run(String... args) {
		showMenu();
	}

	private void showMenu() {

		int option;

		do {
			System.out.println("""
                    
                    ===== LITERALURA =====
                    
                    1 - Search book by title
                    2 - List books
                    3 - List authors
                    4 - List authors alive in a given year
                    5 - List books by language
                    0 - Exit
                    
                    Choose an option:
                    """);

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1 -> searchBook();
				case 2 -> listBooks();
				case 3 -> listAuthors();
				case 4 -> listAuthorsByYear();
				case 5 -> listBooksByLanguage();
				case 0 -> System.out.println("Exiting...");
				default -> System.out.println("Invalid option.");
			}

		} while (option != 0);
	}

	private void searchBook() {

		System.out.println("Enter book title:");
		String title = scanner.nextLine();

		String url = "https://gutendex.com/books/?search=" + title.replace(" ", "%20");

		String json = client.fetchData(url);
		GutendexResponse response = parser.parseJson(json);

		if (response.getResults().isEmpty()) {
			System.out.println("Book not found.");
			return;
		}

		BookData bookData = response.getResults().get(0);

		Book saved = literatureService.saveBookWithAuthor(bookData);

		System.out.println("\nBook saved:");
		System.out.println(saved);
	}

	private void listBooks() {

		List<Book> books = bookRepository.findAll();

		if (books.isEmpty()) {
			System.out.println("No books in database.");
			return;
		}

		System.out.println("\n===== BOOK LIST =====");

		books.forEach(System.out::println);
	}

	private void listAuthors() {

		List<Author> authors = authorRepository.findAll();

		if (authors.isEmpty()) {
			System.out.println("No authors in database.");
			return;
		}

		System.out.println("\n===== AUTHORS =====");

		authors.forEach(System.out::println);
	}

	private void listAuthorsByYear() {

		System.out.println("Enter year:");
		int year = scanner.nextInt();
		scanner.nextLine();

		List<Author> authors = authorRepository
				.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);

		if (authors.isEmpty()) {
			System.out.println("No authors found.");
			return;
		}

		System.out.println("\nAuthors alive in " + year + ":");

		authors.forEach(System.out::println);
	}

	private void listBooksByLanguage() {

		System.out.println("Enter language code (e.g. en, es, fr):");
		String language = scanner.nextLine();

		List<Book> books = bookRepository.findByLanguage(language);

		System.out.println("\nBooks in language " + language + ":");

		books.forEach(System.out::println);

		System.out.println("Total: " + books.size());
	}
}