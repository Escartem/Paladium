package fr.paladium.palamod.modules.spellsv2.tile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInertiumBlock extends TileEntity {
  public ItemStack block;
  
  public void setInfo(ItemStack stack) {
    if (stack != null) {
      this.block = stack;
    } else {
      this.block = new ItemStack(Blocks.field_150348_b);
    } 
    func_70296_d();
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound tagCompound = new NBTTagCompound();
    func_145841_b(tagCompound);
    S35PacketUpdateTileEntity pack = new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, tagCompound);
    return (Packet)pack;
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    super.func_145841_b(tag);
    if (this.block != null)
      this.block.func_77955_b(tag); 
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    this.block = ItemStack.func_77949_a(tag);
  }
  
  public Block getBlock() {
    if (this.block == null || Block.func_149634_a(this.block.func_77973_b()) == null)
      return Blocks.field_150348_b; 
    return Block.func_149634_a(this.block.func_77973_b());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\tile\TileEntityInertiumBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */