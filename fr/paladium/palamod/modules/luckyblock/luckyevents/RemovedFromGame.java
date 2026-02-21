package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.entity.EntityHerobrine;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class RemovedFromGame extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int bx, int by, int bz) {
    WorldServer world = (WorldServer)player.field_70170_p;
    Random r = world.field_73012_v;
    int x = bx - 21 + r.nextInt(42);
    int z = bz - 21 + r.nextInt(42);
    int y = world.func_72976_f(x, z);
    int c = 0;
    while (!EventUtils.canInteract((EntityPlayer)player, x, y, z) && c < 50) {
      x = bx - 21 + r.nextInt(42);
      z = bz - 21 + r.nextInt(42);
      y = world.func_72976_f(x, z);
      c++;
    } 
    if (c >= 50) {
      x = bx;
      z = bz;
      y = by;
    } 
    EntityHerobrine entity = new EntityHerobrine((World)world);
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Retiré du jeu";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "retire_du_jeu";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\RemovedFromGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */