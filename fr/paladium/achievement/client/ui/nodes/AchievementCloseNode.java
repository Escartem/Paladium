package fr.paladium.achievement.client.ui.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import net.minecraft.client.Minecraft;

public class AchievementCloseNode extends TexturedNodeButton {
  public AchievementCloseNode(double x, double y, double width, double height) {
    super(x, y, width, height, "achievement:textures/gui/buttons/close");
    setAnimationDuration(0.1F);
    setCallback(node -> Minecraft.func_71410_x().func_147108_a(null));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\clien\\ui\nodes\AchievementCloseNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */