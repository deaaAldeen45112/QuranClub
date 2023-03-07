package net.jordan.quran_club.service.notification;

public class Notification {

    private IMessage iMessage;

    public Notification(IMessage iMessage){

        this.iMessage=iMessage;

    }

    public void send(String message){
        iMessage.send(message);

    }

}
