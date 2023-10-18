package ru.kononenkoum.fororenapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kononenkoum.fororenapi.entity.Person;
import ru.kononenkoum.fororenapi.exception.PersonNotFoundException;
import ru.kononenkoum.fororenapi.model.PersonDataKeeper;

import java.util.List;
import java.util.Optional;

@Service
public class MainService {
    private final PersonDataKeeper personDataKeeper;

    @Autowired
    public MainService(PersonDataKeeper personDataKeeper) {
        this.personDataKeeper = personDataKeeper;
    }

    public Person getPerson(String name) {
        Optional<Person> optionalPerson = personDataKeeper.getPersonsData().stream().filter(person -> person.getName()
                .equals(name)).findFirst();
        if (optionalPerson.isPresent()){
            return optionalPerson.get();
        }
        else {
            throw new PersonNotFoundException();
        }
    }

    public List<Person> getPersonList() {
        return personDataKeeper.getPersonsData();
    }

    public void reset() {
        personDataKeeper.getPersonsData().clear();
        personDataKeeper.getPersonsData().addAll(personDataKeeper.getDefaultPersonsData());
    }

    public void addPerson(Person person) {
        personDataKeeper.getPersonsData().add(person);
    }
}
