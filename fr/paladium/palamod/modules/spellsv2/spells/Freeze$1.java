package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

class null implements Runnable {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Freeze$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */