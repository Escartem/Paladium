package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RainbowArmor extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 4; i++) {
      ItemStack item = new ItemStack((i == 0) ? (Item)Items.field_151024_Q : ((i == 1) ? (Item)Items.field_151027_R : ((i == 2) ? (Item)Items.field_151026_S : (Item)Items.field_151021_T)));
      item.func_151001_c("§bRainbow Armor");
      NBTTagCompound compound = new NBTTagCompound();
      if (item.func_77942_o())
        compound = item.field_77990_d; 
      compound.func_74757_a("rainbow", true);
      item.func_77982_d(compound);
      player.func_70099_a(item, 0.0F);
    } 
  }
  
  public String getName() {
    return "Arc en armure";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 20;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\RainbowArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */