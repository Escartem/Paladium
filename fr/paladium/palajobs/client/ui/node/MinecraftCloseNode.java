package fr.paladium.palajobs.client.ui.node;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import net.minecraft.client.Minecraft;

public class MinecraftCloseNode extends TexturedNodeButton {
  public MinecraftCloseNode(double x, double y, double width, double height) {
    super(x, y, width, height, "palajobs:textures/gui/buttons/close");
    setAnimationDuration(0.1F);
    setCallback(node -> Minecraft.func_71410_x().func_147108_a(null));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\MinecraftCloseNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */