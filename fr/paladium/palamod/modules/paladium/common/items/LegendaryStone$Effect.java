package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.item.Item;

public enum Effect {
  RANDOM(0, "RANDOM", ItemsRegister.LEGENDARYSTONE_RANDOM, 999, 1.0F, 1.0F, 1.0F),
  TELEPORTATION(1, "TELEPORTATION", ItemsRegister.LEGENDARYSTONE_TELEPORTATION, 13, 0.5F, 0.88F, 0.7F),
  INVISIBILITY(2, "INVISIBILITY", ItemsRegister.LEGENDARYSTONE_INVISIBILITY, 9, 0.8F, 0.8F, 0.8F),
  FORTUNE(3, "FORTUNE", ItemsRegister.LEGENDARYSTONE_FORTUNE, 10, 1.0F, 0.88F, 0.03F),
  POWER(4, "POWER", ItemsRegister.LEGENDARYSTONE_POWER, 12, 0.8F, 0.08F, 0.2F),
  JOBS(5, "JOBS", ItemsRegister.LEGENDARYSTONE_JOBS, 14, 0.3F, 0.7F, 0.2F),
  CHAOS(6, "CHAOS", ItemsRegister.LEGENDARYSTONE_CHAOS, 11, 0.5F, 0.08F, 1.0F);
  
  Effect(int id, String displayName, Item item, int type, float red, float green, float blue) {
    this.id = id;
    this.displayName = displayName;
    this.item = item;
    this.type = type;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  private final int id;
  
  private final String displayName;
  
  private final Item item;
  
  private final int type;
  
  private final float red;
  
  private final float green;
  
  private final float blue;
  
  public int getId() {
    return this.id;
  }
  
  public int getType() {
    return this.type;
  }
  
  public float getRed() {
    return this.red;
  }
  
  public float getGreen() {
    return this.green;
  }
  
  public float getBlue() {
    return this.blue;
  }
  
  public String getDisplayName() {
    return "LEGENDARYSTONE_" + this.displayName;
  }
  
  public Item getItem() {
    switch (LegendaryStone.null.$SwitchMap$fr$paladium$palamod$modules$paladium$common$items$LegendaryStone$Effect[ordinal()]) {
      case 1:
        return ItemsRegister.LEGENDARYSTONE_RANDOM;
      case 2:
        return ItemsRegister.LEGENDARYSTONE_TELEPORTATION;
      case 3:
        return ItemsRegister.LEGENDARYSTONE_INVISIBILITY;
      case 4:
        return ItemsRegister.LEGENDARYSTONE_FORTUNE;
      case 5:
        return ItemsRegister.LEGENDARYSTONE_POWER;
      case 6:
        return ItemsRegister.LEGENDARYSTONE_JOBS;
      case 7:
        return ItemsRegister.LEGENDARYSTONE_CHAOS;
    } 
    return this.item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\LegendaryStone$Effect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */