package pl.studentmed.facultative.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public final String entityName;

    public EntityNotFoundException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

}
