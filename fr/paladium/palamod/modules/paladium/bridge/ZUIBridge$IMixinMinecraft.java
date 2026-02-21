package fr.paladium.palamod.modules.paladium.bridge;

import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

public class IMixinMinecraft {
  public static boolean isAltF4PopupOpen() {
    return ZUI.isOpen(UIYesNoPopup.class);
  }
  
  public static void openAltF4Popup(Runnable yesCallback, Runnable noCallback) {
    UIYesNoPopup popup = (new UIYesNoPopup("fermer le jeu", "Êtes-vous certains de vouloir fermer le jeu ?")).yesCallback(yesCallback).noCallback(noCallback).yesText("fermer").noText("annuler");
    popup.getData().setZlevel(1000.0D);
    ZUI.open((UI)popup, true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\bridge\ZUIBridge$IMixinMinecraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */