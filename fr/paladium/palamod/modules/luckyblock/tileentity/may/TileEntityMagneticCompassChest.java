package fr.paladium.palamod.modules.luckyblock.tileentity.may;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagneticCompassChest extends TileEntity {
  private List<String> usedBy = new ArrayList<>();
  
  public boolean use(String playerUUID) {
    if (this.usedBy.contains(playerUUID))
      return false; 
    this.usedBy.add(playerUUID);
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
    return true;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("usedBy")) {
      NBTTagList tagList = compound.func_150295_c("usedBy", 8);
      this.usedBy = new ArrayList<>();
      for (int i = 0; i < tagList.func_74745_c(); i++) {
        String value = tagList.func_150307_f(i);
        this.usedBy.add(value);
      } 
    } 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.usedBy != null) {
      NBTTagList tagList = new NBTTagList();
      for (String str : this.usedBy)
        tagList.func_74742_a((NBTBase)new NBTTagString(str)); 
      compound.func_74782_a("usedBy", (NBTBase)tagList);
    } 
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.usedBy != null) {
      NBTTagList tagList = new NBTTagList();
      for (String str : this.usedBy)
        tagList.func_74742_a((NBTBase)new NBTTagString(str)); 
      dataTag.func_74782_a("usedBy", (NBTBase)tagList);
    } 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("usedBy")) {
      NBTTagList tagList = nbtData.func_150295_c("usedBy", 8);
      this.usedBy = new ArrayList<>();
      for (int i = 0; i < tagList.func_74745_c(); i++) {
        String value = tagList.func_150307_f(i);
        this.usedBy.add(value);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\may\TileEntityMagneticCompassChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */