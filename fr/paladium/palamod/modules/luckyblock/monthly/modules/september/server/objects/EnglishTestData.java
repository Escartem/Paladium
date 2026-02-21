package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.EnglishTestEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCTimedLanguagePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class EnglishTestData {
  private TimedSchematic schematic;
  
  private int index;
  
  private UUID playerUniqueId;
  
  private Location spawnLocation;
  
  private Cuboid cuboid;
  
  public TimedSchematic getSchematic() {
    return this.schematic;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public Location getSpawnLocation() {
    return this.spawnLocation;
  }
  
  public Cuboid getCuboid() {
    return this.cuboid;
  }
  
  public EnglishTestData(EntityPlayerMP player) {
    this.spawnLocation = new Location((Entity)player);
    this.playerUniqueId = player.func_110124_au();
    this
      
      .cuboid = new Cuboid(player.field_70170_p, this.spawnLocation.getX() - 5.0D, this.spawnLocation.getY() - 5.0D, this.spawnLocation.getZ() - 5.0D, this.spawnLocation.getX() + 5.0D, this.spawnLocation.getY() + 5.0D, this.spawnLocation.getZ() + 5.0D);
    this
      
      .schematic = new TimedSchematic(EnglishTestEvent.SCHEMATIC_DURATION, new DoubleLocation(this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ()), "lb09_anglais.schematic");
    this.schematic.paste(player, false);
    this.index = -1;
    sendNextQuestion(player);
  }
  
  public boolean hasFinished() {
    return (this.index >= EnglishTestEvent.INSTANCE.getResponses().size());
  }
  
  public void handleWin(EntityPlayerMP player) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aBravo, tu as réussi le test d’Anglais. Heureusement car il était super facile ;)" });
  }
  
  public void handleLoose(EntityPlayerMP player) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVu que tu ne parles pas bien Anglais tu vas apprendre directement en jeu !" });
    this.schematic.updateExpiration(5000L);
    SeptemberCommonModule.INSTANCE.getNetwork().sendTo((IMessage)new SCTimedLanguagePacket(SCTimedLanguagePacket.EVENT_DURATION), player);
  }
  
  public void handleFinish() {
    this.schematic.updateExpiration(5000L);
    EnglishTestEvent.INSTANCE.getDatas().remove(this);
  }
  
  public void handleResponse(EntityPlayerMP player, String message) {
    if (hasFinished())
      return; 
    EnglishResponse response = EnglishTestEvent.INSTANCE.getResponses().get(this.index);
    if (!response.isCorrect(message)) {
      handleLoose(player);
      handleFinish();
      return;
    } 
    sendNextQuestion(player);
  }
  
  public void sendNextQuestion(EntityPlayerMP player) {
    this.index++;
    if (hasFinished()) {
      handleWin(player);
      handleFinish();
      return;
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&6" + ((EnglishResponse)EnglishTestEvent.INSTANCE.getResponses().get(this.index)).getQuestion() });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\EnglishTestData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */