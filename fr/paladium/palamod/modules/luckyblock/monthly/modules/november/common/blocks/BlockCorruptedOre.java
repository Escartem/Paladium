package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.Interval;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.world.PWorld;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCorruptedOre extends Block implements ITooltipInformations {
  public static final String NAME = "corrupted_ore";
  
  private static final String DESCRIPTION = "DESC.TXT : Quand il est ACTION:BREAK, donne entre NUMBER:1-5 ITEM:ORE aléatoire parmi tous les ITEM:ORE";
  
  private static final List<Block> DROP_LIST = Arrays.asList(new Block[] { 
        Blocks.field_150352_o, Blocks.field_150366_p, Blocks.field_150365_q, Blocks.field_150482_ag, Blocks.field_150450_ax, Blocks.field_150412_bA, Blocks.field_150449_bY, PWorld.AMETHYST_ORE, PWorld.TITANE_ORE, PWorld.PALADIUM_GREEN_ORE, 
        PWorld.FINDIUM_ORE, PWorld.TRIXIUM_ORE, BlocksRegister.FAKE_ORE_PALADIUM });
  
  private static final Interval DROP_RANGE_INTERVAL = new Interval(1, 5);
  
  private final Random rand;
  
  public BlockCorruptedOre() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("corrupted_ore");
    func_149658_d("palamod:corrupted_ore");
    func_149711_c(5.0F);
    this.rand = new Random();
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> items = new ArrayList<>();
    items.add(new ItemStack(func_149650_a(metadata, this.rand, fortune), func_149745_a(this.rand), func_149692_a(metadata)));
    return items;
  }
  
  public Item func_149650_a(int metadata, Random rand, int fortune) {
    Block randomBlock = DROP_LIST.get(rand.nextInt(DROP_LIST.size()));
    return Item.func_150898_a(randomBlock);
  }
  
  public int func_149745_a(Random rand) {
    return DROP_RANGE_INTERVAL.getRandom();
  }
  
  public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
    if (func_149650_a(metadata, this.rand, fortune) != Item.func_150898_a(this))
      return MathHelper.func_76136_a(this.rand, 2, 5); 
    return 0;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Quand il est ACTION:BREAK, donne entre NUMBER:1-5 ITEM:ORE aléatoire parmi tous les ITEM:ORE");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockCorruptedOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */