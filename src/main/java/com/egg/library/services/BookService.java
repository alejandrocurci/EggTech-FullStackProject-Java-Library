package com.egg.library.services;

import com.egg.library.models.Book;
import com.egg.library.repositories.AuthorRepository;
import com.egg.library.repositories.BookRepository;
import com.egg.library.repositories.EditorialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void createBook(String title, int year, int totalCopies, Long idAuthor, Long idEditorial) {
        Book book = new Book();
        book.setAuthor(authorRepository.findById(idAuthor).orElse(null));
        book.setEditorial(editorialRepository.findById(idEditorial).orElse(null));
        book.setTitle(title);
        book.setYear(year);
        book.setTotalCopies(totalCopies);
        book.setGivenCopies(0);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public void modify(Long isbn, String title, int year, int totalCopies, 
            Long idAuthor, Long idEditorial) {
        Book book = bookRepository.findById(isbn).orElse(null);
        if (book != null) {
            book.setTitle(title);
            book.setYear(year);
            book.setTotalCopies(totalCopies);
            book.setAuthor(authorRepository.findById(idAuthor).orElse(null));
            book.setEditorial(editorialRepository.findById(idEditorial).orElse(null));
            bookRepository.save(book);
        }
    }

    @Transactional
    public void deleteBook(Long isbn) {
        bookRepository.deleteById(isbn);
    }

}
