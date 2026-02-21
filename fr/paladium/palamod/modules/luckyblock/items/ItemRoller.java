package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.helios.ModuleEggHunt;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRoller extends ItemArmor implements ITooltipWiki {
  public ItemRoller() {
    super(ItemArmor.ArmorMaterial.DIAMOND, 0, 3);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("roller");
    func_111206_d("palamod:roller");
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/roller.png";
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    if (player.field_71071_by.func_70440_f(1) != null && 
      player.field_71071_by.func_70440_f(1).func_77973_b() == ItemsRegister.TRAVEL_LEGGINGS)
      return; 
    if (player.field_71071_by.func_70440_f(0) != null && 
      player.field_71071_by.func_70440_f(0).func_77973_b() == ItemsRegister.PALADIUM_GREEN_BOOTS)
      return; 
    if (!player.field_71075_bZ.field_75098_d && WorldGuardUtils.isItemEffectBlocked((Entity)player, itemStack))
      return; 
    if (!world.field_72995_K && PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
      if (player.field_70173_aa % 60 == 0)
        (new Notification(Notification.NotificationType.INFO, "L'item " + func_77653_i(itemStack) + " est désactivé en possession de l'oeuf", PEggHunt.data.isEndEvent() ? "end" : "egghunt")).send((EntityPlayerMP)player); 
      return;
    } 
    if (world.field_72995_K && ModuleEggHunt.getInstance().isActive())
      return; 
    if (player.field_70122_E) {
      player.field_70138_W = 1.0F;
      player.field_70143_R = 0.0F;
      player.func_70612_e(player.field_70702_br * 7.0F, player.field_70701_bs * 7.0F);
    } 
  }
  
  public boolean isRepairable() {
    return false;
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack output) {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemRoller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */