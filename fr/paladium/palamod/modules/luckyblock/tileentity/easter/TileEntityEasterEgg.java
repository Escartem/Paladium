package fr.paladium.palamod.modules.luckyblock.tileentity.easter;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEasterEgg extends TileEntity implements IEntityAdditionalSpawnData {
  private Integer color = Integer.valueOf(0);
  
  public static final int COLOR_BLUE = 1;
  
  public static final int COLOR_YELLOW = 2;
  
  public Integer getColor() {
    return this.color;
  }
  
  public void setColor(Integer color) {
    this.color = color;
  }
  
  public static int getCOLOR_BLUE() {
    return 1;
  }
  
  public static int getCOLOR_YELLOW() {
    return 2;
  }
  
  public TileEntityEasterEgg(int color) {
    this.color = Integer.valueOf(color);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("color"))
      this.color = Integer.valueOf(compound.func_74762_e("color")); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.color != null)
      compound.func_74768_a("color", this.color.intValue()); 
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.color != null)
      dataTag.func_74768_a("color", this.color.intValue()); 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("color"))
      this.color = Integer.valueOf(nbtData.func_74762_e("color")); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.color != null)
      ByteBufUtils.writeVarInt(buffer, this.color.intValue(), 4); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.color = Integer.valueOf(ByteBufUtils.readVarInt(additionalData, 4)); 
  }
  
  public TileEntityEasterEgg() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\easter\TileEntityEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */