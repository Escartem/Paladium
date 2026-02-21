package fr.paladium.palapass.common.pojo.quest;

public class QuestTier {
  private final int amount;
  
  private final int points;
  
  public QuestTier(int amount, int points) {
    this.amount = amount;
    this.points = points;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public int getPoints() {
    return this.points;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\pojo\quest\QuestTier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */