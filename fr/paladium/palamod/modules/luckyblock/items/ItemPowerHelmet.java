package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPowerHelmet extends ItemArmor implements ITooltipWiki {
  public ItemPowerHelmet() {
    super(PArmorMaterial.paladium, 0, 0);
    this.field_77777_bU = 1;
    func_77656_e(1200);
    func_77655_b("power_helmet");
    func_111206_d("palamod:power_helmet");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    return p_77659_1_;
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    p_77624_3_.add("§e50% de Résistance à la §bFake Water");
    p_77624_3_.add("§a1 durabilité / seconde");
    p_77624_3_.add("§7Réparable à la §6 PalaMachine");
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/power_helmet.png";
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    return false;
  }
  
  public int getType() {
    return 5;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.1.-luckystats-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemPowerHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */