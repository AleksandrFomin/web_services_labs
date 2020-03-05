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
        String name = "Владимир";
        String surname = "Иванов";
        Integer age = 26;
        String sex = "male";
        List<Person> persons = personService.getPersonWebServicePort().getPersons(null, null, age, null, null);
        for (Person person : persons) {
            System.out.println("name: " + person.getName() +
                    ", surname: " + person.getSurname() +
                    ", age: " + person.getAge() +
                    ", sex: " + person.getSex() +
                    ", city: " + person.getCity());
        }
        System.out.println("Total persons: " + persons.size());
    }
}
