package com.egg.library.services;

import com.egg.library.models.Author;
import com.egg.library.repositories.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Transactional
    public void createAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        repository.save(author);
    }

    @Transactional(readOnly = true)
    public Author findAuthor(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void modifyName(Long id, String name) {
        Author author = repository.findById(id).orElse(null);
        if( author != null) {
            author.setName(name);
            repository.save(author);
        }
    }

    @Transactional
    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }
}
