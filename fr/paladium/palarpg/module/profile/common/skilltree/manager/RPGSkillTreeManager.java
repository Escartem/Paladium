package fr.paladium.palarpg.module.profile.common.skilltree.manager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaconfiguration.server.dto.file.RemoteDirectory;
import fr.paladium.palaconfiguration.server.dto.file.RemoteFile;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.ProfileModule;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.profile.common.skilltree.SkillTree;
import fr.paladium.palarpg.module.profile.common.skilltree.node.SkillTreeNode;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public final class RPGSkillTreeManager {
  private static final Map<String, SkillTree> SKILL_TREES = new LinkedHashMap<>();
  
  private static final long TIMEOUT = 15L;
  
  private static final TimeUnit TIMEOUT_UNIT = TimeUnit.SECONDS;
  
  @SideOnly(Side.SERVER)
  public static void load() throws Exception {
    SKILL_TREES.clear();
    ProfileModule.logger.info("Loading skill tree from ConfigAPI", new Object[0]);
    RemoteDirectory directory = ((RemoteObject)ConfigurationManager.getInstance().getFile("rpg/skilltree").get(15L, TIMEOUT_UNIT)).asDirectory();
    for (RemoteObject object : directory.listFiles()) {
      if (!object.isFile())
        continue; 
      RemoteFile skillTree = object.asFile();
      String name = skillTree.getName().replace(".json", "");
      ProfileModule.logger.info("Loading skill tree " + name, new Object[0]);
      SKILL_TREES.put(name, SkillTree.fromFile(skillTree));
    } 
    ProfileModule.logger.info("SkillTree loaded !", new Object[0]);
  }
  
  public static Map<String, SkillTree> getSkillTrees() {
    return SKILL_TREES;
  }
  
  public static void buyNodes(@NonNull EntityPlayerMP player, @NonNull String skillTreeName, @NonNull List<String> nodes) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (nodes == null)
      throw new NullPointerException("nodes is marked non-null but is null"); 
    RPGProfilePlayerData pData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (pData == null)
      return; 
    buyNodes(pData, skillTreeName, nodes);
  }
  
  public static void buyNodes(@NonNull RPGProfilePlayerData pData, @NonNull String skillTreeName, @NonNull List<String> nodes) {
    if (pData == null)
      throw new NullPointerException("pData is marked non-null but is null"); 
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (nodes == null)
      throw new NullPointerException("nodes is marked non-null but is null"); 
    for (String node : nodes)
      buyNode(pData, skillTreeName, node); 
  }
  
  public static void buyNode(@NonNull EntityPlayerMP player, @NonNull String skillTreeName, @NonNull String node) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    RPGProfilePlayerData pData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (pData == null)
      return; 
    buyNode(pData, skillTreeName, node);
  }
  
  public static void buyNode(@NonNull RPGProfilePlayerData pData, @NonNull String skillTreeName, @NonNull String node) {
    if (pData == null)
      throw new NullPointerException("pData is marked non-null but is null"); 
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    SkillTree skillTree = getSkillTrees().get(skillTreeName);
    if (skillTree == null)
      return; 
    SkillTreeNode stNode = skillTree.getNodeByName(node);
    if (stNode == null || pData.isUnlocked(skillTreeName, stNode) || !isPreviousNodeUnlocked(pData, skillTreeName, stNode))
      return; 
    pData.unlockSkillTreeNode(skillTreeName, stNode);
  }
  
  public static void resetSkillTree(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    RPGProfilePlayerData pData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (pData == null)
      return; 
    resetSkillTree(pData);
  }
  
  public static void resetSkillTree(@NonNull RPGProfilePlayerData pData) {
    if (pData == null)
      throw new NullPointerException("pData is marked non-null but is null"); 
    if (pData.getUnlockedSkills().isEmpty())
      return; 
    pData.getUnlockedSkills().forEach((tree, nodes) -> nodes.forEach(()));
    pData.resetSkillTree();
    pData.sync();
  }
  
  public static boolean isPreviousNodeUnlocked(@NonNull EntityPlayer player, @NonNull String skillTreeName, @NonNull SkillTreeNode skillTreeNode) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (skillTreeNode == null)
      throw new NullPointerException("skillTreeNode is marked non-null but is null"); 
    RPGProfilePlayerData pData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (pData == null)
      return false; 
    return isPreviousNodeUnlocked(pData, skillTreeName, skillTreeNode);
  }
  
  public static boolean isPreviousNodeUnlocked(@NonNull RPGProfilePlayerData pData, @NonNull String skillTreeName, @NonNull SkillTreeNode skillTreeNode) {
    if (pData == null)
      throw new NullPointerException("pData is marked non-null but is null"); 
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (skillTreeNode == null)
      throw new NullPointerException("skillTreeNode is marked non-null but is null"); 
    AtomicBoolean isPreviousUnlocked = new AtomicBoolean(true);
    if (skillTreeNode.getRequiredLevels() == null)
      return true; 
    skillTreeNode.getRequiredLevels().forEach(nodeName -> {
          SkillTree skillTree = getSkillTrees().get(skillTreeName);
          if (skillTree == null)
            return; 
          SkillTreeNode stNode = skillTree.getNodeByName(nodeName);
          isPreviousUnlocked.set((isPreviousUnlocked.get() && pData.isUnlocked(skillTreeName, stNode)));
        });
    return isPreviousUnlocked.get();
  }
  
  public static void apply(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    RPGStatPlayerData playerStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    RPGProfilePlayerData playerProfile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (playerStats == null || playerProfile == null)
      return; 
    if (isSkillTreeModified(playerProfile) && player instanceof EntityPlayerMP) {
      resetSkillTree(playerProfile);
      FMLServerScheduler.getInstance().add(new Runnable[] { () -> (new Notification(Notification.NotificationType.WARNING, "L'arbre de compétence à été modifié. Votre arbre à donc été réinitialisé.", "RPG")).send((EntityPlayerMP)player) });
      return;
    } 
    playerProfile.getUnlockedSkills().forEach((tree, nodes) -> nodes.forEach(()));
    playerStats.applyAndSync();
  }
  
  public static boolean isSkillTreeModified(@NonNull RPGProfilePlayerData pData) {
    if (pData == null)
      throw new NullPointerException("pData is marked non-null but is null"); 
    boolean isModified = false;
    for (Map.Entry<String, List<SkillTreeNode>> entry : (Iterable<Map.Entry<String, List<SkillTreeNode>>>)pData.getUnlockedSkills().entrySet()) {
      if (isModified)
        break; 
      if (!getSkillTrees().containsKey(entry.getKey())) {
        isModified = true;
        break;
      } 
      SkillTree skillTree = getSkillTrees().get(entry.getKey());
      for (SkillTreeNode node : entry.getValue()) {
        if (!skillTree.containsNode(node))
          isModified = true; 
      } 
    } 
    return isModified;
  }
  
  @SideOnly(Side.CLIENT)
  public static void setSkillTrees(@NonNull Map<String, SkillTree> skillTrees) {
    if (skillTrees == null)
      throw new NullPointerException("skillTrees is marked non-null but is null"); 
    SKILL_TREES.clear();
    SKILL_TREES.putAll(skillTrees);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\skilltree\manager\RPGSkillTreeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */