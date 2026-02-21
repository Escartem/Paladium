package fr.paladium.palavoicechat.common.eep;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class VoiceChatExtendedEntityProperties extends ExtendedEntityProperties {
  public static final String PROP_NAME = "VOICECHAT_EEP";
  
  private boolean isMuted = true;
  
  public boolean isMuted() {
    return this.isMuted;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound voiceChatCompound = new NBTTagCompound();
    voiceChatCompound.func_74757_a("isMuted", this.isMuted);
    compound.func_74782_a("VOICECHAT_EEP", (NBTBase)voiceChatCompound);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound voiceChatCompound = compound.func_74775_l("VOICECHAT_EEP");
    this.isMuted = !voiceChatCompound.func_74764_b("isMuted") ? true : voiceChatCompound.func_74767_n("isMuted");
  }
  
  public static VoiceChatExtendedEntityProperties get(EntityPlayer player) {
    return (VoiceChatExtendedEntityProperties)player.getExtendedProperties("VOICECHAT_EEP");
  }
  
  @SideOnly(Side.CLIENT)
  public static VoiceChatExtendedEntityProperties me() {
    return (VoiceChatExtendedEntityProperties)(Minecraft.func_71410_x()).field_71439_g.getExtendedProperties("VOICECHAT_EEP");
  }
  
  public void setMuted(boolean isMuted) {
    if (this.isMuted == isMuted)
      return; 
    this.isMuted = isMuted;
    sync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\eep\VoiceChatExtendedEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */