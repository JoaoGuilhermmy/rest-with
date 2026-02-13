package com.joaoguilhermmy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaoguilhermmy.data.dto.PersonDTO;
import com.joaoguilhermmy.exception.ResourceNotFoundExcpetion;
import com.joaoguilhermmy.mapper.ObjectMapper;
import com.joaoguilhermmy.model.Person;
import com.joaoguilhermmy.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public List<PersonDTO> findaAll() {
        logger.info("Finding all People!");
        return ObjectMapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Person not found with this ID."));
        return ObjectMapper.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO insert(PersonDTO person) {
        logger.info("Creating one Person!");
        var entity = ObjectMapper.parseObject(person, Person.class);
        return ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {

        logger.info("Updating one Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Person not found with this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person!");
        Person aux = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Person not found with this ID"));
        repository.delete(aux);
    }
}
