package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.uis;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.uis.nodes.BombNode;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets.CSEncryptedComputerPacket;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerUI extends UI {
  private final DoubleLocation location;
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public ComputerUI(DoubleLocation location) {
    this.location = location;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    float squareWidth = width(31.458F);
    float squareHeight = height(21.018F);
    float posX = width(33.958F);
    float posY = height(14.555F);
    addNode((ANode)new BombNode(posX, posY, squareWidth, squareHeight, 1));
    posY = height(39.166F);
    addNode((ANode)new BombNode(posX, posY, squareWidth, squareHeight, 2));
    posY = height(63.425F);
    addNode((ANode)new BombNode(posX, posY, squareWidth, squareHeight, 3));
    addNode((ANode)new MinecraftCloseNode(width(68.177F), height(13.148F)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.021F), height(76.481F));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
    handleStatus();
  }
  
  public void handleStatus() {
    int defuseCount = 0;
    List<BombNode> bombs = (List<BombNode>)getNodes().stream().filter(node -> node instanceof BombNode).map(node -> (BombNode)node).collect(Collectors.toList());
    for (BombNode bomb : bombs) {
      if (bomb.getStatus() == BombNode.BombStatus.FAILED) {
        handleHack(false);
        return;
      } 
      if (bomb.getStatus() == BombNode.BombStatus.DEFUSED)
        defuseCount++; 
    } 
    if (defuseCount == bombs.size())
      handleHack(true); 
  }
  
  public void handleHack(boolean hacked) {
    this.field_146297_k.func_147108_a(null);
    PalaMod.network.sendToServer((IMessage)new CSEncryptedComputerPacket(this.location, hacked));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\clien\\uis\ComputerUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */