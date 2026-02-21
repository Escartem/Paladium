package fr.paladium.palamod.modules.hunter.items;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemCaptureStone extends Item implements ITooltipWiki {
  private static Map<String, String> mapping = new HashMap<>();
  
  private final IIcon[][] icons = new IIcon[2][2];
  
  public static void initMapping() {
    mapping.put("EntityPig", "PIG");
    mapping.put("EntityCow", "COW");
    mapping.put("EntitySheep", "SHEEP");
    mapping.put("EntitySlime", "SLIME");
    mapping.put("EntityZombie", "ZOMBIE");
    mapping.put("EntityCreeper", "CREEPER");
    mapping.put("EntityRabbit", "RABBIT");
    mapping.put("EntityHorse", "HORSE");
    mapping.put("EntityChicken", "CHICKEN");
    mapping.put("EntityElephant", "PALAMOD_ELEPHANT");
    mapping.put("EntityFarmerChicken", "PALAMOD_FARMERCHICKEN");
    mapping.put("EntityCavernousZombie", "PALAMOD_CAVERNOUSZOMBIE");
    mapping.put("EntityFlowerMonster", "PALAMOD_FLOWERMONSTER");
    mapping.put("EntityMegaCreeper", "PALAMOD_MEGACREEPER");
    mapping.put("EntityTurtle", "PALAMOD_TURTLE");
    mapping.put("EntityPanda", "PALAMOD_PANDA");
    mapping.put("EntityDolphin", "PALAMOD_DOLPHIN");
    mapping.put("EntityCrab", "PALAMOD_CRAB");
    mapping.put("EntityParrot", "PALAMOD_PARROT");
    mapping.put("EntitySnail", "PALAMOD_SNAIL");
    mapping.put("EntitySnake", "PALAMOD_SNAKE");
    mapping.put("EntityJellyFish", "PALAMOD_JELLYFISH");
    mapping.put("EntityGoat", "PALAMOD_GOAT");
    mapping.put("EntityDancarokMount", "DANCAROK");
    mapping.put("EntityRavirokMount", "RAVIROK");
    mapping.put("EntityTedarokMount", "TEDAROK");
  }
  
  public ItemCaptureStone() {
    func_77637_a((CreativeTabs)Registry.HUNTER_TAB);
    func_77625_d(1);
    func_77655_b("capture_stone");
    func_111206_d("palamod:capture_stone_empty");
  }
  
  public boolean isEmpty(ItemStack item) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("used"))
      return false; 
    return true;
  }
  
  public void func_94581_a(IIconRegister register) {
    this.icons[0][0] = register.func_94245_a("palamod:capture_stone_empty");
    this.icons[0][1] = register.func_94245_a("palamod:capture_stone_full");
    this.icons[1][0] = register.func_94245_a("palamod:pokeball_empty");
    this.icons[1][1] = register.func_94245_a("palamod:pokeball_full");
    super.func_94581_a(register);
  }
  
  public IIcon getIcon(ItemStack stack, int pass) {
    return this.icons[stack.func_82833_r().startsWith("pokeball") ? 1 : 0][stack.func_77960_j()];
  }
  
  public IIcon func_77650_f(ItemStack stack) {
    return this.icons[stack.func_82833_r().startsWith("pokeball") ? 1 : 0][stack.func_77960_j()];
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (item.func_77942_o() && !world.field_72995_K && 
      item.func_77978_p().func_74764_b("used") && EventUtils.canInteract(player, x, y, z)) {
      String entity = item.func_77978_p().func_74779_i("entity");
      if (mapping.containsKey(entity) && 
        !WorldGuardUtils.checkFlag(world, x, y, z, DefaultFlag.DENY_SPAWN, mapping.get(entity)))
        return false; 
      item.field_77994_a--;
      player.field_71069_bz.func_75142_b();
      String entityStr = "";
      for (Object obj : EntityList.field_75626_c.keySet()) {
        if (("class " + item.func_77978_p().func_74779_i("entityClass")).equalsIgnoreCase(obj.toString()))
          entityStr = EntityList.field_75626_c.get(obj).toString(); 
      } 
      Entity ent = EntityList.func_75620_a(entityStr, world);
      if (ent instanceof EntityLivingBase) {
        ((EntityLivingBase)ent).func_70037_a(item.func_77978_p().func_74775_l("entityNbt"));
        ((EntityLivingBase)ent).func_70020_e(item.func_77978_p().func_74775_l("nbt"));
        ((EntityLivingBase)ent).func_70606_j(((EntityLivingBase)ent).func_110138_aP());
        ((EntityLivingBase)ent).field_70128_L = false;
        ((EntityLivingBase)ent).field_70725_aQ = 0;
        ((EntityLivingBase)ent).field_70159_w = 0.0D;
        ((EntityLivingBase)ent).field_70181_x = 0.0D;
        ((EntityLivingBase)ent).field_70179_y = 0.0D;
      } 
      if (ent != null) {
        ent.func_70107_b(x, (y + 2), z);
        try {
          if (BukkitUtils.hasPermission(player.func_110124_au(), "palamod.capture.admin"))
            ent.func_70062_b(0, null); 
        } catch (Exception e) {
          ent.func_70062_b(0, null);
        } 
        world.func_72838_d(ent);
      } 
      return true;
    } 
    return false;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    if (item.func_77942_o() && item.func_77978_p().func_74764_b("used")) {
      list.add("§eEntité: §b" + item.func_77978_p().func_74779_i("entity"));
    } else {
      list.add("§cCette pierre de capture est vide");
    } 
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemCaptureStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */