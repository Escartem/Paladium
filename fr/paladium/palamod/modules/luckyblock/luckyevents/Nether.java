package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Nether extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random r = player.field_70170_p.field_73012_v;
    for (int ox = -5; ox < 5; ox++) {
      for (int oz = -5; oz < 6; oz++) {
        int oy = player.field_70170_p.func_72976_f(x + ox, z + oz) - 1;
        if (EventUtils.canInteract((EntityPlayer)player, x + ox, oy, z + oz))
          tryPlaceBlock(player, x + ox, oy, z + oz, Blocks.field_150424_aL); 
        if (r.nextInt(10) > 6 && 
          EventUtils.canInteract((EntityPlayer)player, x + ox, oy + 1, z + oz))
          tryPlaceBlock(player, x + ox, oy + 1, z + oz, (Block)Blocks.field_150480_ab); 
        if (r.nextInt(100) > 96) {
          EntityPigZombie pigZombie = new EntityPigZombie(player.field_70170_p);
          pigZombie.func_70634_a((x + ox), (oy + 1), (z + oz));
          player.field_70170_p.func_72838_d((Entity)pigZombie);
        } 
      } 
    } 
  }
  
  private boolean tryPlaceBlock(EntityPlayerMP player, int x, int y, int z, Block block) {
    World w = player.field_70170_p;
    Block b = w.func_147439_a(x, y, z);
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return false; 
    if (b == Blocks.field_150357_h)
      return false; 
    w.func_147449_b(x, y, z, block);
    return true;
  }
  
  public String getName() {
    return "Nether activé !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "nether";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Nether.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */