package lab6.resource;

import lab6.dao.PostgreSQLDAO;
import lab6.exception.PersonServiceException;
import lab6.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static lab6.utils.ValidationUtils.validateId;
import static lab6.utils.ValidationUtils.validateParams;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersons(@QueryParam("name") String name,
                                   @QueryParam("surname") String surname,
                                   @QueryParam("age") Long age,
                                   @QueryParam("sex") String sex,
                                   @QueryParam("city") String city) throws PersonServiceException {
        validateParams(name, surname, age, sex, city);
        return new PostgreSQLDAO().getPersons(name, surname, age, sex, city);
    }

    @POST
    public Long addPerson(Person person) throws PersonServiceException {
        validateParams(person.getName(), person.getSurname(),
                person.getAge(), person.getSex(), person.getCity());
        return new PostgreSQLDAO().addPerson(person.getName(), person.getSurname(),
                person.getAge(), person.getSex(), person.getCity());
    }

    @PUT
    public Long updatePerson(Long id, Person person) throws PersonServiceException {
        validateId(id);
        validateParams(person.getName(), person.getSurname(),
                person.getAge(), person.getSex(), person.getCity());
        return new PostgreSQLDAO().updatePerson(id, person.getName(), person.getSurname(),
                person.getAge(), person.getSex(), person.getCity());
    }

    @DELETE
    public Long deletePerson(Long id) throws PersonServiceException {
        validateId(id);
        return new PostgreSQLDAO().deletePerson(id);
    }
}