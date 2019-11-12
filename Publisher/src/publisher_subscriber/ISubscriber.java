package publisher_subscriber;

public interface ISubscriber<T> {
    void objectModified(T data);
}
