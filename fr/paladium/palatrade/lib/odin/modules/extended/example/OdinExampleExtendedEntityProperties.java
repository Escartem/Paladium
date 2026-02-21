package fr.paladium.palatrade.lib.odin.modules.extended.example;

import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedEntityProperties;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class OdinExampleExtendedEntityProperties extends ExtendedEntityProperties {
  public static final String PROP_NAME = "odin-exemple";
  
  public int points;
  
  public static OdinExampleExtendedEntityProperties get(Entity entity) {
    return (OdinExampleExtendedEntityProperties)entity.getExtendedProperties("odin-exemple");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74768_a("points", this.points);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    this.points = compound.func_74762_e("points");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\extended\example\OdinExampleExtendedEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */