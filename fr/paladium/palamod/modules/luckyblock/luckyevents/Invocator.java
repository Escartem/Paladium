package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Vec3;

public class Invocator extends ALuckyEvent {
  private static int spawnerCount = 9;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    String[] spawnerIds = { "Skeleton", "Zombie", "Spider", "Creeper", "Blaze", "Enderman" };
    Random rand = player.field_70170_p.field_73012_v;
    for (int i = 0; i < spawnerCount; i++) {
      Vec3 available = findAvailablePosition(player);
      if (available == null)
        return; 
      int posX = (int)available.field_72450_a;
      int posY = (int)available.field_72448_b;
      int posZ = (int)available.field_72449_c;
      if (!EventUtils.canInteract((EntityPlayer)player, posX, posY, posZ))
        return; 
      Block b = Blocks.field_150474_ac;
      try {
        player.field_70170_p.func_147449_b(posX, posY, posZ, b);
        TileEntityMobSpawner tiSpawner = (TileEntityMobSpawner)player.func_130014_f_().func_147438_o((int)available.field_72450_a, (int)available.field_72448_b, (int)available.field_72449_c);
        tiSpawner.func_145881_a().func_98272_a(spawnerIds[rand.nextInt(spawnerIds.length)]);
      } catch (Exception exception) {}
    } 
  }
  
  private Vec3 findAvailablePosition(EntityPlayerMP player) {
    int cx = (int)player.field_70165_t;
    int cy = (int)player.field_70163_u;
    int cz = (int)player.field_70161_v;
    for (int px = cx - 3; px < cx + 3; px++) {
      for (int py = cy - 2; py < cy + 4; py++) {
        for (int pz = cz - 3; pz < cz + 3; pz++) {
          if (px >= 0 && py >= 0 && pz >= 0) {
            Block b = player.field_70170_p.func_147439_a(px, py, pz);
            if (b == null || b == Blocks.field_150350_a)
              return Vec3.func_72443_a(px, py, pz); 
          } 
        } 
      } 
    } 
    return null;
  }
  
  public String getName() {
    return "Invocateur";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "invocateur";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Invocator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */