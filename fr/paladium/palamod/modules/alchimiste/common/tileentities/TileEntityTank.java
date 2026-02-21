package fr.paladium.palamod.modules.alchimiste.common.tileentities;

import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumTank;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityTank extends TileEntity {
  public EnumTank tank;
  
  public String liquid;
  
  public int liquidLevel;
  
  public TileEntityTank() {}
  
  public TileEntityTank(EnumTank tank) {
    this.tank = tank;
    this.liquid = "";
    this.liquidLevel = 0;
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    super.func_145841_b(tag);
    tag.func_74778_a("tank", this.tank.toString());
    tag.func_74778_a("liquid", this.liquid);
    tag.func_74768_a("liquidLevel", this.liquidLevel);
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    this.tank = EnumTank.valueOf(tag.func_74779_i("tank"));
    this.liquid = tag.func_74779_i("liquid");
    this.liquidLevel = tag.func_74762_e("liquidLevel");
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
  
  public EnumTank getTank() {
    return this.tank;
  }
  
  public void setTank(EnumTank tank) {
    this.tank = tank;
  }
  
  public String getLiquid() {
    return this.liquid;
  }
  
  public void setLiquid(String liquid) {
    this.liquid = liquid;
    if (!this.field_145850_b.field_72995_K) {
      List<EntityPlayer> players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 8), 0.0D, (this.field_145849_e - 8), (this.field_145851_c + 8), 255.0D, (this.field_145849_e + 8)));
      for (EntityPlayer player : players) {
        if (player instanceof EntityPlayerMP)
          ((EntityPlayerMP)player).field_71135_a.func_147359_a(func_145844_m()); 
      } 
    } 
  }
  
  public int getLiquidLevel() {
    return this.liquidLevel;
  }
  
  public void setLiquidLevel(int liquidLevel) {
    this.liquidLevel = liquidLevel;
    if (!this.field_145850_b.field_72995_K) {
      List<EntityPlayer> players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 8), 0.0D, (this.field_145849_e - 8), (this.field_145851_c + 8), 255.0D, (this.field_145849_e + 8)));
      for (EntityPlayer player : players) {
        if (player instanceof EntityPlayerMP)
          ((EntityPlayerMP)player).field_71135_a.func_147359_a(func_145844_m()); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\tileentities\TileEntityTank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */