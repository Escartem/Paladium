package fr.paladium.pet.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.pet.PalaPetMod;
import fr.paladium.pet.client.ui.feed.UIFeedPetContainer;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.container.ContainerFeedPet;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class FeedPetGuiHandler extends GHandler {
  @SideOnly(Side.SERVER)
  public static void open(EntityPlayerMP player) {
    player.openGui(
        PalaPetMod.getInstance(), PetCommonProxy.getInstance().getFeedPetHandler(), player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (GuiScreen)new UIFeedPetContainer(new ContainerFeedPet(player));
  }
  
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    return (Container)new ContainerFeedPet(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\handler\FeedPetGuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */