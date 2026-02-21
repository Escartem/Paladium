package fr.paladium.palawither.common.item;

import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palawither.common.CommonProxy;
import fr.paladium.palawither.common.utils.WitherUtils;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.impl.VanillaWither;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class ItemWitherUpgrade extends Item {
  public ItemWitherUpgrade(@NonNull String name, @NonNull String texture) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    func_77625_d(16);
    func_111206_d(texture);
    func_77655_b(name);
    func_77637_a(CommonProxy.CREATIVE_TAB);
  }
  
  public boolean func_111207_a(ItemStack item, EntityPlayer player, EntityLivingBase entity) {
    if (!WitherUtils.isWither((Entity)entity) || !(item.func_77973_b() instanceof ItemWitherUpgrade))
      return false; 
    if (player.field_70170_p.field_72995_K)
      return true; 
    IWither wither = (entity instanceof IWither) ? (IWither)entity : VanillaWither.getByEntity((EntityWither)entity).orElse(null);
    if (wither == null) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cImpossible d'appliquer cette amélioration sur ce Wither !"));
      return false;
    } 
    if (!isValid(wither)) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette amélioration n'est pas compatible avec ce Wither !"));
      return false;
    } 
    ItemWitherUpgrade upgrade = (ItemWitherUpgrade)item.func_77973_b();
    if (!wither.addUpgrade(player, upgrade))
      return false; 
    if (!player.field_71075_bZ.field_75098_d) {
      item.field_77994_a--;
      if (item.field_77994_a <= 0) {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
      } else {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, item);
      } 
    } 
    SoundUtils.ITEM_BREAK.playSound((EntityPlayerMP)player, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 1.0F, 1.0F);
    return true;
  }
  
  public abstract boolean isValid(@NonNull IWither paramIWither);
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\item\ItemWitherUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */