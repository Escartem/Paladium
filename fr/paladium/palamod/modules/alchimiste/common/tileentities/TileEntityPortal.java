package fr.paladium.palamod.modules.alchimiste.common.tileentities;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPortal extends TileEntity {
  private int controllerX;
  
  private int controllerY;
  
  private int controllerZ;
  
  private TileEntityAlchemistPortalController controller = null;
  
  public boolean canUpdate() {
    return true;
  }
  
  public void func_145845_h() {
    int m = func_145832_p();
    Block b = this.field_145850_b.func_147439_a(this.controllerX, this.controllerY, this.controllerZ);
    if (b != null && this.controller == null)
      this.controller = (TileEntityAlchemistPortalController)this.field_145850_b.func_147438_o(this.controllerX, this.controllerY, this.controllerZ); 
    if (m == 0) {
      if (b != BlocksRegister.AMETHYSTE_PORTAL_BLOCK)
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a); 
    } else if (m == 1) {
      if (b != BlocksRegister.TITANE_PORTAL_BLOCK)
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a); 
    } else if (m == 2) {
      if (b != BlocksRegister.PALADIUM_PORTAL_BLOCK)
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a); 
    } else if (m == 3 && 
      b != BlocksRegister.ENDIUM_PORTAL_BLOCK) {
      this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a);
    } 
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    super.func_145841_b(tag);
    tag.func_74768_a("controllerX", this.controllerX);
    tag.func_74768_a("controllerY", this.controllerY);
    tag.func_74768_a("controllerZ", this.controllerZ);
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    this.controllerX = tag.func_74762_e("controllerX");
    this.controllerY = tag.func_74762_e("controllerY");
    this.controllerZ = tag.func_74762_e("controllerZ");
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
  
  public void setControllerX(int controllerX) {
    this.controllerX = controllerX;
  }
  
  public void setControllerY(int controllerY) {
    this.controllerY = controllerY;
  }
  
  public void setControllerZ(int controllerZ) {
    this.controllerZ = controllerZ;
  }
  
  public int getControllerX() {
    return this.controllerX;
  }
  
  public int getControllerY() {
    return this.controllerY;
  }
  
  public int getControllerZ() {
    return this.controllerZ;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\tileentities\TileEntityPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */