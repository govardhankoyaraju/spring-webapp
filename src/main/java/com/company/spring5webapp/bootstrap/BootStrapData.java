package com.company.spring5webapp.bootstrap;

import com.company.spring5webapp.domain.Author;
import com.company.spring5webapp.domain.Book;
import com.company.spring5webapp.domain.Publisher;
import com.company.spring5webapp.repositories.AuthorRepository;
import com.company.spring5webapp.repositories.BookRepository;
import com.company.spring5webapp.repositories.PublisherRepository;
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
    public void run(String... args) {

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publisher");
        publisher.setCity("New York");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Started in BootStrap");
        System.out.println("Publisher Count" + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE", "3435466");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        noEjb.setPublisher(publisher);
        publisher.getBooks().add(noEjb);

        authorRepository.save(rod);
        bookRepository.save(noEjb);
        publisherRepository.save(publisher);


        System.out.println("Number of Books:" + bookRepository.count());
        System.out.println(publisher.getBooks().size());
    }
}
