package fr.paladium.palaforgeutils.lib.command.annotated.parser.dto;

import java.util.Objects;

public class SubCommandScore implements Comparable<SubCommandEntry.SubCommandScore> {
  public static final SubCommandScore ZERO = new SubCommandScore(0, 0);
  
  private final int score;
  
  private final int maxScore;
  
  private final float percentage;
  
  public int getScore() {
    return this.score;
  }
  
  public int getMaxScore() {
    return this.maxScore;
  }
  
  public float getPercentage() {
    return this.percentage;
  }
  
  public SubCommandScore(int score, int maxScore) {
    this.score = score;
    this.maxScore = maxScore;
    this.percentage = this.score / this.maxScore;
  }
  
  public int compareTo(SubCommandScore o) {
    if (o == null)
      return 1; 
    if (this.percentage == o.percentage) {
      if (this.score == o.score)
        return Integer.compare(this.maxScore, o.maxScore); 
      return Integer.compare(this.score, o.score);
    } 
    return Float.compare(this.percentage, o.percentage);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.maxScore), Integer.valueOf(this.score) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof SubCommandScore))
      return false; 
    SubCommandScore other = (SubCommandScore)obj;
    return (this.maxScore == other.maxScore && this.score == other.score);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\dto\SubCommandEntry$SubCommandScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */