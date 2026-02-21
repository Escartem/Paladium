package fr.paladium.palamod.modules.miner.dimminer.common.data;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PortalData {
  private final String server;
  
  private final List<DoubleLocation> blocks;
  
  public PortalData(String server, List<DoubleLocation> blocks) {
    this.server = server;
    this.blocks = blocks;
  }
  
  public String getServer() {
    return this.server;
  }
  
  public List<DoubleLocation> getBlocks() {
    return this.blocks;
  }
  
  public PortalData(@NonNull List<DoubleLocation> blocks) throws UnknownHostException {
    if (blocks == null)
      throw new NullPointerException("blocks is marked non-null but is null"); 
    this.server = InetAddress.getLocalHost().getHostName();
    this.blocks = blocks;
  }
  
  @NonNull
  public NBTTagCompound toNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("Server", this.server);
    NBTTagList list = new NBTTagList();
    for (DoubleLocation location : this.blocks) {
      NBTTagCompound block = new NBTTagCompound();
      block.func_74768_a("X", location.getBlockX());
      block.func_74768_a("Y", location.getBlockY());
      block.func_74768_a("Z", location.getBlockZ());
      list.func_74742_a((NBTBase)block);
    } 
    compound.func_74782_a("Blocks", (NBTBase)list);
    return compound;
  }
  
  public static PortalData fromNBT(@NonNull NBTTagCompound compound) {
    if (compound == null)
      throw new NullPointerException("compound is marked non-null but is null"); 
    if (!compound.func_74764_b("Server") || !compound.func_74764_b("Blocks"))
      return null; 
    String server = compound.func_74779_i("Server");
    List<DoubleLocation> blocks = new ArrayList<>();
    NBTTagList list = compound.func_150295_c("Blocks", 10);
    for (int i = 0; i < list.func_74745_c(); i++) {
      NBTTagCompound block = list.func_150305_b(i);
      blocks.add(new DoubleLocation(block.func_74762_e("X"), block.func_74762_e("Y"), block.func_74762_e("Z")));
    } 
    return new PortalData(server, blocks);
  }
  
  public boolean isSameServer() {
    try {
      return InetAddress.getLocalHost().getHostName().equals(this.server);
    } catch (UnknownHostException e) {
      return false;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\data\DimMinerPlayer$PortalData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */