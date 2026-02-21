package fr.paladium.palamod.modules.luckyblock.tileentity.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketColoredParticle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityEasterGift extends TileEntity {
  private long time = 0L;
  
  private boolean isFake;
  
  public static final int ANIMATION_DURATION = 35;
  
  public long getTime() {
    return this.time;
  }
  
  public void setTime(long time) {
    this.time = time;
  }
  
  public static int getANIMATION_DURATION() {
    return 35;
  }
  
  public TileEntityEasterGift(boolean isFake) {
    this.isFake = isFake;
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (!this.field_145850_b.field_72995_K) {
      int radius = 20;
      float r = 0.83137256F;
      float g = 0.6862745F;
      float b = 0.21568628F;
      if (this.isFake) {
        r = 0.0F;
        g = 0.0F;
        b = 0.0F;
      } 
      for (Object o : this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((this.field_145851_c - radius), 0.0D, (this.field_145849_e - radius), (this.field_145851_c + radius), 255.0D, (this.field_145849_e + radius)))) {
        if (o instanceof EntityPlayerMP) {
          EntityPlayerMP p = (EntityPlayerMP)o;
          PalaMod.getNetwork().sendTo((IMessage)new PacketColoredParticle(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, r, g, b, 2.0F), p);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\easter\TileEntityEasterGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */