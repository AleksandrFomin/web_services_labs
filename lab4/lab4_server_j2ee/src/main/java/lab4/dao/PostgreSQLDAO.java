package lab4.dao;

import lab4.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lab4.utils.SQLDAOUtils.getQuoted;
import static lab4.utils.SQLDAOUtils.resultSetToPersonList;

public class PostgreSQLDAO {

    private Connection connection;

    public PostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String str = "select * from persons";
            System.out.println(str);
            ResultSet rs = stmt.executeQuery("select * from persons");

            persons = resultSetToPersonList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public List<Person> getPersons(String name, String surname, Integer age, String sex, String city) {
        List<Person> persons = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

            String str = "select * from persons p " +
                    "where (" + getQuoted(name) + " is NULL or " + getQuoted(name) + " =p.name) AND " +
                    "(" + getQuoted(surname) + " is NULL or " + getQuoted(surname) + " =p.surname) AND " +
                    "(" + age + " is NULL or " + age + " =p.age) AND " +
                    "(" + getQuoted(sex) + " is NULL or " + getQuoted(sex) + " =p.sex) AND " +
                    "(" + getQuoted(city) + " is NULL or " + getQuoted(city) + " =p.city)";
            System.out.println(str);

            ResultSet rs = stmt.executeQuery(
                    "select * from persons p " +
                            "where (" + getQuoted(name) + " is NULL or " + getQuoted(name) + " =p.name) AND " +
                            "(" + getQuoted(surname) + " is NULL or " + getQuoted(surname) + " =p.surname) AND " +
                            "(" + age + " is NULL or " + age + " =p.age) AND " +
                            "(" + getQuoted(sex) + " is NULL or " + getQuoted(sex) + " =p.sex) AND " +
                            "(" + getQuoted(city) + " is NULL or " + getQuoted(city) + " =p.city)");

            persons = resultSetToPersonList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }
}