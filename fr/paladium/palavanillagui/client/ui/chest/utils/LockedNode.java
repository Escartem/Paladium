package fr.paladium.palavanillagui.client.ui.chest.utils;

import fr.paladium.paladiumui.kit.button.HexaButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.translate.common.controller.TranslateController;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class LockedNode extends Node {
  private static final ResourceLocation BACKGROUND_HEXA = new ResourceLocation("palavanillagui", "textures/gui/chest/background_hexa.png");
  
  private final Color lineColor = new Color(97, 132, 138);
  
  public LockedNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> {
          double y = getHeight() / 2.0D - 33.48D;
          List<String> hoverKeylist = new ArrayList<>();
          for (int i = 1; TranslateController.getInstance().getDefaultTranslation().hasKey("vanilla.gui.enderchest.lockednode.hover.line" + i); i++)
            hoverKeylist.add(TTT.format("vanilla.gui.enderchest.lockednode.hover.line" + i, new Object[0])); 
          ImageNode.create(19.1D, y - 4.9D).resource(BACKGROUND_HEXA).linear(false).size(67.21D, 77.16D).hoverLines(()).attach(this);
          HexaButtonNode.create(24.0D, y).icon(ResourceConstant.FLAT_LOCK).border(true).size(58.33D, 66.96D).onClick(()).attach(this);
        });
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (getHeight() > 60.0D) {
      DrawUtils.SHAPE.drawRect(560.0D, 0.0D, 39.0D, 10.0D, this.lineColor);
      DrawUtils.SHAPE.drawRect(560.0D, getHeight() - 10.0D, 39.0D, 10.0D, this.lineColor);
    } 
    DrawUtils.SHAPE.drawRect(589.0D, 0.0D, 10.0D, getHeight(), this.lineColor);
  }
  
  public static LockedNode create(double x, double y, double width, double height) {
    return new LockedNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\ches\\utils\LockedNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */