package fr.paladium.palamod.modules.hunter.tileentities;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;

class null implements Runnable {
  public void run() {
    try {
      for (double d = 0.0D; d < 31.41592653589793D; d += 0.2D) {
        Thread.sleep(30L);
        EventUtils.spawnParticle(TileEntityEndiumTotem.access$000(TileEntityEndiumTotem.this), "instantSpell", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d), TileEntityEndiumTotem.this.field_145848_d + d / 20.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
            Math.cos(d), 5, 0.0D);
        EventUtils.spawnParticle(TileEntityEndiumTotem.access$100(TileEntityEndiumTotem.this), "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d) + 3.0D, TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
            Math.cos(d), 5, 0.0D);
        EventUtils.spawnParticle(TileEntityEndiumTotem.access$200(TileEntityEndiumTotem.this), "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d) - 3.0D, TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
            Math.cos(d), 5, 0.0D);
        EventUtils.spawnParticle(TileEntityEndiumTotem.access$300(TileEntityEndiumTotem.this), "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d), TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
            Math.cos(d) + 3.0D, 5, 0.0D);
        EventUtils.spawnParticle(TileEntityEndiumTotem.access$400(TileEntityEndiumTotem.this), "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d), TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 
            Math.cos(d) - 3.0D, 5, 0.0D);
      } 
      for (int i = 0; i < 10; i++) {
        Thread.sleep(200L);
        ((WorldServer)TileEntityEndiumTotem.access$800(TileEntityEndiumTotem.this)).func_72942_c((Entity)new EntityLightningBolt(TileEntityEndiumTotem.access$500(TileEntityEndiumTotem.this), (TileEntityEndiumTotem.this.field_145851_c + 
              (TileEntityEndiumTotem.access$600(TileEntityEndiumTotem.this)).field_73012_v.nextInt(8) - 4), TileEntityEndiumTotem.this.field_145848_d, (TileEntityEndiumTotem.this.field_145849_e + (TileEntityEndiumTotem.access$700(TileEntityEndiumTotem.this)).field_73012_v.nextInt(8) - 4)));
      } 
      AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a((TileEntityEndiumTotem.this.field_145851_c - 10), (TileEntityEndiumTotem.this.field_145848_d - 10), (TileEntityEndiumTotem.this.field_145849_e - 10), (TileEntityEndiumTotem.this.field_145851_c + 10), (TileEntityEndiumTotem.this.field_145848_d + 10), (TileEntityEndiumTotem.this.field_145849_e + 10));
      List players = TileEntityEndiumTotem.access$900(TileEntityEndiumTotem.this).func_72872_a(EntityLivingBase.class, scanAbove);
      for (Object obj : players) {
        if (obj instanceof EntityLivingBase) {
          EntityLivingBase pl = (EntityLivingBase)obj;
          pl.field_70160_al = true;
          float str = 0.6F;
          pl.field_70159_w += (pl.field_70165_t - TileEntityEndiumTotem.this.field_145851_c > 0.0D) ? str : -str;
          pl.field_70181_x += str;
          pl.field_70179_y += (pl.field_70161_v - TileEntityEndiumTotem.this.field_145849_e > 0.0D) ? str : -str;
          if (obj instanceof EntityPlayerMP) {
            EntityPlayerMP p = (EntityPlayerMP)pl;
            p.func_70606_j(0.5F);
            p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)pl));
          } 
        } 
      } 
      if (TileEntityEndiumTotem.this.isActive())
        TileEntityEndiumTotem.this.hasEndium = true; 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\tileentities\TileEntityEndiumTotem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */