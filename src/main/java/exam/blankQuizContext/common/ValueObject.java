package exam.blankQuizContext.common;

public interface ValueObject<T> {
    boolean sameValueAs(T other);
}
