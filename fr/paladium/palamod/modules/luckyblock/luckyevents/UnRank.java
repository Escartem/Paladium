package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.shop.network.SCPacketUpdateInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class UnRank extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack[] armor = player.field_71071_by.field_70460_b;
    int count = 0;
    for (ItemStack item : armor) {
      if (item == null) {
        count++;
      } else {
        PlayerUtils.dropItemStack((Entity)player, x, y, z, item);
        NBTTagCompound nbt = item.func_77978_p();
        if (count == 3)
          item = new ItemStack((Item)Items.field_151024_Q); 
        if (count == 2)
          item = new ItemStack((Item)Items.field_151027_R); 
        if (count == 1)
          item = new ItemStack((Item)Items.field_151026_S); 
        if (count == 0)
          item = new ItemStack((Item)Items.field_151021_T); 
        item.func_77982_d(nbt);
        player.field_71071_by.field_70460_b[count] = item;
        try {
          PalaMod.getNetHandler().sendTo((IMessage)new SCPacketUpdateInventory(player.field_71071_by), player);
        } catch (NullPointerException nullPointerException) {}
        count++;
      } 
    } 
  }
  
  public String getName() {
    return "Déclassement";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 600;
  }
  
  public String getTexture() {
    return "unrank";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\UnRank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */