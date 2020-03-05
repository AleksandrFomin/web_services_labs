package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {
    public List<Person> getPersons(String name, String surname, Integer age, String sex, String city) {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons p " +
                    "where (" + inQuotes(name) + " is NULL or " + inQuotes(name) + " =p.name) AND " +
                    "(" + inQuotes(surname) + " is NULL or " + inQuotes(surname) + " =p.surname) AND " +
                    "(" + age + " is NULL or " + age + " =p.age) AND " +
                    "(" + inQuotes(sex) + " is NULL or " + inQuotes(sex) + " =p.sex) AND " +
                    "(" + inQuotes(city) + " is NULL or " + inQuotes(city) + " =p.city)");

            while (rs.next()) {
                String resName = rs.getString("name");
                String resSurname = rs.getString("surname");
                int resAge = rs.getInt("age");
                String resSex = rs.getString("sex");
                String resCity = rs.getString("city");

                Person person = new Person(resName, resSurname, resAge, resSex, resCity);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    private String inQuotes(String o) {
        return o == null ? o : ("'" + o + "'");
    }
}
