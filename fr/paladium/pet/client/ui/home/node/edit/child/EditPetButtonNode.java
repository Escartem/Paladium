package fr.paladium.pet.client.ui.home.node.edit.child;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class EditPetButtonNode extends TexturedNodeButton {
  private static final String TEXTURE = "palapet:textures/ui/home/edit_pet";
  
  @Deprecated
  public EditPetButtonNode(double x, double y, double width, double height) {
    super(x, y, width, height, "palapet:textures/ui/home/edit_pet");
    setAnimationDuration(0.1F);
    setCallback(node -> Minecraft.func_71410_x().func_147108_a(null));
  }
  
  public EditPetButtonNode(double x, double y) {
    this(x, y, GuiUtils.width(1.9F), GuiUtils.width(1.9F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\edit\child\EditPetButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */