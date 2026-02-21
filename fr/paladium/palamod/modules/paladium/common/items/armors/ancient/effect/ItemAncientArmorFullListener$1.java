package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect;

import fr.paladium.factions.api.callback.DataCallback;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionPlayer;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null extends DataCallback<IFaction> {
  public void onCallback(IFaction faction) {
    if (ItemAncientArmorFullListener.access$000().contains(playerUuid) && (faction == null || !factionPlayer.getFaction().getUuid().equals(faction.getUuid()) || ItemAncientArmorFullListener.access$100(ItemAncientArmorFullListener.this, player))) {
      player.field_71075_bZ.field_75101_c = false;
      player.field_71075_bZ.field_75100_b = false;
      ((EntityPlayerMP)player).func_71016_p();
      ItemAncientArmorFullListener.access$000().remove(playerUuid);
      if (faction == null || !factionPlayer.getFaction().getUuid().equals(faction.getUuid())) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §cVous ne pouvez plus voler car vous avez quitté le territoire de votre faction."));
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §cVous ne pouvez plus voler car des ennemis sont à proximité."));
      } 
    } else if (faction != null && factionPlayer.getFaction().getUuid().equals(faction.getUuid()) && !ItemAncientArmorFullListener.access$100(ItemAncientArmorFullListener.this, player) && !ItemAncientArmorFullListener.access$000().contains(playerUuid)) {
      player.field_71075_bZ.field_75101_c = true;
      ((EntityPlayerMP)player).func_71016_p();
      ItemAncientArmorFullListener.access$000().add(playerUuid);
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6§lPaladium§8] §aVous pouvez désormais voler grâce à votre armure antique."));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\effect\ItemAncientArmorFullListener$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */