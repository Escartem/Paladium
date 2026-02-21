package fr.paladium.palawither.common.wither.condition;

public class WitherConditionException extends RuntimeException {
  private final String message;
  
  private final Throwable cause;
  
  public String getMessage() {
    return this.message;
  }
  
  public Throwable getCause() {
    return this.cause;
  }
  
  public WitherConditionException(String message) {
    this(message, null);
  }
  
  public WitherConditionException(Throwable cause) {
    this(null, cause);
  }
  
  public WitherConditionException(String message, Throwable cause) {
    super((message == null) ? cause.getMessage() : message, cause);
    this.message = message;
    this.cause = cause;
  }
  
  public boolean isException() {
    return (this.cause != null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\IWitherCondition$WitherConditionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */