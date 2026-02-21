package fr.paladium.palashop.common.pb.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.impl.home.UIShopHomePage;
import fr.paladium.palashop.client.ui.impl.pb.UIShopPBPopup;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

public class SCPacketBuyPB extends ForgePacket {
  @PacketData
  private long required;
  
  public SCPacketBuyPB() {}
  
  public SCPacketBuyPB(long required) {
    this.required = required;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (ZUI.isOpen(UIShopPage.class)) {
      ZUI.open((UI)new UIShopPBPopup(this.required));
      return;
    } 
    FMLClientScheduler.getInstance().add(new Runnable[] { () -> {
            UIYesNoPopup popup = new UIYesNoPopup("vous n'avez pas assez de points boutique", "Il vous manque " + String.format("%,d", new Object[] { Long.valueOf(this.required) }).replace(",", ".").replace(" ", ".") + " points boutique pour effectuer cet achat");
            popup.getData().setZlevel(500.0D);
            ZUI.open((UI)popup.yesText("acheter").noText("annuler").yesCallback(()));
          } });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\pb\network\SCPacketBuyPB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */