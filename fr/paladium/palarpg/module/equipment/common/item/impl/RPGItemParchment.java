package fr.paladium.palarpg.module.equipment.common.item.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGParchmentItemData;
import fr.paladium.palarpg.module.equipment.common.playerdata.RPGCraftPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RPGItemParchment extends RPGItem {
  public RPGItemParchment(RPGItemData itemData) {
    super(itemData);
    func_77637_a(EquipmentCommonProxy.getRPGParchmentTab());
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    RPGCraftPlayerData craftPlayerData = (RPGCraftPlayerData)RPGPlayer.getData((Entity)player, "craft");
    if (craftPlayerData == null)
      return super.func_77659_a(item, world, player); 
    if (!craftPlayerData.isCraftUnlocked(getItemData().getItemStack()))
      craftPlayerData.unlockCraft(getItemData().getItemStack()); 
    item.field_77994_a--;
    return item;
  }
  
  public String func_77653_i(ItemStack item) {
    if ((super.getItemData().getItemName() != null && !super.getItemData().getItemName().isEmpty()) || (super.getItemData().getTranslations() != null && !super.getItemData().getTranslations().isEmpty()))
      return super.func_77653_i(item); 
    String itemName = getItemData().getItemStack().func_82833_r();
    if (getItemData().getItemStack().func_77973_b() instanceof ItemEnchantedBook) {
      ItemEnchantedBook enchantedBook = (ItemEnchantedBook)getItemData().getItemStack().func_77973_b();
      NBTTagList enchantList = enchantedBook.func_92110_g(getItemData().getItemStack());
      if (enchantList != null)
        for (int i = 0; i < enchantList.func_74745_c(); i++) {
          short short1 = enchantList.func_150305_b(i).func_74765_d("id");
          short short2 = enchantList.func_150305_b(i).func_74765_d("lvl");
          if (Enchantment.field_77331_b[short1] != null) {
            itemName = Enchantment.field_77331_b[short1].func_77316_c(short2);
            break;
          } 
        }  
    } 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && "fr_FR".equals(Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a()))
      return "Parchemin de " + itemName; 
    return "Parchment of " + itemName;
  }
  
  public RPGParchmentItemData getItemData() {
    return (RPGParchmentItemData)super.getItemData();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\impl\RPGItemParchment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */