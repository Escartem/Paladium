package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.lang3.tuple.Pair;

public class NearbyPlayerSpeakingContainerNode extends Node {
  private final ListSignal<Pair<String, UUID>> nearbyPlayersSignal;
  
  public ListSignal<Pair<String, UUID>> getNearbyPlayersSignal() {
    return this.nearbyPlayersSignal;
  }
  
  protected NearbyPlayerSpeakingContainerNode(double x, double y) {
    super(x, y);
    this.nearbyPlayersSignal = new ListSignal(new ArrayList());
    watch((Signal)this.nearbyPlayersSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD });
  }
  
  public static NearbyPlayerSpeakingContainerNode create(double x, double y) {
    return new NearbyPlayerSpeakingContainerNode(x, y);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    List<Pair<String, UUID>> speakingPlayers = (List<Pair<String, UUID>>)this.nearbyPlayersSignal.getOrDefault();
    for (int i = 0; i < speakingPlayers.size(); i++) {
      Pair<String, UUID> pair = speakingPlayers.get(i);
      SpeakingPlayerNode.create(-35.0D + i * -15.0D, 0.0D)
        .playerName((String)pair.getLeft())
        .playerUUID((UUID)pair.getRight())
        .attach(this);
    } 
  }
  
  public void update() {
    if (!IoNettyVoIPClient.getInstance().isConnected())
      return; 
    Set<UUID> nearbySpeakingPlayers = new HashSet<>(IoNettyVoIPClient.getInstance().getTalkCache().getCache().keySet());
    nearbySpeakingPlayers.remove((Minecraft.func_71410_x()).field_71439_g.func_110124_au());
    List<EntityPlayer> worldNearbyPlayers = new ArrayList<>((Minecraft.func_71410_x()).field_71439_g.field_70170_p.field_73010_i);
    List<EntityPlayer> filteredNearbySpeakingPlayers = (List<EntityPlayer>)worldNearbyPlayers.stream().filter(player -> nearbySpeakingPlayers.contains(player.func_110124_au())).collect(Collectors.toList());
    Collections.sort(filteredNearbySpeakingPlayers, (p1, p2) -> {
          double dist1 = (Minecraft.func_71410_x()).field_71439_g.func_70032_d((Entity)p1);
          double dist2 = (Minecraft.func_71410_x()).field_71439_g.func_70032_d((Entity)p2);
          return Double.compare(dist1, dist2);
        });
    this.nearbyPlayersSignal.set(filteredNearbySpeakingPlayers.stream().map(player -> Pair.of(player.func_70005_c_(), player.func_110124_au())).collect(Collectors.toList()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\NearbyPlayerSpeakingContainerNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */