package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.LuckyBlockManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityChatDetector extends TileEntity {
  private String word = "";
  
  private int meta;
  
  public String getWord() {
    return this.word;
  }
  
  public void setWord(String word) {
    this.word = word;
    func_70296_d();
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void func_145845_h() {
    if (this.word == "")
      return; 
    if (this.field_145850_b.func_82737_E() % 20L == 0L && !this.field_145850_b.field_72995_K) {
      func_70296_d();
      this.meta = 0;
      for (String msg : LuckyBlockManager.getInstance().getLast10Chat()) {
        if (msg.toUpperCase().contains(this.word.toString().toUpperCase())) {
          this.meta = 1;
          break;
        } 
      } 
      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.meta, 3);
    } 
    super.func_145845_h();
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    if (this.word != "")
      nbt.func_74778_a("word", this.word); 
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    this.word = nbt.func_74779_i("word");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityChatDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */