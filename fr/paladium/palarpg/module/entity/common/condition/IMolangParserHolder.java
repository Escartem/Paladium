package fr.paladium.palarpg.module.entity.common.condition;

import com.eliotlash.mclib.math.IValue;
import com.eliotlash.mclib.math.Variable;
import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import lombok.NonNull;

public interface IMolangParserHolder {
  @NonNull
  MolangParser getParser();
  
  void registerMolangVariables(@NonNull String... names) {
    if (names == null)
      throw new NullPointerException("names is marked non-null but is null"); 
    for (String name : names)
      getParser().register(new Variable(name, 0.0D)); 
  }
  
  default void setMolangVariable(@NonNull String name, double value) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    getParser().setValue(name, value);
  }
  
  @NonNull
  default IValue parse(@NonNull String expression) throws Exception {
    if (expression == null)
      throw new NullPointerException("expression is marked non-null but is null"); 
    return getParser().parse(expression);
  }
  
  default double parseToDouble(@NonNull String expression) throws Exception {
    if (expression == null)
      throw new NullPointerException("expression is marked non-null but is null"); 
    return getParser().parse(expression).get();
  }
  
  default boolean parseToBoolean(@NonNull String expression) throws Exception {
    if (expression == null)
      throw new NullPointerException("expression is marked non-null but is null"); 
    return (getParser().parse(expression).get() == 1.0D);
  }
  
  @NonNull
  default MolangExpression parseExpression(@NonNull String expression) throws MolangException {
    if (expression == null)
      throw new NullPointerException("expression is marked non-null but is null"); 
    return getParser().parseExpression(expression);
  }
  
  default double parseExpressionToDouble(@NonNull String expression) throws MolangException {
    if (expression == null)
      throw new NullPointerException("expression is marked non-null but is null"); 
    return getParser().parseExpression(expression).get();
  }
  
  default boolean parseExpressionToBoolean(@NonNull String expression) throws MolangException {
    if (expression == null)
      throw new NullPointerException("expression is marked non-null but is null"); 
    return (getParser().parseExpression(expression).get() == 1.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\condition\IMolangParserHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */