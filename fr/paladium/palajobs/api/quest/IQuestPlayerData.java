package fr.paladium.palajobs.api.quest;

public interface IQuestPlayerData {
  String getQuestId();
  
  boolean isCompleted();
  
  void setCompleted(boolean paramBoolean);
  
  void progress(int paramInt);
  
  void setProgress(int paramInt);
  
  int getProgress();
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\quest\IQuestPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */