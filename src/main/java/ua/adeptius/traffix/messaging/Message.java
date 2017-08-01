package ua.adeptius.traffix.messaging;

/**
 * Object just for messaging for web controller
 */
public class Message {

    public Message(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    private Status status;
    private String message;


    public enum Status {
        Success, Error
    }


    @Override
    public String toString() {
        return "{\"Status\":\""+status+"\",\"Message\":\""+message+"\"}";
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
