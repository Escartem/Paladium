package fr.paladium.palawither.common.wither.base;

import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.palawither.common.item.ItemWitherUpgrade;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.ai.WitherEntitySelector;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import java.util.List;
import lombok.NonNull;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public interface IWither {
  public static final IEntitySelector DEFAULT_ENTITY_SELECTOR = (IEntitySelector)new WitherEntitySelector();
  
  @NonNull
  default <T extends IWither> T onInvoke(@NonNull TileEntityWitherReceptacle tile, @NonNull EntityPlayer player, IFaction faction) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return (T)this;
  }
  
  @NonNull
  default <T extends IWither> T onLoading(@NonNull TileEntityWitherReceptacle tile) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    return (T)this;
  }
  
  @NonNull
  default <T extends IWither> T onSpawn(@NonNull TileEntityWitherReceptacle tile) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    return (T)this;
  }
  
  @NonNull
  default EntityLivingBase getEntity() {
    return (EntityLivingBase)this;
  }
  
  default ResourceLocation getInvokeSound() {
    return null;
  }
  
  default ResourceLocation getLoopSound() {
    return null;
  }
  
  default ResourceLocation getSpawnSound() {
    return null;
  }
  
  default List<IWitherCondition> getConditions() {
    return null;
  }
  
  default long getCooldown() {
    return 11L;
  }
  
  default WitherInvokeAlert getAlert() {
    return WitherInvokeAlert.LOCAL;
  }
  
  default double getViewDistance() {
    return 80.0D;
  }
  
  default double getMaxDistance() {
    return -1.0D;
  }
  
  boolean isArmored();
  
  String getDisplayName();
  
  String getBarTexture();
  
  String getColor();
  
  boolean hasUpgrade(@NonNull ItemWitherUpgrade paramItemWitherUpgrade);
  
  boolean addUpgrade(@NonNull EntityPlayer paramEntityPlayer, @NonNull ItemWitherUpgrade paramItemWitherUpgrade);
  
  public enum WitherInvokeAlert {
    NONE, LOCAL, SERVER, BROADCAST;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\IWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */