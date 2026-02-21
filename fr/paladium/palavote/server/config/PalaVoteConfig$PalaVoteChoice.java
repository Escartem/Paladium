package fr.paladium.palavote.server.config;

import java.util.Objects;

public class PalaVoteChoice {
  private String name;
  
  private String description;
  
  private String image;
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public String toString() {
    return this.name;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.name });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    PalaVoteChoice other = (PalaVoteChoice)obj;
    return Objects.equals(this.name, other.name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\server\config\PalaVoteConfig$PalaVoteChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */