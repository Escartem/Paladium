package fr.paladium.palamod.modules.paladium.client.gui.palamenu.utils;

public class Article {
  private String textureUrl;
  
  private String name;
  
  private String description;
  
  private String url;
  
  public Article(String textureUrl, String name, String description, String url) {
    this.textureUrl = textureUrl;
    this.name = name;
    this.description = description;
    this.url = url;
  }
  
  public String toString() {
    return "Article(textureUrl=" + getTextureUrl() + ", name=" + getName() + ", description=" + getDescription() + ", url=" + getUrl() + ")";
  }
  
  public String getTextureUrl() {
    return this.textureUrl;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getUrl() {
    return this.url;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamen\\utils\Article.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */