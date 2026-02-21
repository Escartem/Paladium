package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.util.PlayerUtil;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemLasso extends Item implements ITooltipWiki {
  private String[] whitelist = new String[] { 
      "EntityPig", "EntityCow", "EntitySheep", "EntitySlime", "EntityZombie", "EntityCreeper", "EntityRabbit", "EntityHorse", "EntityChicken", "EntityElephant", 
      "EntityFarmerChicken", "EntityCavernousZombie", "EntityFlowerMonster", "EntityMegaCreeper", "EntityTurtle", "EntityPanda", "EntityDolphin", "EntityCrab", "EntityParrot", "EntitySnail", 
      "EntitySnake", "EntityJellyFish", "EntityGoat" };
  
  public ItemLasso() {
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("lasso");
    func_111206_d("palamod:lasso");
    func_77625_d(16);
  }
  
  private boolean isWhitelist(String entity) {
    boolean whitelist = false;
    for (String s : this.whitelist) {
      if (s.equalsIgnoreCase(entity))
        whitelist = true; 
    } 
    return whitelist;
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (item.func_77942_o() && !world.field_72995_K && 
      item.func_77978_p().func_74764_b("used") && EventUtils.canInteract(player, x, y, z)) {
      item.field_77994_a--;
      player.field_71069_bz.func_75142_b();
      String entityStr = "";
      for (Object obj : EntityList.field_75626_c.keySet()) {
        if (obj.toString()
          .equalsIgnoreCase("class " + item.func_77978_p().func_74779_i("entityClass")))
          entityStr = EntityList.field_75626_c.get(obj).toString(); 
      } 
      Entity ent = EntityList.func_75620_a(entityStr, world);
      if (ent instanceof EntityLivingBase) {
        ((EntityLivingBase)ent)
          .func_70037_a(item.func_77978_p().func_74775_l("entityNbt"));
        ((EntityLivingBase)ent).func_70606_j(item.func_77978_p().func_74760_g("health"));
        ((EntityLivingBase)ent).func_70020_e(item.func_77978_p().func_74775_l("nbt"));
      } 
      ent.func_70107_b(x, (y + 2), z);
      world.func_72838_d(ent);
      return true;
    } 
    return false;
  }
  
  public boolean func_111207_a(ItemStack item, EntityPlayer player, EntityLivingBase entity) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("used"))
      return false; 
    if (!isWhitelist(entity.getClass().getSimpleName()))
      return false; 
    if (entity.func_70694_bm() != null)
      return false; 
    if (entity.field_70128_L || entity.func_110143_aJ() <= 0.0F)
      return false; 
    item.field_77994_a--;
    ItemStack result = new ItemStack(ItemsRegister.LASSO);
    result.func_151001_c("§eLasso §b" + entity.func_70005_c_());
    NBTTagCompound compound = result.func_77978_p();
    compound.func_74757_a("used", true);
    compound.func_74778_a("entity", entity.getClass().getSimpleName());
    compound.func_74778_a("entityClass", entity.getClass().getCanonicalName());
    compound.func_74776_a("health", entity.func_110143_aJ());
    compound.func_74776_a("maxhealth", entity.func_110138_aP());
    NBTTagCompound nbt = new NBTTagCompound();
    entity.func_70109_d(nbt);
    compound.func_74782_a("nbt", (NBTBase)nbt);
    NBTTagCompound entityNbt = new NBTTagCompound();
    entity.func_70014_b(entityNbt);
    compound.func_74782_a("entityNbt", (NBTBase)entityNbt);
    result.func_77982_d(compound);
    entity.func_70106_y();
    PlayerUtil.addItemStackToInventory(result, player.field_71071_by);
    return true;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    if (item.func_77942_o()) {
      if (item.func_77978_p().func_74764_b("used")) {
        Entity ent = EntityList.func_75620_a(item.func_77978_p().func_74779_i("entity"), player.field_70170_p);
        list.add("§eEntité: §b" + item.func_77978_p().func_74779_i("entity"));
        list.add("§eVie : §c" + item.func_77978_p().func_74760_g("health") + "/" + item
            .func_77978_p().func_74760_g("maxhealth"));
      } else {
        list.add("§cCe lasso est vide");
      } 
    } else {
      list.add("§cCe lasso est vide");
    } 
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemLasso.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */