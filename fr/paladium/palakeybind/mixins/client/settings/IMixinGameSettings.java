package fr.paladium.palakeybind.mixins.client.settings;

import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GameSettings.class})
public class IMixinGameSettings {
  @Shadow
  public KeyBinding[] field_74324_K;
  
  @Shadow
  private File field_74354_ai;
  
  @Inject(method = {"setOptionKeyBinding"}, at = {@At("HEAD")})
  public void setOptionKeyBinding(KeyBinding key, int code, CallbackInfo ci) {
    if (key instanceof IKeyBinding) {
      IKeyBinding k = (IKeyBinding)key;
      k.setCtrl((Keyboard.isKeyDown(29) && code != 29));
      k.setRightCtrl((Keyboard.isKeyDown(157) && code != 157));
      k.setShift((Keyboard.isKeyDown(42) && code != 42));
      k.setRightShift((Keyboard.isKeyDown(54) && code != 54));
      k.setAlt((Keyboard.isKeyDown(56) && code != 56));
      k.setRightAlt((Keyboard.isKeyDown(184) && code != 184));
    } 
  }
  
  @Redirect(method = {"saveOptions"}, at = @At(value = "INVOKE", target = "Ljava/io/PrintWriter;close()V"))
  public void saveOptions(PrintWriter writer) {
    KeyBinding[] akeybinding = this.field_74324_K;
    int length = akeybinding.length;
    for (int index = 0; index < length; index++) {
      KeyBinding keybinding = akeybinding[index];
      if (keybinding instanceof IKeyBinding) {
        IKeyBinding k = (IKeyBinding)keybinding;
        writer.println("betterkey_" + keybinding.func_151464_g() + ":" + k.isAlt() + ":" + k.isCtrl() + ":" + k.isShift() + ":" + k.isRightAlt() + ":" + k.isRightCtrl() + ":" + k.isRightShift());
      } 
    } 
    writer.close();
  }
  
  @Redirect(method = {"loadOptions"}, at = @At(value = "INVOKE", target = "Ljava/io/BufferedReader;close()V"))
  public void loadOptions(BufferedReader reader) {
    try {
      reader.close();
      BufferedReader bufferedreader = new BufferedReader(new FileReader(this.field_74354_ai));
      String s = "";
      while ((s = bufferedreader.readLine()) != null) {
        String[] astring = s.split(":");
        KeyBinding[] akeybinding = this.field_74324_K;
        int i = akeybinding.length;
        for (int j = 0; j < i; j++) {
          KeyBinding keybinding = akeybinding[j];
          IKeyBinding k = (IKeyBinding)keybinding;
          if (keybinding instanceof IKeyBinding && ("betterkey_" + keybinding.func_151464_g()).equals(astring[0])) {
            k.setAlt(Boolean.parseBoolean(astring[1]));
            k.setCtrl(Boolean.parseBoolean(astring[2]));
            k.setShift(Boolean.parseBoolean(astring[3]));
            k.setRightAlt(Boolean.parseBoolean(astring[4]));
            k.setRightCtrl(Boolean.parseBoolean(astring[5]));
            k.setRightShift(Boolean.parseBoolean(astring[6]));
          } 
        } 
      } 
      bufferedreader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\mixins\client\settings\IMixinGameSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */