package fr.paladium.palaforgeutils.lib.validator.utils;

import fr.paladium.palaforgeutils.lib.validator.Validators;
import scala.reflect.internal.Positions;

public interface IValid {
  default void validate() throws Positions.ValidateException {
    Validators.validate(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validato\\utils\IValid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */