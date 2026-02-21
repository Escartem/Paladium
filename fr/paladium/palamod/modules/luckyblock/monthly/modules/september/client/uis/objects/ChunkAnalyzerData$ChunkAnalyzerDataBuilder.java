package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.objects;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;

public class ChunkAnalyzerDataBuilder {
  private JobType jobType;
  
  private ObjectiveValue objectiveValue;
  
  private int amount;
  
  public ChunkAnalyzerDataBuilder jobType(JobType jobType) {
    this.jobType = jobType;
    return this;
  }
  
  public ChunkAnalyzerDataBuilder objectiveValue(ObjectiveValue objectiveValue) {
    this.objectiveValue = objectiveValue;
    return this;
  }
  
  public ChunkAnalyzerDataBuilder amount(int amount) {
    this.amount = amount;
    return this;
  }
  
  public ChunkAnalyzerData build() {
    return new ChunkAnalyzerData(this.jobType, this.objectiveValue, this.amount);
  }
  
  public String toString() {
    return "ChunkAnalyzerData.ChunkAnalyzerDataBuilder(jobType=" + this.jobType + ", objectiveValue=" + this.objectiveValue + ", amount=" + this.amount + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\objects\ChunkAnalyzerData$ChunkAnalyzerDataBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */