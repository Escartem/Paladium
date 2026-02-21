package fr.paladium.palaautomation.common.tile.pipe;

import fr.paladium.palaautomation.common.block.pipe.PipeType;
import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.ITileFacing;
import fr.paladium.palaautomation.common.util.IntLocation;
import fr.paladium.palaautomation.common.util.PipeError;
import fr.paladium.palaautomation.common.util.PipeNetwork;
import fr.paladium.palaautomation.common.util.PipeUtils;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class ATileEntityPipe extends TileEntity implements ITileFacing {
  public void setLastTransactionMs(long lastTransactionMs) {
    this.lastTransactionMs = lastTransactionMs;
  }
  
  public void setType(PipeType type) {
    this.type = type;
  }
  
  public void setClientAngles(EnumPipeFacing[] clientAngles) {
    this.clientAngles = clientAngles;
  }
  
  public void setError(PipeError error) {
    this.error = error;
  }
  
  public void setParentLocation(IntLocation parentLocation) {
    this.parentLocation = parentLocation;
  }
  
  public void setParentPipe(ATileEntityPipe parentPipe) {
    this.parentPipe = parentPipe;
  }
  
  public static final long CHECK_INTERVAL_MS = TimeUnit.SECONDS.toMillis(10L);
  
  private static final String TAG_FACING = "facing";
  
  private static final String TAG_TYPE = "type";
  
  private static final String TAG_PARENT = "parent";
  
  private static final String TAG_ERROR = "error";
  
  private transient long lastTransactionMs;
  
  private EnumPipeFacing facing;
  
  private PipeType type;
  
  private transient EnumPipeFacing[] clientAngles;
  
  private transient PipeError error;
  
  private transient IntLocation parentLocation;
  
  private transient ATileEntityPipe parentPipe;
  
  public long getLastTransactionMs() {
    return this.lastTransactionMs;
  }
  
  public EnumPipeFacing getFacing() {
    return this.facing;
  }
  
  public PipeType getType() {
    return this.type;
  }
  
  public EnumPipeFacing[] getClientAngles() {
    return this.clientAngles;
  }
  
  public PipeError getError() {
    return this.error;
  }
  
  public IntLocation getParentLocation() {
    return this.parentLocation;
  }
  
  public ATileEntityPipe getParentPipe() {
    return this.parentPipe;
  }
  
  public ATileEntityPipe() {
    this(PipeType.PALADIUM);
  }
  
  public ATileEntityPipe(PipeType type) {
    this.facing = EnumPipeFacing.NORTH;
    this.type = type;
    this.error = null;
  }
  
  public void updateClientFacing(EnumPipeFacing facing) {
    updateClientFacing(facing, (EnumPipeFacing)null);
  }
  
  public void updateClientFacing(EnumPipeFacing firstFacing, EnumPipeFacing secondFacing) {
    if (firstFacing == null && secondFacing == null) {
      this.clientAngles = new EnumPipeFacing[0];
      return;
    } 
    if (firstFacing != null && secondFacing == null) {
      this.clientAngles = new EnumPipeFacing[] { firstFacing };
      return;
    } 
    this.clientAngles = new EnumPipeFacing[] { firstFacing, secondFacing };
  }
  
  public boolean hasError() {
    return (this.error != null && this.error != PipeError.NO_ERROR);
  }
  
  public boolean error(PipeError error) {
    if (this.error == error)
      return false; 
    this.error = error;
    return true;
  }
  
  public boolean canTransfer(PipeType type, long currentMs) {
    return (currentMs - this.lastTransactionMs >= type.getCooldownMs());
  }
  
  public void onTransfer(long currentMs) {
    this.lastTransactionMs = currentMs;
  }
  
  private boolean isHorizontal() {
    return (this.facing == EnumPipeFacing.NORTH || this.facing == EnumPipeFacing.SOUTH || this.facing == EnumPipeFacing.WEST || this.facing == EnumPipeFacing.EAST);
  }
  
  private boolean isVertical() {
    return (this.facing == EnumPipeFacing.UP || this.facing == EnumPipeFacing.DOWN);
  }
  
  private boolean hasChild() {
    if (this.facing == null)
      return false; 
    Set<EnumPipeFacing> possibleDirections = PipeUtils.getPossibleChildDirections(this.facing);
    for (EnumPipeFacing facing : possibleDirections) {
      ATileEntityPipe pipe = getPipe(facing);
      if (pipe != null)
        return true; 
    } 
    return false;
  }
  
  public boolean hasParent() {
    return (this.parentPipe != null);
  }
  
  public ATileEntityPipe getParent() {
    if (this.parentPipe != null)
      return this.parentPipe; 
    if (this.parentLocation != null) {
      TileEntity tile = this.field_145850_b.func_147438_o(this.parentLocation.getX(), this.parentLocation.getY(), this.parentLocation.getZ());
      if (tile instanceof ATileEntityPipe) {
        this.parentPipe = (ATileEntityPipe)tile;
        return this.parentPipe;
      } 
    } 
    return null;
  }
  
  public void updateParent(ATileEntityPipe parent) {
    if (parent == null) {
      this.parentPipe = null;
      this.parentLocation = null;
    } else {
      this.parentPipe = parent;
      this.parentLocation = IntLocation.of(parent.field_145851_c, parent.field_145848_d, parent.field_145849_e);
    } 
  }
  
  public boolean changeParent(ATileEntityPipe newParent) {
    ATileEntityPipe oldParent = getParent();
    if (newParent == null && oldParent != null) {
      updateParent((ATileEntityPipe)null);
      return true;
    } 
    if (oldParent == null) {
      if (newParent == null)
        return false; 
      updateParent(newParent);
      return true;
    } 
    if (oldParent.equals(newParent))
      return false; 
    updateParent(newParent);
    return true;
  }
  
  public boolean equals(Object obj) {
    if (!(obj instanceof ATileEntityPipe))
      return false; 
    ATileEntityPipe pipe = (ATileEntityPipe)obj;
    return (this.field_145851_c == pipe.field_145851_c && this.field_145848_d == pipe.field_145848_d && this.field_145849_e == pipe.field_145849_e && this.facing.equals(pipe.facing));
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.field_145851_c), Integer.valueOf(this.field_145848_d), Integer.valueOf(this.field_145849_e), this.facing });
  }
  
  public boolean receiveFromMachine(IPipeMachine machine) {
    if (machine == null)
      return false; 
    if (machine.shouldCheckSideFromPipe() && 
      !machine.getFacing().equals(this.facing))
      return false; 
    PipeNetwork network = PipeNetwork.of(machine, this);
    if (network == null)
      return false; 
    machine.setLastNetWorkCheckMs(System.currentTimeMillis());
    if (!network.canTransfer())
      return false; 
    IPipeMachine outputMachine = network.getReceiver();
    if (outputMachine == null) {
      network.error(PipeError.NO_RECEIVER);
      return false;
    } 
    if (!outputMachine.receiveFromPipe(machine.getItemData())) {
      network.error(PipeError.RECEIVER_FULL);
      return false;
    } 
    network.error(PipeError.NO_ERROR);
    return true;
  }
  
  private ATileEntityPipe getPipe(EnumPipeFacing facing) {
    IntLocation location = IntLocation.of(this.field_145851_c + facing.getFrontOffsetX(), this.field_145848_d + facing.getFrontOffsetY(), this.field_145849_e + facing.getFrontOffsetZ());
    TileEntity tile = this.field_145850_b.func_147438_o(location.getX(), location.getY(), location.getZ());
    return (tile instanceof ATileEntityPipe) ? (ATileEntityPipe)tile : null;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("facing", this.facing.ordinal());
    compound.func_74768_a("type", this.type.ordinal());
    if (this.parentLocation != null) {
      compound.func_74783_a("parent", this.parentLocation.toIntArray());
    } else {
      compound.func_82580_o("parent");
    } 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.facing = EnumPipeFacing.values()[compound.func_74762_e("facing")];
    this.type = PipeType.values()[compound.func_74762_e("type")];
    if (this.facing != null)
      updateClientFacing(this.facing); 
    if (compound.func_74764_b("parent"))
      this.parentLocation = IntLocation.of(compound.func_74759_k("parent")); 
    if (compound.func_74764_b("error"))
      this.error = PipeError.values()[compound.func_74762_e("error")]; 
  }
  
  public void func_145845_h() {
    super.func_145845_h();
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    if (this.error != null) {
      nbtTag.func_74768_a("error", this.error.ordinal());
    } else {
      nbtTag.func_82580_o("error");
    } 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void setFacing(EnumPipeFacing facing) {
    this.facing = facing;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\pipe\ATileEntityPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */