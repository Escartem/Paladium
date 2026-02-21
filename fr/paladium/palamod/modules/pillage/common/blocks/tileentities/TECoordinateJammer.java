package fr.paladium.palamod.modules.pillage.common.blocks.tileentities;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TECoordinateJammer extends TileEntity {
  private Block blockToCopy;
  
  private BlockPos blockPosToCopy;
  
  private GameProfile owner;
  
  private int fakeX;
  
  private int fakeZ;
  
  public Block getBlockToCopy() {
    return this.blockToCopy;
  }
  
  public BlockPos getBlockPosToCopy() {
    return this.blockPosToCopy;
  }
  
  public GameProfile getOwner() {
    return this.owner;
  }
  
  public int getFakeX() {
    return this.fakeX;
  }
  
  public int getFakeZ() {
    return this.fakeZ;
  }
  
  public void setBlockToCopy(Block blockToCopy, BlockPos blockPosToCopy) {
    this.blockToCopy = blockToCopy;
    this.blockPosToCopy = blockPosToCopy;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public void setOwner(GameProfile owner) {
    this.owner = owner;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public void randCoordX(int max, int min) {
    this.fakeX = (int)(Math.random() * (max - min)) + min;
  }
  
  public void randCoordZ(int max, int min) {
    this.fakeZ = (int)(Math.random() * (max - min)) + min;
  }
  
  public boolean isPlayerInRange(EntityPlayer player) {
    return (player.func_70011_f(this.field_145851_c, this.field_145848_d, this.field_145849_e) <= 50.0D);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (compound.func_74767_n("HasIdentity")) {
      this
        .blockToCopy = GameRegistry.findBlock(compound.func_74779_i("ModID"), compound.func_74779_i("BlockName"));
      if (compound.func_74764_b("BlockPos")) {
        NBTTagCompound pos = compound.func_74775_l("BlockPos");
        this
          .blockPosToCopy = new BlockPos(pos.func_74762_e("x"), pos.func_74762_e("y"), pos.func_74762_e("z"));
      } 
      this.fakeX = compound.func_74762_e("FakeX");
      this.fakeZ = compound.func_74762_e("FakeZ");
      if (compound.func_74767_n("HasOwner"))
        this.owner = NBTUtil.func_152459_a(compound); 
    } 
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    GameRegistry.UniqueIdentifier ident = GameRegistry.findUniqueIdentifierFor(this.blockToCopy);
    if (ident != null) {
      compound.func_74757_a("HasIdentity", true);
      compound.func_74778_a("ModID", ident.modId);
      compound.func_74778_a("BlockName", ident.name);
      if (this.blockPosToCopy != null) {
        NBTTagCompound pos = new NBTTagCompound();
        pos.func_74768_a("x", this.blockPosToCopy.getX());
        pos.func_74768_a("y", this.blockPosToCopy.getY());
        pos.func_74768_a("z", this.blockPosToCopy.getZ());
        compound.func_74782_a("BlockPos", (NBTBase)pos);
      } 
      compound.func_74768_a("FakeX", this.fakeX);
      compound.func_74768_a("FakeZ", this.fakeZ);
      if (this.owner != null) {
        NBTUtil.func_152460_a(compound, this.owner);
        compound.func_74757_a("HasOwner", true);
      } else {
        compound.func_74757_a("HasOwner", false);
      } 
    } else {
      compound.func_74757_a("HasIdentity", false);
    } 
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\tileentities\TECoordinateJammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */