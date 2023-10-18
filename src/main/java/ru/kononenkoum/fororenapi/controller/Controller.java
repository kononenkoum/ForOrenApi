package ru.kononenkoum.fororenapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kononenkoum.fororenapi.entity.Person;
import ru.kononenkoum.fororenapi.exception.PersonNotFoundException;
import ru.kononenkoum.fororenapi.service.MainService;

import java.util.List;

@Slf4j
@RestController
public class Controller {
    private final MainService mainService;

    @Autowired
    public Controller(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping(value = "/getPersonInfo")
    public ResponseEntity<Person> getPersonInfo(@RequestParam String name) {
        log.info("request with param name = " + name + " got");
        return new ResponseEntity<>(mainService.getPerson(name), HttpStatus.OK);
    }

    @GetMapping(value = "/getPersonList")
    public ResponseEntity<List<Person>> getPersonList() {
        log.info("getPersonList request got");
        return new ResponseEntity<>(mainService.getPersonList(), HttpStatus.OK);
    }

    /**
     * используется для эмуляции возврата тестовых данных к изначальному состоянию
     */
    @GetMapping(value = "/reset")
    public ResponseEntity<Boolean> resetPersonList() {
        log.info("reset request got");
        mainService.reset();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/addPerson")
    public ResponseEntity<Boolean> addPerson(@RequestBody Person person) {
        log.info("Person added:\n" + person);
        mainService.addPerson(person);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @ExceptionHandler
    public ResponseEntity<String> handleException(PersonNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("PersonNotFound", HttpStatus.OK);
    }
}
