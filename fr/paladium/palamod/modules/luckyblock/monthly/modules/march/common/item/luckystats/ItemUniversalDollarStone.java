package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats;

import com.google.common.util.concurrent.AtomicDouble;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemUniversalDollarStone extends Item implements ITooltipInformations {
  private List<ItemStack> filter = new ArrayList<>();
  
  private Map<ItemStack, Double> prices = new HashMap<>();
  
  private static String DESCRIPTION = "Quand le détenteur de l’objet casse un objet qui peut être vendu il est directement vendu au prix de indiqué lors du clique droit. Il peut faire clic droit en tenant l’objet pour voir tous les types d’objets qui seront vendus. Elle ne fonctionne quand dans le dernier slot de la barre d’inventaire. ";
  
  public ItemUniversalDollarStone() {
    func_77655_b("universal_dollar_stone");
    func_111206_d("palamod:universal_dollar_stone");
    func_77637_a(PLuckyBlock.TAB);
    this.field_77777_bU = 1;
    addFilter((Block)Blocks.field_150338_P, 0.01D);
    addFilter((Block)Blocks.field_150337_Q, 0.01D);
    addFilter(Blocks.field_150343_Z, 0.01D);
    addFilter(Blocks.field_150434_aF, 0.01D);
    addFilter(Blocks.field_150325_L, 0.01D);
    addFilter(Blocks.field_150346_d, 0.01D);
    addFilter(Blocks.field_150348_b, 0.01D);
    addFilter(Blocks.field_150425_aM, 0.01D);
    addFilter(Blocks.field_150339_S, 0.1D);
    addFilter(Blocks.field_150340_R, 0.2D);
    addFilter(Blocks.field_150475_bE, 5.0D);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription(DESCRIPTION);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (player.field_70170_p.field_72995_K)
      return stack; 
    player.func_146105_b((IChatComponent)new ChatComponentText(""));
    player.func_146105_b((IChatComponent)new ChatComponentText("§6Liste des items vendus:"));
    for (Map.Entry<ItemStack, Double> k : this.prices.entrySet())
      player.func_146105_b((IChatComponent)new ChatComponentText("§7 - " + ((ItemStack)k.getKey()).func_82833_r() + ": §e" + k.getValue() + "$")); 
    player.func_146105_b((IChatComponent)new ChatComponentText(""));
    return stack;
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
    AtomicDouble money = new AtomicDouble();
    for (ItemStack s : this.filter) {
      if (s.func_77969_a(itemstack) && s.func_77960_j() == itemstack.func_77960_j()) {
        player.field_71071_by.func_146026_a(itemstack.func_77973_b());
        this.prices.forEach((c, v) -> {
              if (c.func_77969_a(itemstack) && v.doubleValue() > 0.0D) {
                money.addAndGet(v.doubleValue());
                return;
              } 
            });
      } 
    } 
    return money.get();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\luckystats\ItemUniversalDollarStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */