package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        addRelationship(eric, ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "123124");
        addRelationship(rod, noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        System.out.println("Started in bootstrap");
        System.out.println("Number of books: " + bookRepository.count());

        Publisher publisher = new Publisher("Key", "123 here", "Kansas City", "MO", "64133");
        publisherRepository.save(publisher);
        System.out.println("Publisher count: " + publisherRepository.count());
    }

    private void addRelationship(Author author, Book book) {
        author.getBooks().add(book);
        book.getAuthors().add(author);
    }
}
