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

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");

            persons = rsToList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

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

            rsToList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public long addPerson(String name, String surname, Integer age, String sex, String city) {
        long id = 0;
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "INSERT INTO persons (name, surname, age, sex, city) " +
                            "VALUES (" + inQuotes(name) + ", " + inQuotes(surname) + ", " + age + ", " +
                            inQuotes(sex) + ", " + inQuotes(city) + ") RETURNING id");
            if (rs.next()) {
                id = rs.getLong("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public int updatePerson(Long id, String name, String surname, Integer age, String sex, String city) {
        int status = 500;
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "UPDATE persons SET name = " + inQuotes(name) + ", surname = " + inQuotes(surname) +
                            ", age = " + age + ", sex = " + inQuotes(sex) + ", city = " + inQuotes(city) +
                            " WHERE id = " + id + " RETURNING id");
            if (rs.next()) {
                Long returnId = rs.getLong("id");
                if (returnId.equals(id))
                    status = 200;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public int deletePerson(Long id) {
        int status = 500;
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM persons WHERE id = " + id + " RETURNING id");
            if (rs.next()) {
                Long returnId = rs.getLong("id");
                if (returnId.equals(id))
                    status = 200;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    private List<Person> rsToList(ResultSet rs) throws SQLException {
        List<Person> persons = new ArrayList<>();
        while (rs.next()) {
            String resName = rs.getString("name");
            String resSurname = rs.getString("surname");
            int resAge = rs.getInt("age");
            String resSex = rs.getString("sex");
            String resCity = rs.getString("city");

            Person person = new Person(resName, resSurname, resAge, resSex, resCity);
            persons.add(person);
        }
        return persons;
    }

    private String inQuotes(String o) {
        return o == null ? o : ("'" + o + "'");
    }
}
