package fr.paladium.palamod.modules.alchimiste.common.blocks;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.achievements.types.ExtractSeveAchievement;
import fr.paladium.palamod.modules.achievements.types.ExtractSeveTypeAchievement;
import fr.paladium.palamod.modules.ajobs.common.objectives.ExtractSeveObjective;
import fr.paladium.palamod.modules.ajobs.common.registerer.requirement.ExtractSeveRequirement;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.common.blocks.itemblocks.ItemBlockExtractor;
import fr.paladium.palamod.modules.alchimiste.common.network.servertoclient.SCExtractorSync;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityExtractor;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockAlchemist;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.tutorial.common.event.ExtractorSieveExtractEvent;
import java.util.ArrayList;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockExtractor extends BlockAlchemist implements ITooltipWiki {
  public static final int DEFAULT_USE = 144;
  
  private String name;
  
  public BlockExtractor() {
    super(Material.field_151576_e);
    this.name = "extractor";
    func_149711_c(2.0F);
    func_149752_b(3.0F);
    func_149663_c(this.name);
  }
  
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.field_149761_L = par1IconRegister.func_94245_a("stone");
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    if (!(world.func_147438_o(x, y, z) instanceof TileEntityExtractor)) {
      super.func_149719_a(world, x, y, z);
      return;
    } 
    switch (((TileEntityExtractor)world.func_147438_o(x, y, z)).getAngle()) {
      case 0:
        func_149676_a(0.3F, 0.0F, 0.3F, 1.0F, 0.65F, 0.7F);
        break;
      case 1:
        func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.65F, 1.0F);
        break;
      case 2:
        func_149676_a(0.0F, 0.0F, 0.3F, 0.75F, 0.65F, 0.7F);
        break;
      case 3:
        func_149676_a(0.3F, 0.0F, 0.0F, 0.7F, 0.65F, 0.7F);
        break;
    } 
  }
  
  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {}
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer p, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    TileEntityExtractor tile = (TileEntityExtractor)world.func_147438_o(x, y, z);
    if (!world.field_72995_K) {
      if (p.field_71071_by.func_70448_g() != null && p.field_71071_by
        .func_70448_g().func_77973_b() instanceof fr.paladium.palamod.modules.alchimiste.common.items.ItemFlask && tile.getStack() == null && p.field_71071_by
        .func_70448_g().func_77960_j() == 0) {
        tile.setModified(false);
        tile.setStack(p.field_71071_by.func_70448_g().func_77946_l());
        (tile.getStack()).field_77994_a = 1;
        tile.setEnabled(true);
        (p.field_71071_by.func_70448_g()).field_77994_a--;
      } else if (p.func_70093_af() && tile.getStack() != null) {
        ItemStack stack = tile.getStack();
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("seveType") && 
          tile.isModified()) {
          String seveType = stack.func_77978_p().func_74779_i("seveType");
          ExtractSeveObjective.performCheck(p, seveType, stack.func_77960_j());
          ExtractSeveTypeAchievement.performCheck(p, seveType);
          ExtractSeveAchievement.performCheck(p);
          JobsPlayer jobsPlayer = JobsPlayer.get((Entity)p);
          if (jobsPlayer != null)
            jobsPlayer.getRequirements(ExtractSeveRequirement.class).forEach(optional -> optional.ifPresent(())); 
          MinecraftForge.EVENT_BUS.post((Event)new ExtractorSieveExtractEvent(p, stack, seveType));
          tile.setLevelClimb(0);
        } 
        EntityItem entityitem = p.func_71019_a(stack.func_77946_l(), false);
        entityitem.field_145804_b = 0;
        entityitem.func_145797_a(p.func_70005_c_());
        tile.setStack(null);
      } else {
        Block toVerify = null;
        switch (tile.getAngle()) {
          case 0:
            toVerify = world.func_147439_a(x + 1, y, z);
            break;
          case 1:
            toVerify = world.func_147439_a(x, y, z + 1);
            break;
          case 2:
            toVerify = world.func_147439_a(x - 1, y, z);
            break;
          case 3:
            toVerify = world.func_147439_a(x, y, z - 1);
            break;
        } 
        if (toVerify != null) {
          String name = toVerify.func_149739_a();
          if (name.equals("tile.log.erable") || name.equals("tile.log.judeecercis") || name
            .equals("tile.log.jacaranda") || name.equals("tile.log.ostrya") || name
            .equals("tile.alchimiste.wood")) {
            tile.setEnabled(!tile.isEnabled());
            world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, 0.6F);
          } else {
            tile.setEnabled(false);
          } 
        } 
      } 
      if (p instanceof EntityPlayerMP)
        PAlchimiste.network.sendTo((IMessage)new SCExtractorSync(tile.getStack(), tile.isEnabled(), x, y, z), (EntityPlayerMP)p); 
    } 
    return true;
  }
  
  private int applyPetSkill(EntityPlayerMP player, int use) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.MAGIC_EXTRACTOR.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return use; 
    double percent = PetUtils.getValueAsPercent(value);
    int increase = (int)Math.round(use * percent);
    return use + increase;
  }
  
  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack itemStack) {
    if (!world.field_72995_K) {
      int angle = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      angle++;
      angle %= 4;
      TileEntityExtractor tile = (TileEntityExtractor)world.func_147438_o(i, j, k);
      tile.setAngle(angle);
      if (!itemStack.func_77942_o() || (!itemStack.field_77990_d.func_74764_b("use") && !itemStack.field_77990_d.func_74764_b("maxUse"))) {
        int use = 144;
        use = applyPetSkill((EntityPlayerMP)player, use);
        if (use != 144)
          ChatUtils.sendColoredMessage((ICommandSender)player, new String[] { "§aVotre familier a augmenté la quantité de sève extraite de §e" + use + " §aunités." }); 
        tile.setUsage(use);
        tile.setMaxUsage(use);
        return;
      } 
      if (itemStack.func_77942_o() && itemStack.field_77990_d.func_74764_b("use") && itemStack.field_77990_d.func_74764_b("maxUse")) {
        tile.setUsage(itemStack.field_77990_d.func_74762_e("use"));
        tile.setMaxUsage(itemStack.field_77990_d.func_74762_e("maxUse"));
      } 
    } 
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    if (!world.field_72995_K) {
      TileEntityExtractor extractor = (TileEntityExtractor)world.func_147438_o(x, y, z);
      if (extractor.getStack() != null) {
        EntityItem entityItem = new EntityItem(world, x, y, z, extractor.getStack().func_77946_l());
        entityItem.field_145804_b = 0;
        world.func_72838_d((Entity)entityItem);
      } 
      ItemStack stack = new ItemStack((Block)BlocksRegister.EXTRACTOR, 1);
      NBTTagCompound tag = new NBTTagCompound();
      if (extractor.getUsage() < 0)
        return; 
      tag.func_74768_a("use", extractor.getUsage());
      tag.func_74768_a("maxUse", extractor.getMaxUsage());
      stack.func_77982_d(tag);
      EntityItem entityitem = new EntityItem(world, x, y, z);
      entityitem.field_145804_b = 0;
      entityitem.func_92058_a(stack);
      world.func_72838_d((Entity)entityitem);
    } 
    if (hasTileEntity(meta))
      world.func_147475_p(x, y, z); 
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityExtractor();
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.extractorRenderId;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public Class<? extends TileEntity> getTileEntity() {
    return (Class)TileEntityExtractor.class;
  }
  
  public String getName() {
    return this.name;
  }
  
  public ItemBlock getCustomStack() {
    return (ItemBlock)new ItemBlockExtractor((Block)this);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/autour-du-chaudron#2.-les-objets-divers";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockExtractor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */