package fr.paladium.palakeybind.mixins.client.settings;

import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IntHashMap;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({KeyBinding.class})
public class IMixinKeyBinding implements IKeyBinding {
  @Shadow
  @Final
  private static IntHashMap field_74514_b;
  
  @Shadow
  @Final
  private static List<?> field_74516_a;
  
  @Shadow
  @Final
  private String field_151471_f;
  
  @Shadow
  @Final
  private String field_74515_c;
  
  @Shadow
  @Final
  private int field_151472_e;
  
  @Shadow
  private int field_74512_d;
  
  @Shadow
  private boolean field_74513_e;
  
  @Shadow
  private int field_151474_i;
  
  private boolean ctrl;
  
  private boolean rightCtrl;
  
  private boolean shift;
  
  private boolean rightShift;
  
  private boolean alt;
  
  private boolean rightAlt;
  
  private boolean defaultCtrl;
  
  private boolean defaultRightCtrl;
  
  private boolean defaultShift;
  
  private boolean defaultRightShift;
  
  private boolean defaultAlt;
  
  private boolean defaultRightAlt;
  
  public String getKeyCategory() {
    return this.field_151471_f;
  }
  
  public String getKeyDescription() {
    return this.field_74515_c;
  }
  
  public int getKeyCodeDefault() {
    return this.field_151472_e;
  }
  
  public int getKeyCode() {
    return this.field_74512_d;
  }
  
  public void setPressed(boolean pressed) {
    this.field_74513_e = pressed;
  }
  
  public int getPressTime() {
    return this.field_151474_i;
  }
  
  public boolean isCtrl() {
    return this.ctrl;
  }
  
  public void setCtrl(boolean ctrl) {
    this.ctrl = ctrl;
  }
  
  public boolean isRightCtrl() {
    return this.rightCtrl;
  }
  
  public void setRightCtrl(boolean rightCtrl) {
    this.rightCtrl = rightCtrl;
  }
  
  public boolean isShift() {
    return this.shift;
  }
  
  public void setShift(boolean shift) {
    this.shift = shift;
  }
  
  public boolean isRightShift() {
    return this.rightShift;
  }
  
  public void setRightShift(boolean rightShift) {
    this.rightShift = rightShift;
  }
  
  public boolean isAlt() {
    return this.alt;
  }
  
  public void setAlt(boolean alt) {
    this.alt = alt;
  }
  
  public boolean isRightAlt() {
    return this.rightAlt;
  }
  
  public void setRightAlt(boolean rightAlt) {
    this.rightAlt = rightAlt;
  }
  
  public boolean isDefaultCtrl() {
    return this.defaultCtrl;
  }
  
  public boolean isDefaultRightCtrl() {
    return this.defaultRightCtrl;
  }
  
  public boolean isDefaultShift() {
    return this.defaultShift;
  }
  
  public boolean isDefaultRightShift() {
    return this.defaultRightShift;
  }
  
  public boolean isDefaultAlt() {
    return this.defaultAlt;
  }
  
  public boolean isDefaultRightAlt() {
    return this.defaultRightAlt;
  }
  
  private String subCategory = "none";
  
  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }
  
  private Predicate<KeyBinding> canUse = key -> true;
  
  public void setCanUse(Predicate<KeyBinding> canUse) {
    this.canUse = canUse;
  }
  
  public Predicate<KeyBinding> getCanUse() {
    return this.canUse;
  }
  
  @Overwrite
  public boolean func_151468_f() {
    if (this.field_151474_i == 0) {
      this.field_151474_i = 0;
      return false;
    } 
    this.field_151474_i--;
    return true;
  }
  
  @Overwrite
  public static void func_74507_a(int key) {
    if (key != 0)
      for (Object keybindingObj : field_74516_a) {
        KeyBinding keybinding = (KeyBinding)keybindingObj;
        if (!(keybinding instanceof IKeyBinding) || 
          keybinding.func_151463_i() != key)
          continue; 
        IKeyBinding k = (IKeyBinding)keybindingObj;
        if (!k.getCanUse().test(keybinding))
          continue; 
        if (k.isAlt() && !Keyboard.isKeyDown(56))
          continue; 
        if (k.isCtrl() && !Keyboard.isKeyDown(29))
          continue; 
        if (k.isShift() && !Keyboard.isKeyDown(42))
          continue; 
        if (k.isRightAlt() && !Keyboard.isKeyDown(184))
          continue; 
        if (k.isRightCtrl() && !Keyboard.isKeyDown(157))
          continue; 
        if (k.isRightShift() && !Keyboard.isKeyDown(54))
          continue; 
        k.addPressTime();
      }  
  }
  
  @Overwrite
  public static void func_74510_a(int key, boolean pressed) {
    if (key != 0)
      for (Object keybindingObj : field_74516_a) {
        KeyBinding keybinding = (KeyBinding)keybindingObj;
        if (!(keybinding instanceof IKeyBinding) || 
          keybinding.func_151463_i() != key)
          continue; 
        IKeyBinding k = (IKeyBinding)keybindingObj;
        if (!pressed) {
          k.setPressed(false);
          continue;
        } 
        if (!k.getCanUse().test(keybinding))
          continue; 
        if (k.isAlt() && !Keyboard.isKeyDown(56))
          continue; 
        if (k.isCtrl() && !Keyboard.isKeyDown(29))
          continue; 
        if (k.isShift() && !Keyboard.isKeyDown(42))
          continue; 
        if (k.isRightAlt() && !Keyboard.isKeyDown(184))
          continue; 
        if (k.isRightCtrl() && !Keyboard.isKeyDown(157))
          continue; 
        if (k.isRightShift() && !Keyboard.isKeyDown(54))
          continue; 
        k.setPressed(pressed);
      }  
  }
  
  @Overwrite
  public int compareTo(KeyBinding keyBinding) {
    int i = I18n.func_135052_a(this.field_151471_f, new Object[0]).compareTo(I18n.func_135052_a(keyBinding.func_151466_e(), new Object[0]));
    if (i == 0)
      i = I18n.func_135052_a(this.subCategory, new Object[0]).compareTo(I18n.func_135052_a(((IKeyBinding)keyBinding).getRawSubCategory(), new Object[0])); 
    if (i == 0)
      i = I18n.func_135052_a(this.field_74515_c, new Object[0]).compareTo(I18n.func_135052_a(keyBinding.func_151464_g(), new Object[0])); 
    return i;
  }
  
  public void setDefaultCtrl(boolean value) {
    this.ctrl = value;
    this.defaultCtrl = value;
  }
  
  public void setDefaultRightCtrl(boolean value) {
    this.rightCtrl = value;
    this.defaultRightCtrl = value;
  }
  
  public void setDefaultShift(boolean value) {
    this.shift = value;
    this.defaultShift = value;
  }
  
  public void setDefaultRightShift(boolean value) {
    this.rightShift = value;
    this.defaultRightShift = value;
  }
  
  public void setDefaultAlt(boolean value) {
    this.alt = value;
    this.defaultAlt = value;
  }
  
  public void setDefaultRightAlt(boolean value) {
    this.rightAlt = value;
    this.defaultRightAlt = value;
  }
  
  public boolean isDefault() {
    return (this.field_74512_d == getKeyCodeDefault() && this.alt == this.defaultAlt && this.ctrl == this.defaultCtrl && this.shift == this.defaultShift && this.rightAlt == this.defaultRightAlt && this.rightCtrl == this.defaultRightCtrl && this.rightShift == this.defaultRightShift);
  }
  
  public boolean isSame(KeyBinding keyBinding) {
    if (keyBinding instanceof IKeyBinding) {
      IKeyBinding k = (IKeyBinding)keyBinding;
      return (keyBinding.func_151463_i() == this.field_74512_d && k.isAlt() == isAlt() && k.isCtrl() == isCtrl() && k.isShift() == isShift() && k.isRightAlt() == isRightAlt() && k.isRightCtrl() == isRightCtrl() && k.isRightShift() == isRightShift());
    } 
    return (keyBinding.func_151463_i() == this.field_74512_d);
  }
  
  public String getSubCategory() {
    return "key.subcategories." + this.field_151471_f.replace("key.categories.", "") + "." + this.subCategory;
  }
  
  public String getRawSubCategory() {
    return this.subCategory;
  }
  
  public void addPressTime() {
    this.field_151474_i++;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\mixins\client\settings\IMixinKeyBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */