package fr.paladium.palamod.modules.hunter.items;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.mount.core.entities.EntityMount;
import fr.paladium.mount.core.items.ItemsRegister;
import fr.paladium.mount.core.species.MountType;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.tutorial.common.event.CaptureMountEvent;
import java.util.UUID;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

public class ItemCaptureSword extends ItemSword implements ITooltipWiki {
  private final String[] whitelist = new String[] { 
      "EntityPig", "EntityCow", "EntitySheep", "EntitySlime", "EntityZombie", "EntityCreeper", "EntityRabbit", "EntityHorse", "EntityChicken", "EntityElephant", 
      "EntityFarmerChicken", "EntityCavernousZombie", "EntityFlowerMonster", "EntityMegaCreeper", "EntityTurtle", "EntityPanda", "EntityDolphin", "EntityCrab", "EntityParrot", "EntitySnail", 
      "EntitySnake", "EntityJellyFish", "EntityGoat", "EntityWither", "EntityDancarokMount", "EntityRavirokMount", "EntityTedarokMount" };
  
  public ItemCaptureSword() {
    super(Item.ToolMaterial.EMERALD);
    func_77637_a((CreativeTabs)Registry.HUNTER_TAB);
    func_77655_b("capture_sword");
    func_111206_d("palamod:capture_sword");
    func_77656_e(32);
  }
  
  private boolean isWhitelist(String entity) {
    boolean whitelist = false;
    for (String s : this.whitelist) {
      if (s.equalsIgnoreCase(entity))
        whitelist = true; 
    } 
    return whitelist;
  }
  
  public boolean func_77644_a(ItemStack item, EntityLivingBase entity1, EntityLivingBase entity2) {
    if (entity1.func_110143_aJ() <= 0.0F && 
      entity2 instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)entity2;
      if (player.field_71071_by.func_146028_b(ItemsRegister.CAPTURE_STONE)) {
        ItemStack stone = null;
        for (ItemStack it : player.field_71071_by.field_70462_a) {
          if (it != null && 
            it.func_77973_b() == ItemsRegister.CAPTURE_STONE && (
            (ItemCaptureStone)it.func_77973_b()).isEmpty(it))
            stone = it; 
        } 
        if (stone != null) {
          if (stone.func_77942_o() && 
            stone.func_77978_p().func_74764_b("used"))
            return false; 
          if (!isWhitelist(entity1.getClass().getSimpleName()))
            return false; 
          if (entity1 instanceof net.minecraft.entity.boss.EntityWither && CommonModule.getInstance().getConfig().getServerType() != ServerType.MINAGE) {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cSeul les lointaines contrées des minages permettent de capturer des Withers"));
            return false;
          } 
          if (entity1.func_70694_bm() != null)
            try {
              if (!BukkitUtils.hasPermission(player.func_110124_au(), "palamod.capture.admin"))
                entity1.func_70062_b(0, null); 
            } catch (Exception e) {
              entity1.func_70062_b(0, null);
            }  
          if (entity1.getClass().getCanonicalName().contains("Mount")) {
            for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
              ItemStack it = player.field_71071_by.field_70462_a[i];
              if (it != null && 
                it.func_77969_a(stone) && ItemStack.func_77970_a(it, stone) && it
                .func_77960_j() == stone.func_77960_j()) {
                player.field_71071_by.func_70299_a(i, null);
                break;
              } 
            } 
            if (!(entity1 instanceof EntityMount))
              return false; 
            EntityMount entityMount = (EntityMount)entity1;
            if (!entityMount.getOwnerUUID().isEmpty())
              return false; 
            ItemStack gemstone = new ItemStack(ItemsRegister.MOUNT_GEMSTONE);
            NBTTagCompound tagCompound = new NBTTagCompound();
            MountType mountType = MountType.getMountTypeByClass(entityMount.getClass());
            tagCompound.func_74778_a("MountUUID", UUID.randomUUID().toString());
            tagCompound.func_74768_a("MountType", mountType.getId());
            tagCompound.func_74776_a("Xp", 0.0F);
            tagCompound.func_74768_a("SharedXp", 50);
            tagCompound.func_74778_a("Owner", "");
            tagCompound.func_74778_a("Name", mountType.getMount().getName());
            gemstone.func_77982_d(tagCompound);
            player.field_71071_by.func_70441_a(gemstone);
            if (!player.field_70170_p.field_72995_K)
              MinecraftForge.EVENT_BUS.post((Event)new CaptureMountEvent(player, mountType.name())); 
            return super.func_77644_a(item, entity1, entity2);
          } 
          UseItemAchievement.performCheck(player, stone, "CAPTURE_" + entity1.getClass().getSimpleName());
          stone.func_77964_b(1);
          stone.func_151001_c("§ePierre de capture §b" + entity1.func_70005_c_());
          NBTTagCompound compound = stone.func_77978_p();
          compound.func_74757_a("used", true);
          compound.func_74778_a("entity", entity1.getClass().getSimpleName());
          compound.func_74778_a("entityClass", entity1.getClass().getCanonicalName());
          NBTTagCompound nbt = new NBTTagCompound();
          entity1.func_70109_d(nbt);
          compound.func_74782_a("nbt", (NBTBase)nbt);
          NBTTagCompound entityNbt = new NBTTagCompound();
          entity1.func_70014_b(entityNbt);
          compound.func_74782_a("entityNbt", (NBTBase)entityNbt);
          stone.func_77982_d(compound);
          entity1.func_70106_y();
        } 
      } 
    } 
    return super.func_77644_a(item, entity1, entity2);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemCaptureSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */