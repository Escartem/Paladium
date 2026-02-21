package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MaladiePouetPouet extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    boolean done = false;
    for (int i = 0; i < player.field_71071_by.func_70302_i_() - 4; i++) {
      ItemStack itemStack = player.field_71071_by.func_70301_a(i);
      if (itemStack != null && itemStack.field_77994_a == 1) {
        NBTTagCompound tag = itemStack.func_77978_p();
        if (tag == null)
          tag = new NBTTagCompound(); 
        tag.func_74757_a("PouetPouet", true);
        tag.func_74772_a("PouetPouetTime", 0L);
        itemStack.func_77982_d(tag);
        itemStack.func_151001_c("Pouet pouet");
        done = true;
        break;
      } 
    } 
    if (!done)
      PlayerUtils.sendMessage((EntityPlayer)player, "Tu as de la chance... pouuet!"); 
  }
  
  public String getName() {
    return "Maladie du pouet pouet";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public String getTexture() {
    return "june/maladie_pouet_pouet";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\MaladiePouetPouet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */