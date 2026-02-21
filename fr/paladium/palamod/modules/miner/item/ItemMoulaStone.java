package fr.paladium.palamod.modules.miner.item;

import com.google.common.util.concurrent.AtomicDouble;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.world.PWorld;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ItemMoulaStone extends ItemMiner implements ITooltipWiki {
  private final List<ItemStack> filter = new ArrayList<>();
  
  private final Map<ItemStack, Double> prices = new HashMap<>();
  
  public ItemMoulaStone() {
    super("moula_stone");
    addFilter(Blocks.field_150348_b, 0.0D);
    addFilter(Blocks.field_150347_e, 0.1D);
    addFilter(Blocks.field_150346_d, 0.1D);
    addFilter(Blocks.field_150351_n, 0.0D);
    addFilter(PWorld.GRANITE_BLOCK, 0, 0.0D);
    addFilter(PWorld.GRANITE_BLOCK, 1, 0.0D);
    addFilter(PWorld.DIORITE_BLOCK, 0, 0.0D);
    addFilter(PWorld.DIORITE_BLOCK, 1, 0.0D);
    addFilter(PWorld.ANDESITE_BLOCK, 0, 0.0D);
    addFilter(PWorld.ANDESITE_BLOCK, 1, 0.0D);
    addFilter(PWorld.LIMESTONE_BLOCK, 0, 0.0D);
    addFilter(PWorld.LIMESTONE_BLOCK, 1, 0.0D);
    addFilter(PWorld.MARBLE_BLOCK, 0, 0.0D);
    addFilter(PWorld.MARBLE_BLOCK, 1, 0.0D);
  }
  
  public void addFilter(Block block, double price) {
    this.filter.add(new ItemStack(block));
    this.prices.put(new ItemStack(block), Double.valueOf(price));
  }
  
  public void addFilter(Block block, int meta, double price) {
    this.filter.add(new ItemStack(block, 1, meta));
    this.prices.put(new ItemStack(block, 1, meta), Double.valueOf(price));
  }
  
  public double check(TickEvent.PlayerTickEvent event, ItemStack voidstone, ItemStack itemstack, EntityPlayer player) {
    if (!JobsBridge.canUseCraft(player, new ItemStack(ItemsRegister.MOULA_STONE)))
      return 0.0D; 
    AtomicDouble money = new AtomicDouble();
    for (ItemStack s : this.filter) {
      if (s.func_77969_a(itemstack) && s.func_77960_j() == itemstack.func_77960_j()) {
        player.field_71071_by.func_146026_a(itemstack.func_77973_b());
        this.prices.forEach((c, v) -> {
              if (c.func_77969_a(itemstack) && v.doubleValue() > 0.0D)
                money.addAndGet(v.doubleValue()); 
            });
      } 
    } 
    return money.get();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemMoulaStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */