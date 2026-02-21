package fr.paladium.palamod.modules.ajobs.common.tiles;

import fr.paladium.palajobs.api.constant.JobsConstants;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import org.apache.commons.lang.math.RandomUtils;

public class TileEntityRoot extends TileEntity {
  public int getTick() {
    return this.tick;
  }
  
  public boolean isBlocked() {
    return this.blocked;
  }
  
  public boolean isInit() {
    return this.init;
  }
  
  private int tick = 0;
  
  private boolean blocked = false;
  
  private boolean init = false;
  
  public void onSpawn() {
    this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e, BlocksRegistry.ROOT_LAST);
  }
  
  public boolean canUpdateTimer() {
    return (this.tick % 20 == 0);
  }
  
  public Location getMinLocation() {
    Location location = new Location(this.field_145850_b, this.field_145851_c, (this.field_145848_d - 4), this.field_145849_e);
    while (this.field_145850_b.func_147439_a((int)location.getX(), (int)location.getY(), (int)location.getZ()).equals(BlocksRegistry.ROOT_INFERIOR) || this.field_145850_b
      .func_147439_a((int)location.getX(), (int)location.getY(), 
        (int)location.getZ()).equals(BlocksRegistry.ROOT_LAST)) {
      location.setY(location.getY() - 1.0D);
      if (location.getY() <= 0.0D)
        return null; 
    } 
    return location;
  }
  
  public boolean isBlackListedBlock(Block block) {
    for (Block blackListedBlock : JobsConstants.ROOT_BLACKLISTED_BLOCKS) {
      if (block.equals(blackListedBlock))
        return true; 
    } 
    return false;
  }
  
  public boolean canReplaceBlock(Location location) {
    Block block = this.field_145850_b.func_147439_a((int)location.getX(), (int)location.getY(), (int)location.getZ());
    if (isBlackListedBlock(block))
      return false; 
    return true;
  }
  
  public boolean isGrowable() {
    int ret = RandomUtils.nextInt(90);
    return (ret <= 1);
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    this.tick++;
    if (!canUpdateTimer())
      return; 
    if (!this.init) {
      boolean canInit = true;
      for (int i = 1; i <= 4; i++) {
        if (!canReplaceBlock(new Location(this.field_145851_c, (this.field_145848_d - i), this.field_145849_e)))
          canInit = false; 
      } 
      if (!canInit) {
        setBlocked(true);
        return;
      } 
      spawnStructure(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      this.init = true;
    } 
    if (!isGrowable())
      return; 
    if (!isStructureValid()) {
      setBlocked(true);
      return;
    } 
    Location minLocation = getMinLocation();
    if (minLocation == null)
      return; 
    if (!canReplaceBlock(minLocation)) {
      setBlocked(true);
      return;
    } 
    if (this.blocked)
      setBlocked(false); 
    replaceBlocks(minLocation);
  }
  
  public void replaceBlocks(Location location) {
    this.field_145850_b.func_147449_b((int)location.getX(), (int)location.getY() + 1, (int)location.getZ(), BlocksRegistry.ROOT_INFERIOR);
    this.field_145850_b.func_147449_b((int)location.getX(), (int)location.getY(), (int)location.getZ(), BlocksRegistry.ROOT_LAST);
  }
  
  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
    updateClientSide();
  }
  
  public boolean isStructureValid() {
    Location location = new Location(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    for (int i = 1; i < 3; i++) {
      double y = location.getY() - i;
      if (!this.field_145850_b.func_147439_a((int)location.getX(), (int)y, (int)location.getZ()).equals(BlocksRegistry.ROOT_LOG))
        return false; 
    } 
    Location minLocation = getMinLocation();
    if (minLocation == null)
      return false; 
    minLocation.setY(minLocation.getY() + 1.0D);
    if (!minLocation.getBlock().equals(BlocksRegistry.ROOT_LAST))
      return false; 
    return true;
  }
  
  public void spawnStructure(World world, int x, int y, int z) {
    world.func_147449_b(x, y - 1, z, BlocksRegistry.ROOT_LOG);
    world.func_147449_b(x, y - 2, z, BlocksRegistry.ROOT_LOG);
    world.func_147449_b(x, y - 3, z, BlocksRegistry.ROOT_LOG);
    world.func_147449_b(x, y - 4, z, BlocksRegistry.ROOT_LAST);
  }
  
  public void updateClientSide() {
    Packet packet = func_145844_m();
    List<EntityPlayerMP> players = this.field_145850_b.func_72872_a(EntityPlayerMP.class, 
        AxisAlignedBB.func_72330_a((this.field_145851_c - 32), (this.field_145848_d - 32), (this.field_145849_e - 32), (this.field_145851_c + 32), (this.field_145848_d + 32), (this.field_145849_e + 32)));
    for (EntityPlayerMP playerArround : players) {
      EntityPlayerMP playerMPArround = playerArround;
      playerMPArround.field_71135_a.func_147359_a(packet);
    } 
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.blocked = nbt.func_74767_n("blocked");
    this.init = nbt.func_74767_n("init");
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74757_a("blocked", this.blocked);
    nbt.func_74757_a("init", this.init);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\tiles\TileEntityRoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */