package fr.paladium.palaplaceholder.common.extension;

import fr.paladium.palaplaceholder.common.extension.placeholder.Placeholder;
import net.minecraft.entity.player.EntityPlayer;

public interface IPlaceholderExtension {
  String getIdentifier();
  
  void init();
  
  void registerPlaceholder(Placeholder... paramVarArgs);
  
  String replace(String paramString, EntityPlayer paramEntityPlayer);
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\common\extension\IPlaceholderExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */