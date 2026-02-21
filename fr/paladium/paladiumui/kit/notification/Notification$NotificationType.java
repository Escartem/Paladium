package fr.paladium.paladiumui.kit.notification;

public enum NotificationType {
  SUCCESS("Succès"),
  ERROR("Erreur"),
  WARNING("Attention"),
  INFO("Info");
  
  NotificationType(String text) {
    this.text = text;
  }
  
  private final String text;
  
  public String getText() {
    return this.text;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\notification\Notification$NotificationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */