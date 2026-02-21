package fr.paladium.palamod.util;

class Footer {
  private String text;
  
  private String iconUrl;
  
  private Footer(String text, String iconUrl) {
    this.text = text;
    this.iconUrl = iconUrl;
  }
  
  private String getText() {
    return this.text;
  }
  
  private String getIconUrl() {
    return this.iconUrl;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\DiscordWebhook$EmbedObject$Footer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */