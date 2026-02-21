package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Admins extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int r = player.field_70170_p.field_73012_v.nextInt(4);
    NBTTagCompound compoundS = new NBTTagCompound();
    ItemStack stack = new ItemStack(BlocksRegister.STATUE, 1);
    switch (r) {
      case 0:
        compoundS.func_74778_a("playerUUID", "96e76f2a-d77d-4cb7-91a0-3eba67e74397");
        break;
      case 1:
        compoundS.func_74778_a("playerUUID", "d4c2669a-9ffe-44f5-841e-dda7808fb223");
        break;
      case 2:
        compoundS.func_74778_a("playerUUID", "c9baa462-dca3-4c50-a10e-f135802a7321");
        break;
      case 3:
        compoundS.func_74778_a("playerUUID", "c2f8f1e3-e8a6-4295-970a-17b7fb7cdc8a");
        break;
    } 
    stack.func_77982_d(compoundS);
    player.func_70099_a(stack, 1.0F);
  }
  
  public String getName() {
    return "Fuzayyyy/Goldoooo/Looooooorn/Terraaaaain";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 10000;
  }
  
  public String getTexture() {
    return "admins";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Admins.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */