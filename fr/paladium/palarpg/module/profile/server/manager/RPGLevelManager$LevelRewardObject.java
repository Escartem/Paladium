package fr.paladium.palarpg.module.profile.server.manager;

import com.google.gson.annotations.SerializedName;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class LevelRewardObject {
  @SerializedName("skillpoints")
  private int skillPoints;
  
  public int getSkillPoints() {
    return this.skillPoints;
  }
  
  public void giveTo(@NonNull EntityPlayer player, @NonNull RPGProfilePlayerData pData) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (pData == null)
      throw new NullPointerException("pData is marked non-null but is null"); 
    if (this.skillPoints > 0) {
      pData.addSkillPoints(this.skillPoints);
      player.func_145747_a((new ChatComponentText("§8[§6Paladium§8] §aVous avez reçu un point de compétence !")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\manager\RPGLevelManager$LevelRewardObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */