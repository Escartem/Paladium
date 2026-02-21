package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;

public class HeadOnShoulders extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
    List<EntityPlayerMP> filteredPlayers = (List<EntityPlayerMP>)players.stream().filter(mp -> !mp.func_110124_au().equals(player.func_110124_au())).collect(Collectors.toList());
    if (filteredPlayers == null || filteredPlayers.isEmpty()) {
      PlayerUtils.sendMessage((EntityPlayer)player, "§cAucun joueur à l'horizon. Vous avez récupéré votre propre tête.");
      givePlayerSkull(player, player.func_70005_c_());
      return;
    } 
    EntityPlayerMP randomPlayer = filteredPlayers.get(player.field_70170_p.field_73012_v.nextInt(filteredPlayers.size()));
    if (randomPlayer == null)
      return; 
    givePlayerSkull(player, randomPlayer.func_70005_c_());
    PlayerUtils.sendMessage((EntityPlayer)randomPlayer, "§e" + player.func_70005_c_() + " a récupéré §evotre §etête §edans §eun §eLuckyBlock §e:o");
  }
  
  public String getName() {
    return "La tête sur les épaules";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "la_tete_sur_les_epaules";
  }
  
  private boolean givePlayerSkull(EntityPlayerMP player, String username) {
    ItemStack stack = new ItemStack(Items.field_151144_bL, 1, 3);
    stack.func_77982_d(new NBTTagCompound());
    stack.func_77978_p().func_74782_a("SkullOwner", (NBTBase)new NBTTagString(username));
    player.field_71071_by.func_70441_a(stack);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\HeadOnShoulders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */