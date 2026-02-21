package fr.paladium.palamod.modules.miner.item;

import com.google.common.base.Strings;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.core.pojo.objectives.types.SmeltObjective;
import fr.paladium.palajobs.core.quest.types.FurnaceCraftQuest;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.smeltery.utils.ToolHandler;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.tutorial.common.event.PickaxeOfTheGodLevelUpEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import org.apache.commons.lang3.StringUtils;

public class ItemGodPickaxe extends ItemPickaxe implements ITooltipWiki {
  private final IIcon[] icons = new IIcon[20];
  
  public IIcon[] getIcons() {
    return this.icons;
  }
  
  private final List<Item.ToolMaterial> toolMaterialsByLevel = new ArrayList<>();
  
  public List<Item.ToolMaterial> getToolMaterialsByLevel() {
    return this.toolMaterialsByLevel;
  }
  
  private final List<Integer> efficiencyByLevel = new ArrayList<>();
  
  public List<Integer> getEfficiencyByLevel() {
    return this.efficiencyByLevel;
  }
  
  private final List<Integer> fortuneByLevel = new ArrayList<>();
  
  public List<Integer> getFortuneByLevel() {
    return this.fortuneByLevel;
  }
  
  private final List<Upgrade> upgradeByLevel = new ArrayList<>();
  
  public List<Upgrade> getUpgradeByLevel() {
    return this.upgradeByLevel;
  }
  
  public ItemGodPickaxe() {
    super(PToolMaterial.paladium);
    func_77637_a((CreativeTabs)Registry.MINER_TAB);
    func_77655_b("god_pickaxe");
    func_111206_d("palamod:god_pickaxe/");
    func_77656_e(999);
    initLevels();
  }
  
  public int func_77619_b() {
    return 0;
  }
  
  private void initLevels() {
    int i;
    for (i = 0; i < 5; i++)
      registerLevel(Item.ToolMaterial.WOOD, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(Item.ToolMaterial.STONE, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(Item.ToolMaterial.IRON, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(Item.ToolMaterial.EMERALD, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(Item.ToolMaterial.GOLD, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.amethyst, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.titane, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 0, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 1, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 2, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 3, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 4, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 5, 0, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 5, 1, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 5, 2, (Upgrade)null); 
    for (i = 0; i < 5; i++)
      registerLevel(PToolMaterial.paladium, 5, 3, (Upgrade)null); 
    for (i = 0; i < 19; i++)
      registerLevel(PToolMaterial.paladiumGreen, 5, 3, (Upgrade)null); 
    registerLevel(PToolMaterial.paladiumGreen, 5, 3, new Upgrade(Upgrades.AUTO_SMELT, 1));
  }
  
  private void registerLevel(Item.ToolMaterial toolMaterial, int efficiency, int fortune, Upgrade upgrade) {
    this.toolMaterialsByLevel.add(toolMaterial);
    this.efficiencyByLevel.add(Integer.valueOf(efficiency));
    this.fortuneByLevel.add(Integer.valueOf(fortune));
    this.upgradeByLevel.add(upgrade);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta) {
    int lvl = getLevel(stack);
    return ((Item.ToolMaterial)this.toolMaterialsByLevel.get(lvl)).func_77998_b();
  }
  
  public int getHarvestLevel(ItemStack stack, String toolClass) {
    int lvl = getLevel(stack);
    return ((Item.ToolMaterial)this.toolMaterialsByLevel.get(lvl)).func_77996_d();
  }
  
  public void func_94581_a(IIconRegister register) {
    for (int i = 0; i < this.icons.length; i++)
      this.icons[i] = register.func_94245_a(func_111208_A() + "god_pickaxe_" + i); 
    this.field_77791_bV = this.icons[0];
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public IIcon func_77650_f(ItemStack item) {
    int lvl = getLevel(item);
    int index = Math.max(0, lvl / 5);
    return this.icons[index % this.icons.length];
  }
  
  public IIcon getIcon(ItemStack stack, int pass) {
    int lvl = getLevel(stack);
    int index = Math.max(0, lvl / 5);
    return this.icons[index % this.icons.length];
  }
  
  public String func_77653_i(ItemStack item) {
    return "Pickaxe of the gods §7(Niveau " + (getLevel(item) + 1) + ")" + (isSilkTouch(item) ? " §8[§bSilk§8]" : "");
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    double xp = getXp(item);
    int lvl = getLevel(item);
    list.add("§3Niveau " + (lvl + 1));
    list.add("");
    if (getLevel(item) + 1 >= 100) {
      list.add("§cNiveau max");
    } else {
      String formatted = String.format("%.1f", new Object[] { Double.valueOf(getXpForLevel(lvl + 1) - xp) });
      list.add("§eProchain niveau dans: §7" + formatted + " exp");
    } 
    for (Upgrade upgrade : getUpgrades(item)) {
      String upgradeName = upgrade.getType().toString().toLowerCase().replace("_", " ");
      list.add("§a⚫ " + StringUtils.capitalize(upgradeName) + " §7(" + Strings.repeat("I", upgrade.level) + ")");
    } 
    super.func_77624_a(item, player, list, flag);
  }
  
  public int getDamage(ItemStack item) {
    double xp = getXp(item);
    int lvl = getLevel(item);
    double old = getXpForLevel(lvl);
    double needXp = getXpForLevel(lvl + 1);
    if (lvl >= 99)
      return 0; 
    float p = (float)(xp - old) / (float)(needXp - old);
    int damage = (int)(func_77612_l() * (1.0F - p));
    if (damage >= func_77612_l())
      damage = func_77612_l() - 1; 
    return damage;
  }
  
  public void setDamage(ItemStack stack, int damage) {}
  
  public boolean func_150894_a(ItemStack item, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)entity;
      if ((block.func_149662_c() || block.isLeaves((IBlockAccess)world, x, y, z)) && block.func_149712_f(world, x, y, z) > 0.0F) {
        int lvl = getLevel(item);
        if (lvl + 1 < 100) {
          boolean has = false;
          if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            PetPlayer pet = PetPlayer.get((EntityPlayer)playerMP);
            PassiveResponse response = PassiveSkillEnum.EXPERIENCED_PICKAXE.getResponse(pet);
            double value = response.getPersonalValue(pet);
            if (response.has(value)) {
              double xp = 1.0D + response.getValueAsPercent(value);
              addXp(player, item, xp);
              has = true;
            } 
          } 
          if (!has)
            addXp(player, item, 1.0D); 
        } 
      } 
      boolean smelt = (hasUpgrade(item, Upgrades.AUTO_SMELT) && !isSilkTouch(item));
      if (smelt) {
        ItemStack result = null;
        Block blk = world.func_147439_a(x, y, z);
        int meta = world.func_72805_g(x, y, z);
        int localMeta = world.func_72805_g(x, y, z);
        blk.func_149681_a(world, x, y, z, localMeta, player);
        if (blk.removedByPlayer(world, player, x, y, z, true)) {
          BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, world, blk, meta, player);
          if (event.isCanceled())
            return true; 
          blk.func_149664_b(world, x, y, z, localMeta);
        } 
        int fortune = EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, item);
        if (blk.getDrops(world, x, y, z, blk.func_149643_k(world, x, y, z), fortune) != null && 
          !blk.getDrops(world, x, y, z, blk.func_149643_k(world, x, y, z), fortune).isEmpty()) {
          List<ItemStack> drops = blk.getDrops(world, x, y, z, blk.func_149643_k(world, x, y, z), fortune);
          result = (drops == null || drops.isEmpty()) ? null : FurnaceRecipes.func_77602_a().func_151395_a(drops.get(0));
        } 
        if (result == null) {
          blk.func_149697_b(world, x, y, z, localMeta, fortune);
        } else {
          ItemStack resultCopy = result.func_77946_l();
          float count = 1.0F;
          if (fortune > 0 && ToolHandler.isMineral(block))
            count = (1 + (fortune - world.field_73012_v.nextInt(fortune)) / 2); 
          if (count <= 0.0F)
            count = 1.0F; 
          resultCopy.field_77994_a = (int)count;
          FMLCommonHandler.instance().firePlayerSmeltedEvent(player, resultCopy);
          EntityItem entityItem = new EntityItem(world, x, y, z, resultCopy);
          world.func_72838_d((Entity)entityItem);
          FurnaceCraftQuest.performCheck(player, resultCopy.func_77946_l(), (int)count);
          SmeltObjective.performCheck(player, resultCopy.func_77946_l());
        } 
        world.func_72926_e(2001, x, y, z, Block.func_149682_b(blk) + (meta << 12));
        world.func_147468_f(x, y, z);
      } 
    } 
    return true;
  }
  
  public enum Upgrades {
    AUTO_SMELT(1),
    BIG_HOLE(1);
    
    private final int maxLevel;
    
    public int getMaxLevel() {
      return this.maxLevel;
    }
    
    Upgrades(int maxLevel) {
      this.maxLevel = maxLevel;
    }
  }
  
  public static class Upgrade {
    private final ItemGodPickaxe.Upgrades type;
    
    private final int level;
    
    public ItemGodPickaxe.Upgrades getType() {
      return this.type;
    }
    
    public int getLevel() {
      return this.level;
    }
    
    public Upgrade(ItemGodPickaxe.Upgrades type, int level) {
      this.type = type;
      this.level = level;
    }
  }
  
  public int getLevel(ItemStack item) {
    return getLevelForXp(getXp(item));
  }
  
  public double getXp(ItemStack item) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("god_pickaxe_xp"))
      return item.func_77978_p().func_74769_h("god_pickaxe_xp"); 
    return 0.0D;
  }
  
  public ItemStack addXp(EntityPlayer player, ItemStack item, double xp) {
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    double oldXp = 0.0D;
    if (compound.func_74764_b("god_pickaxe_xp"))
      oldXp = compound.func_74769_h("god_pickaxe_xp"); 
    int oldLevel = getLevelForXp(oldXp);
    if (getLevelForXp(oldXp) < 99)
      oldXp += xp; 
    compound.func_74780_a("god_pickaxe_xp", oldXp);
    item.func_77982_d(compound);
    int newLevel = getLevelForXp(oldXp);
    if (newLevel > oldLevel)
      MinecraftForge.EVENT_BUS.post((Event)new PickaxeOfTheGodLevelUpEvent(player, oldLevel, newLevel, item)); 
    Map<Integer, Integer> ench = new HashMap<>();
    if (((Integer)this.efficiencyByLevel.get(getLevel(item))).intValue() > 0)
      ench.put(Integer.valueOf(Enchantment.field_77349_p.field_77352_x), this.efficiencyByLevel.get(getLevel(item))); 
    if (((Integer)this.fortuneByLevel.get(getLevel(item))).intValue() > 0)
      ench.put(Integer.valueOf(Enchantment.field_77346_s.field_77352_x), this.fortuneByLevel.get(getLevel(item))); 
    EnchantmentHelper.func_82782_a(ench, item);
    this.field_77862_b = this.toolMaterialsByLevel.get(getLevel(item));
    this.field_77864_a = this.field_77862_b.func_77998_b();
    return item;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (player.func_70093_af() && getLevel(stack) >= 99) {
      boolean isSilk = isSilkTouch(stack);
      if (!world.field_72995_K && player instanceof EntityPlayerMP)
        (new Notification(Notification.NotificationType.INFO, "Silk Touch " + (isSilk ? "désactivé" : "activé"), "paladium")).send((EntityPlayerMP)player); 
      Map<Integer, Integer> ench = new HashMap<>();
      if (((Integer)this.efficiencyByLevel.get(getLevel(stack))).intValue() > 0)
        ench.put(Integer.valueOf(Enchantment.field_77349_p.field_77352_x), this.efficiencyByLevel.get(getLevel(stack))); 
      if (((Integer)this.fortuneByLevel.get(getLevel(stack))).intValue() > 0)
        ench.put(Integer.valueOf(Enchantment.field_77346_s.field_77352_x), this.fortuneByLevel.get(getLevel(stack))); 
      if (!isSilk)
        ench.put(Integer.valueOf(Enchantment.field_77348_q.field_77352_x), Integer.valueOf(1)); 
      EnchantmentHelper.func_82782_a(ench, stack);
    } 
    return super.func_77659_a(stack, world, player);
  }
  
  private boolean isSilkTouch(ItemStack item) {
    return (EnchantmentHelper.func_77506_a(Enchantment.field_77348_q.field_77352_x, item) > 0);
  }
  
  public List<Upgrade> getUpgrades(ItemStack item) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("pickaxe_upgrades")) {
      NBTTagList list = item.func_77978_p().func_150295_c("pickaxe_upgrades", 10);
      List<Upgrade> upgrades = new ArrayList<>();
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound compound = list.func_150305_b(i);
        upgrades.add(new Upgrade(Upgrades.valueOf(compound.func_74779_i("type")), compound.func_74762_e("level")));
      } 
      return upgrades;
    } 
    return new ArrayList<>();
  }
  
  public boolean hasUpgrade(ItemStack item, Upgrades upgrade) {
    for (Upgrade up : getUpgrades(item)) {
      if (up.getType() == upgrade)
        return true; 
    } 
    return false;
  }
  
  public void addUpgrade(ItemStack item, Upgrades type) {
    if (!hasUpgrade(item, type)) {
      List<Upgrade> upgrades = getUpgrades(item);
      upgrades.add(new Upgrade(type, 1));
      setUpgrades(item, upgrades);
    } else {
      List<Upgrade> upgrades = getUpgrades(item);
      int lvl = 1;
      int index = 0;
      for (Upgrade upgrade : upgrades) {
        if (upgrade.type == type) {
          lvl = upgrade.level;
          index = upgrades.indexOf(upgrade);
        } 
      } 
      lvl++;
      if (lvl > type.maxLevel)
        lvl = type.maxLevel; 
      upgrades.set(index, new Upgrade(type, lvl));
      setUpgrades(item, upgrades);
    } 
  }
  
  public void addUpgrade(ItemStack item, Upgrade upgrade) {
    if (!hasUpgrade(item, upgrade.getType())) {
      List<Upgrade> upgrades = getUpgrades(item);
      upgrades.add(upgrade);
      setUpgrades(item, upgrades);
    } 
  }
  
  public void setUpgrades(ItemStack item, List<Upgrade> upgrades) {
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    NBTTagList list = new NBTTagList();
    for (Upgrade upgrade : upgrades) {
      NBTTagCompound uc = new NBTTagCompound();
      uc.func_74778_a("type", upgrade.getType().toString());
      uc.func_74768_a("level", upgrade.getLevel());
      list.func_74742_a((NBTBase)uc);
    } 
    compound.func_74782_a("pickaxe_upgrades", (NBTBase)list);
    item.func_77982_d(compound);
  }
  
  public void setUpgrades(ItemStack item, Upgrade... upgrades) {
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    NBTTagList list = new NBTTagList();
    for (Upgrade upgrade : upgrades) {
      NBTTagCompound uc = new NBTTagCompound();
      uc.func_74778_a("type", upgrade.getType().toString());
      uc.func_74768_a("level", upgrade.getLevel());
      list.func_74742_a((NBTBase)uc);
    } 
    compound.func_74782_a("pickaxe_upgrades", (NBTBase)list);
    item.func_77982_d(compound);
  }
  
  public int getXpForLevel(int level) {
    return (int)(80.0D * Math.pow(level, 2.3D) / 1.5D);
  }
  
  public int getLevelForXp(double xp) {
    for (int i = 0; i < 100; i++) {
      double xpforlevel = getXpForLevel(i);
      double xpfornextlevel = getXpForLevel(i + 1);
      if (xp > xpforlevel && xp < xpfornextlevel)
        return i; 
    } 
    return 0;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#6.-la-pickaxe-of-the-gods-pog";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemGodPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */