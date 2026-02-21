package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.welsymc.guardiangolem.common.entities.EntityGolem;
import fr.welsymc.guardiangolem.common.utils.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class Coucou extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityGolem entity = new EntityGolem(player.field_70170_p);
    entity.func_70634_a(x, y, z);
    ItemStack[] inventory = entity.inventory;
    inventory[0] = new ItemStack(BlocksRegister.BLOCK_PALADIUM, 64);
    PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 576.0D);
    entity.setLevel(40);
    entity.setCapacityPoints(40);
    entity.setOwner(player.func_110124_au().toString());
    entity.addUpgrade(References.GOLEMANCY_UPGRADE_ID);
    entity.addUpgrade(1);
    entity.addUpgrade(4);
    entity.addUpgrade(5);
    entity.addUpgrade(12);
    player.field_70170_p.func_72838_d((Entity)entity);
    player.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Tue moi si tu l'oses"));
  }
  
  public String getName() {
    return "Coucou";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "coucou";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Coucou.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */