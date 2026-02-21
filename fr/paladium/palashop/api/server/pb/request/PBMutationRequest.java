package fr.paladium.palashop.api.server.pb.request;

public class PBMutationRequest {
  private final String uuid;
  
  private final String reason;
  
  private final String origin;
  
  private final MutationType mutation;
  
  private final long value;
  
  public String toString() {
    return "PBMutationRequest(uuid=" + getUuid() + ", reason=" + getReason() + ", origin=" + getOrigin() + ", mutation=" + getMutation() + ", value=" + getValue() + ")";
  }
  
  public PBMutationRequest(String uuid, String reason, String origin, MutationType mutation, long value) {
    this.uuid = uuid;
    this.reason = reason;
    this.origin = origin;
    this.mutation = mutation;
    this.value = value;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getReason() {
    return this.reason;
  }
  
  public String getOrigin() {
    return this.origin;
  }
  
  public MutationType getMutation() {
    return this.mutation;
  }
  
  public long getValue() {
    return this.value;
  }
  
  public enum MutationType {
    DECREMENT, SET_SEASON, SET_PERMANENT, INCREMENT_SEASON, INCREMENT_PERMANENT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\pb\request\PBMutationRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */