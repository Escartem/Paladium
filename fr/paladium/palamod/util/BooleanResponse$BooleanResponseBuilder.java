package fr.paladium.palamod.util;

public class BooleanResponseBuilder {
  private boolean success;
  
  private String message;
  
  public BooleanResponseBuilder success(boolean success) {
    this.success = success;
    return this;
  }
  
  public BooleanResponseBuilder message(String message) {
    this.message = message;
    return this;
  }
  
  public BooleanResponse build() {
    return new BooleanResponse(this.success, this.message);
  }
  
  public String toString() {
    return "BooleanResponse.BooleanResponseBuilder(success=" + this.success + ", message=" + this.message + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\BooleanResponse$BooleanResponseBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */