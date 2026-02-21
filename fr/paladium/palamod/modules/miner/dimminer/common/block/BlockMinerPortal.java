package fr.paladium.palamod.modules.miner.dimminer.common.block;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityMinerPortal;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityWitheredObsidian;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.palamod.modules.miner.dimminer.common.network.SCPacketDimMinerOpen;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMinerPortal extends BlockBreakable implements ITileEntityProvider {
  public static final int[][] field_150001_a = new int[][] { {}, { 3, 1 }, { 2, 0 } };
  
  public BlockMinerPortal() {
    super("palamod:miner_portal", Material.field_151567_E, false);
    func_149663_c("miner_portal");
    func_149675_a(true);
    func_149715_a(1.0F);
    func_149711_c(-1.0F);
    func_149672_a(Block.field_149778_k);
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityMinerPortal();
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    int orientation = getOrientation(world.func_72805_g(x, y, z));
    if (orientation == 0) {
      if (world.func_147439_a(x - 1, y, z) != this && world.func_147439_a(x + 1, y, z) != this) {
        orientation = 2;
      } else {
        orientation = 1;
      } 
      if (world instanceof World && !((World)world).field_72995_K)
        ((World)world).func_72921_c(x, y, z, orientation, 2); 
    } 
    float f = 0.125F;
    float f1 = 0.125F;
    if (orientation == 1)
      f = 0.5F; 
    if (orientation == 2)
      f1 = 0.5F; 
    func_149676_a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean check(World world, int x, int y, int z) {
    Size size = new Size(world, x, y, z, 1);
    Size size1 = new Size(world, x, y, z, 2);
    if (size.func_150860_b() && size.field_150864_e == 0) {
      size.func_150859_c();
      return true;
    } 
    if (size1.func_150860_b() && size1.field_150864_e == 0) {
      size1.func_150859_c();
      return true;
    } 
    return false;
  }
  
  public boolean noCheck(World world, int x, int y, int z) {
    NoCheckSize size = new NoCheckSize(world, x, y, z, 1);
    NoCheckSize size1 = new NoCheckSize(world, x, y, z, 2);
    if (size.func_150860_b() && size.field_150864_e == 0) {
      size.func_150859_c();
      return true;
    } 
    if (size1.func_150860_b() && size1.field_150864_e == 0) {
      size1.func_150859_c();
      return true;
    } 
    return false;
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    int orientation = getOrientation(world.func_72805_g(x, y, z));
    NoCheckSize size = new NoCheckSize(world, x, y, z, 1);
    NoCheckSize size1 = new NoCheckSize(world, x, y, z, 2);
    if (orientation == 1 && (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g)) {
      world.func_147449_b(x, y, z, Blocks.field_150350_a);
    } else if (orientation == 2 && (!size1.func_150860_b() || size1.field_150864_e < size1.field_150868_h * size1.field_150862_g)) {
      world.func_147449_b(x, y, z, Blocks.field_150350_a);
    } else if (orientation == 0 && !size.func_150860_b() && !size1.func_150860_b()) {
      world.func_147449_b(x, y, z, Blocks.field_150350_a);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
    int orientation = 0;
    if (iBlockAccess.func_147439_a(x, y, z) == this) {
      orientation = getOrientation(iBlockAccess.func_72805_g(x, y, z));
      if (orientation == 0 || (orientation == 2 && side != 5 && side != 4))
        return false; 
      if (orientation == 1 && side != 3 && side != 2)
        return false; 
    } 
    boolean flag = (iBlockAccess.func_147439_a(x - 1, y, z) == this && iBlockAccess.func_147439_a(x - 2, y, z) != this);
    boolean flag1 = (iBlockAccess.func_147439_a(x + 1, y, z) == this && iBlockAccess.func_147439_a(x + 2, y, z) != this);
    boolean flag2 = (iBlockAccess.func_147439_a(x, y, z - 1) == this && iBlockAccess.func_147439_a(x, y, z - 2) != this);
    boolean flag3 = (iBlockAccess.func_147439_a(x, y, z + 1) == this && iBlockAccess.func_147439_a(x, y, z + 2) != this);
    boolean flag4 = (flag || flag1 || orientation == 1);
    boolean flag5 = (flag2 || flag3 || orientation == 2);
    return (flag4 && side == 4) ? true : ((flag4 && side == 5) ? true : ((flag5 && side == 2) ? true : ((flag5 && side == 3))));
  }
  
  public int func_149745_a(Random random) {
    return 0;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (world.field_72995_K || !(entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)entity;
    try {
      if ("ptr".equalsIgnoreCase(CommonModule.getInstance().getConfig().getServerName()) || CommonModule.getInstance().getConfig().getServerType() != ServerType.FACTION) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLa dimension mineur n'est pas disponible sur ce serveur."));
        return;
      } 
      if (JobsPlayer.get((Entity)player).getLevel(JobType.MINER) < 18) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cIl vous faut être niveau 17 dans le métier de mineur pour accéder à cette dimension."));
        return;
      } 
      if (PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), "paladium.dimminer")) {
        DimMinerPlayer data = DimMinerPlayer.get((Entity)player);
        if (data == null)
          return; 
        boolean inPortal = data.isInPortal();
        data.setInPortal(true);
        if (inPortal)
          return; 
        TileEntity te = world.func_147438_o(x, y, z);
        if (!(te instanceof TileEntityMinerPortal))
          return; 
        TileEntityMinerPortal portal = (TileEntityMinerPortal)te;
        if (portal.hasOwner() && !portal.isOwner((Entity)player)) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bVous n'êtes pas le propriétaire de ce portail."));
          float strength = 0.3F;
          player.field_70160_al = true;
          player.field_70159_w += (player.field_70165_t - x > 0.0D) ? 0.30000001192092896D : -0.30000001192092896D;
          player.field_70179_y += (player.field_70161_v - z > 0.0D) ? 0.30000001192092896D : -0.30000001192092896D;
          player.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
          player.field_70170_p.func_72942_c((Entity)new EntityLightningBolt(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v));
          SoundUtils.EXPLODE.playSound(player, x, y, z, 1.0F, 1.0F);
          return;
        } 
        if (!portal.hasOwner())
          for (int i = -3; i <= 3; i++) {
            for (int oz = -3; oz <= 3; oz++) {
              for (int oy = -4; oy <= 4; oy++) {
                TileEntity te1 = world.func_147438_o(x + i, y + oy, z + oz);
                if (te1 instanceof TileEntityMinerPortal)
                  ((TileEntityMinerPortal)te1).setOwner((Entity)player); 
              } 
            } 
          }  
        List<DoubleLocation> blocks = new ArrayList<>();
        for (int ox = -4; ox <= 4; ox++) {
          for (int oz = -4; oz <= 4; oz++) {
            for (int oy = -5; oy <= 5; oy++) {
              TileEntity te1 = world.func_147438_o(x + ox, y + oy, z + oz);
              if (te1 instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te1).isCreated())
                blocks.add(new DoubleLocation(te1.field_145851_c, te1.field_145848_d, te1.field_145849_e)); 
            } 
          } 
        } 
        if (!blocks.isEmpty())
          data.setPortalData(new DimMinerPlayer.PortalData(blocks)); 
        PMiner.network.sendTo((IMessage)new SCPacketDimMinerOpen(), player);
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bPaladium ne vous procure pas la magie requise pour accéder à cette obscure dimension."));
      } 
    } catch (Exception e2) {
      e2.printStackTrace();
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bPaladium ne vous procure pas la magie requise pour accéder à cette obscure dimension."));
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149701_w() {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random random) {
    if (random.nextInt(100) == 0)
      world.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "ambient.weather.thunder", 0.5F, random.nextFloat() * 0.4F + 0.8F, false); 
    for (int l = 0; l < 10; l++) {
      double d0 = x + 0.5D + ((random.nextFloat() - 0.5F) * 5.0F);
      double d1 = y + 0.5D + ((random.nextFloat() - 0.5F) * 5.0F);
      double d2 = z + 0.5D + ((random.nextFloat() - 0.5F) * 5.0F);
      world.func_72869_a(random.nextBoolean() ? "depthsuspend" : "portal", d0, d1, d2, 0.0D, 0.0D, 0.0D);
    } 
  }
  
  public static int getOrientation(int meta) {
    return meta & 0x3;
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z) {
    return Item.func_150899_d(0);
  }
  
  public static class Size {
    private final World world;
    
    private final int field_150865_b;
    
    private final int field_150866_c;
    
    private final int field_150863_d;
    
    private int field_150864_e = 0;
    
    private ChunkCoordinates chunkCoords;
    
    private int field_150862_g;
    
    private int field_150868_h;
    
    public Size(World world, int x, int y, int z, int p_i45415_5_) {
      this.world = world;
      this.field_150865_b = p_i45415_5_;
      this.field_150863_d = BlockMinerPortal.field_150001_a[p_i45415_5_][0];
      this.field_150866_c = BlockMinerPortal.field_150001_a[p_i45415_5_][1];
      for (int i1 = y; y > i1 - 21 && y > 0 && func_150857_a(world.func_147439_a(x, y - 1, z)); y--);
      int j1 = func_150853_a(x, y, z, this.field_150863_d) - 1;
      if (j1 >= 0) {
        this.chunkCoords = new ChunkCoordinates(x + j1 * Direction.field_71583_a[this.field_150863_d], y, z + j1 * Direction.field_71581_b[this.field_150863_d]);
        this.field_150868_h = func_150853_a(this.chunkCoords.field_71574_a, this.chunkCoords.field_71572_b, this.chunkCoords.field_71573_c, this.field_150866_c);
        if (this.field_150868_h < 2 || this.field_150868_h > 21) {
          this.chunkCoords = null;
          this.field_150868_h = 0;
        } 
      } 
      if (this.chunkCoords != null)
        this.field_150862_g = func_150858_a(); 
    }
    
    protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_) {
      int j1 = Direction.field_71583_a[p_150853_4_];
      int k1 = Direction.field_71581_b[p_150853_4_];
      int i1;
      for (i1 = 0; i1 < 22; i1++) {
        Block block = this.world.func_147439_a(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
        if (!func_150857_a(block))
          break; 
        TileEntity tileEntity = this.world.func_147438_o(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);
        if (!(tileEntity instanceof TileEntityWitheredObsidian) || (tileEntity instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)tileEntity).getFeed() < 10))
          break; 
      } 
      TileEntity te = this.world.func_147438_o(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
      return (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() >= 10) ? i1 : 0;
    }
    
    protected int func_150858_a() {
      label48: for (this.field_150862_g = 0; this.field_150862_g < 21; this.field_150862_g++) {
        int k = this.chunkCoords.field_71572_b + this.field_150862_g;
        for (int j = 0; j < this.field_150868_h; j++) {
          int m = this.chunkCoords.field_71574_a + j * Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]];
          int l = this.chunkCoords.field_71573_c + j * Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]];
          Block block = this.world.func_147439_a(m, k, l);
          if (!func_150857_a(block))
            break label48; 
          if (block == BlocksRegister.MINER_PORTAL)
            this.field_150864_e++; 
          if (j == 0) {
            TileEntity te = this.world.func_147438_o(m + Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][0]], k, l + Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][0]]);
            if (te instanceof TileEntityWitheredObsidian) {
              if (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() < 10)
                break label48; 
            } else {
              break label48;
            } 
          } else if (j == this.field_150868_h - 1) {
            TileEntity te = this.world.func_147438_o(m + Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]], k, l + Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]]);
            if (te instanceof TileEntityWitheredObsidian) {
              if (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() < 10)
                break label48; 
            } else {
              break label48;
            } 
          } 
        } 
      } 
      for (int i = 0; i < this.field_150868_h; i++) {
        int j = this.chunkCoords.field_71574_a + i * Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]];
        int k = this.chunkCoords.field_71572_b + this.field_150862_g;
        int l = this.chunkCoords.field_71573_c + i * Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]];
        TileEntity te = this.world.func_147438_o(j, k, l);
        if (!(te instanceof TileEntityWitheredObsidian) || (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() < 10)) {
          this.field_150862_g = 0;
          break;
        } 
      } 
      if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
        return this.field_150862_g; 
      this.chunkCoords = null;
      this.field_150868_h = 0;
      this.field_150862_g = 0;
      return 0;
    }
    
    protected boolean func_150857_a(Block block) {
      return (block.func_149688_o() == Material.field_151579_a || block == Blocks.field_150480_ab || block == BlocksRegister.MINER_PORTAL);
    }
    
    public boolean func_150860_b() {
      return (this.chunkCoords != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21);
    }
    
    public void func_150859_c() {
      for (int i = 0; i < this.field_150868_h; i++) {
        int j = this.chunkCoords.field_71574_a + Direction.field_71583_a[this.field_150866_c] * i;
        int k = this.chunkCoords.field_71573_c + Direction.field_71581_b[this.field_150866_c] * i;
        for (int l = 0; l < this.field_150862_g; l++) {
          int i1 = this.chunkCoords.field_71572_b + l;
          this.world.func_147465_d(j, i1, k, (Block)BlocksRegister.MINER_PORTAL, this.field_150865_b, 2);
        } 
      } 
    }
  }
  
  public static class NoCheckSize {
    private final World world;
    
    private final int field_150865_b;
    
    private final int field_150866_c;
    
    private final int field_150863_d;
    
    private int field_150864_e = 0;
    
    private ChunkCoordinates chunkCoords;
    
    private int field_150862_g;
    
    private int field_150868_h;
    
    public NoCheckSize(World world, int x, int y, int z, int p_i45415_5_) {
      this.world = world;
      this.field_150865_b = p_i45415_5_;
      this.field_150863_d = BlockMinerPortal.field_150001_a[p_i45415_5_][0];
      this.field_150866_c = BlockMinerPortal.field_150001_a[p_i45415_5_][1];
      for (int i1 = y; y > i1 - 21 && y > 0 && func_150857_a(world.func_147439_a(x, y - 1, z)); y--);
      int j1 = func_150853_a(x, y, z, this.field_150863_d) - 1;
      if (j1 >= 0) {
        this.chunkCoords = new ChunkCoordinates(x + j1 * Direction.field_71583_a[this.field_150863_d], y, z + j1 * Direction.field_71581_b[this.field_150863_d]);
        this.field_150868_h = func_150853_a(this.chunkCoords.field_71574_a, this.chunkCoords.field_71572_b, this.chunkCoords.field_71573_c, this.field_150866_c);
        if (this.field_150868_h < 2 || this.field_150868_h > 21) {
          this.chunkCoords = null;
          this.field_150868_h = 0;
        } 
      } 
      if (this.chunkCoords != null)
        this.field_150862_g = func_150858_a(); 
    }
    
    protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_) {
      int j1 = Direction.field_71583_a[p_150853_4_];
      int k1 = Direction.field_71581_b[p_150853_4_];
      int i1;
      for (i1 = 0; i1 < 22; i1++) {
        Block block = this.world.func_147439_a(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
        if (!func_150857_a(block))
          break; 
        TileEntity tileEntity = this.world.func_147438_o(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);
        if (!(tileEntity instanceof TileEntityWitheredObsidian))
          break; 
      } 
      TileEntity te = this.world.func_147438_o(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
      return (te instanceof TileEntityWitheredObsidian) ? i1 : 0;
    }
    
    protected int func_150858_a() {
      label40: for (this.field_150862_g = 0; this.field_150862_g < 21; this.field_150862_g++) {
        int k = this.chunkCoords.field_71572_b + this.field_150862_g;
        for (int j = 0; j < this.field_150868_h; j++) {
          int m = this.chunkCoords.field_71574_a + j * Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]];
          int l = this.chunkCoords.field_71573_c + j * Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]];
          Block block = this.world.func_147439_a(m, k, l);
          if (!func_150857_a(block))
            break label40; 
          if (block == BlocksRegister.MINER_PORTAL)
            this.field_150864_e++; 
          if (j == 0) {
            TileEntity te = this.world.func_147438_o(m + Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][0]], k, l + Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][0]]);
            if (!(te instanceof TileEntityWitheredObsidian))
              break label40; 
          } else if (j == this.field_150868_h - 1) {
            TileEntity te = this.world.func_147438_o(m + Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]], k, l + Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]]);
            if (!(te instanceof TileEntityWitheredObsidian))
              break label40; 
          } 
        } 
      } 
      for (int i = 0; i < this.field_150868_h; i++) {
        int j = this.chunkCoords.field_71574_a + i * Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]];
        int k = this.chunkCoords.field_71572_b + this.field_150862_g;
        int l = this.chunkCoords.field_71573_c + i * Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]];
        TileEntity te = this.world.func_147438_o(j, k, l);
        if (!(te instanceof TileEntityWitheredObsidian)) {
          this.field_150862_g = 0;
          break;
        } 
      } 
      if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
        return this.field_150862_g; 
      this.chunkCoords = null;
      this.field_150868_h = 0;
      this.field_150862_g = 0;
      return 0;
    }
    
    protected boolean func_150857_a(Block block) {
      return (block.func_149688_o() == Material.field_151579_a || block == Blocks.field_150480_ab || block == BlocksRegister.MINER_PORTAL);
    }
    
    public boolean func_150860_b() {
      return (this.chunkCoords != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21);
    }
    
    public void func_150859_c() {
      for (int i = 0; i < this.field_150868_h; i++) {
        int j = this.chunkCoords.field_71574_a + Direction.field_71583_a[this.field_150866_c] * i;
        int k = this.chunkCoords.field_71573_c + Direction.field_71581_b[this.field_150866_c] * i;
        for (int l = 0; l < this.field_150862_g; l++) {
          int i1 = this.chunkCoords.field_71572_b + l;
          this.world.func_147465_d(j, i1, k, (Block)BlocksRegister.MINER_PORTAL, this.field_150865_b, 2);
        } 
      } 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\BlockMinerPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */