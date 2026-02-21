package fr.paladium.pet.client.ui.debug;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.utils.MinecraftScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.pet.client.ui.home.node.assignment.child.AssignmentNode;
import fr.paladium.pet.client.ui.utils.data.AssignmentClientData;
import java.util.List;

public class UIDebugAssignment extends UI {
  private List<AssignmentClientData> assignments;
  
  public UIDebugAssignment(List<AssignmentClientData> assignments) {
    this.assignments = assignments;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    double posX = width(40.0F);
    double posY = height(1.0F);
    double areaWidth = width(20.0F);
    double areaHeight = height(98.0F);
    double scrollAreaX = posX + areaWidth + width(0.5F);
    ScrollArea scrollArea = new ScrollArea(scrollAreaX, posY, areaHeight, width(0.8F), height(20.83F));
    MinecraftScrollableArea area = MinecraftScrollableArea.builder().bounds(posX, posY, posX + areaWidth, posY + areaHeight);
    int i = 0;
    for (AssignmentClientData assignment : this.assignments) {
      double flexNodeY = posY + height(0.5F) + (i * height(16.0F));
      double flexNodeX = posX + width(0.2F);
      double flexNodeWidth = width(19.6F);
      double flexNodeHeight = height(15.0F);
      AssignmentNode node = new AssignmentNode(assignment, flexNodeX, flexNodeY, flexNodeWidth, flexNodeHeight);
      node.setArea((ScrollableArea)area);
      addNode((ANode)node);
      i++;
    } 
    area.setScrollArea(scrollArea);
    addScrollableArea((ScrollableArea)area);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    func_146276_q_();
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\debug\UIDebugAssignment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */