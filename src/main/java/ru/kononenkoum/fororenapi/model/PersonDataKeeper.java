package ru.kononenkoum.fororenapi.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.kononenkoum.fororenapi.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class PersonDataKeeper {
    private List<Person> personsData;
    private final List<Person> defaultPersonsData;

    public PersonDataKeeper() {
        defaultPersonsData = new ArrayList<>();
        defaultPersonsData.add(new Person("Ivan", "+79863456545", "Some info about me. Ivan"));
        defaultPersonsData.add(new Person("Petr", "+79864558585", "Some info about me. Petr"));
        defaultPersonsData.add(new Person("Marry", "+79869598695", "Some info about. Marry"));
        defaultPersonsData.add(new Person("Gleb", "+79861258855", "Some info about me. Gleb"));
        defaultPersonsData.add(new Person("Alex", "+79861555522", "Some info about me. Alex"));
        personsData = new ArrayList<>();
        personsData.addAll(defaultPersonsData);
    }
}
