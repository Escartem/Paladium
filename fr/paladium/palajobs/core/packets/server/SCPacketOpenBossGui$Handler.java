package fr.paladium.palajobs.core.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.client.ui.boss.UIBoss;
import fr.paladium.palajobs.client.ui.home.UIJobsHome;
import fr.paladium.palajobs.core.constant.BossConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class Handler implements IMessageHandler<SCPacketOpenBossGui, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketOpenBossGui message, MessageContext ctx) {
    (PalaJobs.getClient()).bossItem = (ItemStack)BossConstants.BOSS_ITEM.get((SCPacketOpenBossGui.access$000(message)).type);
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIBoss((GuiScreen)new UIJobsHome(), SCPacketOpenBossGui.access$000(message)));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCPacketOpenBossGui$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */