package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets.SCPirateMessagePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDryMessage extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Message illisible. Il pourrait peut être devenir lisible en étant réchauffé";
  
  public static final String NAME = "dry_message";
  
  private static final String MESSAGE = "&cCe message semble illisible. Il pourrait peut être devenir lisible en étant réchauffé";
  
  public ItemDryMessage() {
    func_77655_b("dry_message");
    func_111206_d("palamod:dry_message");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K) {
      MonthlyUtils.sendMessage(player, new String[] { "&cCe message semble illisible. Il pourrait peut être devenir lisible en étant réchauffé" });
      return itemStack;
    } 
    PalaMod.getNetwork().sendTo((IMessage)new SCPirateMessagePacket(0), (EntityPlayerMP)player);
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("Message illisible. Il pourrait peut être devenir lisible en étant réchauffé");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemDryMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */