package fr.paladium.palajobs.client.ui.node;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class MinecraftBackNode extends TexturedNodeButton {
  public MinecraftBackNode(double x, double y, double width, double height, GuiScreen prev) {
    super(x, y, width, height, "palajobs:textures/gui/buttons/back");
    setAnimationDuration(0.1F);
    setCallback(node -> Minecraft.func_71410_x().func_147108_a(prev));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\MinecraftBackNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */