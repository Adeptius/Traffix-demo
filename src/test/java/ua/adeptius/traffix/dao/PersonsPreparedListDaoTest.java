package ua.adeptius.traffix.dao;

import org.junit.Before;
import org.junit.Test;
import ua.adeptius.traffix.model.Person;

import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.*;

public class PersonsPreparedListDaoTest {

    PersonsPreparedListDao preparedListDao;

    @Before
    public void init() throws Exception {
        preparedListDao = new PersonsPreparedListDao();
        preparedListDao.init();
    }

    @Test
    public void getRandomMales() throws Exception {
        Set<Person> randomMales = preparedListDao.getRandomMales(100);
        testPersons(randomMales);

    }

    @Test
    public void getRandomFemales() throws Exception {
        Set<Person> randomFemales = preparedListDao.getRandomFemales(100);
        testPersons(randomFemales);
    }

    @Test
    public void getRandomFemale() throws Exception {
        for (int i = 0; i < 200; i++) {
            testPerson(preparedListDao.getRandomFemale());
        }
    }

    @Test
    public void getRandomMale() throws Exception {
        for (int i = 0; i < 200; i++) {
            testPerson(preparedListDao.getRandomMale());
        }
    }


    private void testPersons(Set<Person> persons) {
        HashMap<String, Integer> usedSurnames = new HashMap<>();

        for (Person person : persons) {
            testPerson(person);
            String surname = person.getSurname();
            Integer usedSurnameCount = usedSurnames.get(surname);
            if (usedSurnameCount == null){
                usedSurnameCount = 0;
            }
            if (usedSurnameCount > 1) {
                fail("Surname " + surname + " was used more than twice");
            }
            usedSurnames.put(surname, usedSurnameCount + 1);
        }
    }

    private void testPerson(Person person) {
        assertNotNull(person.getName());
        assertNotNull(person.getSurname());
        assertNotNull(person.getSity());
        assertNotNull(person.getJob());
    }
}