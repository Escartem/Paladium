package fr.paladium.palaforgeutils.lib.command.annotated.parser;

import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import fr.paladium.palaforgeutils.lib.validator.exception.impl.NotNullValidatorException;
import fr.paladium.palaforgeutils.lib.validator.impl.minecraft.EntityValidator;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.string.StringValidator;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public class CommandValidatorParser {
  private static final Map<Class<? extends ValidatorException>, String> EXCEPTIONS = new HashMap<>();
  
  static {
    register((Class)NotNullValidatorException.class, "la valeur ne doit pas être nulle");
    register((Class)NumberValidator.NumberValidatorRangeException.class, "la valeur doit être comprise entre {min} et {max}");
    register((Class)NumberValidator.NumberValidatorEqualsException.class, "la valeur doit être comprise dans la liste {values}");
    register((Class)NumberValidator.NumberValidatorGreaterException.class, "la valeur doit être supérieure à {greater}");
    register((Class)NumberValidator.NumberValidatorGreaterOrEqualsException.class, "la valeur doit être supérieure ou égale à {greater}");
    register((Class)NumberValidator.NumberValidatorLowerException.class, "la valeur doit être inférieure à {lower}");
    register((Class)NumberValidator.NumberValidatorLowerOrEqualsException.class, "la valeur doit être inférieure ou égale à {greater}");
    register((Class)NumberValidator.NumberValidatorModuloException.class, "la valeur doit être un multiple de {modulo}");
    register((Class)NumberValidator.NumberValidatorNegativeException.class, "la valeur doit être négative");
    register((Class)NumberValidator.NumberValidatorPositiveException.class, "la valeur doit être positive");
    register((Class)NumberValidator.NumberValidatorZeroException.class, "la valeur doit être égale à 0");
    register((Class)NumberValidator.NumberValidatorNotZeroException.class, "la valeur doit être différente de 0");
    register((Class)NumberValidator.NumberValidatorOddException.class, "la valeur doit être impaire");
    register((Class)NumberValidator.NumberValidatorEvenException.class, "la valeur doit être paire");
    register((Class)StringValidator.StringValidatorLengthException.class, "la longueur de la valeur doit être comprise entre {min} et {max}");
    register((Class)StringValidator.StringValidatorRegexException.class, "la valeur doit correspondre à l'expression régulière \"{regex}\"");
    register((Class)StringValidator.StringValidatorEmptyException.class, "la valeur doit être vide");
    register((Class)StringValidator.StringValidatorNotEmptyException.class, "la valeur ne doit pas être vide");
    register((Class)EntityValidator.EntityValidatorOnlineException.class, "le joueur doit être connecté");
    register((Class)EntityValidator.EntityValidatorHandEmptyException.class, "la main du joueur doit être vide");
    register((Class)EntityValidator.EntityValidatorHandNotEmptyException.class, "la main du joueur ne doit pas être vide");
  }
  
  public static void register(@NonNull Class<? extends ValidatorException> clazz, @NonNull String message) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    EXCEPTIONS.put(clazz, message);
  }
  
  public static String parse(@NonNull ValidatorException exception) {
    if (exception == null)
      throw new NullPointerException("exception is marked non-null but is null"); 
    if (EXCEPTIONS.containsKey(exception.getClass()))
      return exception.format(EXCEPTIONS.get(exception.getClass())); 
    return exception.format(exception.getRawMessage());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\CommandValidatorParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */