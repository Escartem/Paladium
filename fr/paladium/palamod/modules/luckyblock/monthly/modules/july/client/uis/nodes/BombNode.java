package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.uis.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import java.util.Random;
import net.minecraft.client.Minecraft;

public class BombNode extends ANode {
  public static final Color RED_COLOR = Color.decode("#FF3939");
  
  public static final Color BLUE_COLOR = Color.decode("#2DC7FF");
  
  public static final Color YELLOW_COLOR = Color.decode("#FFD174");
  
  private final int number;
  
  private double squareSize;
  
  private double rotatorY;
  
  private double rotatorX;
  
  private RotatorDirection direction;
  
  private BombStatus status;
  
  private MinecraftTextCallToActionNode node;
  
  public BombStatus getStatus() {
    return this.status;
  }
  
  public BombNode(double x, double y, double width, double height, int number) {
    super(x, y, width, height);
    this.number = number;
    this.rotatorX = 0.0D;
    this.rotatorY = 0.0D;
    this.direction = pickupRandomPreDirection();
    this.status = BombStatus.WAITING;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    this.squareSize = this.ui.width(11.822917F);
    double shadowSize = this.ui.height(1.1111112D);
    GuiUtils.drawFilledBorder(this.x, this.y, this.x + this.squareSize, this.y + this.height, getColorByNumber(), 10.0D);
    GuiUtils.drawCenteredStringWithCustomFont(mc, String.valueOf(this.number), this.x + this.squareSize / 2.0D, this.y, Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 600);
    double posX = this.x + this.squareSize - this.ui.width(1.2760416D);
    double posY = this.y + this.squareSize / 2.0D - this.ui.height(2.1296296D);
    drawActivator();
    posX += this.ui.width(2.5520833D);
    posY = this.y + this.squareSize / 2.0D - this.ui.height(2.777778D);
    GuiUtils.drawRect(posX, posY, posX + this.ui.width(2.5520833D), posY + this.ui.height(3.1481483D) + shadowSize, Color.GRAY);
    GuiUtils.drawRect(posX, posY, posX + this.ui.width(2.5520833D), posY + this.ui.height(3.1481483D), Color.WHITE);
    posX += this.ui.width(2.5520833D);
    posY = this.y + this.squareSize / 2.0D - this.ui.height(2.1296296D);
    GuiUtils.drawRect(posX, posY, posX + this.ui.width(9.21875D), posY + this.ui.height(1.1111112D) + shadowSize, Color.GRAY);
    GuiUtils.drawRect(posX, posY, posX + this.ui.width(9.21875D), posY + this.ui.height(1.1111112D), Color.WHITE);
    posX += this.ui.width(9.21875D);
    posY -= this.ui.height(0.5F);
    if (this.node != null)
      return; 
    getChildren().clear();
    this.node = new MinecraftTextCallToActionNode(width(80.0F), height(39.0F), this.ui.width(7.1354165D), "CLICK");
    this.node.setCallback(callback -> handleClick());
    addChild((ANode)this.node);
  }
  
  private void handleClick() {
    if (this.direction != RotatorDirection.RIGHT) {
      onFail();
      return;
    } 
    double distance = this.rotatorY - this.y;
    double h = this.height / 2.0D - distance;
    double maxDistance = 20.0D;
    if (h >= -maxDistance && h <= maxDistance) {
      onWin();
    } else {
      onFail();
    } 
  }
  
  private void onFail() {
    this.status = BombStatus.FAILED;
  }
  
  private void onWin() {
    this.status = BombStatus.DEFUSED;
  }
  
  private void drawActivator() {
    if (this.direction == RotatorDirection.PRE_RIGHT) {
      this.rotatorX = this.x + this.squareSize - this.ui.width(1.2760416D);
      this.rotatorY = this.y;
      this.direction = RotatorDirection.RIGHT;
    } else if (this.direction == RotatorDirection.PRE_LEFT) {
      this.rotatorX = this.x - this.ui.width(1.2760416D);
      this.rotatorY = this.y + this.height - this.ui.height(1.4D);
      this.direction = RotatorDirection.LEFT;
    } else if (this.direction == RotatorDirection.PRE_BOTTOM) {
      this.rotatorX = this.x + this.squareSize - this.ui.width(1.0160416D);
      this.rotatorY = this.y + this.height - this.ui.height(4.0F);
      this.direction = RotatorDirection.BOTTOM;
    } else if (this.direction == RotatorDirection.PRE_TOP) {
      this.rotatorX = this.x;
      this.rotatorY = this.y - this.ui.height(2.5D);
      this.direction = RotatorDirection.TOP;
    } 
    if (this.direction == RotatorDirection.RIGHT) {
      drawRight();
    } else if (this.direction == RotatorDirection.LEFT) {
      drawLeft();
    } else if (this.direction == RotatorDirection.BOTTOM) {
      drawBottom();
    } else if (this.direction == RotatorDirection.TOP) {
      drawTop();
    } 
  }
  
  private void drawTop() {
    double shadowSize = this.ui.height(1.1111112D);
    if (this.status == BombStatus.WAITING) {
      double nextX = this.rotatorX + width(0.22552085D);
      if (nextX >= this.x + this.ui.width(11.5D)) {
        this.direction = RotatorDirection.PRE_RIGHT;
        return;
      } 
      this.rotatorX = nextX;
    } 
    double wid = this.ui.width(1.8520833D);
    double hei = this.ui.height(1.8518518D);
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + hei, this.rotatorY + wid + shadowSize, Color.GRAY);
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + hei, this.rotatorY + wid, Color.WHITE);
  }
  
  private void drawBottom() {
    double shadowSize = this.ui.height(1.1111112D);
    if (this.status == BombStatus.WAITING) {
      double nextX = this.rotatorX - width(0.22552085D);
      if (nextX <= this.x - this.ui.width(0.55D)) {
        this.direction = RotatorDirection.PRE_LEFT;
        return;
      } 
      this.rotatorX = nextX;
    } 
    double wid = this.ui.width(2.5520833D);
    double hei = this.ui.height(1.8518518D);
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + hei, this.rotatorY + wid + shadowSize, Color.GRAY);
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + hei, this.rotatorY + wid, Color.WHITE);
  }
  
  private void drawLeft() {
    double shadowSize = this.ui.height(1.1111112D);
    if (this.status == BombStatus.WAITING) {
      double nextY = this.rotatorY - height(0.4518518D);
      if (nextY <= this.y - this.ui.height(1.4D)) {
        this.direction = RotatorDirection.PRE_TOP;
        return;
      } 
      this.rotatorY = nextY;
    } 
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + this.ui.width(2.5520833D), this.rotatorY + this.ui.height(1.8518518D) + shadowSize, Color.GRAY);
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + this.ui.width(2.5520833D), this.rotatorY + this.ui.height(1.8518518D), Color.WHITE);
  }
  
  private void drawRight() {
    double shadowSize = this.ui.height(1.1111112D);
    if (this.status == BombStatus.WAITING) {
      double nextY = this.rotatorY + height(0.4518518D);
      if (nextY >= this.y + this.height - this.ui.height(1.4D)) {
        this.direction = RotatorDirection.PRE_BOTTOM;
        return;
      } 
      this.rotatorY = nextY;
    } 
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + this.ui.width(2.5520833D), this.rotatorY + this.ui.height(1.8518518D) + shadowSize, Color.GRAY);
    GuiUtils.drawRect(this.rotatorX, this.rotatorY, this.rotatorX + this.ui.width(2.5520833D), this.rotatorY + this.ui.height(1.8518518D), Color.WHITE);
  }
  
  public boolean onClick(int i, int i1, int i2) {
    return false;
  }
  
  public void onRelease(int i, int i1, int i2) {}
  
  public void onKeyTyped(char c, int i) {}
  
  public void onHover(int i, int i1) {}
  
  public void onHoverOut(int i, int i1) {}
  
  public void fixedUpdate() {}
  
  private Color getColorByNumber() {
    switch (this.number) {
      case 1:
        return YELLOW_COLOR;
      case 2:
        return BLUE_COLOR;
    } 
    return RED_COLOR;
  }
  
  private RotatorDirection pickupRandomPreDirection() {
    int random = (new Random()).nextInt(4);
    switch (random) {
      case 0:
        return RotatorDirection.PRE_RIGHT;
      case 1:
        return RotatorDirection.PRE_BOTTOM;
      case 2:
        return RotatorDirection.PRE_LEFT;
      case 3:
        return RotatorDirection.PRE_TOP;
    } 
    return RotatorDirection.PRE_RIGHT;
  }
  
  public enum RotatorDirection {
    PRE_RIGHT, PRE_BOTTOM, PRE_LEFT, PRE_TOP, RIGHT, BOTTOM, LEFT, TOP;
  }
  
  public enum BombStatus {
    WAITING, FAILED, DEFUSED;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\clien\\uis\nodes\BombNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */