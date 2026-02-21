package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityGrappin;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGrappin extends ItemBow implements ITooltipWiki {
  public ItemGrappin() {
    func_77656_e(255);
    func_77655_b("grappin");
    func_111206_d("palamod:grappin");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K && WorldGuardUtils.isItemEffectBlocked(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, 
        Item.func_150891_b(item.func_77973_b())))
      return item; 
    if (player.field_71075_bZ.field_75098_d || player.field_71071_by
      .func_146028_b(ItemsRegister.GRAPPIN_HOOK))
      player.func_71008_a(item, func_77626_a(item)); 
    return item;
  }
  
  public void func_77615_a(ItemStack item, World world, EntityPlayer player, int itemInUseCount) {
    int j = func_77626_a(item) - itemInUseCount;
    float f = j / 20.0F;
    f = (f * f + f * 2.0F) / 3.0F;
    if (f < 0.1D)
      return; 
    if (f > 1.0F)
      f = 1.0F; 
    if (!world.field_72995_K && 
      ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())) != null && (
      (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.INERTIUM.getSpell().getId())))
      .contains(player.func_110124_au())) {
      PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM.getSpell().getId(), 
            FastUUID.toString((Entity)player), false));
      player.func_130014_f_().func_147468_f(((Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(0)).intValue(), (
          (Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(1)).intValue(), (
          (Integer)((List<Integer>)ServerManager.getFreeze().get(player)).get(2)).intValue());
      EventUtils.spawnParticle(player.field_70170_p, "smoke", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 100, 0.10000000149011612D);
      ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), 
          FastUUID.toString((Entity)player));
      ServerManager.removeFreeze((EntityPlayerMP)player);
    } 
    EntityGrappin grappin = new EntityGrappin(world, (EntityLivingBase)player, f * 2.0F);
    item.func_77972_a(1, (EntityLivingBase)player);
    world.func_72956_a((Entity)player, "random.bow", 1.0F, 1.0F);
    world.func_72838_d((Entity)grappin);
    player.field_71071_by.func_146026_a(ItemsRegister.GRAPPIN_HOOK);
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    p_77624_3_.add("§cAttention un crochet de grappin est nécessaire !");
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemGrappin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */