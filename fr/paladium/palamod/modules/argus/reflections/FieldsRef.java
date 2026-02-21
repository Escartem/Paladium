package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class FieldsRef {
  public static Class<? extends Annotation>[] _a = new Class[] { Shadow.class, Final.class };
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\FieldsRef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */