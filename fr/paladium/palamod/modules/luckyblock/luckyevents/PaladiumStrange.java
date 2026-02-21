package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PaladiumStrange extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack itemStack = new ItemStack(ItemsRegister.PALADIUM_INGOT);
    NBTTagCompound nbtTagCompound = new NBTTagCompound();
    if (itemStack.func_77942_o())
      nbtTagCompound = itemStack.func_77978_p(); 
    nbtTagCompound.func_74757_a("STRANGE", true);
    itemStack.func_77982_d(nbtTagCompound);
    PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 1.0D);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, itemStack);
  }
  
  public String getName() {
    return "Paladium Bizarre";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "palastrange";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PaladiumStrange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */