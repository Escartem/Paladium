package fr.paladium.palamod.modules.paladium.common.logics;

import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityAncientHammerBlockCorrupted extends TileEntity {
  private long lifetime;
  
  private Block originBlock;
  
  private int originMeta;
  
  private String protectedPlayer;
  
  public void setOriginBlock(Block originBlock) {
    this.originBlock = originBlock;
  }
  
  public void setOriginMeta(int originMeta) {
    this.originMeta = originMeta;
  }
  
  public void setProtectedPlayer(String protectedPlayer) {
    this.protectedPlayer = protectedPlayer;
  }
  
  public TileEntityAncientHammerBlockCorrupted() {
    this.lifetime = UniversalTimeUtils.now() + 10000L;
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.field_72995_K) {
      for (int i = 0; i < 2; i++) {
        double x = this.field_145851_c + 0.5D + this.field_145850_b.field_73012_v.nextDouble() - 0.5D;
        double y = this.field_145848_d + 0.25D;
        double z = this.field_145849_e + 0.5D + this.field_145850_b.field_73012_v.nextDouble() - 0.5D;
        this.field_145850_b.func_72869_a("portal", x, y, z, 0.0D, 0.4D, 0.0D);
      } 
      return;
    } 
    if (UniversalTimeUtils.now() > this.lifetime) {
      func_145843_s();
      if (this.originBlock != null) {
        this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.originBlock, this.originMeta, 3);
      } else {
        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      } 
      this.field_145850_b.func_72926_e(2001, this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149682_b(this.originBlock) + (this.originMeta << 12));
      return;
    } 
    AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a(this.field_145851_c, (this.field_145848_d + 1), this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 2), (this.field_145849_e + 1));
    List<EntityPlayer> players = this.field_145850_b.func_72872_a(EntityPlayer.class, boundingBox);
    for (EntityPlayer player : players) {
      if (this.protectedPlayer != null && this.protectedPlayer.equals(player.func_70005_c_()))
        continue; 
      player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, 2));
      player.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 200, 0));
    } 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.lifetime = compound.func_74763_f("lifetime");
    this.originBlock = Block.func_149729_e(compound.func_74762_e("originBlockId"));
    this.originMeta = compound.func_74762_e("originMeta");
    this.protectedPlayer = compound.func_74779_i("protectedPlayer");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74772_a("lifetime", this.lifetime);
    compound.func_74768_a("originBlockId", (this.originBlock != null) ? Block.func_149682_b(this.originBlock) : 0);
    compound.func_74768_a("originMeta", this.originMeta);
    compound.func_74778_a("protectedPlayer", (this.protectedPlayer != null) ? this.protectedPlayer : "");
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void setLifetime(long durationMillis) {
    this.lifetime = UniversalTimeUtils.now() + durationMillis;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileEntityAncientHammerBlockCorrupted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */