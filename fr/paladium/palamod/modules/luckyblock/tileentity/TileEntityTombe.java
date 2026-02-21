package fr.paladium.palamod.modules.luckyblock.tileentity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTombe extends TileEntity implements IEntityAdditionalSpawnData {
  private String owner = null;
  
  public String getOwner() {
    return this.owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  private ItemStack[] inventory = new ItemStack[64];
  
  public ItemStack[] getInventory() {
    return this.inventory;
  }
  
  public void setInventory(ItemStack[] inventory) {
    this.inventory = inventory;
  }
  
  Random r = new Random();
  
  public TileEntityTombe(String owner) {
    this.owner = owner;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      this.owner = compound.func_74779_i("owner"); 
    NBTTagList nbttaglist = compound.func_150295_c("Items", 64);
    this.inventory = new ItemStack[getSizeInventory()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte b0 = nbttagcompound1.func_74771_c("Slot");
      if (b0 >= 0 && b0 < this.inventory.length)
        this.inventory[b0] = ItemStack.func_77949_a(nbttagcompound1); 
    } 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.owner != null)
      compound.func_74778_a("owner", this.owner); 
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.inventory.length; i++) {
      if (this.inventory[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.inventory[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      } 
    } 
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K && System.currentTimeMillis() % 500L < 100L)
      EventUtils.spawnParticle(this.field_145850_b, (this.field_145847_g == 3) ? "witchMagic" : ((this.field_145847_g == 2) ? "flame" : "snowshovel"), this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, 1, 0.05D); 
    super.func_145845_h();
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    if (this.owner != null)
      dataTag.func_74778_a("owner", this.owner); 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("owner"))
      this.owner = nbtData.func_74779_i("owner"); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.owner != null)
      ByteBufUtils.writeUTF8String(buffer, this.owner); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.owner = ByteBufUtils.readUTF8String(additionalData); 
  }
  
  public int getSizeInventory() {
    return this.inventory.length;
  }
  
  public TileEntityTombe() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityTombe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */