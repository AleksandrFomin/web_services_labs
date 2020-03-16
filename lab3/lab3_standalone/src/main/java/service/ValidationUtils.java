package service;

import exception.PersonServiceException;
import exception.PersonServiceFault;

public class ValidationUtils {
    public static void validateParams(String name, String surname, Integer age,
                                      String sex, String city) throws PersonServiceException {
        if (name == null || name.isEmpty())
            throw new PersonServiceException("Invalid name!", PersonServiceFault.defaultInstance());
        if (surname == null || surname.isEmpty())
            throw new PersonServiceException("Invalid surname!", PersonServiceFault.defaultInstance());
        if (age == null || age <= 0)
            throw new PersonServiceException("Invalid age!", PersonServiceFault.defaultInstance());
        if (sex == null || sex.isEmpty())
            throw new PersonServiceException("Invalid sex!", PersonServiceFault.defaultInstance());
        if (city == null || city.isEmpty())
            throw new PersonServiceException("Invalid city!", PersonServiceFault.defaultInstance());
    }

    public static void validateId(Long id) throws PersonServiceException {
        if (id == null || id <= 0)
            throw new PersonServiceException("Invalid id!", PersonServiceFault.defaultInstance());
    }
}
