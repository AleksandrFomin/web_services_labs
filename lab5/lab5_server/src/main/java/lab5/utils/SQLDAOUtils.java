package lab5.utils;


import lab5.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SQLDAOUtils {

    public static String getQuoted(String o) {
        return o == null ? o : ("'" + o + "'");
    }

    public static List<Person> resultSetToPersonList(ResultSet rs) throws SQLException {
        List<Person> persons = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String resName = rs.getString("name");
            String resSurname = rs.getString("surname");
            long resAge = rs.getLong("age");
            String resSex = rs.getString("sex");
            String resCity = rs.getString("city");

            Person person = new Person(id, resName, resSurname, resAge, resSex, resCity);
            persons.add(person);
        }
        return persons;
    }
}
