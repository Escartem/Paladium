package fr.paladium.palamod.modules.miner.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.Constants;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemMagnet extends ItemMiner implements ITooltipWiki {
  private static final String NBT_MAGNET_KEY = "miner_magnet";
  
  public ItemMagnet() {
    super("magnet");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!item.func_77942_o()) {
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74757_a("miner_magnet", false);
      item.func_77982_d(nbt);
    } 
    item.func_77978_p().func_74757_a("miner_magnet", !item.func_77978_p().func_74767_n("miner_magnet"));
    if (!world.field_72995_K)
      if (item.func_77978_p().func_74767_n("miner_magnet")) {
        (new Notification(Notification.NotificationType.SUCCESS, "Aimant activé", "Paladium")).send((EntityPlayerMP)player);
      } else {
        (new Notification(Notification.NotificationType.SUCCESS, "Aimant désactivé", "Paladium")).send((EntityPlayerMP)player);
      }  
    return super.func_77659_a(item, world, player);
  }
  
  public void func_77663_a(ItemStack item, World world, Entity entity, int slot, boolean p_77663_5_) {
    if (!(entity instanceof EntityPlayer) || slot < 0 || slot >= 9 || !item.func_77942_o()) {
      super.func_77663_a(item, world, entity, slot, p_77663_5_);
      return;
    } 
    if (!item.func_77978_p().func_74764_b("miner_magnet") || !item.func_77978_p().func_74767_n("miner_magnet") || WorldGuardUtils.isItemEffectBlocked(entity, item)) {
      super.func_77663_a(item, world, entity, slot, p_77663_5_);
      return;
    } 
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entity.field_70165_t - 6.0D, entity.field_70163_u - 6.0D, entity.field_70161_v - 6.0D, entity.field_70165_t + 6.0D, entity.field_70163_u + 6.0D, entity.field_70161_v + 6.0D);
    List<?> items = world.func_72872_a(EntityItem.class, scanAbove);
    for (Object it : items) {
      EntityItem entityItem = (EntityItem)it;
      entityItem.func_70107_b(entity.field_70165_t, entity.field_70163_u - 0.5D, entity.field_70161_v);
    } 
    super.func_77663_a(item, world, entity, slot, p_77663_5_);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack item, int pass) {
    if (!Constants.Environment.SERVER.equals(Constants.MOD_ENV))
      return (item.func_77942_o() && item.func_77978_p().func_74764_b("miner_magnet") && item.func_77978_p().func_74767_n("miner_magnet")); 
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemMagnet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */