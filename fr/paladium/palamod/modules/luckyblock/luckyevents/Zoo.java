package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.hunter.entites.EntityCrab;
import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.hunter.entites.EntityElephant;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.hunter.entites.EntityGoat;
import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.hunter.entites.EntityPanda;
import fr.paladium.palamod.modules.hunter.entites.EntityParrot;
import fr.paladium.palamod.modules.hunter.entites.EntitySnail;
import fr.paladium.palamod.modules.hunter.entites.EntitySnake;
import fr.paladium.palamod.modules.hunter.entites.EntityTurtle;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;

public class Zoo extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityLiving[] entities = { 
        (EntityLiving)new EntityFarmerChicken(player.field_70170_p), (EntityLiving)new EntityCavernousZombie(player.field_70170_p), (EntityLiving)new EntityFlowerMonster(player.field_70170_p), (EntityLiving)new EntityMegaCreeper(player.field_70170_p), (EntityLiving)new EntityCrab(player.field_70170_p), (EntityLiving)new EntitySnail(player.field_70170_p), (EntityLiving)new EntityJellyFish(player.field_70170_p), (EntityLiving)new EntitySnake(player.field_70170_p), (EntityLiving)new EntityTurtle(player.field_70170_p), (EntityLiving)new EntityParrot(player.field_70170_p), 
        (EntityLiving)new EntityPanda(player.field_70170_p), (EntityLiving)new EntityGoat(player.field_70170_p), (EntityLiving)new EntityDolphin(player.field_70170_p), (EntityLiving)new EntityElephant(player.field_70170_p) };
    for (EntityLiving entity : entities) {
      entity.func_70634_a(x, y, z);
      player.field_70170_p.func_72838_d((Entity)entity);
    } 
  }
  
  public String getName() {
    return "Zoo";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 600;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "zoo";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Zoo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */