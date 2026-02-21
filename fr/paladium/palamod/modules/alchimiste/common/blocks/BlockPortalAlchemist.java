package fr.paladium.palamod.modules.alchimiste.common.blocks;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftPortalObjective;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.objectives.ExtractSeveObjective;
import fr.paladium.palamod.modules.ajobs.common.registerer.requirement.CraftPortalRequirement;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityAlchemistPortalController;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityPortal;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockAlchemist;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.PortalRecipe;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.tutorial.common.event.PortalCraftEvent;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockPortalAlchemist extends BlockAlchemist {
  private String name;
  
  public BlockPortalAlchemist() {
    super(Material.field_151567_E);
    func_149647_a(null);
    this.name = "portal";
    func_149663_c(this.name);
    func_149658_d("palamod:alchimiste/" + this.name);
    func_149711_c(-1.0F);
    func_149752_b(2.0F);
    this.field_149756_F = 0.125D;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public void func_149695_a(World w, int x, int y, int z, Block p_149695_5_) {
    super.func_149695_a(w, x, y, z, p_149695_5_);
  }
  
  private int applyPetSkill(EntityPlayer player, int currentPercentage) {
    PetPlayer pet = PetPlayer.get(player);
    PassiveResponse response = PassiveSkillEnum.MAGIC_PORTAL.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return currentPercentage; 
    double percentageValue = PetUtils.getValueAsPercent(value);
    int increment = (int)(currentPercentage * percentageValue);
    return currentPercentage + increment;
  }
  
  public void func_149670_a(World w, int x, int y, int z, Entity e) {
    if (e instanceof EntityItem && !w.field_72995_K) {
      AxisAlignedBB bounds2 = w.func_147439_a(x, y, z).func_149668_a(w, x, y, z);
      bounds2 = bounds2.func_72314_b(4.5D, 2.0D, 4.5D);
      List<EntityPlayer> players = w.func_72872_a(EntityPlayer.class, bounds2);
      EntityPlayer closest = closestPlayer(players, x, y, z);
      if (closest == null)
        return; 
      ItemStack stack = ((EntityItem)e).func_92059_d();
      if (stack != null && stack.func_77973_b() instanceof fr.paladium.palamod.modules.alchimiste.common.items.ItemFlask && stack.func_77942_o() && stack
        .func_77978_p().func_74764_b("seveType")) {
        String seveType = stack.func_77978_p().func_74779_i("seveType");
        TileEntityPortal portal = (TileEntityPortal)w.func_147438_o(x, y, z);
        if (w.func_147438_o(portal.getControllerX(), portal.getControllerY(), portal
            .getControllerZ()) instanceof TileEntityAlchemistPortalController) {
          TileEntityAlchemistPortalController controller = (TileEntityAlchemistPortalController)w.func_147438_o(portal.getControllerX(), portal.getControllerY(), portal.getControllerZ());
          int sevePercentage = (int)(stack.func_77960_j() / 6.0D * 100.0D) * 10;
          sevePercentage *= stack.field_77994_a;
          sevePercentage = applyPetSkill(closest, sevePercentage);
          String seveFound = "";
          int baseXp = 0;
          JobsPlayer data = JobsPlayer.get((Entity)closest);
          if (data == null);
          boolean needUpdate = false;
          if (portal.func_145832_p() == 0 && seveType.equals("Jacaranda")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
            baseXp = 10;
            needUpdate = true;
          } else if (portal.func_145832_p() == 1 && seveType.equals("Judeecercis")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
            baseXp = 15;
            needUpdate = true;
          } else if (portal.func_145832_p() == 2 && seveType.equals("Erable")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
            baseXp = 20;
            needUpdate = true;
          } else if (portal.func_145832_p() == 3 && seveType.equals("Ostrya")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
            baseXp = 25;
            needUpdate = true;
          } 
          PortalRecipe recipe = PAlchimiste.portalController.getRecipeFor(portal.func_145832_p());
          if (needUpdate && recipe != null && controller.getSeveTotal() >= recipe.getRequiredFullSeve() * 1000)
            if (recipe.getDropStack().func_77946_l().func_77973_b() == ItemsRegister.ENDIUM_NUGGET) {
              if (controller.totalEndiumPollen >= 2) {
                ItemStack is = recipe.getDropStack().func_77946_l();
                if (is.field_77994_a > 1) {
                  for (int i = 0; i < is.field_77994_a; i++) {
                    ItemStack toDrop = recipe.getDropStack().func_77946_l();
                    toDrop.field_77994_a = 1;
                    EntityItem entityitem = new EntityItem(w, x, (y + 1), z, toDrop);
                    entityitem.field_145804_b = 0;
                    w.func_72838_d((Entity)entityitem);
                  } 
                } else {
                  EntityItem entityitem = new EntityItem(w, x, (y + 1), z, recipe.getDropStack().func_77946_l());
                  entityitem.field_145804_b = 0;
                  w.func_72838_d((Entity)entityitem);
                } 
                controller.totalEndiumPollen -= 2;
                if (controller.totalEndiumPollen <= 0)
                  controller.totalEndiumPollen = 0; 
                controller.removeSeveToTotal(recipe.getRequiredFullSeve() * 1000);
                CraftPortalObjective.performCheck(closest, recipe.getDropStack().func_77946_l());
                JobsPlayer jobsPlayer = JobsPlayer.get((Entity)closest);
                if (jobsPlayer != null)
                  jobsPlayer.getRequirements(CraftPortalRequirement.class).forEach(optional -> optional.ifPresent(())); 
                MinecraftForge.EVENT_BUS.post((Event)new PortalCraftEvent(closest, recipe.getDropStack().func_77946_l()));
              } else {
                controller.setTotalSeve(recipe.getRequiredFullSeve() * 1000);
              } 
            } else {
              while (recipe != null && recipe.getRequiredFullSeve() * 1000 <= controller.getSeveTotal()) {
                EntityItem entityitem = new EntityItem(w, x, (y + 1), z, recipe.getDropStack().func_77946_l());
                entityitem.field_145804_b = 0;
                w.func_72838_d((Entity)entityitem);
                controller.removeSeveToTotal(recipe.getRequiredFullSeve() * 1000);
                CraftPortalObjective.performCheck(closest, recipe.getDropStack().func_77946_l());
                JobsPlayer jobsPlayer = JobsPlayer.get((Entity)closest);
                if (jobsPlayer != null)
                  jobsPlayer.getRequirements(CraftPortalRequirement.class).forEach(optional -> optional.ifPresent(())); 
                MinecraftForge.EVENT_BUS.post((Event)new PortalCraftEvent(closest, recipe.getDropStack().func_77946_l()));
              } 
            }  
        } else {
          w.func_147468_f(x, y, z);
        } 
      } else if (stack != null && (stack
        .func_77973_b() == Item.func_150898_a((Block)BlocksRegister.TANK_AMETHYSTE) || stack
        .func_77973_b() == Item.func_150898_a((Block)BlocksRegister.TANK_GOLD) || stack
        .func_77973_b() == Item.func_150898_a((Block)BlocksRegister.TANK_PALADIUM) || stack
        .func_77973_b() == Item.func_150898_a((Block)BlocksRegister.TANK_TITANE)) && stack
        .func_77942_o() && stack.func_77978_p().func_74764_b("liquid")) {
        String seveType = stack.func_77978_p().func_74779_i("liquid");
        TileEntityPortal portal = (TileEntityPortal)w.func_147438_o(x, y, z);
        if (w.func_147438_o(portal.getControllerX(), portal.getControllerY(), portal
            .getControllerZ()) instanceof TileEntityAlchemistPortalController) {
          TileEntityAlchemistPortalController controller = (TileEntityAlchemistPortalController)w.func_147438_o(portal.getControllerX(), portal.getControllerY(), portal.getControllerZ());
          int level = stack.func_77978_p().func_74762_e("liquidLevel");
          int sevePercentage = level;
          sevePercentage *= stack.field_77994_a;
          sevePercentage = applyPetSkill(closest, sevePercentage);
          String seveFound = "";
          int baseXp = 0;
          JobsPlayer data = JobsPlayer.get((Entity)closest);
          if (data == null)
            return; 
          if (portal.func_145832_p() == 0 && seveType.contains("Jacaranda")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
          } else if (portal.func_145832_p() == 1 && seveType.contains("Judeecercis")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
          } else if (portal.func_145832_p() == 2 && seveType.contains("Erable")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
            ExtractSeveObjective.performCheck(closest, seveType, level / 4000);
          } else if (portal.func_145832_p() == 3 && seveType.contains("Ostrya")) {
            if (JobsManager.getInstance().canUseUseItem(closest, data, new ItemStack(w
                  .func_147439_a(portal.getControllerX(), portal.getControllerY(), portal
                    .getControllerZ()))) != null)
              return; 
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.addSeveToTotal(sevePercentage);
            seveFound = seveType;
          } 
          PortalRecipe recipe = PAlchimiste.portalController.getRecipeFor(portal.func_145832_p());
          while (recipe != null && recipe.getRequiredFullSeve() * 1000 <= controller.getSeveTotal()) {
            EntityItem entityitem = new EntityItem(w, x, (y + 1), z, recipe.getDropStack().func_77946_l());
            entityitem.field_145804_b = 0;
            w.func_72838_d((Entity)entityitem);
            controller.removeSeveToTotal(recipe.getRequiredFullSeve() * 1000);
            CraftPortalObjective.performCheck(closest, recipe.getDropStack().func_77946_l());
            JobsPlayer jobsPlayer = JobsPlayer.get((Entity)closest);
            if (jobsPlayer != null)
              jobsPlayer.getRequirements(CraftPortalRequirement.class).forEach(optional -> optional.ifPresent(())); 
            MinecraftForge.EVENT_BUS.post((Event)new PortalCraftEvent(closest, recipe.getDropStack().func_77946_l()));
          } 
        } else {
          w.func_147468_f(x, y, z);
        } 
      } else if (stack != null && stack.func_77973_b() == ItemsRegister.ENDIUM_POLLEN) {
        TileEntityPortal portal = (TileEntityPortal)w.func_147438_o(x, y, z);
        if (w.func_147438_o(portal.getControllerX(), portal.getControllerY(), portal
            .getControllerZ()) instanceof TileEntityAlchemistPortalController) {
          TileEntityAlchemistPortalController controller = (TileEntityAlchemistPortalController)w.func_147438_o(portal.getControllerX(), portal.getControllerY(), portal.getControllerZ());
          if (portal.func_145832_p() == 3) {
            if (e.field_70128_L)
              return; 
            e.func_70106_y();
            w.func_72900_e(e);
            controller.setTotalEndiumPollen(controller.getTotalEndiumPollen() + ((stack.field_77994_a == 0) ? 1 : stack.field_77994_a));
          } 
        } 
      } 
    } 
  }
  
  public Class<? extends TileEntity> getTileEntity() {
    return (Class)TileEntityPortal.class;
  }
  
  public EntityPlayer closestPlayer(List<EntityPlayer> players, int x, int y, int z) {
    double distance = 1000.0D;
    EntityPlayer closeste = null;
    for (EntityPlayer player : players) {
      double tempdistance = Math.sqrt(
          Math.pow(player.field_70165_t - x, 2.0D) + Math.pow(player.field_70163_u - y, 2.0D) + Math.pow(player.field_70161_v - z, 2.0D));
      if (tempdistance < distance) {
        distance = tempdistance;
        closeste = player;
      } 
    } 
    return closeste;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityPortal();
  }
  
  public String getName() {
    return this.name;
  }
  
  public boolean func_149659_a(Explosion p_149659_1_) {
    return false;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockPortalAlchemist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */