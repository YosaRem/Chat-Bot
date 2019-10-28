package publisher_subscriber;

public interface ISubscriber {
    void objectModified(String data);
    boolean isSubscriberReady();
}
