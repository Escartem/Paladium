package fr.paladium.palaforgeutils.lib.sidedaction.utils.cache;

public class SidedActionCacheResult {
  private final Object result;
  
  private final long timestamp;
  
  public Object getResult() {
    return this.result;
  }
  
  public long getTimestamp() {
    return this.timestamp;
  }
  
  public SidedActionCacheResult(Object result) {
    this.result = result;
    this.timestamp = System.currentTimeMillis();
  }
  
  public boolean isExpired(long timeout) {
    return (System.currentTimeMillis() - this.timestamp > timeout);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\cache\SidedActionCacheResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */