package fr.paladium.palamod.modules.luckyblock.commands.luckystats;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import java.time.Duration;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SupermanCommand extends CommandBase {
  public String func_71517_b() {
    return "superman";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "superman";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      if (player.func_82169_q(2) != null) {
        if (player.func_82169_q(2).func_77973_b() == ItemsRegister.SUPERMAN_CAPE) {
          if (PlayerLuckyBlockProperties.get((EntityPlayer)player).getSuperman() == 0L || 10L - 
            Duration.between(TimeUtil.fromLong(PlayerLuckyBlockProperties.get((EntityPlayer)player).getSuperman()), 
              TimeUtil.fromLong(TimeUtil.now()))
            .toMinutes() <= 0L) {
            player.func_70690_d(new PotionEffect(PLuckyBlock.SUPER_MAN.field_76415_H, 1200, 0));
            player.field_71075_bZ.field_75101_c = true;
            player.field_71075_bZ.field_75100_b = true;
            player.func_71016_p();
            PlayerLuckyBlockProperties.get((EntityPlayer)player).setSuperman(TimeUtil.now());
          } else {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cCommande utilisable dans §e" + (10L - 
                  Duration.between(
                    TimeUtil.fromLong(PlayerLuckyBlockProperties.get((EntityPlayer)player).getSuperman()), 
                    TimeUtil.fromLong(TimeUtil.now())).toMinutes()) + " minutes."));
          } 
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cVous devez avoir une cape de Superman."));
        } 
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cVous devez avoir une cape de Superman."));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\luckystats\SupermanCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */