package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.blocks.BaseBlockFlower;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemPerfectHelmet;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCorruptedPlant extends BaseBlockFlower implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "corrupted_plant";
  
  private static final String DESCRIPTION = "DESC.TXT : Quand la ITEM:CORRUPTED.PLANT a atteint son dernier stade, la ACTION:BREAK fera tomber au sol un ITEM:RANDOM parmi tous les items disponibles sur SERVER:PALADIUM";
  
  private final Random random;
  
  private List<ItemStack> rewards;
  
  public BlockCorruptedPlant() {
    super("corrupted_plant");
    this.random = new Random();
    this.rewards = new ArrayList<>();
  }
  
  public void func_149674_a(World worldObj, int xCoord, int yCoord, int zCoord, Random random) {
    super.func_149674_a(worldObj, xCoord, yCoord, zCoord, random);
  }
  
  public ItemStack getDrop(int data) {
    if (data < 4)
      return new ItemStack(BlocksRegister.CORRUPTED_PLANT, 1); 
    if (this.rewards.isEmpty())
      initList(); 
    ItemStack reward = this.rewards.get(this.random.nextInt(this.rewards.size()));
    return reward.func_77946_l();
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> items = new ArrayList<>();
    items.add(getDrop(metadata));
    return items;
  }
  
  public void initList() {
    this.rewards = Arrays.asList(new ItemStack[] { 
          new ItemStack(ItemsRegister.GOLDEN_EGG, 1), new ItemStack(BlocksRegister.YELLOW_EASTER_EGG, 4), new ItemStack(BlocksRegister.MAGIC_BELL, 1), new ItemStack(BlocksRegister.LILY_OF_THE_VALLEY, 1), new ItemStack(ItemsRegister.PARALYZING_RADIO, 1), new ItemStack(ItemsRegister.FOGHORN, 1), new ItemStack(ModBlocks.banner, 1, 16), new ItemStack(ItemsRegister.DUTCH_BOAT, 1), new ItemStack(ItemsRegister.SKULL_CURSED_KING, 1), new ItemStack(ItemsRegister.SUMMER_AMULET, 1), 
          new ItemStack(ItemsRegister.SUITCASE, 1), new ItemStack(ItemsRegister.ARMOR_BAG, 1), 
          
          ItemPerfectHelmet.create(), new ItemStack(ItemsRegister.WELDING_GLASSES, 1), new ItemStack(ItemsRegister.JOB_CHUNK_ANALYZER, 1), new ItemStack(ItemsRegister.PAPER_AIRPLANE, 1), new ItemStack(ItemsRegister.JOB_TRANSFEROR, 1), new ItemStack(ItemsRegister.SWORD_TOO_BIG, 1), new ItemStack(ItemsRegister.IRON_SKULL_HAMMER, 1), new ItemStack(ItemsRegister.RUNIC_AXE, 1), 
          new ItemStack(ItemsRegister.ENERGETIC_BOW_SWORD, 1), new ItemStack(ItemsRegister.SITAR, 1), new ItemStack(ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE, 1), new ItemStack(ItemsRegister.BLUNDERBUSS, 1), new ItemStack(ItemsRegister.SWORD_PISTOL, 1) });
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityCorruptedPlant();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderCorruptedPlant;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Quand la ITEM:CORRUPTED.PLANT a atteint son dernier stade, la ACTION:BREAK fera tomber au sol un ITEM:RANDOM parmi tous les items disponibles sur SERVER:PALADIUM");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockCorruptedPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */