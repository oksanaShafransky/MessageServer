package com.oksana.messageserver;

public abstract class Message {
    protected String messageBody;
    protected String messageSubject;
    protected String messageFrom;

    public abstract boolean sendMessage();
    public abstract void createMessage();

    @Override
    public String toString() {
        return "subject: " + messageSubject + ", body: " + messageBody + ", from: " + messageFrom;
    }
}
