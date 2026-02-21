package fr.paladium.palamod.mixins.forge;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;
import cpw.mods.fml.common.ModContainer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.mixin.transformer.Proxy;

@Mixin(value = {Loader.class}, remap = false)
public class MixinLoader {
  @Shadow
  private List<ModContainer> mods;
  
  @Shadow
  private ModClassLoader modClassLoader;
  
  @Inject(method = {"loadMods"}, at = {@At(value = "INVOKE", target = "Lcpw/mods/fml/common/LoadController;transition(Lcpw/mods/fml/common/LoaderState;Z)V", ordinal = 1)}, remap = false)
  private void beforeConstructingMods(CallbackInfo ci) {
    for (ModContainer mod : this.mods) {
      try {
        this.modClassLoader.addFile(mod.getSource());
      } catch (MalformedURLException e) {
        throw new RuntimeException(e);
      } 
    } 
    Mixins.addConfiguration("mixins.palamod-after.json");
    Proxy mixinProxy = Launch.classLoader.getTransformers().stream().filter(transformer -> transformer instanceof Proxy).findFirst().get();
    try {
      Field transformerField = Proxy.class.getDeclaredField("transformer");
      transformerField.setAccessible(true);
      MixinTransformer transformer = (MixinTransformer)transformerField.get(mixinProxy);
      Method selectConfigsMethod = MixinTransformer.class.getDeclaredMethod("selectConfigs", new Class[] { MixinEnvironment.class });
      selectConfigsMethod.setAccessible(true);
      selectConfigsMethod.invoke(transformer, new Object[] { MixinEnvironment.getCurrentEnvironment() });
      Method prepareConfigsMethod = MixinTransformer.class.getDeclaredMethod("prepareConfigs", new Class[] { MixinEnvironment.class });
      prepareConfigsMethod.setAccessible(true);
      prepareConfigsMethod.invoke(transformer, new Object[] { MixinEnvironment.getCurrentEnvironment() });
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\forge\MixinLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */