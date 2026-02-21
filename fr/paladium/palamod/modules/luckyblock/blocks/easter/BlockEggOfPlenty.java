package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.ChanceObject;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.RandomObjectPicker;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEggOfPlenty;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockEggOfPlenty extends Block implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "egg_of_plenty";
  
  private static final String DESCRIPTION = "Au plus l'œuf est posé longtemps sans être cassé au plus les récompenses qu’il contient seront intéressantes. ";
  
  private static final String PLACED_MESSAGE = "§eVotre oeuf est resté posé pendant §c%s. §eAprès 15 jours les chances d’obtenir de bonnes récompenses en cassant l’oeuf sont maximisées";
  
  private static final String NO_REWARD_FOUND_MESSAGE = "§cAucune récompense n’a été trouvée pour cet oeuf. ";
  
  public BlockEggOfPlenty() {
    super(Material.field_151575_d);
    func_149663_c("egg_of_plenty");
    func_149658_d("palamod:egg_of_plenty");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityEggOfPlenty))
      return true; 
    TileEntityEggOfPlenty egg = (TileEntityEggOfPlenty)tile;
    long placedMillis = egg.getPlacedMillis();
    long time = System.currentTimeMillis() - placedMillis;
    MonthlyUtils.sendMessage(player, new String[] { String.format("§eVotre oeuf est resté posé pendant §c%s. §eAprès 15 jours les chances d’obtenir de bonnes récompenses en cassant l’oeuf sont maximisées", new Object[] { DurationConverter.fromMillisToString(time) }) });
    return true;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityEggOfPlenty))
      return; 
    ItemStack stack = getReward(((TileEntityEggOfPlenty)tile).getPlacedMillis());
    if (stack == null) {
      MonthlyUtils.sendMessage(player, new String[] { "§cAucune récompense n’a été trouvée pour cet oeuf. " });
      return;
    } 
    ItemUtils.spawnItemInWorld(world, x, y, z, stack);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
  }
  
  private ItemStack getReward(long placedMillis) {
    long now = System.currentTimeMillis();
    long duration = now - placedMillis;
    if (duration >= TimeUnit.DAYS.toMillis(15L))
      return (ItemStack)(new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(100.0D, new ItemStack(ItemsRegister.POTION_JOB_TEN_MULTIPLIER)), 
              ChanceObject.of(75.0D, new ItemStack(ItemsRegister.JOB_CANDY, 1)), 
              ChanceObject.of(75.0D, new ItemStack(BlocksRegister.BLOCK_FINDIUM, 7)), 
              ChanceObject.of(50.0D, new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 7)) }))).pickRandomObject(); 
    if (duration >= TimeUnit.DAYS.toMillis(10L))
      return (ItemStack)(new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(100.0D, new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 5)), 
              ChanceObject.of(75.0D, new ItemStack(ItemsRegister.JOB_CANDY, 1, 2)), 
              ChanceObject.of(75.0D, new ItemStack(BlocksRegister.BLOCK_FINDIUM, 3)), 
              ChanceObject.of(50.0D, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 2)) }))).pickRandomObject(); 
    if (duration >= TimeUnit.DAYS.toMillis(7L))
      return (ItemStack)(new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(100.0D, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 5)), 
              ChanceObject.of(75.0D, new ItemStack(ItemsRegister.JOB_CANDY, 1, 4)), 
              ChanceObject.of(50.0D, new ItemStack(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER, 1)), 
              ChanceObject.of(50.0D, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1)) }))).pickRandomObject(); 
    if (duration >= TimeUnit.DAYS.toMillis(3L))
      return (ItemStack)(new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(100.0D, new ItemStack(BlocksRegister.BLOCK_TITANE, 15)), 
              ChanceObject.of(50.0D, new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1)), 
              ChanceObject.of(50.0D, new ItemStack(SpawnerBlockRegistry.EMPTY_BROKEN_MOB_SPAWNER, 1)), 
              ChanceObject.of(50.0D, new ItemStack(ItemsRegister.FINDIUM, 5)) }))).pickRandomObject(); 
    if (duration >= TimeUnit.DAYS.toMillis(1L))
      return (ItemStack)(new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(100.0D, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 1)), 
              ChanceObject.of(75.0D, new ItemStack(BlocksRegister.BLOCK_TITANE, 3)), 
              ChanceObject.of(50.0D, new ItemStack(ItemsRegister.FINDIUM, 3)) }))).pickRandomObject(); 
    if (duration >= TimeUnit.HOURS.toMillis(6L))
      return (ItemStack)(new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(100.0D, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 1)), 
              ChanceObject.of(75.0D, new ItemStack(BlocksRegister.BLOCK_TITANE, 3)), 
              ChanceObject.of(50.0D, new ItemStack(ItemsRegister.FINDIUM, 1)) }))).pickRandomObject(); 
    if (duration >= TimeUnit.HOURS.toMillis(1L))
      return new ItemStack(BlocksRegister.BLOCK_AMETHYST, 1); 
    return null;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityEggOfPlenty();
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderEggOfPlenty;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Au plus l'œuf est posé longtemps sans être cassé au plus les récompenses qu’il contient seront intéressantes. ");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockEggOfPlenty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */