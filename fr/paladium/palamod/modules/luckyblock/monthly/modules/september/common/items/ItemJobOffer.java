package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.factions.client.registers.ItemsRegister;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.AnnouncesUI;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemJobOffer extends Item implements ITooltipInformations {
  public static final String NAME = "job_offer";
  
  public static final String DESCRIPTION = "Permet d’explorer les offres d’emploi de Paladium. Tu devrais cliquer sur certaines, cela pourrait t’être utile !";
  
  public static final String JUSTIN_NBT_FIELD = "justin";
  
  public static final String KILLER_NBT_FIELD = "killer";
  
  public static final String DIDI_NBT_FIELD = "didi";
  
  public static final int JUSTIN_ACTION = 0;
  
  public static final int KILLER_ACTION = 1;
  
  public static final int DIDI_ACTION = 2;
  
  public static final int KING_ACTION = 3;
  
  public static final int FAN_ACTION = 4;
  
  public static final int PVP_ACTION = 5;
  
  private static final String ALREADY_RECEIVED_MESSAGE = "&cCette offre d'emploi a déjà été reçue !";
  
  private static final String REWARD_NOT_FOUND_MESSAGE = "&cCette offre d'emploi est expirée !";
  
  private static final String REWARD_RECEIVED_MESSAGE = "&aVous avez reçu votre récompense !";
  
  public ItemJobOffer() {
    func_77655_b("job_offer");
    func_111206_d("palamod:job_offer");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K) {
      openUI(player);
      return stack;
    } 
    initNbt(stack);
    return stack;
  }
  
  public void handlePacket(EntityPlayer player, ItemStack stack, int action) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null || action < 0 || action > 2) {
      MonthlyUtils.sendMessage(player, new String[] { "&cCette offre d'emploi est expirée !" });
      return;
    } 
    boolean justinRet = compound.func_74767_n("justin");
    boolean killerRet = compound.func_74767_n("killer");
    boolean didiRet = compound.func_74767_n("didi");
    switch (action) {
      case 0:
        if (justinRet) {
          MonthlyUtils.sendMessage(player, new String[] { "&cCette offre d'emploi a déjà été reçue !" });
          break;
        } 
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.COMPASS_BASE_CLAIM));
        MonthlyUtils.sendMessage(player, new String[] { "&aVous avez reçu votre récompense !" });
        compound.func_74757_a("justin", true);
        justinRet = true;
        break;
      case 1:
        if (killerRet) {
          MonthlyUtils.sendMessage(player, new String[] { "&cCette offre d'emploi a déjà été reçue !" });
          break;
        } 
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.COMPASS_HUNTING_TREASURE));
        MonthlyUtils.sendMessage(player, new String[] { "&aVous avez reçu votre récompense !" });
        compound.func_74757_a("killer", true);
        killerRet = true;
        break;
      case 2:
        if (didiRet) {
          MonthlyUtils.sendMessage(player, new String[] { "&cCette offre d'emploi a déjà été reçue !" });
          break;
        } 
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.COMPASS_EXPLORATION));
        MonthlyUtils.sendMessage(player, new String[] { "&aVous avez reçu votre récompense !" });
        compound.func_74757_a("didi", true);
        didiRet = true;
        break;
    } 
    if (justinRet && killerRet && didiRet)
      MonthlyUtils.decrementCurrentItem(player, stack); 
  }
  
  @SideOnly(Side.CLIENT)
  public void openUI(EntityPlayer player) {
    MonthlyUtils.openUI((GuiScreen)new AnnouncesUI());
  }
  
  public void initNbt(ItemStack stack) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null)
      compound = new NBTTagCompound(); 
    if (!compound.func_74764_b("justin") && 
      !compound.func_74764_b("killer") && 
      !compound.func_74764_b("didi")) {
      compound.func_74757_a("justin", false);
      compound.func_74757_a("killer", false);
      compound.func_74757_a("didi", false);
    } 
    stack.func_77982_d(compound);
  }
  
  public String[] getInformations(ItemStack stack, EntityPlayer player, boolean b) {
    return MonthlyUtils.splitDescription("Permet d’explorer les offres d’emploi de Paladium. Tu devrais cliquer sur certaines, cela pourrait t’être utile !");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemJobOffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */