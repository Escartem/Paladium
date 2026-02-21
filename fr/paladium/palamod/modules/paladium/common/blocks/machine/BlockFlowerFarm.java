package fr.paladium.palamod.modules.paladium.common.blocks.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.paladium.common.logics.TileFlowerFarm;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import software.bernie.geckolib3.block.BlockModelPropsBlock;
import software.bernie.geckolib3.block.texture.AnimatedResourceLocation;

public class BlockFlowerFarm extends BlockModelPropsBlock implements ITooltipWiki {
  public static final ItemStack FLOWER_FARM_FUEL = new ItemStack(Items.field_151100_aR, 1, 15);
  
  public BlockFlowerFarm() {
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileFlowerFarm();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {
      if (!JobsBridge.canUseCraft(player, new ItemStack(BlocksRegister.FLOWER_FARM), true))
        return true; 
      setOwner(player, world, x, y, z);
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_FLOWER_FARM, world, x, y, z);
    } 
    return true;
  }
  
  public void setOwner(EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileFlowerFarm))
      return; 
    TileFlowerFarm tileFlowerFarm = (TileFlowerFarm)tileEntity;
    int alchemistLvl = JobsBridge.getLevel(player, "FARMER");
    tileFlowerFarm.setAlchemistLvl(alchemistLvl);
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    if (tileentity instanceof IInventory) {
      IInventory inv = (IInventory)tileentity;
      for (int i1 = 0; i1 < inv.func_70302_i_(); i1++) {
        ItemStack itemstack = inv.func_70301_a(i1);
        if (itemstack != null)
          spawnItemInWorld(world, x, y, z, itemstack); 
      } 
      world.func_147453_f(x, y, z, block);
    } 
    super.func_149749_a(world, x, y, z, block, metadata);
  }
  
  private void spawnItemInWorld(World world, int x, int y, int z, ItemStack itemstack) {
    float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
    float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
    for (float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; world.func_72838_d((Entity)entityitem)) {
      int j1 = world.field_73012_v.nextInt(21) + 10;
      if (j1 > itemstack.field_77994_a)
        j1 = itemstack.field_77994_a; 
      itemstack.field_77994_a -= j1;
      EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
      float f3 = 0.05F;
      entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
      entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
      entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
      if (itemstack.func_77942_o())
        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iiconRegister) {
    this.field_149761_L = iiconRegister.func_94245_a("palamod:totem_of_flowery");
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    direction(world, x, y, z);
  }
  
  private void direction(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      byte byte0 = 3;
      Block direction = world.func_147439_a(x, y, z - 1);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 3; 
      direction = world.func_147439_a(x, y, z + 1);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 2; 
      direction = world.func_147439_a(x - 1, y, z);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 5; 
      direction = world.func_147439_a(x + 1, y, z);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 3; 
      world.func_72921_c(x, y, z, byte0, 2);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a((Block)this);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#6.-flowerfarm";
  }
  
  @SideOnly(Side.CLIENT)
  public ResourceLocation getGeckolibModel() {
    return new ResourceLocation("palamod", "geo/totem_of_flowery.geo.json");
  }
  
  @SideOnly(Side.CLIENT)
  public AnimatedResourceLocation getTexture() {
    return new AnimatedResourceLocation(new ResourceLocation("palamod", "textures/blocks/totem_of_flowery.png"), 32, 32);
  }
  
  @SideOnly(Side.CLIENT)
  public ResourceLocation getAnimation() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\machine\BlockFlowerFarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */