package fr.paladium.palamod.modules.paladium.client.ui.util;

import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.head.HeadNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class PlayerListItemNode extends RectNode {
  private static final Resource CORNER = Resource.of(new ResourceLocation("palamod", "textures/gui/playeritem_corner.png"));
  
  private String playerName;
  
  protected PlayerListItemNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.playerName == null || this.playerName.isEmpty())
      return; 
    HeadNode.create(14.0D, dh(2.0D) - 20.0D, 40.0D)
      .player(this.playerName)
      .attach((Node)this);
    TextNode.create(100.0D, 15.0D)
      .text(Text.create(this.playerName, PaladiumText.TITLE))
      .attach((Node)this);
    ImageNode.create(getWidth() - 22.0D, 0.0D)
      .resource(CORNER)
      .size(23.0D, 23.0D)
      .attach((Node)this);
    onClick((node, mouseX, mouseY, clickType) -> (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/openinv " + this.playerName));
  }
  
  public static PlayerListItemNode create(double x, double y, double width, double height) {
    return new PlayerListItemNode(x, y, width, height);
  }
  
  public <T extends PlayerListItemNode> T playerUUID(@NonNull String playerName) {
    if (playerName == null)
      throw new NullPointerException("playerName is marked non-null but is null"); 
    this.playerName = playerName;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\u\\util\PlayerListItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */