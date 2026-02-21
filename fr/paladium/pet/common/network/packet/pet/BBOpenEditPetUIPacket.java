package fr.paladium.pet.common.network.packet.pet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import fr.paladium.pet.client.ui.home.UIPetHome;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBOpenEditPetUIPacket extends ForgePacket {
  @PacketData(PacketSide.SERVER)
  private HomeData data;
  
  public BBOpenEditPetUIPacket() {}
  
  public BBOpenEditPetUIPacket(EntityPlayerMP player, PetPlayer pet) {
    this.data = new HomeData(player, pet);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has())
      return; 
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBOpenEditPetUIPacket(player, pet), player);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Minecraft mc = Minecraft.func_71410_x();
    mc.func_147108_a((GuiScreen)new UIPetHome(this.data));
    if (mc.field_71462_r instanceof UIPetHome) {
      UIPetHome ui = (UIPetHome)mc.field_71462_r;
      ui.openEditPetUI();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\pet\BBOpenEditPetUIPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */