package fr.paladium.palamod.modules.paladium.client.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.network.PacketPaladiumArmor;
import fr.paladium.palamod.modules.paladium.network.data.PaladiumPlayer;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@UIDataGuiscale(active = true)
public class UISkin extends UI {
  private static final Resource NEXT_ICON = Resource.of(new ResourceLocation("palamod", "textures/gui/next_skin.png"));
  
  private static final Resource PREV_ICON = Resource.of(new ResourceLocation("palamod", "textures/gui/prev_skin.png"));
  
  public void init() {
    BackgroundNode.create(600.0D, 740.0D)
      .body(background -> {
          TextNode.create(31.0D, 5.0D).text(Text.create("skin", PaladiumText.HEADER)).attach(background);
          CloseButtonNode.create(background.getWidth() - 48.8D, 25.0D).onClick(()).attach(background);
          RectNode.create((background.getWidth() - 344.0D) / 2.0D, 152.0D, 344.0D, 535.0D).color(new Color(0.0F, 0.0F, 0.0F, 0.15F)).body(()).attach(background);
          IconButtonNode.create(44.0D, 390.0D, 70.0D).icon(PREV_ICON).padding(0.0D).showBackground(false).width(66.0D).onClick(()).attach(background);
          IconButtonNode.create(background.getWidth() - 70.0D - 39.0D, 390.0D, 70.0D).icon(NEXT_ICON).padding(0.0D).showBackground(false).height(66.0D).onClick(()).attach(background);
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UISkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */