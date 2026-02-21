package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import java.util.List;

public class EnglishResponse {
  private String question;
  
  private List<String> responses;
  
  EnglishResponse(String question, List<String> responses) {
    this.question = question;
    this.responses = responses;
  }
  
  public static EnglishResponseBuilder builder() {
    return new EnglishResponseBuilder();
  }
  
  public static class EnglishResponseBuilder {
    private String question;
    
    private List<String> responses;
    
    public EnglishResponseBuilder question(String question) {
      this.question = question;
      return this;
    }
    
    public EnglishResponseBuilder responses(List<String> responses) {
      this.responses = responses;
      return this;
    }
    
    public EnglishResponse build() {
      return new EnglishResponse(this.question, this.responses);
    }
    
    public String toString() {
      return "EnglishResponse.EnglishResponseBuilder(question=" + this.question + ", responses=" + this.responses + ")";
    }
  }
  
  public String getQuestion() {
    return this.question;
  }
  
  public List<String> getResponses() {
    return this.responses;
  }
  
  public boolean isCorrect(String response) {
    boolean correct = false;
    for (String valid : this.responses) {
      if (valid.equalsIgnoreCase(response)) {
        correct = true;
        break;
      } 
    } 
    return correct;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\EnglishResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */