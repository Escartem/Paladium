package fr.paladium.palavoicechat.client.ui.overlay;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palavoicechat.client.ui.UIVoiceConfig;
import fr.paladium.palavoicechat.client.ui.node.NearbyPlayerSpeakingContainerNode;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.interaction.UIDataOverlayInteraction;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

@UIData(active = true, background = false, anchorX = Align.START, anchorY = Align.START)
@UIDataOverlay(active = true, interaction = @UIDataOverlayInteraction(active = true, cancelKeyboard = false, cancelScroll = false))
public class UIOverlayVoiceChat extends UI {
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(root -> ContainerNode.create(1420.0D, 0.0D, 500.0D, 300.0D).body(()).attach(root))
      
      .visible(node -> !ZUI.isOpen(UIVoiceConfig.class))
      .attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\overlay\UIOverlayVoiceChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */