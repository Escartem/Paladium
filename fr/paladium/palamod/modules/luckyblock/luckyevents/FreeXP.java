package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class FreeXP extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityFarmerChicken entityFarmerChicken;
    EntityCavernousZombie entityCavernousZombie;
    EntityFlowerMonster entityFlowerMonster;
    EntityMegaCreeper entityMegaCreeper;
    Entity ent = null;
    Random r = player.field_70170_p.field_73012_v;
    World world = player.field_70170_p;
    int er = r.nextInt(4);
    switch (er) {
      case 0:
        entityFarmerChicken = new EntityFarmerChicken(world);
        break;
      case 1:
        entityCavernousZombie = new EntityCavernousZombie(world);
        break;
      case 2:
        entityFlowerMonster = new EntityFlowerMonster(world);
        break;
      case 3:
        entityMegaCreeper = new EntityMegaCreeper(world);
        break;
    } 
    entityMegaCreeper.func_70107_b(x, y, z);
    world.func_72838_d((Entity)entityMegaCreeper);
  }
  
  public String getName() {
    return "XP gratuite";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FreeXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */