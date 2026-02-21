package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSupermanArmor extends ItemArmor implements ITooltipWiki {
  protected String model;
  
  public ItemSupermanArmor(ItemArmor.ArmorMaterial material, int type, String texture, String model) {
    super(material, 0, type);
    this.model = model;
    func_111206_d("palamod:" + texture);
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    if (slot == 2)
      return "palamod:textures/models/" + this.model + "_2.png"; 
    return "palamod:textures/models/" + this.model + "_1.png";
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    super.onArmorTick(world, player, itemStack);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemSupermanArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */