package exception;

public class PersonServiceFault {

    private static final String DEFAULT_MESSAGE = "Person service error";
    private String message;

    public PersonServiceFault() {
    }

    public PersonServiceFault(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static PersonServiceFault defaultInstance() {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
