package fr.paladium.palarpg.module.profile.common.skilltree.node;

public class NodePosition {
  private final int x;
  
  private final int y;
  
  public String toString() {
    return "NodePosition(x=" + getX() + ", y=" + getY() + ")";
  }
  
  public NodePosition(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj instanceof NodePosition) {
      NodePosition nodePos = (NodePosition)obj;
      return (this.x == nodePos.getX() && this.y == nodePos.getY());
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\skilltree\node\NodePosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */