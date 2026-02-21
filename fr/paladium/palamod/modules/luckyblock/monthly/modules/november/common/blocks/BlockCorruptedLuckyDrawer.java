package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockCorruptedLuckyDrawer extends Block implements ITooltipInformations {
  private static final String NAME = "corrupted_luckydrawer";
  
  private static final String DESCRIPTION = "DESC.TXT : Une fois ACTION:BREAK le block donnera un des objets suivants :  ORE.CORRUPTED X4, BED.CORRUPTED X2, CHRONO.CORRUPTED, CORRUPTION.SPLASH, FLOWER.CORRUPTED, PLANT.CORRUPTED X2, CHEST.CORRUPTED, SWORD.CORRUPTED, LUCKYSWORD.CORRUPTED, POISONPOTION.CORRUPTED X2, POISONPOTION.CORRUPTED X5, ORE.CORRUPTED X8, ORE.CORRUPTED X16, FLOWER.CORRUPTED X2, PLANT.CORRUPTED X8";
  
  private static final List<ItemStack> REWARDS = Arrays.asList(new ItemStack[] { 
        new ItemStack(BlocksRegister.CORRUPTED_ORE, 4), new ItemStack(ItemsRegister.ITEM_CORRUPTED_BED, 2), new ItemStack(ItemsRegister.CORRUPTED_CHRONO, 1), new ItemStack(ItemsRegister.CORRUPTED_SPLASH_POTION, 1), new ItemStack(BlocksRegister.CORRUPTED_HUNTER_PLANT, 1), new ItemStack(BlocksRegister.CORRUPTED_PLANT, 2), new ItemStack(BlocksRegister.FAKE_CORRUPTED_CHEST, 1), new ItemStack(ItemsRegister.CORRUPTED_SWORD, 1), new ItemStack(ItemsRegister.CORRUPTED_POISON_POTION, 2), new ItemStack(ItemsRegister.CORRUPTED_POISON_POTION, 5), 
        new ItemStack(BlocksRegister.CORRUPTED_ORE, 8), new ItemStack(BlocksRegister.CORRUPTED_ORE, 16), new ItemStack(BlocksRegister.CORRUPTED_HUNTER_PLANT, 2), new ItemStack(BlocksRegister.CORRUPTED_PLANT, 8) });
  
  private final Random random;
  
  private List<ItemStack> rewards;
  
  public BlockCorruptedLuckyDrawer() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("corrupted_luckydrawer");
    func_149658_d("palamod:corrupted_luckydrawer");
    func_149711_c(5.0F);
    this.random = new Random();
    this.rewards = new ArrayList<>();
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    if (this.rewards.isEmpty())
      initList(); 
    ItemStack reward = this.rewards.get(this.random.nextInt(this.rewards.size()));
    int amount = reward.field_77994_a;
    for (int i = 0; i < amount; i++)
      drops.add(new ItemStack(reward.func_77973_b(), 1, reward.func_77960_j())); 
    return drops;
  }
  
  public void initList() {
    this.rewards = Arrays.asList(new ItemStack[] { 
          new ItemStack(BlocksRegister.CORRUPTED_ORE, 4), new ItemStack(ItemsRegister.ITEM_CORRUPTED_BED, 2), new ItemStack(ItemsRegister.CORRUPTED_CHRONO, 1), new ItemStack(ItemsRegister.CORRUPTED_SPLASH_POTION, 1), new ItemStack(BlocksRegister.CORRUPTED_HUNTER_PLANT, 1), new ItemStack(BlocksRegister.CORRUPTED_PLANT, 2), new ItemStack(BlocksRegister.FAKE_CORRUPTED_CHEST, 1), new ItemStack(ItemsRegister.CORRUPTED_SWORD, 1), new ItemStack(ItemsRegister.CORRUPTED_POISON_POTION, 2), new ItemStack(ItemsRegister.CORRUPTED_POISON_POTION, 5), 
          new ItemStack(BlocksRegister.CORRUPTED_ORE, 8), new ItemStack(BlocksRegister.CORRUPTED_ORE, 16), new ItemStack(BlocksRegister.CORRUPTED_HUNTER_PLANT, 2), new ItemStack(BlocksRegister.CORRUPTED_PLANT, 8) });
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Une fois ACTION:BREAK le block donnera un des objets suivants :  ORE.CORRUPTED X4, BED.CORRUPTED X2, CHRONO.CORRUPTED, CORRUPTION.SPLASH, FLOWER.CORRUPTED, PLANT.CORRUPTED X2, CHEST.CORRUPTED, SWORD.CORRUPTED, LUCKYSWORD.CORRUPTED, POISONPOTION.CORRUPTED X2, POISONPOTION.CORRUPTED X5, ORE.CORRUPTED X8, ORE.CORRUPTED X16, FLOWER.CORRUPTED X2, PLANT.CORRUPTED X8");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockCorruptedLuckyDrawer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */