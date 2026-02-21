package fr.paladium.palarpg.module.profile.common.skilltree;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palaconfiguration.server.dto.file.RemoteFile;
import fr.paladium.palarpg.module.profile.common.skilltree.node.SkillTreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

public class SkillTree {
  public void setId(String id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setPosition(RPGSkillTreePosition position) {
    this.position = position;
  }
  
  public void setNodes(List<SkillTreeNode> nodes) {
    this.nodes = nodes;
  }
  
  protected SkillTree() {
    this.nodes = new LinkedList<>();
  }
  
  protected SkillTree(String id, String name, RPGSkillTreePosition position, List<SkillTreeNode> nodes) {
    this.nodes = new LinkedList<>();
    this.id = id;
    this.name = name;
    this.position = position;
    this.nodes = nodes;
  }
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private String id;
  
  private String name;
  
  private RPGSkillTreePosition position;
  
  private List<SkillTreeNode> nodes;
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public RPGSkillTreePosition getPosition() {
    return this.position;
  }
  
  public List<SkillTreeNode> getNodes() {
    return this.nodes;
  }
  
  public static SkillTree fromFile(@NonNull RemoteFile file) {
    if (file == null)
      throw new NullPointerException("file is marked non-null but is null"); 
    SkillTree skillTree = null;
    try {
      String jsonFileContent = file.getContentString().get();
      skillTree = (SkillTree)GSON.fromJson(jsonFileContent, SkillTree.class);
      skillTree.setId(file.getName().replace(".json", ""));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
    return skillTree;
  }
  
  public boolean containsNode(@NonNull SkillTreeNode skillNode) {
    if (skillNode == null)
      throw new NullPointerException("skillNode is marked non-null but is null"); 
    return this.nodes.contains(skillNode);
  }
  
  public SkillTreeNode getNodeByName(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return this.nodes.stream().filter(node -> name.equals(node.getName())).findFirst().orElse(null);
  }
  
  public List<SkillTreeNode> getNodesByName(List<String> name) {
    return (List<SkillTreeNode>)this.nodes.stream().filter(node -> name.contains(node.getName())).collect(Collectors.toList());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\skilltree\SkillTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */