package lab5.resource;

import lab5.model.Person;
import lab5.dao.PostgreSQLDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersons(@QueryParam("name") String name,
                                   @QueryParam("surname") String surname,
                                   @QueryParam("age") Long age,
                                   @QueryParam("sex") String sex,
                                   @QueryParam("city") String city) {
        return new PostgreSQLDAO().getPersons(name, surname, age, sex, city);
    }

    @POST
    public Long addPerson(Person person){
        return new PostgreSQLDAO().addPerson(person.getName(), person.getSurname(),
                person.getAge(), person.getSex(), person.getCity());
    }

    @PUT
    public Long updatePerson(Long id, Person person){
        return new PostgreSQLDAO().updatePerson(id, person.getName(), person.getSurname(),
                person.getAge(), person.getSex(), person.getCity());
    }

    @DELETE
    public Long deletePerson(Long id){
        return new PostgreSQLDAO().deletePerson(id);
    }
}