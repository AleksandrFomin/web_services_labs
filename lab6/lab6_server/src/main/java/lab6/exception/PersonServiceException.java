package lab6.exception;

public class PersonServiceException extends Exception
{
    private static final long serialVersionUID = -6647544772732631047L;
    public static PersonServiceException DEFAULT_INSTANCE = new
            PersonServiceException("personName cannot be null or empty");

    public PersonServiceException(String message) {
        super(message);
    }
}