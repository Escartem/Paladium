package fr.paladium.palamod.modules.luckyblock.utils;

import com.allatori.annotations.DoNotRename;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.util.IWeighted;
import java.util.Objects;
import net.minecraft.entity.player.EntityPlayerMP;

@DoNotRename
public abstract class ALuckyEvent implements LuckyEvent, IWeighted {
  protected String name = "Event";
  
  protected int rarity = 30;
  
  protected boolean isBad = false;
  
  public static int qm = 37659;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {}
  
  public String getName() {
    return this.name;
  }
  
  public boolean isBad() {
    return this.isBad;
  }
  
  public int getRarity() {
    return this.rarity;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return getName().toLowerCase().replace(' ', '_');
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Boolean.valueOf(isBad()), Integer.valueOf(getRarity()), getName(), getTexture() });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || !(obj instanceof ALuckyEvent))
      return false; 
    ALuckyEvent other = (ALuckyEvent)obj;
    return (isBad() == other.isBad() && getRarity() == other.getRarity() && getName().equals(other.getName()) && getTexture().equals(other.getTexture()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\ALuckyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */