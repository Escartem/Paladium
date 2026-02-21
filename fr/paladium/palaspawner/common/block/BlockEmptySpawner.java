package fr.paladium.palaspawner.common.block;

import com.google.common.base.Strings;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.tab.SpawnerTabs;
import fr.paladium.palaspawner.common.tile.Tier;
import fr.paladium.palaspawner.common.tile.TileEntityEmptyMobSpawner;
import fr.paladium.palaspawner.common.utils.FastUUID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockEmptySpawner extends Block implements ITooltipWiki, ITileEntityProvider {
  private boolean broken = false;
  
  private final IIcon[] icons = new IIcon[7];
  
  public BlockEmptySpawner(boolean broken) {
    super(Material.field_151576_e);
    this.broken = broken;
    func_149647_a((CreativeTabs)SpawnerTabs.getInstance());
    func_149663_c(broken ? "empty_broken_mob_spawner" : "empty_mob_spawner");
    func_149658_d(broken ? "palaspawner:empty_broken_mob_spawner" : "mob_spawner");
    func_149711_c(5.0F);
    func_149752_b(10.0F);
  }
  
  public static ItemStack createSpawnerItemStack(String entity, int souls) {
    ItemStack itemStack = new ItemStack(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER);
    NBTTagCompound tagCompound = new NBTTagCompound();
    tagCompound.func_74778_a("entityId", entity);
    tagCompound.func_74768_a("souls", souls);
    tagCompound.func_74778_a("securityUuid", FastUUID.toString(UUID.randomUUID()));
    itemStack.func_77964_b(Tier.getTier(souls).ordinal());
    itemStack.func_77982_d(tagCompound);
    return itemStack;
  }
  
  public void func_149666_a(Item item, CreativeTabs ctab, List<ItemStack> list) {
    list.add(new ItemStack(item, 1, 0));
    list.add(new ItemStack(item, 1, 1));
    list.add(new ItemStack(item, 1, 2));
    list.add(new ItemStack(item, 1, 3));
    list.add(new ItemStack(item, 1, 4));
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palaspawner:empty_broken_mob_spawner");
    this.icons[1] = register.func_94245_a("palaspawner:spawner/spawner_top");
    this.icons[2] = register.func_94245_a("palaspawner:spawner/regular_spawner_side");
    this.icons[3] = register.func_94245_a("palaspawner:spawner/amethyst_spawner_side");
    this.icons[4] = register.func_94245_a("palaspawner:spawner/titane_spawner_side");
    this.icons[5] = register.func_94245_a("palaspawner:spawner/paladium_spawner_side");
    this.icons[6] = register.func_94245_a("palaspawner:spawner/endium_spawner_side");
    super.func_149651_a(register);
  }
  
  public IIcon func_149691_a(int side, int meta) {
    ForgeDirection dir = ForgeDirection.getOrientation(side);
    if (this.broken)
      return this.icons[0]; 
    if (dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
      return this.icons[1]; 
    return this.icons[(meta + 2) % this.icons.length];
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    if (world.field_72995_K)
      return; 
    NBTTagCompound tagCompound = item.func_77978_p();
    if (tagCompound == null)
      return; 
    TileEntityEmptyMobSpawner tile = (TileEntityEmptyMobSpawner)world.func_147438_o(x, y, z);
    String entityId = tagCompound.func_74779_i("entityId");
    int souls = tagCompound.func_74762_e("souls");
    String uniqueId = tagCompound.func_74779_i("securityUuid");
    if (Strings.isNullOrEmpty(uniqueId))
      uniqueId = FastUUID.toString(UUID.randomUUID()); 
    tile.setEntityId(entityId);
    tile.setSouls(souls);
    tile.setSecurityUuid(FastUUID.parseUUID(uniqueId));
    world.func_72921_c(x, y, z, tile.getIntTier(), 2);
    super.func_149689_a(world, x, y, z, entity, item);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityEmptyMobSpawner();
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    if (!world.field_72995_K)
      world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 4); 
    super.func_149726_b(world, x, y, z);
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K)
      world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 4); 
    super.func_149695_a(world, x, y, z, block);
  }
  
  public boolean func_149742_c(World world, int x, int y, int z) {
    if (this.broken)
      return false; 
    return super.func_149742_c(world, x, y, z);
  }
  
  public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
    return false;
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile instanceof TileEntityEmptyMobSpawner) {
      TileEntityEmptyMobSpawner spawner = (TileEntityEmptyMobSpawner)tile;
      ItemStack itemStack = new ItemStack(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER);
      NBTTagCompound tagCompound = new NBTTagCompound();
      if (!Strings.isNullOrEmpty(spawner.getEntityId())) {
        tagCompound.func_74778_a("entityId", spawner.getEntityId());
        tagCompound.func_74768_a("souls", spawner.getSouls());
        if (spawner.getSecurityUuid() != null) {
          tagCompound.func_74778_a("securityUuid", FastUUID.toString(spawner.getSecurityUuid()));
        } else {
          tagCompound.func_74778_a("securityUuid", FastUUID.toString(UUID.randomUUID()));
        } 
        itemStack.func_77964_b(spawner.getIntTier());
        itemStack.func_77982_d(tagCompound);
      } 
      return itemStack;
    } 
    return super.getPickBlock(target, world, x, y, z, player);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    ItemStack item = new ItemStack(Item.func_150898_a(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER));
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityEmptyMobSpawner) || player.field_71075_bZ.field_75098_d)
      return; 
    TileEntityEmptyMobSpawner spawner = (TileEntityEmptyMobSpawner)tile;
    String uuid = null;
    NBTTagCompound compound = new NBTTagCompound();
    if (!Strings.isNullOrEmpty(spawner.getEntityId())) {
      compound.func_74778_a("entityId", spawner.getEntityId());
      compound.func_74768_a("souls", spawner.getSouls());
      if (spawner.getSecurityUuid() != null) {
        uuid = FastUUID.toString(spawner.getSecurityUuid());
        compound.func_74778_a("securityUuid", uuid);
      } else {
        compound.func_74778_a("securityUuid", FastUUID.toString(UUID.randomUUID()));
      } 
      item.func_77964_b(spawner.getIntTier());
    } 
    item.func_77982_d(compound);
    ItemUtils.spawnItemInWorld(world, x, y, z, item);
  }
  
  public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    if (world.field_72995_K) {
      super.onBlockExploded(world, x, y, z, explosion);
      return;
    } 
    ItemStack item = new ItemStack(Item.func_150898_a(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER));
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityEmptyMobSpawner)) {
      super.onBlockExploded(world, x, y, z, explosion);
      return;
    } 
    TileEntityEmptyMobSpawner spawner = (TileEntityEmptyMobSpawner)tile;
    if (!Strings.isNullOrEmpty(spawner.getEntityId())) {
      NBTTagCompound compound = new NBTTagCompound();
      compound.func_74778_a("entityId", spawner.getEntityId());
      compound.func_74768_a("souls", spawner.getSouls());
      if (spawner.getSecurityUuid() != null) {
        compound.func_74778_a("securityUuid", FastUUID.toString(spawner.getSecurityUuid()));
      } else {
        compound.func_74778_a("securityUuid", FastUUID.toString(UUID.randomUUID()));
      } 
      item.func_77964_b(spawner.getIntTier());
      item.func_77982_d(compound);
    } 
    ItemUtils.spawnItemInWorld(world, x, y, z, item);
    super.onBlockExploded(world, x, y, z, explosion);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> items = new ArrayList<>();
    return items;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter";
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityEmptyMobSpawner();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\block\BlockEmptySpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */