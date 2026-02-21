package fr.paladium.palamod.modules.paladium.common.eep;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class WelcomeEEP extends ExtendedEntityProperties {
  public static final String PROP_NAME = "welcome_eep";
  
  public static final String KEY_KIT_WELCOME = "welcomeKit";
  
  private boolean welcomeKit = false;
  
  public boolean isWelcomeKit() {
    return this.welcomeKit;
  }
  
  public static WelcomeEEP get(Entity entity) {
    return (WelcomeEEP)entity.getExtendedProperties("welcome_eep");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74757_a("welcomeKit", this.welcomeKit);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("welcomeKit"))
      this.welcomeKit = compound.func_74767_n("welcomeKit"); 
  }
  
  public void giveWelcomeKit(EntityPlayerMP player) {
    ItemStack[] kit = { new ItemStack(ItemsRegister.AMETHYST_SWORD), new ItemStack(ItemsRegister.PALADIUM_PICKAXE), new ItemStack(Items.field_151083_be, 64), new ItemStack(Blocks.field_150478_aa, 20), new ItemStack(Blocks.field_150364_r, 32) };
    ItemStack[] armor = { new ItemStack(ItemsRegister.TITANE_BOOTS), new ItemStack(ItemsRegister.TITANE_LEGGINGS), new ItemStack(ItemsRegister.TITANE_CHESTPLATE), new ItemStack(ItemsRegister.PALADIUM_HELMET) };
    for (ItemStack item : kit)
      InventoryUtils.addItem((EntityPlayer)player, item, item.field_77994_a); 
    for (int i = 0; i < armor.length; i++)
      player.field_71071_by.field_70460_b[i] = armor[i]; 
    this.welcomeKit = true;
    sync();
    (new Notification(Notification.NotificationType.INFO, "Tiens, voici un kit de départ, de quoi débuter ton aventure paladienne !", "Paladium")).send(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\eep\WelcomeEEP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */