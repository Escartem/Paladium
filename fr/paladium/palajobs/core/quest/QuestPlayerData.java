package fr.paladium.palajobs.core.quest;

import fr.paladium.palajobs.api.quest.IQuestPlayerData;

public class QuestPlayerData implements IQuestPlayerData {
  private String questId;
  
  private int progress;
  
  private boolean completed;
  
  public void setQuestId(String questId) {
    this.questId = questId;
  }
  
  public void setProgress(int progress) {
    this.progress = progress;
  }
  
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
  
  public String getQuestId() {
    return this.questId;
  }
  
  public int getProgress() {
    return this.progress;
  }
  
  public boolean isCompleted() {
    return this.completed;
  }
  
  public QuestPlayerData(String questId, int progress, boolean completed) {
    this.questId = questId;
    this.progress = progress;
    this.completed = completed;
  }
  
  public QuestPlayerData(String questId) {
    this.questId = questId;
  }
  
  public void progress(int nb) {
    this.progress += nb;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.questId == null) ? 0 : this.questId.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    QuestPlayerData other = (QuestPlayerData)obj;
    if (this.questId == null) {
      if (other.questId != null)
        return false; 
    } else if (!this.questId.equals(other.questId)) {
      return false;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\quest\QuestPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */