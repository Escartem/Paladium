package fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWitheredObsidian extends TileEntity {
  private int feed;
  
  private boolean created;
  
  public void setFeed(int feed) {
    this.feed = feed;
  }
  
  public void setCreated(boolean created) {
    this.created = created;
  }
  
  public int getFeed() {
    return this.feed;
  }
  
  public boolean isCreated() {
    return this.created;
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.field_72995_K)
      return; 
    if (!this.created && hasPortal())
      this.created = true; 
    if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof net.minecraft.block.BlockFire)
      BlocksRegister.MINER_PORTAL.check(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e); 
  }
  
  public boolean hasPortal() {
    if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal || this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal || this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal)
      return true; 
    if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof fr.paladium.palamod.modules.miner.dimminer.common.block.BlockMinerPortal)
      return true; 
    return false;
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    if (nbt.func_74764_b("feed"))
      this.feed = nbt.func_74762_e("feed"); 
    if (nbt.func_74764_b("created"))
      this.created = nbt.func_74767_n("created"); 
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("feed", this.feed);
    nbt.func_74757_a("created", this.created);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound compound = new NBTTagCompound();
    func_145841_b(compound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\tileentity\TileEntityWitheredObsidian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */