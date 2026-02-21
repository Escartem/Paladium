package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class Ghast extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_72838_d((Entity)new EntityFireball(player.field_70170_p, (x + 1), y, z, 1.0D, 0.0D, 0.0D) {
          protected void func_70227_a(MovingObjectPosition obj) {
            player.field_70170_p.func_72876_a((Entity)this, obj.field_72311_b, obj.field_72312_c, obj.field_72309_d, 10.0F, true);
            func_70106_y();
          }
        });
    player.field_70170_p.func_72838_d((Entity)new EntityFireball(player.field_70170_p, x, y, (z + 1), 0.0D, 0.0D, 1.0D) {
          protected void func_70227_a(MovingObjectPosition obj) {
            player.field_70170_p.func_72876_a((Entity)this, obj.field_72311_b, obj.field_72312_c, obj.field_72309_d, 10.0F, true);
            func_70106_y();
          }
        });
    player.field_70170_p.func_72838_d((Entity)new EntityFireball(player.field_70170_p, (x - 1), y, z, -1.0D, 0.0D, 0.0D) {
          protected void func_70227_a(MovingObjectPosition obj) {
            player.field_70170_p.func_72876_a((Entity)this, obj.field_72311_b, obj.field_72312_c, obj.field_72309_d, 10.0F, true);
            func_70106_y();
          }
        });
    player.field_70170_p.func_72838_d((Entity)new EntityFireball(player.field_70170_p, x, y, (z - 1), 0.0D, 0.0D, -1.0D) {
          protected void func_70227_a(MovingObjectPosition obj) {
            player.field_70170_p.func_72876_a((Entity)this, obj.field_72311_b, obj.field_72312_c, obj.field_72309_d, 10.0F, true);
            func_70106_y();
          }
        });
  }
  
  public String getName() {
    return "Ghast dans l’âme";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 60;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "ghast";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Ghast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */