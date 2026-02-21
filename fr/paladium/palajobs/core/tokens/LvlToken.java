package fr.paladium.palajobs.core.tokens;

import fr.paladium.palajobs.api.type.JobType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LvlToken {
  public String uuid;
  
  private int lvl;
  
  private JobType jobType;
  
  private List<String> pendingRewards;
  
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  
  public void setLvl(int lvl) {
    this.lvl = lvl;
  }
  
  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }
  
  public void setPendingRewards(List<String> pendingRewards) {
    this.pendingRewards = pendingRewards;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public int getLvl() {
    return this.lvl;
  }
  
  public JobType getJobType() {
    return this.jobType;
  }
  
  public List<String> getPendingRewards() {
    return this.pendingRewards;
  }
  
  public LvlToken(String uuid, int lvl, JobType jobType, List<String> pendingRewards) {
    this.uuid = uuid;
    this.lvl = lvl;
    this.jobType = jobType;
    this.pendingRewards = pendingRewards;
  }
  
  public LvlToken(int lvl, JobType jobType) {
    this.uuid = UUID.randomUUID().toString();
    this.lvl = lvl;
    this.jobType = jobType;
    this.pendingRewards = new ArrayList<>();
  }
  
  public LvlToken(String lvlTokenUUID) {
    this.uuid = lvlTokenUUID;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.uuid == null) ? 0 : this.uuid.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    LvlToken other = (LvlToken)obj;
    if (this.uuid == null) {
      if (other.uuid != null)
        return false; 
    } else if (!this.uuid.equals(other.uuid)) {
      return false;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\tokens\LvlToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */