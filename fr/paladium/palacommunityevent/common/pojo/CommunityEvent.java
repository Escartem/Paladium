package fr.paladium.palacommunityevent.common.pojo;

import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CommunityEvent {
  public String id;
  
  public String name;
  
  public String instructions;
  
  public String npcName;
  
  public String textureFolder;
  
  public String color;
  
  public String colorShadow;
  
  public String titleColor;
  
  public String titleColorShadow;
  
  public int totalItemsPerPlayer;
  
  public int totalItems;
  
  public List<ItemStack> targetedItems = new ArrayList<>();
  
  public List<CommunityStep> playerSteps = new ArrayList<>();
  
  public List<CommonCommunityStep> commonSteps = new ArrayList<>();
  
  public CommunityEvent(String communityEventId) {
    this.id = communityEventId;
  }
  
  public static CommunityEvent init(String id, String name, String instruction, String npcName, int totalItems, int totalItemsPerPlayer, String textureFolder, String color, String colorShadow) {
    CommunityEvent communityEvent = new CommunityEvent();
    communityEvent.id = id;
    communityEvent.name = name;
    communityEvent.instructions = instruction;
    communityEvent.textureFolder = textureFolder;
    communityEvent.color = color;
    communityEvent.colorShadow = colorShadow;
    communityEvent.titleColor = color;
    communityEvent.titleColorShadow = colorShadow;
    communityEvent.npcName = npcName;
    communityEvent.totalItems = totalItems;
    communityEvent.totalItemsPerPlayer = totalItemsPerPlayer;
    return communityEvent;
  }
  
  public CommunityEvent setTitleColor(String color) {
    this.titleColor = color;
    return this;
  }
  
  public CommunityEvent setTitleShadowColor(String color) {
    this.titleColorShadow = color;
    return this;
  }
  
  public CommunityEvent addTargetedItems(ItemStack targetedItemStack) {
    this.targetedItems.add(targetedItemStack);
    return this;
  }
  
  public CommunityEvent addPlayerStep(CommunityStep playerStep) {
    this.playerSteps.add(playerStep);
    return this;
  }
  
  public CommunityEvent addCommonStep(CommonCommunityStep commonStep) {
    this.commonSteps.add(commonStep);
    return this;
  }
  
  public boolean verify() {
    if (this.id.isEmpty() || PalaCommunityEventManager.getInstance().getCommunityEvents().contains(this) || this.npcName.isEmpty() || this.name.isEmpty())
      return false; 
    if (this.totalItemsPerPlayer <= 0 || this.totalItems <= this.totalItemsPerPlayer || this.targetedItems.size() < 1 || this.playerSteps.size() < 2)
      return false; 
    if (this.commonSteps.size() < 2)
      return false; 
    return true;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    CommunityEvent other = (CommunityEvent)obj;
    if (!Objects.equals(this.id, other.id))
      return false; 
    return true;
  }
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("id", this.id);
    compound.func_74778_a("name", this.name);
    compound.func_74778_a("instructions", this.instructions);
    compound.func_74778_a("npcName", this.npcName);
    compound.func_74778_a("textureFolder", this.textureFolder);
    compound.func_74778_a("color", this.color);
    compound.func_74778_a("colorShadow", this.colorShadow);
    compound.func_74778_a("titleColor", this.titleColor);
    compound.func_74778_a("titleColorShadow", this.titleColorShadow);
    compound.func_74768_a("totalItemsPerPlayer", this.totalItemsPerPlayer);
    compound.func_74768_a("totalItems", this.totalItems);
    NBTTagList targetedItemsList = new NBTTagList();
    for (ItemStack targetedItem : this.targetedItems) {
      NBTTagCompound itemCompound = new NBTTagCompound();
      targetedItem.func_77955_b(itemCompound);
      targetedItemsList.func_74742_a((NBTBase)itemCompound);
    } 
    compound.func_74782_a("targetedItems", (NBTBase)targetedItemsList);
    NBTTagList playerStepsList = new NBTTagList();
    for (CommunityStep playerStep : this.playerSteps) {
      NBTTagCompound stepCompound = playerStep.writeToNBT();
      playerStepsList.func_74742_a((NBTBase)stepCompound);
    } 
    compound.func_74782_a("playerSteps", (NBTBase)playerStepsList);
    NBTTagList commonStepsList = new NBTTagList();
    for (CommonCommunityStep commonStep : this.commonSteps) {
      NBTTagCompound stepCompound = commonStep.writeToNBT();
      commonStepsList.func_74742_a((NBTBase)stepCompound);
    } 
    compound.func_74782_a("commonSteps", (NBTBase)commonStepsList);
    return compound;
  }
  
  public CommunityEvent readFromNBT(NBTTagCompound compound) {
    this.id = compound.func_74779_i("id");
    this.name = compound.func_74779_i("name");
    this.instructions = compound.func_74779_i("instructions");
    this.npcName = compound.func_74779_i("npcName");
    this.textureFolder = compound.func_74779_i("textureFolder");
    this.color = compound.func_74779_i("color");
    this.colorShadow = compound.func_74779_i("colorShadow");
    this.titleColor = compound.func_74779_i("titleColor");
    this.titleColorShadow = compound.func_74779_i("titleColorShadow");
    this.totalItemsPerPlayer = compound.func_74762_e("totalItemsPerPlayer");
    this.totalItems = compound.func_74762_e("totalItems");
    this.targetedItems.clear();
    NBTTagList targetedItemsList = compound.func_150295_c("targetedItems", 10);
    for (int i = 0; i < targetedItemsList.func_74745_c(); i++) {
      NBTTagCompound itemCompound = targetedItemsList.func_150305_b(i);
      ItemStack targetedItem = ItemStack.func_77949_a(itemCompound);
      this.targetedItems.add(targetedItem);
    } 
    this.playerSteps.clear();
    NBTTagList playerStepsList = compound.func_150295_c("playerSteps", 10);
    for (int j = 0; j < playerStepsList.func_74745_c(); j++) {
      NBTTagCompound stepCompound = playerStepsList.func_150305_b(j);
      CommunityStep playerStep = new CommunityStep();
      playerStep.readFromNBT(stepCompound);
      this.playerSteps.add(playerStep);
    } 
    this.commonSteps.clear();
    NBTTagList commonStepsList = compound.func_150295_c("commonSteps", 10);
    for (int k = 0; k < commonStepsList.func_74745_c(); k++) {
      NBTTagCompound stepCompound = commonStepsList.func_150305_b(k);
      CommonCommunityStep commonStep = new CommonCommunityStep();
      commonStep.readFromNBT(stepCompound);
      this.commonSteps.add(commonStep);
    } 
    return this;
  }
  
  public CommunityEvent() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\pojo\CommunityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */