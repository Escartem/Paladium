package fr.paladium.palakeybind.common.key.impl;

import java.util.function.Predicate;
import net.minecraft.client.settings.KeyBinding;

public interface IKeyBinding {
  boolean isCtrl();
  
  boolean isRightCtrl();
  
  boolean isShift();
  
  boolean isRightShift();
  
  boolean isAlt();
  
  boolean isRightAlt();
  
  void setCtrl(boolean paramBoolean);
  
  void setRightCtrl(boolean paramBoolean);
  
  void setShift(boolean paramBoolean);
  
  void setRightShift(boolean paramBoolean);
  
  void setAlt(boolean paramBoolean);
  
  void setRightAlt(boolean paramBoolean);
  
  String getSubCategory();
  
  String getRawSubCategory();
  
  void setSubCategory(String paramString);
  
  void addPressTime();
  
  void setPressed(boolean paramBoolean);
  
  boolean isDefaultCtrl();
  
  boolean isDefaultRightCtrl();
  
  boolean isDefaultShift();
  
  boolean isDefaultRightShift();
  
  boolean isDefaultAlt();
  
  boolean isDefaultRightAlt();
  
  void setDefaultCtrl(boolean paramBoolean);
  
  void setDefaultRightCtrl(boolean paramBoolean);
  
  void setDefaultShift(boolean paramBoolean);
  
  void setDefaultRightShift(boolean paramBoolean);
  
  void setDefaultAlt(boolean paramBoolean);
  
  void setDefaultRightAlt(boolean paramBoolean);
  
  boolean isDefault();
  
  boolean isSame(KeyBinding paramKeyBinding);
  
  Predicate<KeyBinding> getCanUse();
  
  void setCanUse(Predicate<KeyBinding> paramPredicate);
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\common\key\impl\IKeyBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */