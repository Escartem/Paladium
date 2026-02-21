package fr.paladium.palawither.common.wither.condition.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FactionCooldownWitherCondition implements IWitherCondition {
  private FactionCooldownWitherCondition(long cooldown) {
    this.cooldown = cooldown;
  }
  
  private static final Map<String, Set<FactionCooldownWither>> FACTION_COOLDOWNS = new HashMap<>();
  
  private final long cooldown;
  
  @NonNull
  public static FactionCooldownWitherCondition create(long cooldown) {
    return new FactionCooldownWitherCondition(cooldown);
  }
  
  @SideOnly(Side.SERVER)
  public void validate(@NonNull World world, @NonNull TileEntityWitherReceptacle receptacle, @NonNull EntityPlayer player, @NonNull ItemStack item, @NonNull IWither wither) throws IWitherCondition.WitherConditionException {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (receptacle == null)
      throw new NullPointerException("receptacle is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    if (this.cooldown <= 0L)
      return; 
    IPlayer factionPlayer = FactionAPI.getInstance().getPlayer(player);
    if (factionPlayer == null || factionPlayer.isAdminMode())
      return; 
    if (!factionPlayer.hasFaction())
      throw new IWitherCondition.WitherConditionException("vous devez appartenir à une faction pour invoquer ce wither."); 
    IFaction faction = factionPlayer.getFaction();
    FactionCooldownWither cooldown = getCooldown(faction, wither);
    if (cooldown != null) {
      long timeSinceLastWither = System.currentTimeMillis() - cooldown.getDate();
      if (timeSinceLastWither < this.cooldown) {
        long timeLeft = this.cooldown - timeSinceLastWither;
        throw new IWitherCondition.WitherConditionException("votre faction doit attendre encore " + DurationConverter.fromMillisToString(Math.max(0L, timeLeft)) + " avant de pouvoir invoquer un autre wither de ce type.");
      } 
    } 
    addCooldown(faction, wither);
  }
  
  private FactionCooldownWither getCooldown(@NonNull IFaction faction, @NonNull IWither wither) {
    if (faction == null)
      throw new NullPointerException("faction is marked non-null but is null"); 
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    Set<FactionCooldownWither> cooldowns = FACTION_COOLDOWNS.get(UUIDUtils.toString(faction.getUuid()));
    if (cooldowns != null)
      for (FactionCooldownWither cooldown : cooldowns) {
        if (cooldown.getWither().equals(wither.getClass().getName()))
          return cooldown; 
      }  
    return null;
  }
  
  private void addCooldown(@NonNull IFaction faction, @NonNull IWither wither) {
    if (faction == null)
      throw new NullPointerException("faction is marked non-null but is null"); 
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    Set<FactionCooldownWither> cooldowns = FACTION_COOLDOWNS.computeIfAbsent(UUIDUtils.toString(faction.getUuid()), k -> new HashSet());
    cooldowns.add(new FactionCooldownWither(wither.getClass().getName(), System.currentTimeMillis()));
  }
  
  private class FactionCooldownWither {
    private final String wither;
    
    private final long date;
    
    public FactionCooldownWither(String wither, long date) {
      this.wither = wither;
      this.date = date;
    }
    
    public String getWither() {
      return this.wither;
    }
    
    public long getDate() {
      return this.date;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\impl\FactionCooldownWitherCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */