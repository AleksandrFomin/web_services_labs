package main;

import generated.Person;
import generated.PersonService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        PersonService personService = new PersonService(url);

        long id, status;
        List<Person> persons;

        persons = personService.getPersonWebServicePort().getAllPersons();
        printPersons(persons);

        id = personService.getPersonWebServicePort().addPerson("name", "surname", 23, "male", "m");
        System.out.println("Insert Person. Id = " + id);
        persons = personService.getPersonWebServicePort().getAllPersons();
        printPersons(persons);

        status = personService.getPersonWebServicePort().updatePerson(id, "name" + id, "surname", 23, "female", "m");
        System.out.println("Update existent Person. Status = " + status);
        persons = personService.getPersonWebServicePort().getAllPersons();
        printPersons(persons);

        status = personService.getPersonWebServicePort().updatePerson((long) -1, "name", "surname" + id, 23, "male", "m");
        System.out.println("Update non-existent Person. Status = " + status);
        persons = personService.getPersonWebServicePort().getAllPersons();
        printPersons(persons);

        status = personService.getPersonWebServicePort().deletePerson(id);
        System.out.println("Delete existent Person. Status = " + status);
        persons = personService.getPersonWebServicePort().getAllPersons();
        printPersons(persons);

        status = personService.getPersonWebServicePort().deletePerson((long) -1);
        System.out.println("Delete non-existent Person. Status = " + status);
        persons = personService.getPersonWebServicePort().getAllPersons();
        printPersons(persons);
    }

    private static void printPersons(List<Person> persons) {
        for (Person person : persons) {
            System.out.println("name: " + person.getName() +
                    ", surname: " + person.getSurname() + ", age: " + person.getAge());
        }
        System.out.println("Total persons: " + persons.size());
    }
}
