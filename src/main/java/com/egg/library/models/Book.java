package com.egg.library.models;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "books")
@Data
@ToString(exclude = {"author", "editorial"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;

    private String title;
    private int year;
    @Column(name = "total_copies")
    private int totalCopies;
    @Column(name = "given_copies")
    private int givenCopies;

    @JoinColumn(name = "id_author")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Author author;

    @JoinColumn(name = "id_editorial")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Editorial editorial;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
