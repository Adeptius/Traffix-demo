package ua.adeptius.traffix.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.adeptius.traffix.exceptions.NotEnoughSurnamesException;
import ua.adeptius.traffix.model.Gender;
import ua.adeptius.traffix.model.Person;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static ua.adeptius.traffix.model.Gender.FEMALE;
import static ua.adeptius.traffix.model.Gender.MALE;

public class PersonsPreparedListDao implements PersonDao {

    private static Logger LOGGER = LoggerFactory.getLogger(PersonsPreparedListDao.class.getSimpleName());

    private Random random = new Random();
    private List<String> jobs = null;
    private List<String> cities = null;
    private List<String> maleNames = null;
    private List<String> femaleNames = null;
    private List<String> maleSurnames = null;
    private List<String> femaleSurnames = null;
    private Set<String> surnamesThatUsedOnce = new HashSet<>();

    /**
     * Init needed for filling lists of names, surnames, etc..
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        cities = loadStringsFromFile("cities.list");
        jobs = loadStringsFromFile("jobs.list");
        maleNames = loadStringsFromFile("maleNames.list");
        femaleNames = loadStringsFromFile("femaleNames.list");
        maleSurnames = loadStringsFromFile("maleSurnames.list");
        femaleSurnames = loadStringsFromFile("femaleSurnames.list");
    }

    private List<String> loadStringsFromFile(String filename) throws Exception{
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        List<String> list = new ArrayList<>();
        while (reader.ready()){
            list.add(reader.readLine());
        }
        LOGGER.info("Loaded {} strings from file {}", list.size(), filename);
        return list;
    }

    /**
     * Generating persons with 60/40 ratio
     * @param count needed persons
     * @throws NotEnoughSurnamesException if surnames repeated more than twice
     */
    @Override
    public List<Person> getRandomMaleAndFemale(int count) throws NotEnoughSurnamesException {
        int maleCount = (int) (count * 0.6);
        int femaleCount = count - maleCount;
        LOGGER.info("Generating {} male and {} female persons", maleCount, femaleCount);
        List<Person> people = new ArrayList<>();
        people.addAll(getRandomMales(maleCount));
        people.addAll(getRandomFemales(femaleCount));
        return people;
    }

    @Override
    public Set<Person> getRandomMales(int count) throws NotEnoughSurnamesException {
        Set<Person> personSet = new HashSet<>();
        while (personSet.size() != count){
            personSet.add(getRandomMale());
        }
        return personSet;
    }

    @Override
    public Set<Person> getRandomFemales(int count) throws NotEnoughSurnamesException {
        Set<Person> personSet = new HashSet<>();
        while (personSet.size() != count){
            personSet.add(getRandomFemale());
        }
        return personSet;
    }

    @Override
    public Person getRandomFemale() throws NotEnoughSurnamesException {
        String name = getRandomString(femaleNames);
        String surname = getRandomSurname(femaleSurnames);
        return fillFullPerson(name, surname, FEMALE);
    }

    @Override
    public Person getRandomMale() throws NotEnoughSurnamesException {
        String name = getRandomString(maleNames);
        String surname = getRandomSurname(maleSurnames);
        return fillFullPerson(name, surname, MALE);
    }

    private Person fillFullPerson(String name, String surname, Gender gender) throws NotEnoughSurnamesException {
        Person person = new Person(name, surname,gender);
        person.setJob(getRandomString(jobs));
        person.setSity(getRandomString(cities));
        person.setSalary(random.nextInt(10000)+5000);
        return person;
    }

    private String getRandomSurname(List<String> strings) throws NotEnoughSurnamesException {
        if (strings.size()==0){
            LOGGER.info("Not enough strings for compete generating persons");
            throw new NotEnoughSurnamesException();
        }
        int rand = random.nextInt(strings.size());
        String foundedSurname = strings.get(rand);
        if (surnamesThatUsedOnce.contains(foundedSurname)){
            strings.remove(foundedSurname);
            return foundedSurname;
        }else {
            surnamesThatUsedOnce.add(foundedSurname);
            return foundedSurname;
        }
    }

    private String getRandomString(List<String> strings) throws NotEnoughSurnamesException {
        int rand = random.nextInt(strings.size() - 1);
        return strings.get(rand);
    }
}
