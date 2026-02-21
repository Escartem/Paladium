package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class Freeze implements Spell {
  public void startAnimation(final EntityPlayer player, final int tier) {
    final int offset = (tier == 1) ? 3 : ((tier == 2) ? 5 : 8);
    (new Thread(new Runnable() {
          public void run() {
            for (int count = 8; count < offset + 8; count++) {
              try {
                Thread.sleep(100L);
                float i;
                for (i = 0.5F; i < 2.0F; i++) {
                  if (player.field_70170_p.field_73012_v.nextInt(100) <= 90) {
                    double x = (count - 6) * Math.cos(Math.toRadians((i * 6.0F + player.field_70177_z + 90.0F))) + player.field_70165_t;
                    double z = (count - 6) * Math.sin(Math.toRadians((i * 6.0F + player.field_70177_z + 90.0F))) + player.field_70161_v;
                    BlockPos pos = new BlockPos(x, player.field_70163_u - 1.0D, z);
                    EntityCustomFallingBlock falling = new EntityCustomFallingBlock(player.func_130014_f_(), (Entity)player, x, player.field_70163_u, z, 0.20000000298023224D, i * 6.0F + player.field_70177_z + 90.0F, pos, Blocks.field_150403_cj, 0, 2, EntityCustomFallingBlock.Type.FREEZE, tier, player.func_110124_au());
                    if (!(player.func_130014_f_()).field_72995_K)
                      player.func_130014_f_().func_72838_d((Entity)falling); 
                  } 
                } 
                for (i = 0.5F; i < 2.0F; i++) {
                  if (player.field_70170_p.field_73012_v.nextInt(100) <= 90) {
                    double x = (count - 6) * Math.cos(Math.toRadians((i * -6.0F + player.field_70177_z + 90.0F))) + player.field_70165_t;
                    double z = (count - 6) * Math.sin(Math.toRadians((i * -6.0F + player.field_70177_z + 90.0F))) + player.field_70161_v;
                    BlockPos pos = new BlockPos(x, player.field_70163_u - 1.0D, z);
                    EntityCustomFallingBlock falling = new EntityCustomFallingBlock(player.func_130014_f_(), (Entity)player, x, player.field_70163_u, z, 0.20000000298023224D, i * -6.0F + player.field_70177_z + 90.0F, pos, Blocks.field_150403_cj, 0, 2, EntityCustomFallingBlock.Type.FREEZE, tier, player.func_110124_au());
                    if (!(player.func_130014_f_()).field_72995_K)
                      player.func_130014_f_().func_72838_d((Entity)falling); 
                  } 
                } 
              } catch (Exception e) {
                e.printStackTrace();
              } 
            } 
          }
        })).start();
  }
  
  public void perform(EntityPlayerMP player, int tier) {
    startAnimation((EntityPlayer)player, tier);
  }
  
  public int getId() {
    return 12;
  }
  
  public String getName() {
    return "Freeze";
  }
  
  public int getMaxTiers() {
    return 3;
  }
  
  public int getCost() {
    return 4;
  }
  
  public int getCooldown() {
    return 10;
  }
  
  public int getLevel() {
    return 65;
  }
  
  public String[] getDescription() {
    return new String[] { "Sort permettant de freeze les joueurs dans un rayon de 10 blocs pendant 2 secondes", "Sort permettant de freeze les joueurs dans un rayon de 10 blocs pendant 4 secondes", "Sort permettant de freeze les joueurs dans un rayon de 10 blocs pendant 6 secondes" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Freeze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */