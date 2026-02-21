package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public class Batum implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    int d = 0;
    double x = player.field_70165_t;
    double y = player.field_70163_u;
    double z = player.field_70161_v;
    Block block = Blocks.field_150350_a;
    while (d < 10 && block == Blocks.field_150350_a) {
      double ox = (-MathHelper.func_76126_a(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F));
      double oz = (MathHelper.func_76134_b(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F));
      double oy = -MathHelper.func_76126_a(player.field_70125_A / 180.0F * 3.1415927F);
      block = player.field_70170_p.func_147439_a((int)(x + ox * d), (int)(y + oy * d), (int)(z + oz * d));
      if (block != Blocks.field_150350_a) {
        x += ox * d;
        y = y + oy * d + 1.0D;
        z += oz * d;
      } 
      d++;
    } 
    EntityBoat boat = new EntityBoat(player.func_130014_f_(), x, y, z);
    player.func_130014_f_().func_72838_d((Entity)boat);
    EventUtils.spawnParticle(player.field_70170_p, "splash", x, y, z, 1000, 1.0D);
    player.field_70170_p.func_72956_a((Entity)player, "liquid.water", 1.0F, 1.0F);
  }
  
  public int getId() {
    return 1;
  }
  
  public String getName() {
    return "Batum";
  }
  
  public int getMaxTiers() {
    return 1;
  }
  
  public int getCost() {
    return 1;
  }
  
  public int getCooldown() {
    return 10;
  }
  
  public int getLevel() {
    return 1;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet de faire spawner un bateau." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Batum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */