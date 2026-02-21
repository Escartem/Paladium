package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.config.ConfigManager;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.RandomCollection;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;

public class ItemLuckySword extends ItemSword implements ITooltipWiki {
  public static final int CORRUPTED_ID = 3;
  
  private int type = 0;
  
  public ItemLuckySword(int type) {
    super(Item.ToolMaterial.EMERALD);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("lucky_sword" + ((type != 0) ? ("_" + type) : ""));
    func_111206_d("palamod:lucky_sword" + ((type != 0) ? ("_" + type) : ""));
    this.type = type;
    func_77656_e((type == 3) ? 5 : 32);
  }
  
  public boolean func_77644_a(ItemStack item, EntityLivingBase entityTarget, EntityLivingBase entity) {
    if (entityTarget instanceof EntityPlayerMP && entity instanceof EntityPlayerMP) {
      EntityPlayerMP target = (EntityPlayerMP)entityTarget;
      if (!target.field_70170_p.field_72995_K) {
        LuckyEvents event = getEvent();
        if (performEvent(event, target)) {
          NBTTagCompound nbt = new NBTTagCompound();
          if (item.func_77942_o())
            nbt = item.func_77978_p(); 
          nbt.func_74778_a("lastEvent", event.getEvent().getName());
          item.func_77982_d(nbt);
          return super.func_77644_a(item, entityTarget, entity);
        } 
      } 
    } 
    return true;
  }
  
  public String func_77653_i(ItemStack item) {
    NBTTagCompound nbt = new NBTTagCompound();
    if (item.func_77942_o())
      nbt = item.func_77978_p(); 
    String name = "§eEpée en Lucky-ium";
    switch (this.type) {
      case 1:
        name = "§eEpée en Lucky-ium [§2+100%§e]";
        break;
      case 2:
        name = "§eEpée en Lucky-ium [§4-100%§e]";
        break;
      case 3:
        name = "§eLUCKYSWORD.CORRUPTED";
        break;
    } 
    if (nbt.func_74764_b("lastEvent"))
      return name + " §7(" + nbt.func_74779_i("lastEvent") + ")"; 
    return name;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> lore, boolean flag) {
    lore.add("§eRéparable avec des Lucky Blocks");
    if (this.type == 3)
      lore.add("§eDESC.TXT : Le ENTITY:PLAYER touché reçoit un LUCKYBLOCK:EFFECT.BAD aléatoire"); 
    super.func_77624_a(item, player, lore, flag);
  }
  
  public LuckyEvents getEvent() {
    if (this.type == 3)
      return LuckyEvents.getRandomBadEvent(); 
    LuckyEvents event = null;
    LuckyType type = LuckyType.PALADIUM;
    if (this.type == 1)
      type = LuckyType.ENDIUM; 
    ALuckyEvent e = (ALuckyEvent)((RandomCollection)PLuckyBlock.events.get(type)).next();
    if (this.type == 2)
      while (!e.isBad())
        e = (ALuckyEvent)((RandomCollection)PLuckyBlock.events.get(type)).next();  
    event = LuckyEvents.fromEvent(e);
    return event;
  }
  
  public boolean performEvent(LuckyEvents event, EntityPlayerMP player) {
    if (ConfigManager.hasKey("luckyblock", String.valueOf(event)) && 
      ConfigManager.getBoolean("luckyblock", String.valueOf(event))) {
      PlayerUtils.sendMessage((EntityPlayer)player, "§cCet évenement est en cours de maintenance");
      return false;
    } 
    event.getEvent().perform(player, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77636_d(ItemStack item) {
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemLuckySword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */