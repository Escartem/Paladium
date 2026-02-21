package fr.paladium.palamod.modules.paladium.common.items.armors;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.util.FastUUID;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArmorTravel extends ItemArmor implements ITooltipWiki {
  public static final int TRAVEL_BOOTS = 0;
  
  public static final int TRAVEL_LEGGINGS = 1;
  
  public static final int JUMP_CHEST = 2;
  
  public static final int SLIMY_HELMET = 3;
  
  public static final int SCUBA_HELMET = 4;
  
  public static final int HOOD_HELMET = 5;
  
  public int id;
  
  private final HashMap<String, Integer> hashMap = new HashMap<>();
  
  public ItemArmorTravel(int type, String name, String textureName, int id) {
    super(ItemArmor.ArmorMaterial.IRON, 0, type);
    func_77655_b(name);
    func_111206_d("palamod:" + textureName);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77625_d(1);
    this.id = id;
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    switch (this.id) {
      case 5:
        return "palamod:textures/models/HoodHelmet.png";
      case 2:
        return "palamod:textures/models/JumpArmor.png";
      case 4:
        return "palamod:textures/models/ScubaArmor.png";
      case 3:
        return "palamod:textures/models/Slimy_Helmet.png";
      case 0:
        return "palamod:textures/models/travel_boots.png";
      case 1:
        return "palamod:textures/models/travel_leggings.png";
    } 
    return "palamod:textures/models/paladium_armor_1.png";
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == Items.field_151042_j)
      return true; 
    return false;
  }
  
  public int getType() {
    return this.id;
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    if (world.field_72995_K && getType() != 0 && getType() != 3)
      return; 
    if (getType() != 0 && getType() != 3 && WorldGuardUtils.isItemEffectBlocked(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, Item.func_150891_b(stack.func_77973_b())))
      return; 
    if (!world.field_72995_K && (getType() == 1 || getType() == 2) && PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
      if (player.field_70173_aa % 60 == 0)
        (new Notification(Notification.NotificationType.WARNING, "L'item " + func_77653_i(stack) + " est désactivé en possession de l'oeuf", PEggHunt.data.isEndEvent() ? "end" : "egghunt")).send((EntityPlayerMP)player); 
      return;
    } 
    String uuidString = FastUUID.toString(player.func_110124_au());
    switch (this.id) {
      case 3:
        if (player.field_70163_u > 0.0D) {
          player.field_70143_R = 0.0F;
          if (world.field_72995_K && FMLCommonHandler.instance().getSide().isClient()) {
            int l = MathHelper.func_76128_c(player.field_70165_t);
            int i1 = MathHelper.func_76128_c(player.field_70163_u + 1.0D);
            int j1 = MathHelper.func_76128_c(player.field_70161_v);
            if (world.func_147439_a(l, i1, j1).func_149688_o().func_76220_a()) {
              if (GameSettings.func_100015_a((Minecraft.func_71410_x()).field_71474_y.field_74314_A)) {
                player.field_70181_x = 0.1D;
                break;
              } 
              player.field_70181_x *= 0.6D;
            } 
          } 
        } 
        break;
      case 4:
        player.func_70050_g(300);
        break;
      case 2:
        if (this.hashMap.containsKey(uuidString + ";;" + Potion.field_76430_j.field_76415_H)) {
          int i = ((Integer)this.hashMap.get(uuidString + ";;" + Potion.field_76430_j.field_76415_H)).intValue();
          i++;
          this.hashMap.replace(uuidString + ";;" + Potion.field_76430_j.field_76415_H, Integer.valueOf(i));
          if (i >= 40)
            this.hashMap.remove(uuidString + ";;" + Potion.field_76430_j.field_76415_H); 
          break;
        } 
        player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 60, 2));
        this.hashMap.put(player.func_110124_au().toString() + ";;" + Potion.field_76430_j.field_76415_H, Integer.valueOf(1));
        break;
      case 1:
        if (this.hashMap.containsKey(uuidString + ";;" + Potion.field_76424_c.field_76415_H)) {
          int i = ((Integer)this.hashMap.get(uuidString + ";;" + Potion.field_76424_c.field_76415_H)).intValue();
          i++;
          this.hashMap.replace(uuidString + ";;" + Potion.field_76424_c.field_76415_H, Integer.valueOf(i));
          if (i >= 40)
            this.hashMap.remove(uuidString + ";;" + Potion.field_76424_c.field_76415_H); 
          break;
        } 
        player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 60, 2));
        this.hashMap.put(uuidString + ";;" + Potion.field_76424_c.field_76415_H, Integer.valueOf(1));
        break;
      case 0:
        player.field_70138_W = 1.0F;
        break;
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack item, EntityPlayer player, List list, boolean flag) {
    super.func_77624_a(item, player, list, flag);
    BaseItemArmorEffect.addInfo(item, list);
  }
  
  public String getUrl(ItemStack item) {
    if (getType() == 5 || getType() == 3 || getType() == 4)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armures#7.-les-casques"; 
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armures#6.-stuff-travel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ItemArmorTravel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */