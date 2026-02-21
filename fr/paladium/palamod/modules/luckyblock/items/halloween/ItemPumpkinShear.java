package fr.paladium.palamod.modules.luckyblock.items.halloween;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockPumpkinCustom;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPumpkinShear extends Item implements ITooltipWiki {
  public ItemPumpkinShear() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77655_b("pumpkinshear");
    func_111206_d("palamod:pumpkinshear");
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
      return false; 
    Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
    int meta = p_77648_3_.func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_);
    if (block instanceof net.minecraft.block.BlockPumpkin) {
      if (block instanceof BlockPumpkinCustom) {
        int next_variante = (((BlockPumpkinCustom)block).variante == 8) ? 0 : ((BlockPumpkinCustom)block).variante;
        p_77648_3_.func_147465_d(p_77648_4_, p_77648_5_, p_77648_6_, BlocksRegister.PUMKINCUSTOM[next_variante], meta, 3);
      } else {
        p_77648_3_.func_147465_d(p_77648_4_, p_77648_5_, p_77648_6_, BlocksRegister.PUMKINCUSTOM[0], meta, 3);
      } 
      return true;
    } 
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.-lucky-block-halloween";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\halloween\ItemPumpkinShear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */