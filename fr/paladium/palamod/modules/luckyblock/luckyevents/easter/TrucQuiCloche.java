package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TrucQuiCloche extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    boolean done = false;
    boolean isEmpty = true;
    do {
      isEmpty = true;
      for (int i = 0; i < player.field_71071_by.func_70302_i_() - 4; i++) {
        if (player.field_71071_by.func_70301_a(i) != null) {
          isEmpty = false;
          break;
        } 
      } 
      if (isEmpty)
        continue; 
      int slotId = ThreadLocalRandom.current().nextInt(0, player.field_71071_by.func_70302_i_() - 4);
      ItemStack itemStack = player.field_71071_by.func_70301_a(slotId);
      if (itemStack == null)
        continue; 
      NBTTagCompound tag = itemStack.func_77978_p();
      if (tag == null)
        tag = new NBTTagCompound(); 
      tag.func_74757_a("TrucQuiCloche", true);
      itemStack.func_77982_d(tag);
      itemStack.func_151001_c("Un truc qui cloche");
      done = true;
    } while (!done && !isEmpty);
    if (!done)
      PlayerUtils.sendMessage((EntityPlayer)player, "Tu as de la chance... diiing!"); 
  }
  
  public String getName() {
    return "Un truc qui cloche";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 240;
  }
  
  public String getTexture() {
    return "easter/truc_qui_cloche";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\TrucQuiCloche.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */