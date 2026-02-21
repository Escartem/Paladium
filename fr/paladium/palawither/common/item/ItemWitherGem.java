package fr.paladium.palawither.common.item;

import fr.paladium.palawither.common.CommonProxy;
import fr.paladium.palawither.common.wither.base.IWither;
import java.lang.reflect.Constructor;
import lombok.NonNull;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemWitherGem extends Item {
  private boolean defaultConstructor;
  
  private Constructor<? extends IWither> constructor;
  
  public boolean isDefaultConstructor() {
    return this.defaultConstructor;
  }
  
  public Constructor<? extends IWither> getConstructor() {
    return this.constructor;
  }
  
  public ItemWitherGem(@NonNull String name, @NonNull String texture, @NonNull Class<? extends IWither> witherClass) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    if (witherClass == null)
      throw new NullPointerException("witherClass is marked non-null but is null"); 
    try {
      this.defaultConstructor = false;
      this.constructor = witherClass.getConstructor(new Class[] { World.class });
    } catch (Exception silent) {
      try {
        this.defaultConstructor = true;
        this.constructor = witherClass.getConstructor(new Class[0]);
      } catch (Exception e) {
        throw new RuntimeException("Could not instantiate wither class: " + witherClass.getName(), e);
      } 
    } 
    func_77625_d(16);
    func_111206_d(texture);
    func_77655_b(name);
    func_77637_a(CommonProxy.CREATIVE_TAB);
  }
  
  @NonNull
  public IWither create(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    try {
      if (this.defaultConstructor)
        return this.constructor.newInstance(new Object[0]); 
      return this.constructor.newInstance(new Object[] { world });
    } catch (Exception e) {
      throw new RuntimeException("Could not create wither instance for class: " + this.constructor.getDeclaringClass().getName(), e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\item\ItemWitherGem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */