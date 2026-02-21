package fr.paladium.palarpg.module.profile.server.action;

import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

public class RPGSkillTreeServerAction {
  @ServerAction
  public static CompletableFuture<Void> buySkillTreeNodes(@NonNull String skillTreeName, @NonNull List<String> nodes) {
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (nodes == null)
      throw new NullPointerException("nodes is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> {
          RPGSkillTreeManager.buyNodes(context.getPlayer(), skillTreeName, nodes);
          RPGPlayer.syncPlayer(context.getPlayer());
        }new Object[] { skillTreeName, nodes });
  }
  
  @ServerAction
  public static CompletableFuture<Void> resetSkillTree() {
    return ServerActionHook.useServer(context -> RPGSkillTreeManager.resetSkillTree(context.getPlayer()), new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\action\RPGSkillTreeServerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */