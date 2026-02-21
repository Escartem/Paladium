package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Spawner extends ALuckyEvent {
  String[] mobs = new String[] { "Zombie", "Skeleton", "Spider" };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    try {
      player.func_130014_f_().func_147449_b(x, y, z, Blocks.field_150474_ac);
      TileEntityMobSpawner tiSpawner = (TileEntityMobSpawner)player.func_130014_f_().func_147438_o(x, y, z);
      tiSpawner.func_145881_a().func_98272_a(this.mobs[player.field_70170_p.field_73012_v.nextInt(this.mobs.length)]);
    } catch (Exception e) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§cOops :( Une erreur est survenue..."));
    } 
  }
  
  public String getName() {
    return "Spawner";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Spawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */