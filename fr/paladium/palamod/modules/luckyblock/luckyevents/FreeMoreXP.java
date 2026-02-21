package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class FreeMoreXP extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    Entity[] entities = { (Entity)new EntityFarmerChicken(world), (Entity)new EntityCavernousZombie(world), (Entity)new EntityFlowerMonster(world), (Entity)new EntityMegaCreeper(world) };
    for (Entity entity : entities) {
      entity.func_70107_b(x, y, z);
      world.func_72838_d(entity);
    } 
  }
  
  public String getName() {
    return "XP encore plus gratuite";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FreeMoreXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */