package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NametagFirework extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack item = new ItemStack(ItemsRegister.NAMETAG_FIREWORK, 10);
    item.func_151001_c("§bFirework §e§o" + player.func_70005_c_());
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    compound.func_74778_a("nametag", player.func_70005_c_());
    item.func_77982_d(compound);
    player.func_70099_a(item, 10.0F);
  }
  
  public String getName() {
    return "Des étoiles plein les yeux";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 10;
  }
  
  public String getTexture() {
    return "nametag_firework";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\NametagFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */