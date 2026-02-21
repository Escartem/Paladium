package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.lang3.tuple.Pair;

public class SpeakingPlayerNode extends Node {
  public static final List<Pair<Color, Color>> COLORS = Arrays.asList((Pair<Color, Color>[])new Pair[] { Pair.of(Color.decode("#00E397"), Color.decode("#008232")), 
        Pair.of(Color.decode("#CB3544"), Color.decode("#FF3C3C")), 
        Pair.of(Color.decode("#DDA3FF"), Color.decode("#9E0AF3")), 
        Pair.of(Color.decode("#FFE567"), Color.decode("#E8B923")) });
  
  private final Color speakingColor = Color.decode("#00E357");
  
  private String playerName = "";
  
  private UUID playerUUID = null;
  
  private double headSize = 19.0D;
  
  private Pair<Color, Color> currentColor = null;
  
  protected SpeakingPlayerNode(double x, double y) {
    super(x, y);
    size(35.0D, 35.0D);
  }
  
  public static SpeakingPlayerNode create(double x, double y) {
    return new SpeakingPlayerNode(x, y);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.playerName.isEmpty())
      return; 
    double cornerSize = 3.0D;
    double offset = 2.0D;
    boolean speaking = (this.playerUUID != null && IoNettyVoIPClient.getInstance().getTalkCache().isTalking(this.playerUUID));
    DrawUtils.SHAPE.drawRect(getX() - 2.0D, getY() + 3.0D - 2.0D, 5.0D, h() - 6.0D + 4.0D, speaking ? this.speakingColor : Color.TRANSPARENT);
    DrawUtils.SHAPE.drawRect(getX() + 3.0D - 2.0D, getY() - 2.0D, w() - 6.0D + 4.0D, h() + 4.0D, speaking ? this.speakingColor : Color.TRANSPARENT);
    DrawUtils.SHAPE.drawRect(getX() + w() - 3.0D, getY() + 3.0D - 2.0D, 5.0D, h() - 6.0D + 4.0D, speaking ? this.speakingColor : Color.TRANSPARENT);
    DrawUtils.SHAPE.drawRect(getX(), getY() + 3.0D, 3.0D, h() - 6.0D, ((Color)this.currentColor.getLeft()).toGradient((Color)this.currentColor.getRight(), new Vector4f(0.0F, -((float)(3.0D / h())), 0.0F, 1.0F + (float)(3.0D / h()))));
    DrawUtils.SHAPE.drawRect(getX() + 3.0D, getY(), w() - 6.0D, h(), ((Color)this.currentColor.getLeft()).toGradient((Color)this.currentColor.getRight(), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)));
    DrawUtils.SHAPE.drawRect(getX() + w() - 3.0D, getY() + 3.0D, 3.0D, h() - 6.0D, ((Color)this.currentColor.getLeft()).toGradient((Color)this.currentColor.getRight(), new Vector4f(0.0F, -((float)(3.0D / h())), 0.0F, 1.0F + (float)(3.0D / h()))));
    DrawUtils.RESOURCE.drawImage(getX() + dw(2.0D) - this.headSize / 2.0D, getY() + dh(2.0D) - this.headSize / 2.0D, this.headSize, this.headSize, Resource.of("https://minotar.net/helm/" + this.playerName + "/" + '&').nearest());
  }
  
  public void update() {
    if (!this.playerName.isEmpty() && this.playerUUID == null) {
      List<EntityPlayer> loadedPlayers = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.field_73010_i;
      EntityPlayer player = loadedPlayers.stream().filter(p -> p.func_70005_c_().equalsIgnoreCase(this.playerName)).findFirst().orElse(null);
      if (player != null)
        this.playerUUID = player.func_110124_au(); 
    } 
  }
  
  public SpeakingPlayerNode playerName(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    this.playerName = name.trim();
    int h = 0;
    for (char c : this.playerName.toCharArray())
      h = 31 * h + c; 
    int index = Math.floorMod(h, COLORS.size());
    this.currentColor = COLORS.get(index);
    return this;
  }
  
  public SpeakingPlayerNode playerUUID(@NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    this.playerUUID = uuid;
    return this;
  }
  
  public SpeakingPlayerNode headSize(double size) {
    this.headSize = size;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\SpeakingPlayerNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */