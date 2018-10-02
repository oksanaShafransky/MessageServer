package com.oksana.messageserver;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageServer extends Thread {
    private BlockingQueue<Message> messageQueue;
    private static MessageServer messageServer;
    private AtomicBoolean stopSygnal;
    private MessageServer() {
        messageQueue = new LinkedBlockingQueue<Message>();
        stopSygnal = new AtomicBoolean(false);
    }

    public void run() {
        while(!stopSygnal.get()) {
            try {
                if (!messageQueue.isEmpty()) {
                    processMessages(messageQueue.take());
                } else {
                    System.out.println("No mails, going to sleep...");
                    sleep(5000);
                }
            } catch (Exception e) {

            }
        }
        stopMessageServer();
    }

    private void stopMessageServer() {


    }

    public static MessageServer getInstance() {
        synchronized (messageServer) {
            if (messageServer == null) {
                messageServer = new MessageServer();
                messageServer.run();
            }
        }
        return messageServer;
    }

    public void processMessages(Message msg) {
        //do something
        messageQueue.add(msg);
        System.out.println("Adding new message to queue: " + msg + ". Queue size is " + messageQueue.size());
    }
}
