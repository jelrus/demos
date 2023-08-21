package epam.com.esm.exception.types;

public class EntityAlreadyExists extends RuntimeException{

    public EntityAlreadyExists(String message) {
        super(message);
    }
}
