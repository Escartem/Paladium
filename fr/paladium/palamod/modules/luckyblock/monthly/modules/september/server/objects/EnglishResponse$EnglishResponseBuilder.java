package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import java.util.List;

public class EnglishResponseBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\EnglishResponse$EnglishResponseBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */