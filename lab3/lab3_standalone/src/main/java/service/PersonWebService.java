package service;

import exception.PersonServiceException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

import static service.ValidationUtils.validateId;
import static service.ValidationUtils.validateParams;

@WebService(serviceName = "PersonService")
public class PersonWebService {
    @WebMethod(operationName = "getAllPersons")
    public List<Person> getAllPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getAllPersons();
    }

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(@WebParam(name = "name") String name,
                                   @WebParam(name = "surname") String surname,
                                   @WebParam(name = "age") Integer age,
                                   @WebParam(name = "sex") String sex,
                                   @WebParam(name = "city") String city) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons(name, surname, age, sex, city);
        return persons;
    }

    @WebMethod(operationName = "addPerson")
    public long addPerson(@WebParam(name = "name") String name,
                          @WebParam(name = "surname") String surname,
                          @WebParam(name = "age") Integer age,
                          @WebParam(name = "sex") String sex,
                          @WebParam(name = "city") String city) throws PersonServiceException {
        validateParams(name, surname, age, sex, city);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.addPerson(name, surname, age, sex, city);
    }

    @WebMethod(operationName = "updatePerson")
    public long updatePerson(@WebParam(name = "id") Long id,
                             @WebParam(name = "name") String name,
                             @WebParam(name = "surname") String surname,
                             @WebParam(name = "age") Integer age,
                             @WebParam(name = "sex") String sex,
                             @WebParam(name = "city") String city) throws PersonServiceException {
        validateId(id);
        validateParams(name, surname, age, sex, city);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updatePerson(id, name, surname, age, sex, city);
    }

    @WebMethod(operationName = "deletePerson")
    public long deletePerson(@WebParam(name = "id") Long id) throws PersonServiceException {
        validateId(id);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deletePerson(id);
    }
}