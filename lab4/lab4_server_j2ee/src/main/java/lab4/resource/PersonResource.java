package lab4.resource;

import lab4.dao.PostgreSQLDAO;
import lab4.model.Person;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {
    @Resource(lookup = "jdbc/lab1")
    private DataSource dataSource;

    @GET
    public List<Person> getPersons(@QueryParam("name") String name,
                                   @QueryParam("surname") String surname,
                                   @QueryParam("age") Integer age,
                                   @QueryParam("sex") String sex,
                                   @QueryParam("city") String city) {
        List<Person> persons = new PostgreSQLDAO(getConnection()).getPersons(name, surname, age, sex, city);
        return persons;
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}