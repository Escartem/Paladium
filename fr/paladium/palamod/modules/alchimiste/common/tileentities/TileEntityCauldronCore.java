package fr.paladium.palamod.modules.alchimiste.common.tileentities;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.betternei.client.util.ItemUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftCauldronObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.CraftObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.DropCauldronObjective;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.CraftCauldronAchievement;
import fr.paladium.palamod.modules.achievements.types.InitCauldronAchievement;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.ajobs.common.registerer.requirement.DropCauldronRequirement;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.CauldronRecipe;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.SeveInformations;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.tutorial.common.event.CauldronCraftEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityCauldronCore extends TileEntity {
  private int timer;
  
  private boolean isMultiBlockCorrect = false;
  
  private boolean isSulfuricPresent = false;
  
  public double getPetSkillPercentage(EntityPlayer player) {
    if (!(player instanceof net.minecraft.entity.player.EntityPlayerMP))
      return 0.0D; 
    PetPlayer pet = PetPlayer.get(player);
    PassiveResponse response = PassiveSkillEnum.FLOWER_CHANCE.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return 0.0D; 
    return response.getValueAsPercent(value);
  }
  
  public int getPetNewValue(double percentage, int currentValue) {
    if (percentage <= 0.0D)
      return currentValue; 
    int decremented = (int)(currentValue * percentage);
    currentValue -= decremented;
    if (currentValue <= 0)
      return 1; 
    return currentValue;
  }
  
  public void func_145845_h() {
    this.timer++;
    this.isSulfuricPresent = true;
    boolean isSulfuricPartialPresent = false;
    for (int xc = -1; xc < 2; xc++) {
      for (int zc = -1; zc < 2; zc++) {
        if (this.field_145850_b.func_147439_a(this.field_145851_c + xc, this.field_145848_d + 1, this.field_145849_e + zc) != BlocksRegister.FLUIDS_ANGELICWATER || this.field_145850_b
          .func_72805_g(this.field_145851_c + xc, this.field_145848_d + 1, this.field_145849_e + zc) != 0) {
          this.isSulfuricPresent = false;
        } else if (this.field_145850_b.func_147439_a(this.field_145851_c + xc, this.field_145848_d + 1, this.field_145849_e + zc) == BlocksRegister.FLUIDS_ANGELICWATER) {
          isSulfuricPartialPresent = true;
        } 
      } 
    } 
    if (isSulfuricPartialPresent && !this.isSulfuricPresent)
      destroy(); 
    if (!isSulfuricPresent())
      return; 
    AxisAlignedBB bounds = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_149668_a(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    if (bounds == null)
      return; 
    bounds = bounds.func_72314_b(1.5D, 1.0D, 1.5D);
    AxisAlignedBB bounds2 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_149668_a(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    bounds2 = bounds2.func_72314_b(4.5D, 2.0D, 4.5D);
    List<EntityItem> entities = this.field_145850_b.func_72872_a(EntityItem.class, bounds);
    List<EntityPlayer> players = this.field_145850_b.func_72872_a(EntityPlayer.class, bounds2);
    EntityPlayer closest = closestPlayer(players);
    if (closest == null)
      return; 
    InitCauldronAchievement.performCheck(closest);
    if (!JobsBridge.canUseCraft(closest, new ItemStack((Block)BlocksRegister.CAULDRON_CORE), false))
      return; 
    List<ItemStack> stacks = new ArrayList<>();
    double petValue = getPetSkillPercentage(closest);
    for (EntityItem item : entities) {
      ItemStack stack = item.func_92059_d();
      if (stack != null && (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150328_O) || stack
        .func_77973_b() == Item.func_150898_a((Block)Blocks.field_150327_N) || stack
        .func_77973_b() == Item.func_150898_a(BlocksRegister.FLOWER_ENDIUM) || stack.func_77973_b() == Item.func_150898_a(BlocksRegister.FLOWER_PALADIUM))) {
        if (closest != null && !this.field_145850_b.field_72995_K) {
          DropCauldronObjective.performCheck(closest, stack);
          JobsPlayer jobsPlayer = JobsPlayer.get((Entity)closest);
          if (jobsPlayer != null)
            jobsPlayer.getRequirements(DropCauldronRequirement.class).forEach(optional -> optional.ifPresent(())); 
        } 
        if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150328_O) || stack
          .func_77973_b() == Item.func_150898_a((Block)Blocks.field_150327_N)) {
          for (int i = 0; i < stack.field_77994_a; i++) {
            if (this.field_145850_b.field_73012_v.nextInt(getPetNewValue(petValue, 500)) == 0) {
              ItemStack it = new ItemStack(BlocksRegister.FLOWER_MINERAL);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
            } else if (this.field_145850_b.field_73012_v.nextInt(getPetNewValue(petValue, 300)) == 0) {
              ItemStack it = new ItemStack(BlocksRegister.FLOWER_PALADIUM);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
            } else if (this.field_145850_b.field_73012_v.nextInt(getPetNewValue(petValue, 5000)) == 0) {
              ItemStack it = new ItemStack(BlocksRegister.FLOWER_HARPAGOPHYTUM);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
            } else if (this.field_145850_b.field_73012_v.nextInt(getPetNewValue(petValue, 15000)) == 0) {
              ItemStack it = new ItemStack(BlocksRegister.FLOWER_ENDIUM);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
            } else if (this.field_145850_b.field_73012_v.nextInt(getPetNewValue(petValue, 1754)) == 0) {
              ItemStack it = new ItemStack(BlocksRegister.FLOWER_CLATHRUSARCHERI);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
            } 
          } 
        } else if (stack.func_77973_b() == Item.func_150898_a(BlocksRegister.FLOWER_ENDIUM) && JobsBridge.canUseCraft(closest, new ItemStack(ItemsRegister.ENDIUM_POLLEN))) {
          for (int i = 0; i < stack.field_77994_a; i++) {
            ItemStack it = new ItemStack(ItemsRegister.ENDIUM_POLLEN);
            CraftCauldronObjective.performCheck(closest, it);
            CraftCauldronAchievement.performCheck(closest, it);
            MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, it));
            PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
            if (this.field_145850_b.field_73012_v.nextInt(getPetNewValue(petValue, 1000)) <= 1) {
              it = new ItemStack(ItemsRegister.ENDIUM_NUGGET);
              CraftCauldronAchievement.performCheck(closest, it);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
              CraftCauldronObjective.performCheck(closest, it);
              MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, it));
            } 
            int rdm = this.field_145850_b.field_73012_v.nextInt(getPetNewValue(petValue, 100));
            if (rdm >= 50 && rdm < 75) {
              it = new ItemStack(ItemsRegister.PALADIUM_POLLEN);
              CraftCauldronAchievement.performCheck(closest, it);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
              CraftCauldronObjective.performCheck(closest, it);
              MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, it));
            } else if (rdm >= 75 && rdm < 90) {
              it = new ItemStack(ItemsRegister.TITANE_POLLEN);
              CraftCauldronAchievement.performCheck(closest, it);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
              CraftCauldronObjective.performCheck(closest, it);
              MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, it));
            } else if (rdm >= 90) {
              it = new ItemStack(ItemsRegister.AMETHYST_POLLEN);
              CraftCauldronAchievement.performCheck(closest, it);
              PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
              CraftCauldronObjective.performCheck(closest, it);
              MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, it));
            } 
          } 
        } else if (stack.func_77973_b() == Item.func_150898_a(BlocksRegister.FLOWER_PALADIUM)) {
          for (int i = 0; i < stack.field_77994_a; i++) {
            ItemStack it = new ItemStack(ItemsRegister.PALADIUM_INGOT);
            CraftCauldronObjective.performCheck(closest, it);
            CraftCauldronAchievement.performCheck(closest, it);
            PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
            MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, it));
          } 
        } 
        this.field_145850_b.func_72900_e((Entity)item);
        continue;
      } 
      if (stack != null && (stack.func_77973_b() == ItemsRegister.TITANE_POLLEN || stack
        .func_77973_b() == ItemsRegister.AMETHYST_POLLEN || stack
        .func_77973_b() == ItemsRegister.PALADIUM_POLLEN)) {
        if (stack.func_77973_b() == ItemsRegister.TITANE_POLLEN) {
          ItemStack it = new ItemStack(BlocksRegister.BLOCK_TITANE, stack.field_77994_a);
          PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
        } else if (stack.func_77973_b() == ItemsRegister.AMETHYST_POLLEN) {
          ItemStack it = new ItemStack(BlocksRegister.BLOCK_AMETHYST, 2 * stack.field_77994_a);
          PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
        } else if (stack.func_77973_b() == ItemsRegister.PALADIUM_POLLEN) {
          ItemStack it = new ItemStack(ItemsRegister.PALADIUM_INGOT, 5 * stack.field_77994_a);
          PlayerUtils.dropItemStack((Entity)closest, item.field_70165_t, item.field_70163_u + 2.0D, item.field_70161_v, it);
          PPalaDynamique.instance.addGenerated("CAULDRON", it.field_77994_a);
        } 
        this.field_145850_b.func_72900_e((Entity)item);
        continue;
      } 
      stacks.add(stack);
    } 
    CauldronRecipe cr = PAlchimiste.cauldronController.getOutputFor(closest, stacks.<ItemStack>toArray(new ItemStack[0]));
    if (cr != null)
      for (int i = 0; i < 4; i++) {
        for (int j = -1; j < 3; j++) {
          for (int zc = -1; zc < 3; zc++)
            this.field_145850_b.func_72869_a("flame", (this.field_145851_c + j) + this.field_145850_b.field_73012_v
                .nextDouble() - this.field_145850_b.field_73012_v.nextDouble(), (this.field_145848_d + 2), (this.field_145849_e + zc) + this.field_145850_b.field_73012_v
                .nextDouble() - this.field_145850_b.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D); 
        } 
      }  
    if (!this.field_145850_b.field_72995_K)
      for (int i = 0; i < 32 && 
        cr != null && cr.getOutput() != null; i++) {
        ItemStack output = cr.getOutput();
        EntityItem item = new EntityItem(this.field_145850_b);
        item.func_70107_b(this.field_145851_c, (this.field_145848_d + 2), this.field_145849_e);
        ItemStack o = output.func_77946_l();
        o.field_77994_a = cr.getOutputSize();
        item.func_92058_a(o);
        this.field_145850_b.func_72838_d((Entity)item);
        List<ItemStack> inputs = new ArrayList<>();
        for (EntityItem e : entities) {
          for (ItemStack s : cr.getInputs()) {
            if (ItemUtils.isEquals(e.func_92059_d(), s, true)) {
              (e.func_92059_d()).field_77994_a--;
              if ((e.func_92059_d()).field_77994_a <= 0) {
                this.field_145850_b.func_72900_e((Entity)e);
                continue;
              } 
            } 
            if ((e.func_92059_d()).field_77994_a > 0)
              inputs.add(e.func_92059_d()); 
          } 
        } 
        cr = PAlchimiste.cauldronController.getOutputFor(closest, inputs.<ItemStack>toArray(new ItemStack[0]));
        if (cr == null || cr.getOutput() == null)
          this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "mob.wither.death", 0.6F, 0.6F); 
      }  
    SeveInformations left = getSeveInfoLeft();
    SeveInformations right = getSeveInfosRight();
    for (EntityItem it : entities) {
      if (it.func_92059_d().func_77973_b() == ItemsRegister.GLUEBALL_PATTERN) {
        if (left != null && right != null) {
          ItemStack stack = PAlchimiste.cauldronController.getOutputFor(closest, left.getType(), right.getType());
          if (stack != null && !JobsBridge.canUseCraft(closest, stack))
            return; 
          if (stack != null && left.getLevel() >= 500 && right.getLevel() >= 500) {
            EntityItem i = new EntityItem(this.field_145850_b, this.field_145851_c, (this.field_145848_d + 2), this.field_145849_e);
            i.func_92058_a(stack.func_77946_l());
            if (!this.field_145850_b.field_72995_K) {
              this.field_145850_b.func_72838_d((Entity)i);
              this.field_145850_b.func_72900_e((Entity)it);
              decrementLeftTank();
              decrementRightTank();
              this.field_145850_b.func_72908_a((this.field_145851_c + 0.5F), (this.field_145848_d + 0.5F), (this.field_145849_e + 0.5F), "random.fizz", 0.5F, 2.6F + (this.field_145850_b.field_73012_v
                  .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
              if (closest != null && !this.field_145850_b.field_72995_K) {
                CraftCauldronAchievement.performCheck(closest, stack);
                CraftObjective.performCheck(closest, stack);
              } 
            } 
            continue;
          } 
          if (stack == null) {
            stack = PAlchimiste.cauldronController.getOutputFor(closest, right.getType(), left.getType());
            if (stack != null && !JobsBridge.canUseCraft(closest, stack))
              return; 
            if (stack != null && left.getLevel() >= 500 && right.getLevel() >= 500) {
              EntityItem i = new EntityItem(this.field_145850_b, this.field_145851_c, (this.field_145848_d + 2), this.field_145849_e);
              i.func_92058_a(stack.func_77946_l());
              if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_72838_d((Entity)i);
                this.field_145850_b.func_72900_e((Entity)it);
                decrementLeftTank();
                decrementRightTank();
                this.field_145850_b.func_72908_a((this.field_145851_c + 0.5F), (this.field_145848_d + 0.5F), (this.field_145849_e + 0.5F), "random.fizz", 0.5F, 2.6F + (this.field_145850_b.field_73012_v
                    .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
                if (closest != null && !this.field_145850_b.field_72995_K) {
                  CraftCauldronAchievement.performCheck(closest, stack);
                  CraftObjective.performCheck(closest, stack);
                } 
              } 
            } 
          } 
          continue;
        } 
        if (left != null && right == null) {
          ItemStack stack = PAlchimiste.cauldronController.getOutputFor(closest, left.getType(), "");
          if (stack != null && !JobsBridge.canUseCraft(closest, stack))
            return; 
          if (stack != null && left.getLevel() >= 500) {
            EntityItem i = new EntityItem(this.field_145850_b, this.field_145851_c, (this.field_145848_d + 2), this.field_145849_e);
            i.func_92058_a(stack.func_77946_l());
            if (!this.field_145850_b.field_72995_K) {
              this.field_145850_b.func_72838_d((Entity)i);
              this.field_145850_b.func_72900_e((Entity)it);
              decrementLeftTank();
              this.field_145850_b.func_72908_a((this.field_145851_c + 0.5F), (this.field_145848_d + 0.5F), (this.field_145849_e + 0.5F), "random.fizz", 0.5F, 2.6F + (this.field_145850_b.field_73012_v
                  .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
              if (closest != null && !this.field_145850_b.field_72995_K) {
                CraftCauldronAchievement.performCheck(closest, stack);
                CraftObjective.performCheck(closest, stack);
              } 
            } 
          } 
          continue;
        } 
        if (left == null && right != null) {
          ItemStack stack = PAlchimiste.cauldronController.getOutputFor(closest, right.getType(), "");
          if (stack != null && !JobsBridge.canUseCraft(closest, stack))
            return; 
          if (stack != null && right.getLevel() >= 500) {
            EntityItem i = new EntityItem(this.field_145850_b, this.field_145851_c, (this.field_145848_d + 2), this.field_145849_e);
            i.func_92058_a(stack.func_77946_l());
            if (!this.field_145850_b.field_72995_K) {
              this.field_145850_b.func_72838_d((Entity)i);
              this.field_145850_b.func_72900_e((Entity)it);
              decrementRightTank();
              this.field_145850_b.func_72908_a((this.field_145851_c + 0.5F), (this.field_145848_d + 0.5F), (this.field_145849_e + 0.5F), "random.fizz", 0.5F, 2.6F + (this.field_145850_b.field_73012_v
                  .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
              if (closest != null && !this.field_145850_b.field_72995_K) {
                CraftCauldronAchievement.performCheck(closest, stack);
                CraftObjective.performCheck(closest, stack);
              } 
            } 
          } 
        } 
      } 
    } 
    if (this.timer > 10) {
      this.timer = 0;
      this.isMultiBlockCorrect = true;
      for (int xC = -3; xC < 4; xC++) {
        for (int zC = -3; zC < 4; zC++) {
          if ((this.field_145851_c + xC != this.field_145851_c || this.field_145849_e + zC != this.field_145849_e) && 
            !(this.field_145850_b.func_147439_a(this.field_145851_c + xC, this.field_145848_d, this.field_145849_e + zC) instanceof fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockTankSupport))
            if (!a(this.field_145850_b, this.field_145851_c + xC, this.field_145848_d, this.field_145849_e + zC)) {
              this.isMultiBlockCorrect = false;
              break;
            }  
        } 
      } 
      int i;
      for (i = -2; i < 2; i++) {
        if (!a(this.field_145850_b, this.field_145851_c + i, this.field_145848_d + 1, this.field_145849_e + 2)) {
          this.isMultiBlockCorrect = false;
          break;
        } 
      } 
      for (i = -2; i < 2; i++) {
        if (!a(this.field_145850_b, this.field_145851_c + i, this.field_145848_d + 1, this.field_145849_e - 2)) {
          this.isMultiBlockCorrect = false;
          break;
        } 
      } 
      for (i = -2; i < 2; i++) {
        if (!a(this.field_145850_b, this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e + i)) {
          this.isMultiBlockCorrect = false;
          break;
        } 
      } 
      for (i = -2; i < 2; i++) {
        if (!a(this.field_145850_b, this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e + i)) {
          this.isMultiBlockCorrect = false;
          break;
        } 
      } 
      if (this.field_145850_b.func_147439_a(this.field_145851_c - 3, this.field_145848_d, this.field_145849_e) != BlocksRegister.CAULDRON_TANKSUPPORT || this.field_145850_b
        .func_147439_a(this.field_145851_c + 3, this.field_145848_d, this.field_145849_e) != BlocksRegister.CAULDRON_TANKSUPPORT)
        this.isMultiBlockCorrect = false; 
      if (!this.isMultiBlockCorrect)
        destroy(); 
    } 
  }
  
  public void destroy() {
    if (!this.field_145850_b.field_72995_K) {
      EventUtils.spawnParticle(this.field_145850_b, "hugeexplosion", this.field_145851_c, this.field_145848_d, this.field_145849_e, 20, 0.1D);
      this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_149697_b(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 0);
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "random.explode", 4.0F, (1.0F + (this.field_145850_b.field_73012_v
          .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
      int xc;
      for (xc = -1; xc < 2; xc++) {
        for (int zc = -1; zc < 2; zc++)
          this.field_145850_b.func_147468_f(this.field_145851_c + xc, this.field_145848_d + 1, this.field_145849_e + zc); 
      } 
      for (xc = -1; xc < 2; xc++) {
        for (int zc = -1; zc < 2; zc++)
          this.field_145850_b.func_147449_b(this.field_145851_c + xc, this.field_145848_d + 1, this.field_145849_e + zc, Blocks.field_150353_l); 
      } 
      this.isSulfuricPresent = false;
    } 
  }
  
  public void unmount() {
    if (!this.field_145850_b.field_72995_K) {
      EventUtils.spawnParticle(this.field_145850_b, "flame", this.field_145851_c, this.field_145848_d, this.field_145849_e, 20, 0.1D);
      for (int xc = -1; xc < 2; xc++) {
        for (int zc = -1; zc < 2; zc++)
          this.field_145850_b.func_147468_f(this.field_145851_c + xc, this.field_145848_d + 1, this.field_145849_e + zc); 
      } 
    } 
  }
  
  public EntityPlayer closestPlayer(List<EntityPlayer> players) {
    double distance = 1000.0D;
    EntityPlayer closeste = null;
    for (EntityPlayer player : players) {
      double tempdistance = Math.sqrt(Math.pow(player.field_70165_t - this.field_145851_c, 2.0D) + Math.pow(player.field_70163_u - this.field_145848_d, 2.0D) + 
          Math.pow(player.field_70161_v - this.field_145849_e, 2.0D));
      if (tempdistance < distance) {
        distance = tempdistance;
        closeste = player;
      } 
    } 
    return closeste;
  }
  
  public boolean a(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y, z) == BlocksRegister.CAULDRON_BLOCK);
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    super.func_145841_b(tag);
    tag.func_74768_a("timer", this.timer);
    tag.func_74757_a("isMultiBlockCorrect", this.isMultiBlockCorrect);
    tag.func_74757_a("isSulfuricPresent", this.isSulfuricPresent);
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    this.timer = tag.func_74762_e("timer");
    this.isMultiBlockCorrect = tag.func_74767_n("isMultiBlockCorrect");
    this.isSulfuricPresent = tag.func_74767_n("isSulfuricPresent");
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
  
  public int getTimer() {
    return this.timer;
  }
  
  public void setTimer(int timer) {
    this.timer = timer;
  }
  
  public boolean isMultiBlockCorrect() {
    return this.isMultiBlockCorrect;
  }
  
  public void setMultiBlockCorrect(boolean multiBlockCorrect) {
    this.isMultiBlockCorrect = multiBlockCorrect;
  }
  
  public boolean isSulfuricPresent() {
    return this.isSulfuricPresent;
  }
  
  public void setSulfuricPresent(boolean sulfuricPresent) {
    this.isSulfuricPresent = sulfuricPresent;
  }
  
  public SeveInformations getSeveInfoLeft() {
    Block block = this.field_145850_b.func_147439_a(this.field_145851_c - 3, this.field_145848_d, this.field_145849_e);
    if (block == BlocksRegister.CAULDRON_TANKSUPPORT && 
      this.field_145850_b.func_147439_a(this.field_145851_c - 3, this.field_145848_d + 1, this.field_145849_e) instanceof fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank) {
      TileEntityTank tank = (TileEntityTank)this.field_145850_b.func_147438_o(this.field_145851_c - 3, this.field_145848_d + 1, this.field_145849_e);
      return new SeveInformations(tank.getLiquid(), tank.getLiquidLevel());
    } 
    return null;
  }
  
  public SeveInformations getSeveInfosRight() {
    Block block = this.field_145850_b.func_147439_a(this.field_145851_c + 3, this.field_145848_d, this.field_145849_e);
    if (block == BlocksRegister.CAULDRON_TANKSUPPORT && 
      this.field_145850_b.func_147439_a(this.field_145851_c + 3, this.field_145848_d + 1, this.field_145849_e) instanceof fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank) {
      TileEntityTank tank = (TileEntityTank)this.field_145850_b.func_147438_o(this.field_145851_c + 3, this.field_145848_d + 1, this.field_145849_e);
      return new SeveInformations(tank.getLiquid(), tank.getLiquidLevel());
    } 
    return null;
  }
  
  public void decrementLeftTank() {
    Block block = this.field_145850_b.func_147439_a(this.field_145851_c - 3, this.field_145848_d, this.field_145849_e);
    if (block == BlocksRegister.CAULDRON_TANKSUPPORT && 
      this.field_145850_b.func_147439_a(this.field_145851_c - 3, this.field_145848_d + 1, this.field_145849_e) instanceof fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank) {
      TileEntityTank tank = (TileEntityTank)this.field_145850_b.func_147438_o(this.field_145851_c - 3, this.field_145848_d + 1, this.field_145849_e);
      if (tank.getLiquidLevel() >= 500) {
        tank.setLiquidLevel(tank.getLiquidLevel() - 500);
      } else {
        tank.setLiquidLevel(0);
        tank.setLiquid("");
      } 
    } 
  }
  
  public void decrementRightTank() {
    Block block = this.field_145850_b.func_147439_a(this.field_145851_c + 3, this.field_145848_d, this.field_145849_e);
    if (block == BlocksRegister.CAULDRON_TANKSUPPORT && 
      this.field_145850_b.func_147439_a(this.field_145851_c + 3, this.field_145848_d + 1, this.field_145849_e) instanceof fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank) {
      TileEntityTank tank = (TileEntityTank)this.field_145850_b.func_147438_o(this.field_145851_c + 3, this.field_145848_d + 1, this.field_145849_e);
      if (tank.getLiquidLevel() >= 500) {
        tank.setLiquidLevel(tank.getLiquidLevel() - 500);
      } else {
        tank.setLiquidLevel(0);
        tank.setLiquid("");
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\tileentities\TileEntityCauldronCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */