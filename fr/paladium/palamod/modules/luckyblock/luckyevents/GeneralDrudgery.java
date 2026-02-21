package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import java.util.Random;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class GeneralDrudgery extends ALuckyEvent {
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
    Set<Class<? extends EntityBossBase>> bosses = BossRegistry.INSTANCE.getRegisteredBosses().keySet();
    int rand = r.nextInt(bosses.size());
    int i = 0;
    for (Class<? extends EntityBossBase> clazz : bosses) {
      if (rand == i) {
        try {
          EntityBossBase boss = clazz.getDeclaredConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
          boss.func_70634_a(x, y, z);
          player.field_70170_p.func_72838_d((Entity)boss);
          player.func_145747_a((IChatComponent)new ChatComponentText("§a(Privé) §aUn boss a spawné en " + x + " | " + y + " | " + z + "."));
          player.func_145747_a((IChatComponent)new ChatComponentText("§a(Privé) §aSpawné par " + player
                .func_70005_c_() + "."));
        } catch (Exception e) {
          e.printStackTrace();
        } 
        break;
      } 
      i++;
    } 
  }
  
  public String getName() {
    return "Corvée générale";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 4000;
  }
  
  public String getTexture() {
    return "corve_generale";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\GeneralDrudgery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */