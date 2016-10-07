package graphs.entities;

/**
 * Created by zua on 02.10.16.
 */
public final class BusinessRules {


    private BusinessRules(){}

    public static void validateTitle(String title) {
        if(title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid title");
        }
    }

    public static void validateDescription(String description) {
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Invalid description");
        }
    }
}
