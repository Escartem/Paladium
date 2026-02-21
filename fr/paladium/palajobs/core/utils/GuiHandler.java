package fr.paladium.palajobs.core.utils;

import cpw.mods.fml.common.network.IGuiHandler;
import fr.paladium.palajobs.client.ui.boss.UIBossContainer;
import fr.paladium.palajobs.core.constant.BossConstants;
import fr.paladium.palajobs.core.container.ContainerBoss;
import fr.paladium.palajobs.server.managers.JobsManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    return new ContainerBoss(player, (ItemStack)BossConstants.BOSS_ITEM.get((JobsManager.getInstance().getBossData()).type));
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    return new UIBossContainer();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\cor\\utils\GuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */