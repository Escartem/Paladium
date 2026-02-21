package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.VitalSpaceEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;

public class VitalSpaceData {
  private static final int RADIUS = 5;
  
  public static final String[] START_MESSAGES = new String[] { "§eUn espace vital a été créé autour de vous.", "§eSi vous ne contaminez pas cet espace, vous serez récompensé.", "§eL'espace vital est actif pendant 5 minutes." };
  
  public static final String PLAYER_MESSAGE = "§aVous avez ralenti %s joueur(s).";
  
  public static final String TARGET_MESSAGE = "§cVous avez été ralenti par le joueur %s.";
  
  public static final String WIN_MESSAGE = "§aFélicitations, vous avez réussi à ne pas contaminer l'espace vital.";
  
  public static final String LOSE_MESSAGE = "§cVous avez contaminé l'espace vital.";
  
  private final UUID playerUniqueId;
  
  private final long startMillis;
  
  private int score;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public long getStartMillis() {
    return this.startMillis;
  }
  
  public int getScore() {
    return this.score;
  }
  
  public VitalSpaceData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.startMillis = System.currentTimeMillis();
    this.score = 0;
    MonthlyUtils.sendMessage((EntityPlayer)player, START_MESSAGES);
  }
  
  public void perform(EntityPlayerMP player) {
    if (player == null)
      return; 
    AxisAlignedBB box = AxisAlignedBB.func_72330_a(player.field_70165_t - 5.0D, player.field_70163_u - 5.0D, player.field_70161_v - 5.0D, player.field_70165_t + 5.0D, player.field_70163_u + 5.0D, player.field_70161_v + 5.0D);
    List<EntityPlayerMP> players = player.field_70170_p.func_72872_a(EntityPlayerMP.class, box);
    int count = 0;
    for (EntityPlayerMP other : players) {
      if (other == player)
        continue; 
      if (!other.func_70644_a(Potion.field_76421_d)) {
        MonthlyUtils.sendMessage((EntityPlayer)other, new String[] { String.format("§cVous avez été ralenti par le joueur %s.", new Object[] { player.func_70005_c_() }) });
        other.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, MonthlyUtils.translateSecondsToTicks(60), 0));
        this.score++;
        count++;
      } 
    } 
    if (count > 0)
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("§aVous avez ralenti %s joueur(s).", new Object[] { Integer.valueOf(count) }) }); 
  }
  
  public void expire(EntityPlayerMP player) {
    if (player == null)
      return; 
    if (this.score <= 0) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aFélicitations, vous avez réussi à ne pas contaminer l'espace vital." });
      InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SURVIVINGSTONE, 1));
      InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack((Item)ItemsRegister.STICK_HEAL, 1));
    } else {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cVous avez contaminé l'espace vital." });
      MonthlyUtils.playSound(player, "soft_fail");
    } 
    MonthlyUtils.stopHeliosEvent(player, VitalSpaceEvent.class);
  }
  
  public boolean isExpired(long now) {
    return (now - this.startMillis >= VitalSpaceEvent.DURATION_MILLIS);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\VitalSpaceData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */