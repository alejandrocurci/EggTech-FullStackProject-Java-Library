package com.egg.library.services;

import com.egg.library.models.Editorial;
import com.egg.library.repositories.EditorialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository repository;

    @Transactional
    public void createEditorial(String name) {
        Editorial editorial = new Editorial();
        editorial.setName(name);
        repository.save(editorial);
    }

    @Transactional(readOnly = true)
    public Editorial findEditorial(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public List<Editorial> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void modifyName(Long id, String name) {
        Editorial editorial = repository.findById(id).orElse(null);
        if (editorial != null) {
            editorial.setName(name);
            repository.save(editorial);
        }
    }

    @Transactional
    public void deleteEditorial(Long id) {
        repository.deleteById(id);
    }
}
