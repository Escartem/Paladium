package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntitySantaClaus;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemChristmasBook extends Item implements ITooltipWiki {
  public ItemChristmasBook() {
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("christmas_book");
    func_111206_d("palamod:christmas_book");
    func_77625_d(1);
    func_77656_e(1);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      if (!EventUtils.canInteract(player, player.field_70165_t, player.field_70163_u, player.field_70161_v))
        return item; 
      if (item.func_77942_o()) {
        if (item.func_77978_p().func_74764_b("lastUse")) {
          long lastUse = item.func_77978_p().func_74763_f("lastUse");
          if (System.currentTimeMillis() - lastUse > 172800000L) {
            performEffect(player);
            item.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
          } else {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cVeuillez attendre " + ((172800000L - 
                  System.currentTimeMillis() - lastUse) / 1000L / 60L / 60L) + " heures avant de pouvoir utiliser cet item"));
          } 
        } else {
          performEffect(player);
          item.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        } 
      } else {
        performEffect(player);
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74772_a("lastUse", System.currentTimeMillis());
        item.func_77982_d(compound);
      } 
    } 
    return item;
  }
  
  private void performEffect(EntityPlayer player) {
    EntitySantaClaus entity = new EntitySantaClaus(player.field_70170_p);
    entity.func_70107_b(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public boolean func_77636_d(ItemStack item) {
    if (item.func_77942_o()) {
      if (item.func_77978_p().func_74764_b("lastUse")) {
        long lastUse = item.func_77978_p().func_74763_f("lastUse");
        return (System.currentTimeMillis() - lastUse > 172800000L);
      } 
      return true;
    } 
    return true;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    list.add("§eInvoque le père noël à vos côtés");
    list.add("§eCelui-ci procure régénération I aux joueurs dans un rayon de 5 blocs");
    list.add("");
    list.add("§7Utilisable toutes les 48 heures");
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#5.1.-luckystats-noel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemChristmasBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */