package fr.paladium.palajobs.client.proxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ClientTooltipHandler {
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onTooltip(ItemTooltipEvent event) {
    EntityPlayer player = event.entityPlayer;
    ItemStack item = event.itemStack;
    List<String> tooltip = event.toolTip;
    if (player == null || event.itemStack == null || event.itemStack.func_77973_b() == null || tooltip == null)
      return; 
    if ((Minecraft.func_71410_x()).field_71462_r instanceof fr.paladium.palajobs.client.ui.reward.UIJobsRewards)
      return; 
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null)
      return; 
    IBlackListedItem canUse = JobsManager.getInstance().canUseUseItem(player, data, item);
    IBlackListedItem canCraft = (item != null && item.func_77973_b() == Items.field_151134_bR) ? JobsManager.getInstance().canUseCraftEnchantment(player, data, item) : JobsManager.getInstance().canUseCraft(player, data, item);
    if (canUse == null && canCraft == null)
      return; 
    tooltip.add("");
    if (canUse != null)
      tooltip.add(TTT.format("tooltip.jobs.use", new Object[] { canUse.getRequiredLevel() + " " + canUse.getType().getPrefix() + TTT.format(canUse.getType().getName(), new Object[0]) })); 
    if (canCraft != null)
      tooltip.add(TTT.format("tooltip.jobs.craft", new Object[] { canCraft.getRequiredLevel() + " " + canCraft.getType().getPrefix() + TTT.format(canCraft.getType().getName(), new Object[0]) })); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\proxy\ClientTooltipHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */