package net.jordan.quran_club.service.notification;

public abstract class IMessage {
   protected String from;
   protected String to;

   public String getFrom() {
      return from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getTo() {
      return to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   abstract protected void send(String message);
}
