package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVisionHelmet extends ItemArmor {
  public ItemVisionHelmet() {
    super(ItemArmor.ArmorMaterial.IRON, 0, 0);
    this.field_77777_bU = 1;
    func_77655_b("vision_helmet");
    func_111206_d("palamod:vision_helmet");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p) {
    return p_77659_1_;
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    p_77624_3_.add("§eClique droit sur un joueur:");
    p_77624_3_.add("§aPermet de demander le partage d'informations à ce joueur.");
    p_77624_3_.add("§a(vie,inventaire,position...)");
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/monocle.png";
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    return false;
  }
  
  public int getType() {
    return 5;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemVisionHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */