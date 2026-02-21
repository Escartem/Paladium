package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.Mod;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.mixin.Mixin;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class TypesRef {
  public static Class<? extends Annotation>[] _a = new Class[] { Mod.class, Mixin.class };
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\TypesRef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */