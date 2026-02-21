package fr.paladium.palamod.modules.luckyblock.tileentity.may;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDigicode extends TileEntity {
  private boolean master = false;
  
  private Integer code = null;
  
  public Integer getCode() {
    return this.code;
  }
  
  private Integer displayNumber = null;
  
  public Integer getDisplayNumber() {
    return this.displayNumber;
  }
  
  public void setCode(int code) {
    this.code = Integer.valueOf(code);
    this.displayNumber = Integer.valueOf(code);
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
    onDisplayNumberChange();
    notifyChange();
  }
  
  public void onDisplayNumberChange() {
    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.displayNumber.intValue(), 2);
  }
  
  public void notifyChange() {
    this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, BlocksRegister.DIGICODE);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("master"))
      this.master = compound.func_74767_n("master"); 
    if (compound.func_74764_b("code"))
      this.code = Integer.valueOf(compound.func_74762_e("code")); 
    if (compound.func_74764_b("displayNumber"))
      this.displayNumber = Integer.valueOf(compound.func_74762_e("displayNumber")); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74757_a("master", this.master);
    if (this.code != null)
      compound.func_74768_a("code", this.code.intValue()); 
    if (this.displayNumber == null)
      this.displayNumber = Integer.valueOf(0); 
    compound.func_74768_a("displayNumber", this.displayNumber.intValue());
    super.func_145841_b(compound);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    dataTag.func_74757_a("master", this.master);
    if (this.code != null)
      dataTag.func_74768_a("code", this.code.intValue()); 
    if (this.displayNumber == null)
      this.displayNumber = Integer.valueOf(0); 
    dataTag.func_74768_a("displayNumber", this.displayNumber.intValue());
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    if (nbtData.func_74764_b("master"))
      this.master = nbtData.func_74767_n("master"); 
    if (nbtData.func_74764_b("code"))
      this.code = Integer.valueOf(nbtData.func_74762_e("code")); 
    if (nbtData.func_74764_b("displayNumber"))
      this.displayNumber = Integer.valueOf(nbtData.func_74762_e("displayNumber")); 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public boolean isInit() {
    if (this.code != null)
      return true; 
    return false;
  }
  
  public void forceDiplayNumber(int displayNumber) {
    this.displayNumber = Integer.valueOf(displayNumber);
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
    onDisplayNumberChange();
  }
  
  public void updateDiplayNumber() {
    if (this.displayNumber != null) {
      Integer integer1 = this.displayNumber, integer2 = this.displayNumber = Integer.valueOf(this.displayNumber.intValue() + 1);
      if (this.displayNumber.intValue() >= 10)
        this.displayNumber = Integer.valueOf(1); 
    } 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
    onDisplayNumberChange();
    if (!this.master)
      updateMaster(); 
  }
  
  public void removeNotInit() {
    if (!isInit()) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      PlayerUtils.dropItemStack(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, new ItemStack(BlocksRegister.DIGICODE));
    } 
  }
  
  public boolean generatePower() {
    if (this.master && isGoodCode() && checkNeighborCode())
      return true; 
    if (!this.master) {
      TileEntityDigicode master = getMaster();
      if (master != null)
        return master.generatePower(); 
    } 
    return false;
  }
  
  public boolean isGoodCode() {
    if (this.displayNumber != null && this.code != null && this.displayNumber == this.code)
      return true; 
    return false;
  }
  
  public boolean checkNeighborCode() {
    boolean hasNeighbor = true;
    int i = 1;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c + i, this.field_145848_d, this.field_145849_e);
      if (neighbor != null) {
        if (!neighbor.isGoodCode())
          return false; 
      } else {
        hasNeighbor = false;
      } 
      i++;
    } while (hasNeighbor);
    i = -1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c + i, this.field_145848_d, this.field_145849_e);
      if (neighbor != null) {
        if (!neighbor.isGoodCode())
          return false; 
      } else {
        hasNeighbor = false;
      } 
      i--;
    } while (hasNeighbor);
    i = 1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
      if (neighbor != null) {
        if (!neighbor.isGoodCode())
          return false; 
      } else {
        hasNeighbor = false;
      } 
      i++;
    } while (hasNeighbor);
    i = -1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
      if (neighbor != null) {
        if (!neighbor.isGoodCode())
          return false; 
      } else {
        hasNeighbor = false;
      } 
      i--;
    } while (hasNeighbor);
    i = 1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d, this.field_145849_e + i);
      if (neighbor != null) {
        if (!neighbor.isGoodCode())
          return false; 
      } else {
        hasNeighbor = false;
      } 
      i++;
    } while (hasNeighbor);
    i = -1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d, this.field_145849_e + i);
      if (neighbor != null) {
        if (!neighbor.isGoodCode())
          return false; 
      } else {
        hasNeighbor = false;
      } 
      i--;
    } while (hasNeighbor);
    return true;
  }
  
  public void updateMaster() {
    TileEntityDigicode tileMaster = getMaster();
    if (tileMaster == null) {
      setMaster();
    } else {
      tileMaster.notifyChange();
    } 
  }
  
  public TileEntityDigicode getMaster() {
    boolean hasNeighbor = true;
    int i = 1;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c + i, this.field_145848_d, this.field_145849_e);
      if (neighbor != null && neighbor.isMaster())
        return neighbor; 
      hasNeighbor = false;
      i++;
    } while (hasNeighbor);
    i = -1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c + i, this.field_145848_d, this.field_145849_e);
      if (neighbor != null && neighbor.isMaster())
        return neighbor; 
      hasNeighbor = false;
      i--;
    } while (hasNeighbor);
    i = 1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
      if (neighbor != null && neighbor.isMaster())
        return neighbor; 
      hasNeighbor = false;
      i++;
    } while (hasNeighbor);
    i = -1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
      if (neighbor != null && neighbor.isMaster())
        return neighbor; 
      hasNeighbor = false;
      i--;
    } while (hasNeighbor);
    i = 1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d, this.field_145849_e + i);
      if (neighbor != null && neighbor.isMaster())
        return neighbor; 
      hasNeighbor = false;
      i++;
    } while (hasNeighbor);
    i = -1;
    hasNeighbor = true;
    do {
      TileEntityDigicode neighbor = getNeighborDigicode(this.field_145851_c, this.field_145848_d, this.field_145849_e + i);
      if (neighbor != null && neighbor.isMaster())
        return neighbor; 
      hasNeighbor = false;
      i--;
    } while (hasNeighbor);
    return null;
  }
  
  public void setMaster() {
    if (getMaster() == null) {
      this.master = true;
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      func_70296_d();
    } 
  }
  
  public boolean isMaster() {
    return this.master;
  }
  
  public boolean isNeighborDigicode(int x, int y, int z) {
    if (getNeighborDigicode(x, y, z) != null)
      return true; 
    return false;
  }
  
  public TileEntityDigicode getNeighborDigicode(int x, int y, int z) {
    TileEntity tile = this.field_145850_b.func_147438_o(x, y, z);
    if (tile != null && tile instanceof TileEntityDigicode)
      return (TileEntityDigicode)tile; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\may\TileEntityDigicode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */