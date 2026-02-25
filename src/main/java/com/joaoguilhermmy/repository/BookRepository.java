package com.joaoguilhermmy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaoguilhermmy.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
