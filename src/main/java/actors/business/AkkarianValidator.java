package actors.business;

/**
 * Created by zua on 06/10/16.
 */
@FunctionalInterface
public interface AkkarianValidator<T> {
    boolean isValid(T value);
}
