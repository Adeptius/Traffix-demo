package ua.adeptius.traffix.controllers;

import ua.adeptius.traffix.dao.PersonDao;
import ua.adeptius.traffix.dao.PersonsPreparedListDao;
import ua.adeptius.traffix.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonController {

    private static PersonDao personDao = new PersonsPreparedListDao();

    private static List<Person> currentList = new ArrayList<>();

    private static int listCount = 0;


    public static void init() throws Exception {
        personDao.init();
    }

    public static void updateList(int count) throws Exception {
        int countBackup = getListCount();
        try{
            setListCount(count);
            updateList();
        }catch (Exception e){
            // if new count invoking exception - return it from backup
            setListCount(countBackup);
            throw e;
        }
    }

     public static void updateList() throws Exception {
        init();
         currentList = personDao.getRandomMaleAndFemale(getListCount());
    }

    public static int getListCount() {
        return listCount;
    }

    public static void setListCount(int listCount) {
        PersonController.listCount = listCount;
    }


    public static List<Person> getCurrentList() {
        return currentList;
    }

    public static void setCurrentList(List<Person> currentList) {
        PersonController.currentList = currentList;
    }
}
