package fr.paladium.palashop.api.server.pb.dto;

import java.util.Objects;

public class PBData {
  private final long total;
  
  private final long season;
  
  private final long permanent;
  
  public PBData(long total, long season, long permanent) {
    this.total = total;
    this.season = season;
    this.permanent = permanent;
  }
  
  public long getTotal() {
    return this.total;
  }
  
  public long getSeason() {
    return this.season;
  }
  
  public long getPermanent() {
    return this.permanent;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Long.valueOf(this.permanent), Long.valueOf(this.season), Long.valueOf(this.total) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    PBData other = (PBData)obj;
    return (this.permanent == other.permanent && this.season == other.season && this.total == other.total);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\pb\dto\PBData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */