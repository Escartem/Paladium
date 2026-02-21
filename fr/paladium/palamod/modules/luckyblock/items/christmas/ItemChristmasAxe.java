package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChristmasAxe extends ItemAxe implements ITooltipWiki {
  public ItemChristmasAxe() {
    super(Item.ToolMaterial.EMERALD);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("christmas_axe");
    func_111206_d("palamod:christmas_axe");
    func_77656_e(1);
  }
  
  public boolean func_150894_a(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
    if (!world.field_72995_K && entity instanceof EntityPlayer && 
      block == Blocks.field_150364_r) {
      EntityPlayer player = (EntityPlayer)entity;
      PlayerUtil.addItemStackToInventory(new ItemStack(BlocksRegister.CHRISTMAS_TREE), player.field_71071_by);
      item.field_77994_a--;
    } 
    return (block == Blocks.field_150364_r);
  }
  
  public boolean canHarvestBlock(Block block, ItemStack itemStack) {
    return (block == Blocks.field_150364_r);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#5.-lucky-block-noel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemChristmasAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */