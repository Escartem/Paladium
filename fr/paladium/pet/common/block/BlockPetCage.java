package fr.paladium.pet.common.block;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.client.tab.TabPet;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.capture.SCPacketTrapOpen;
import fr.paladium.pet.common.registry.impl.PetItemRegistry;
import fr.paladium.pet.common.tile.cage.CageStatus;
import fr.paladium.pet.common.tile.cage.TileEntityPetCage;
import fr.paladium.pet.common.utils.ItemUtils;
import fr.paladium.pet.common.utils.PetUtils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPetCage extends Block implements ITileEntityProvider {
  public static final String NAME = "pet_cage";
  
  public BlockPetCage() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)TabPet.INSTANCE);
    func_149663_c("pet_cage");
    func_149658_d("palapet:cage");
    func_149711_c(0.6F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer globalPlayer, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntityPetCage cage = getCage(world, x, y, z);
    long now = System.currentTimeMillis();
    EntityPlayerMP player = (EntityPlayerMP)globalPlayer;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (!PetUtils.canUseCraft((EntityPlayer)player, new ItemStack(this))) {
      PetTranslateEnum.MESSAGE_NOT_OWNER.message((ICommandSender)player);
      return false;
    } 
    if (cage == null || pet == null || pet.has()) {
      PetTranslateEnum.MESSAGE_ALREADY_HAS_FAMILIAR.message((ICommandSender)player);
      return false;
    } 
    if (!cage.isOwner(player)) {
      PetTranslateEnum.MESSAGE_NOT_OWNER.message((ICommandSender)player);
      return false;
    } 
    if (CageStatus.isPlaying(cage.getStatus())) {
      if (cage.canPlayNextStep(player, now))
        openUI(player, cage); 
      return true;
    } 
    if (cage.isFilled()) {
      PetTranslateEnum.MESSAGE_WAITING_FOR_FAMILIAR.message((ICommandSender)player);
      return true;
    } 
    if (!cage.isFilled()) {
      if (fill(player, cage)) {
        PetTranslateEnum.MESSAGE_WAITING_FOR_FAMILIAR.message((ICommandSender)player);
        return true;
      } 
      PetTranslateEnum.MESSAGE_CAGE_NOT_FILLED.message((ICommandSender)player);
    } 
    return true;
  }
  
  public void openUI(EntityPlayerMP player, TileEntityPetCage cage) {
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new SCPacketTrapOpen(cage.getStatus(), cage.field_145851_c, cage.field_145848_d, cage.field_145849_e), player);
  }
  
  public void handlePacket(EntityPlayerMP player, int score, World world, int x, int y, int z) {
    TileEntityPetCage cage = getCage(world, x, y, z);
    if (cage == null || !cage.isOwner(player) || !CageStatus.isPlaying(cage.getStatus()))
      return; 
    cage.updateScore(player, score);
  }
  
  private boolean fill(EntityPlayerMP player, TileEntityPetCage cage) {
    ItemStack hand = player.func_70694_bm();
    if (hand == null || !hand.func_77973_b().equals(PetItemRegistry.BAIT))
      return false; 
    cage.fill(player);
    ItemUtils.decrementCurrentItem((EntityPlayer)player, hand);
    return true;
  }
  
  private TileEntityPetCage getCage(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityPetCage))
      return null; 
    return (TileEntityPetCage)tileEntity;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityPetCage();
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return PetClientProxy.cageRenderId;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    super.func_149689_a(world, x, y, z, entity, stack);
    onBlockPlacedBy(world, x, y, z, entity);
  }
  
  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity) {
    int l = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      world.func_72921_c(x, y, z, 2, 2); 
    if (l == 1)
      world.func_72921_c(x, y, z, 5, 2); 
    if (l == 2)
      world.func_72921_c(x, y, z, 3, 2); 
    if (l == 3)
      world.func_72921_c(x, y, z, 4, 2); 
  }
  
  public void setDefaultDirection(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      Block block = world.func_147439_a(x, y, z - 1);
      Block block1 = world.func_147439_a(x, y, z + 1);
      Block block2 = world.func_147439_a(x - 1, y, z);
      Block block3 = world.func_147439_a(x + 1, y, z);
      byte b0 = 3;
      if (block.func_149730_j() && !block1.func_149730_j())
        b0 = 3; 
      if (block1.func_149730_j() && !block.func_149730_j())
        b0 = 2; 
      if (block2.func_149730_j() && !block3.func_149730_j())
        b0 = 5; 
      if (block3.func_149730_j() && !block2.func_149730_j())
        b0 = 4; 
      world.func_72921_c(x, y, z, b0, 2);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\block\BlockPetCage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */