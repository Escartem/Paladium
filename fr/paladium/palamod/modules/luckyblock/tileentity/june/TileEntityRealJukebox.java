package fr.paladium.palamod.modules.luckyblock.tileentity.june;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockJukebox;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityRealJukebox extends BlockJukebox.TileEntityJukebox implements IEntityAdditionalSpawnData {
  public TileEntityRealJukebox(boolean palaBestSoundDone, boolean palaBestSoundStep1, boolean palaBestSoundStep2, boolean hasGivedItem) {
    this.palaBestSoundDone = palaBestSoundDone;
    this.palaBestSoundStep1 = palaBestSoundStep1;
    this.palaBestSoundStep2 = palaBestSoundStep2;
    this.hasGivedItem = hasGivedItem;
  }
  
  public void setPalaBestSoundStep1(boolean palaBestSoundStep1) {
    this.palaBestSoundStep1 = palaBestSoundStep1;
  }
  
  public void setPalaBestSoundStep2(boolean palaBestSoundStep2) {
    this.palaBestSoundStep2 = palaBestSoundStep2;
  }
  
  public void setHasGivedItem(boolean hasGivedItem) {
    this.hasGivedItem = hasGivedItem;
  }
  
  private boolean palaBestSoundDone = false;
  
  public boolean isPalaBestSoundDone() {
    return this.palaBestSoundDone;
  }
  
  public void setPalaBestSoundDone(boolean palaBestSoundDone) {
    this.palaBestSoundDone = palaBestSoundDone;
  }
  
  private boolean palaBestSoundStep1 = false;
  
  public boolean isPalaBestSoundStep1() {
    return this.palaBestSoundStep1;
  }
  
  private boolean palaBestSoundStep2 = false;
  
  public boolean isPalaBestSoundStep2() {
    return this.palaBestSoundStep2;
  }
  
  private boolean hasGivedItem = false;
  
  public boolean isHasGivedItem() {
    return this.hasGivedItem;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("palaBestSoundDone"))
      this.palaBestSoundDone = compound.func_74767_n("palaBestSoundDone"); 
    if (compound.func_74764_b("palaBestSoundStep1"))
      this.palaBestSoundStep1 = compound.func_74767_n("palaBestSoundStep1"); 
    if (compound.func_74764_b("palaBestSoundStep2"))
      this.palaBestSoundStep2 = compound.func_74767_n("palaBestSoundStep2"); 
    if (compound.func_74764_b("hasGivedItem"))
      this.hasGivedItem = compound.func_74767_n("hasGivedItem"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74757_a("palaBestSoundDone", this.palaBestSoundDone);
    compound.func_74757_a("palaBestSoundStep1", this.palaBestSoundStep1);
    compound.func_74757_a("palaBestSoundStep2", this.palaBestSoundStep2);
    compound.func_74757_a("hasGivedItem", this.hasGivedItem);
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    dataTag.func_74757_a("palaBestSoundDone", this.palaBestSoundDone);
    dataTag.func_74757_a("palaBestSoundStep1", this.palaBestSoundStep1);
    dataTag.func_74757_a("palaBestSoundStep2", this.palaBestSoundStep2);
    dataTag.func_74757_a("hasGivedItem", this.hasGivedItem);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("palaBestSoundDone"))
      this.palaBestSoundDone = nbtData.func_74767_n("palaBestSoundDone"); 
    if (nbtData.func_74764_b("palaBestSoundStep1"))
      this.palaBestSoundStep1 = nbtData.func_74767_n("palaBestSoundStep1"); 
    if (nbtData.func_74764_b("palaBestSoundStep2 "))
      this.palaBestSoundStep2 = nbtData.func_74767_n("palaBestSoundStep2"); 
    if (nbtData.func_74764_b("hasGivedItem"))
      this.hasGivedItem = nbtData.func_74767_n("hasGivedItem"); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeBoolean(this.palaBestSoundDone);
    buffer.writeBoolean(this.hasGivedItem);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable()) {
      this.palaBestSoundDone = additionalData.readBoolean();
      this.hasGivedItem = additionalData.readBoolean();
    } 
  }
  
  public void eventPlay(String discName, boolean hasGived) {
    if (!this.palaBestSoundDone) {
      if (ItemsRegister.DISC_ROULETTE_PALADIENNE.func_77658_a().equals(discName)) {
        this.palaBestSoundStep1 = true;
        this.palaBestSoundStep2 = false;
      } else if (ItemsRegister.DISC_ANTI_FUZE.func_77658_a().equals(discName) && this.palaBestSoundStep1) {
        this.palaBestSoundStep2 = true;
      } else if (ItemsRegister.DISC_CLASH_KUMIZ.func_77658_a().equals(discName) && this.palaBestSoundStep1 && this.palaBestSoundStep2) {
        if (!hasGived) {
          ItemStack stack = new ItemStack(ItemsRegister.DISC_PALADIUM_BEST_SOUND);
          PlayerUtils.dropItemStack(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, stack);
        } 
        this.hasGivedItem = true;
        this.palaBestSoundDone = true;
      } else {
        this.palaBestSoundStep1 = false;
        this.palaBestSoundStep2 = false;
      } 
      func_70296_d();
    } 
  }
  
  public TileEntityRealJukebox() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\june\TileEntityRealJukebox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */