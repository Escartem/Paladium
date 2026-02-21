package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class MethodsRef {
  public static Class<? extends Annotation>[] _a = new Class[] { Inject.class, Overwrite.class, Mod.EventHandler.class, SubscribeEvent.class, SidedProxy.class };
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\MethodsRef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */