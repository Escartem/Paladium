package fr.paladium.palamod.modules.paladium.common.items.armors;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.util.FastUUID;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BaseItemArmorEffect extends BaseItemArmor {
  private final PotionEffect[] effects;
  
  private final HashMap<String, Integer> hashMap = new HashMap<>();
  
  public BaseItemArmorEffect(ItemArmor.ArmorMaterial material, int type, String texture, String model, Item item_repair, PotionEffect[] effects) {
    super(material, type, texture, model, item_repair);
    this.effects = effects;
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    PotionEffect op = getEffect(this.field_77881_a);
    if (!world.field_72995_K && op != null) {
      if (op.func_76456_a() == Potion.field_76424_c.field_76415_H && PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
        if (player.field_70173_aa % 60 == 0)
          (new Notification(Notification.NotificationType.WARNING, "L'item " + func_77653_i(stack) + " est désactivé en possession de l'oeuf", PEggHunt.data.isEndEvent() ? "end" : "egghunt")).send((EntityPlayerMP)player); 
        return;
      } 
      PotionEffect effect = new PotionEffect(op.func_76456_a(), op.func_76459_b(), op.func_76458_c(), op.func_82720_e());
      String uuidString = FastUUID.toString(player.func_110124_au());
      if (!player.func_82165_m(op.func_76456_a())) {
        player.func_70690_d(effect);
        this.hashMap.put(uuidString + ";;" + op.func_76456_a(), Integer.valueOf(1));
        return;
      } 
      if (this.hashMap.containsKey(uuidString + ";;" + op.func_76456_a())) {
        int i = ((Integer)this.hashMap.get(uuidString + ";;" + op.func_76456_a())).intValue();
        i++;
        this.hashMap.replace(uuidString + ";;" + op.func_76456_a(), Integer.valueOf(i));
        if (i >= 1000)
          this.hashMap.remove(uuidString + ";;" + op.func_76456_a()); 
      } else {
        player.func_70690_d(effect);
        this.hashMap.put(uuidString + ";;" + op.func_76456_a(), Integer.valueOf(1));
      } 
    } 
  }
  
  private PotionEffect getEffect(int armorType) {
    return this.effects[armorType];
  }
  
  public static void addInfo(ItemStack stack, List<String> l) {
    if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("alchemistUnclaimType")) {
      String type = stack.func_77978_p().func_74779_i("alchemistUnclaimType");
      if ("unclaimfinder".equals(type))
        type = "Unclaim Finder Vert"; 
      if ("unclaimfinder_bleu".equals(type))
        type = "Unclaim Finder Bleu"; 
      if ("unclaimfinder_rouge".equals(type))
        type = "Unclaim Finder Rouge"; 
      if ("unclaimfinder_orange".equals(type))
        type = "Unclaim Finder Orange"; 
      l.add("§6" + type);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\BaseItemArmorEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */