package fr.paladium.palavanillagui.client.ui.chest.container;

import fr.paladium.palavanillagui.client.ui.chest.utils.LockedNode;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class EnderchestFakeSlotContainer extends ContainerNode {
  private static final Resource FAKE_ENDERCHEST_SLOT = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/chest/fake_enderchest_slot.png"));
  
  private final int numRows;
  
  protected EnderchestFakeSlotContainer(double x, double y, int numRows) {
    super(x, y, 540.0D, 60.0D * numRows);
    this.numRows = numRows;
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> {
          for (int i = 0; i < this.numRows; i++) {
            for (int k = 0; k < 9; k++)
              ImageNode.create(k * 60.0D, i * 60.0D).resource(FAKE_ENDERCHEST_SLOT).linear(false).size(60.0D, 60.0D).attach((Node)this); 
          } 
          if (getHeight() > 0.0D)
            LockedNode.create(getWidth(), 0.0D, 87.21D, getHeight()).attach((Node)this); 
        });
  }
  
  public static EnderchestFakeSlotContainer create(double x, double y, int numRows) {
    return new EnderchestFakeSlotContainer(x, y, numRows);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\chest\container\EnderchestFakeSlotContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */