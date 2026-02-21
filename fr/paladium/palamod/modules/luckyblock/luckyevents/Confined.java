package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class Confined extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EventUtils.spawnStructure(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u + 2, (int)player.field_70161_v, 5, 5, 5, Blocks.field_150359_w, player);
    EventUtils.spawnStructure(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u + 2, (int)player.field_70161_v, 3, 5, 3, Blocks.field_150350_a, player);
    if (EventUtils.canInteract((EntityPlayer)player, (int)player.field_70165_t, (int)player.field_70163_u + 5, (int)player.field_70161_v))
      player.field_70170_p.func_147449_b((int)player.field_70165_t, (int)player.field_70163_u + 5, (int)player.field_70161_v, (Block)BlocksRegister.FLUIDS_SULFURICWATER); 
  }
  
  public String getName() {
    return "Enfermé";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "enferme";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Confined.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */