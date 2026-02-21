package fr.paladium.palamod.modules.palaboss.client.profile.gui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ProfileBossNode extends Node {
  private Resource texture;
  
  private Map<ResourceLocation, String> data;
  
  protected ProfileBossNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ProfileBossNode create(double x, double y, double width, double height) {
    return new ProfileBossNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.data == null || this.data.isEmpty())
      return; 
    FlexNode.vertical(getWidth() * 0.65D, dh(2.0D), getWidth() * 0.35D)
      .align(Align.CENTER)
      .margin(20.0D)
      .anchorY(Align.CENTER)
      .body(flex -> {
          TextInfo textInfo = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F, Color.WHITE);
          for (Map.Entry<ResourceLocation, String> entry : this.data.entrySet())
            ContainerNode.create(0.0D, 0.0D, flex.getWidth(), 30.0D).body(()).attach(flex); 
        }).attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.texture == null)
      return; 
    DrawUtils.RESOURCE.drawScaledImageHeight(getX(), getY(), getHeight(), this.texture);
  }
  
  @NonNull
  public final ProfileBossNode texture(Resource texture) {
    this.texture = texture;
    return this;
  }
  
  @NonNull
  public final ProfileBossNode data(Map<ResourceLocation, String> data) {
    this.data = data;
    return this;
  }
  
  @NonNull
  public final ProfileBossNode data(ResourceLocation icon, String data) {
    if (this.data == null)
      this.data = new HashMap<>(); 
    this.data.put(icon, data);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\profile\gui\node\ProfileBossNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */