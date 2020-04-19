package lab6.dao;

import lab6.model.Person;
import lab6.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lab6.utils.SQLDAOUtils.getQuoted;
import static lab6.utils.SQLDAOUtils.resultSetToPersonList;

public class PostgreSQLDAO {

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");

            persons = resultSetToPersonList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public List<Person> getPersons(String name, String surname, Long age, String sex, String city) {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons p " +
                    "where (" + getQuoted(name) + " is NULL or " + getQuoted(name) + " =p.name) AND " +
                    "(" + getQuoted(surname) + " is NULL or " + getQuoted(surname) + " =p.surname) AND " +
                    "(" + age + " is NULL or " + age + " =p.age) AND " +
                    "(" + getQuoted(sex) + " is NULL or " + getQuoted(sex) + " =p.sex) AND " +
                    "(" + getQuoted(city) + " is NULL or " + getQuoted(city) + " =p.city)");

            resultSetToPersonList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public long addPerson(String name, String surname, Long age, String sex, String city) {
        long id = 0;
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "INSERT INTO persons (name, surname, age, sex, city) " +
                            "VALUES (" + getQuoted(name) + ", " + getQuoted(surname) + ", " + age + ", " +
                            getQuoted(sex) + ", " + getQuoted(city) + ") RETURNING id");
            if (rs.next()) {
                id = rs.getLong("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public long updatePerson(Long id, String name, String surname, Long age, String sex, String city) {
        long status = 500;
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "UPDATE persons SET name = " + getQuoted(name) + ", surname = " + getQuoted(surname) +
                            ", age = " + age + ", sex = " + getQuoted(sex) + ", city = " + getQuoted(city) +
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

    public long deletePerson(Long id) {
        int status = 500;
        try (Connection connection = ConnectionUtils.getConnection()) {
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
}
