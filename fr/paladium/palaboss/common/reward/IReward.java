package fr.paladium.palaboss.common.reward;

import java.util.UUID;
import lombok.NonNull;

public interface IReward<T extends fr.paladium.palaboss.common.entity.EntityBossMob> {
  boolean canBeGiven(@NonNull UUID paramUUID, @NonNull T paramT, float paramFloat);
  
  void give(@NonNull UUID paramUUID, @NonNull T paramT);
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\reward\IReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */