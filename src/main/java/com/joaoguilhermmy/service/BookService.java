package com.joaoguilhermmy.service;

import static com.joaoguilhermmy.exception.mapper.ObjectMapper.parseListObjects;
import static com.joaoguilhermmy.exception.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaoguilhermmy.controllers.BookController;
import com.joaoguilhermmy.data.dto.BookDTO;
import com.joaoguilhermmy.exception.RequiredObjectIsNullException;
import com.joaoguilhermmy.exception.ResourceNotFoundExcpetion;
import com.joaoguilhermmy.model.Book;
import com.joaoguilhermmy.repository.BookRepository;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll() {

        logger.info("Finding all People!");

        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("No records found for this ID!"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {

        if (book == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating one book!");
        var entity = parseObject(book, Book.class);

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);

        return dto;
    }

    public BookDTO update(BookDTO book) {

        if (book == null)
            throw new RequiredObjectIsNullException();

        logger.info("Updating one book!");
        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundExcpetion("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Deleting one book!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("No records found for this ID!"));
        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
