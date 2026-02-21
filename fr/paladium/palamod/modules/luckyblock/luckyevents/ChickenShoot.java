package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.entity.EntityBalloon;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class ChickenShoot extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 3; i++) {
      EntityChicken chicken = new EntityChicken(player.field_70170_p);
      NBTTagCompound compound = new NBTTagCompound();
      if (chicken.getEntityData() != null)
        compound = chicken.getEntityData(); 
      compound.func_74768_a("drop_count", player.field_70170_p.field_73012_v.nextInt(30));
      chicken.func_70634_a((x + i), y, (z + i));
      player.field_70170_p.func_72838_d((Entity)chicken);
      player.field_70170_p.func_72838_d((Entity)new EntityBalloon(player.field_70170_p, (Entity)chicken, chicken.field_70165_t, chicken.field_70163_u + 1.0D, chicken.field_70161_v));
    } 
  }
  
  public String getName() {
    return "Tire aux pigeons";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 30;
  }
  
  public String getTexture() {
    return "tir_aux_pigeons";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ChickenShoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */