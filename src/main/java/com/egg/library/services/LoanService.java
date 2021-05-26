package com.egg.library.services;

import com.egg.library.models.Book;
import com.egg.library.models.Loan;
import com.egg.library.repositories.BookRepository;
import com.egg.library.repositories.ClientRepository;
import com.egg.library.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
public class LoanService {

    // TODO add calendar input to dates in templates (instead of plain text)

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void createLoan(Long idClient, Long idBook) {
        Loan loan = new Loan();
        Book book = bookRepository.findById(idBook).orElse(null);
        book.setGivenCopies(book.getGivenCopies() + 1);
        loan.setBook(book);
        loan.setClient(clientRepository.findById(idClient).orElse(null));
        Calendar fromDate = Calendar.getInstance();
        loan.setStartDate(fromDate);
        Calendar toDate = Calendar.getInstance();
        toDate.add(Calendar.DATE, 7);
        loan.setReturnDate(toDate);
        loanRepository.save(loan);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Loan findLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Loan> findAllLoans() {
        return loanRepository.findAll();
    }

    @Transactional
    public void modify(Long id, Calendar returnDate) {
        Loan loan = loanRepository.findById(id).orElse(null);
        if(loan!=null) {
            loan.setReturnDate(returnDate);
            loanRepository.save(loan);
        }
    }

    @Transactional
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }



}
