package fr.paladium.palamod.modules.luckyblock.monthly.dialogs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.ariane.Ariane;
import fr.paladium.ariane.lib.dialog.ArianeDialog;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenCustomDialogGui;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.entity.EntityNPCInterface;

public abstract class AbstractDialogManager {
  public static final String EXTENSION = "DialogManager";
  
  protected String title;
  
  protected List<String> npcNames;
  
  public String getTitle() {
    return this.title;
  }
  
  public List<String> getNpcNames() {
    return this.npcNames;
  }
  
  public AbstractDialogManager(String title, List<String> npcNames) {
    this.title = title;
    this.npcNames = npcNames;
  }
  
  public AbstractDialogManager(String title) {
    this(title, Collections.singletonList(title));
  }
  
  public AbstractDialogManager() {
    String name = getClass().getSimpleName();
    if (!name.endsWith("DialogManager"))
      throw new IllegalArgumentException("DialogManager class name must end with DialogManager"); 
    name = name.substring(0, name.length() - "DialogManager".length());
    this.title = name;
    this.npcNames = Collections.singletonList(name);
  }
  
  public boolean sendDialog(EntityPlayerMP player, Entity npc) {
    CustomDialog dialog = getDialog(player, npc);
    if (dialog == null)
      return false; 
    Ariane.getServer().addDialog((EntityPlayer)player, (ArianeDialog)dialog);
    PalaMod.getNetwork().sendTo((IMessage)new PacketOpenCustomDialogGui(dialog), player);
    return true;
  }
  
  public void sendOtherDialog(EntityPlayerMP player, CustomDialog dialog) {
    if (dialog == null)
      return; 
    Ariane.getServer().addDialog((EntityPlayer)player, (ArianeDialog)dialog);
    PalaMod.getNetwork().sendTo((IMessage)new PacketOpenCustomDialogGui(dialog), player);
  }
  
  public boolean canDisplay(EntityLivingBase npc) {
    if (this.npcNames == null || this.npcNames.isEmpty())
      return false; 
    if (npc instanceof EntityNPCInterface) {
      EntityNPCInterface npcInterface = (EntityNPCInterface)npc;
      return this.npcNames
        .stream()
        .anyMatch(name -> npcInterface.display.name.equals(name));
    } 
    return this.npcNames
      .stream()
      .anyMatch(name -> npc.func_70005_c_().equals(name));
  }
  
  public abstract CustomDialog getDialog(EntityPlayerMP paramEntityPlayerMP, Entity paramEntity);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\dialogs\AbstractDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */