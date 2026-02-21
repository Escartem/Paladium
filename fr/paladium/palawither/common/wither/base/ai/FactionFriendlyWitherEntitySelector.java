package fr.paladium.palawither.common.wither.base.ai;

import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palawither.common.wither.base.IWither;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class FactionFriendlyWitherEntitySelector implements IEntitySelector {
  private final Supplier<UUID> factionUuidSupplier;
  
  public FactionFriendlyWitherEntitySelector(Supplier<UUID> factionUuidSupplier) {
    this.factionUuidSupplier = factionUuidSupplier;
  }
  
  public boolean func_82704_a(Entity entity) {
    if (entity instanceof EntityPlayer) {
      if (ForgeEnv.isIDE())
        return IWither.DEFAULT_ENTITY_SELECTOR.func_82704_a(entity); 
      UUID factionUuid = this.factionUuidSupplier.get();
      if (factionUuid == null)
        return IWither.DEFAULT_ENTITY_SELECTOR.func_82704_a(entity); 
      IPlayer iPlayer = FactionAPI.getInstance().getPlayer((EntityPlayer)entity);
      if (iPlayer == null || !iPlayer.hasFaction())
        return false; 
      return !iPlayer.getFaction().getUuid().equals(this.factionUuidSupplier.get());
    } 
    return IWither.DEFAULT_ENTITY_SELECTOR.func_82704_a(entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\ai\FactionFriendlyWitherEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */