package fr.paladium.palarpg.module.dungeon.common.network.choice;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.client.ui.choice.UIDungeonChoice;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CSPacketRPGDungeonChoiceNext extends ForgePacket {
  @PacketData
  private int column;
  
  public CSPacketRPGDungeonChoiceNext() {}
  
  public CSPacketRPGDungeonChoiceNext(int column) {
    this.column = column;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalWorld.isPresent())
      return; 
    DungeonWorld world = optionalWorld.get();
    try {
      if (!world.isLeader((Entity)player))
        return; 
      world.next(this.column);
    } catch (Exception e1) {
      e1.printStackTrace();
      world.getOnlinePlayers().forEach(onlinePlayer -> {
            ZUIServer.close(UIDungeonChoice.class, (EntityPlayerMP)onlinePlayer, true);
            (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg", 2.0F)).send((EntityPlayerMP)onlinePlayer);
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lUne §6§lerreur §6§lest §6§lsurvenue §6§llors §6§lde §6§lla §6§lgénération §6§ldes §6§lsalles, §6§lle §6§ldonjon §6§la §6§lété §6§lstoppé."));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §cVeuillez §créessayer §cou §ccontacter §cun §cadministrateur."));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
            try {
              world.stop();
            } catch (Exception e2) {
              e2.printStackTrace();
              world.remove();
            } 
          });
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\choice\CSPacketRPGDungeonChoiceNext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */