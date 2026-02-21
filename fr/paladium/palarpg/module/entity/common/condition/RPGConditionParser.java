package fr.paladium.palarpg.module.entity.common.condition;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class RPGConditionParser {
  public static boolean evaluate(IMolangParserHolder molangHolder, String condition) {
    try {
      return molangHolder.parseExpressionToBoolean(condition);
    } catch (Exception e) {
      throw new RuntimeException("An error occured for the condition " + condition, e);
    } 
  }
  
  public static boolean evaluates(IMolangParserHolder molangHolder, List<String> conditions) {
    AtomicBoolean result = new AtomicBoolean(true);
    conditions.forEach(conditon -> result.set((result.get() && evaluate(molangHolder, conditon))));
    return result.get();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\condition\RPGConditionParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */