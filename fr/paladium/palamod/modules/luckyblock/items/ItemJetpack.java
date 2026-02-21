package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemJetpack extends ItemArmor implements ITooltipWiki {
  public ItemJetpack() {
    super(ItemArmor.ArmorMaterial.DIAMOND, 0, 1);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("jetpack");
    func_111206_d("palamod:jetpack");
    func_77625_d(1);
    func_77656_e(256);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/jetpack_layer_1.png";
  }
  
  public boolean isRepairable() {
    return false;
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack output) {
    return false;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    list.add("§eAppuyez sur votre touche de saut en portant le jetpack");
    int c = InventoryUtils.getItemCount(player, new ItemStack(Items.field_151152_bP));
    list.add("§bMunition : fireworks §7(" + c + ")");
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemJetpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */