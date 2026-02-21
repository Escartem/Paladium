package fr.paladium.palamod.util;

public class BooleanResponse {
  private boolean success;
  
  private String message;
  
  BooleanResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }
  
  public static BooleanResponseBuilder builder() {
    return new BooleanResponseBuilder();
  }
  
  public static class BooleanResponseBuilder {
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
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public boolean isSuccessful() {
    return this.success;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\BooleanResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */