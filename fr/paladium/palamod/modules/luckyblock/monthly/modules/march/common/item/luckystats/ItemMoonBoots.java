package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemMoonBoots extends ItemArmor {
  public ItemMoonBoots() {
    super(PArmorMaterial.paladium, 0, 3);
    func_77655_b("moon_boots");
    func_111206_d("palamod:moon_boots");
    func_77637_a(PLuckyBlock.TAB);
    this.field_77777_bU = 1;
  }
  
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/armors/moon_boots.png";
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    if (item.func_77942_o()) {
      NBTTagCompound tagCompound = item.func_77978_p();
      if (tagCompound.func_74764_b("endium"))
        list.add("§dAmélioration en Endium"); 
    } 
    super.func_77624_a(item, player, list, flag);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\luckystats\ItemMoonBoots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */