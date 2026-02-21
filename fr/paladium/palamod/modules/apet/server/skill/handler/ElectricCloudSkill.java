package fr.paladium.palamod.modules.apet.server.skill.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftObjective;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.apet.common.PetCommonProxy;
import fr.paladium.palamod.modules.apet.common.network.packet.SCLightningBoltPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.List;
import java.util.Random;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ElectricCloudSkill extends ASkillHandler {
  public static final String ID = "electric_cloud";
  
  private static final int RADIUS = 1;
  
  private static final double CHANCE = 15.0D;
  
  public ElectricCloudSkill() {
    super("electric_cloud");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    int amount = (int)getSkill().getPersonalValue(pet);
    if (amount <= 0)
      return false; 
    lightning(player, amount);
    return true;
  }
  
  private Cuboid getCuboid(EntityPlayerMP player) {
    return new Cuboid(player.field_70170_p, player.field_70165_t - 1.0D, player.field_70163_u, player.field_70161_v - 1.0D, player.field_70165_t + 1.0D, player.field_70163_u, player.field_70161_v + 1.0D);
  }
  
  public void lightning(EntityPlayerMP player, int amount) {
    Random random = new Random();
    (new Thread(() -> {
          try {
            for (int i = 0; i < amount; i++) {
              FMLServerScheduler.getInstance().add(new Runnable[] { () });
              Thread.sleep(1000L);
            } 
          } catch (InterruptedException e) {
            e.printStackTrace();
          } 
        })).start();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\skill\handler\ElectricCloudSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */