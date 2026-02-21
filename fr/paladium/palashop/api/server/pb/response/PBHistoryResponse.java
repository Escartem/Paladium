package fr.paladium.palashop.api.server.pb.response;

import fr.paladium.palashop.api.server.pb.request.PBMutationRequest;

public class PBHistoryResponse {
  private final String uuid;
  
  private final PBHistoryLog[] history;
  
  public PBHistoryResponse(String uuid, PBHistoryLog[] history) {
    this.uuid = uuid;
    this.history = history;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public PBHistoryLog[] getHistory() {
    return this.history;
  }
  
  public class PBHistoryLog implements Comparable<PBHistoryLog> {
    public String uuid;
    
    public String reason;
    
    public PBMutationRequest.MutationType mutation;
    
    public long seasonValue;
    
    public long permanentValue;
    
    public long beforeTotal;
    
    public long beforeSeason;
    
    public long beforePermanent;
    
    public long afterTotal;
    
    public long afterSeason;
    
    public long afterPermanent;
    
    public String date;
    
    public String origin;
    
    public String toString() {
      return "PBHistoryResponse.PBHistoryLog(uuid=" + getUuid() + ", reason=" + getReason() + ", mutation=" + getMutation() + ", seasonValue=" + getSeasonValue() + ", permanentValue=" + getPermanentValue() + ", beforeTotal=" + getBeforeTotal() + ", beforeSeason=" + getBeforeSeason() + ", beforePermanent=" + getBeforePermanent() + ", afterTotal=" + getAfterTotal() + ", afterSeason=" + getAfterSeason() + ", afterPermanent=" + getAfterPermanent() + ", date=" + getDate() + ", origin=" + getOrigin() + ")";
    }
    
    public PBHistoryLog() {}
    
    public PBHistoryLog(String uuid, String reason, PBMutationRequest.MutationType mutation, long seasonValue, long permanentValue, long beforeTotal, long beforeSeason, long beforePermanent, long afterTotal, long afterSeason, long afterPermanent, String date, String origin) {
      this.uuid = uuid;
      this.reason = reason;
      this.mutation = mutation;
      this.seasonValue = seasonValue;
      this.permanentValue = permanentValue;
      this.beforeTotal = beforeTotal;
      this.beforeSeason = beforeSeason;
      this.beforePermanent = beforePermanent;
      this.afterTotal = afterTotal;
      this.afterSeason = afterSeason;
      this.afterPermanent = afterPermanent;
      this.date = date;
      this.origin = origin;
    }
    
    public String getUuid() {
      return this.uuid;
    }
    
    public String getReason() {
      return this.reason;
    }
    
    public PBMutationRequest.MutationType getMutation() {
      return this.mutation;
    }
    
    public long getSeasonValue() {
      return this.seasonValue;
    }
    
    public long getPermanentValue() {
      return this.permanentValue;
    }
    
    public long getBeforeTotal() {
      return this.beforeTotal;
    }
    
    public long getBeforeSeason() {
      return this.beforeSeason;
    }
    
    public long getBeforePermanent() {
      return this.beforePermanent;
    }
    
    public long getAfterTotal() {
      return this.afterTotal;
    }
    
    public long getAfterSeason() {
      return this.afterSeason;
    }
    
    public long getAfterPermanent() {
      return this.afterPermanent;
    }
    
    public String getDate() {
      return this.date;
    }
    
    public String getOrigin() {
      return this.origin;
    }
    
    public int compareTo(PBHistoryLog o) {
      return this.date.compareTo(o.date);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\pb\response\PBHistoryResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */