package ua.adeptius.traffix.dao;

import ua.adeptius.traffix.exceptions.NotEnoughSurnamesException;
import ua.adeptius.traffix.model.Person;

import java.util.List;
import java.util.Set;

public interface PersonDao {

    void init() throws Exception;

    Person getRandomMale() throws NotEnoughSurnamesException;

    Person getRandomFemale() throws NotEnoughSurnamesException;

    Set<Person> getRandomMales(int count) throws NotEnoughSurnamesException;

    Set<Person> getRandomFemales(int count) throws NotEnoughSurnamesException;

    List<Person> getRandomMaleAndFemale(int count) throws NotEnoughSurnamesException;

}
