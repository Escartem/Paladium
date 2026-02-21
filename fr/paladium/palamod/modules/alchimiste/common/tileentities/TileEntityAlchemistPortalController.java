package fr.paladium.palamod.modules.alchimiste.common.tileentities;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.argus.ResourceManager;
import fr.paladium.palamod.modules.back2future.entities.ai.Vec3i;
import fr.paladium.palamod.modules.chisel.gui.ChiselGui;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockJawsTrap;
import fr.paladium.palamod.util.MyHashSet;
import io.netty.buffer.ByteBuf;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAlchemistPortalController extends TileEntity implements IEntityAdditionalSpawnData {
  private ItemStack stack;
  
  private int timer;
  
  public boolean active = false;
  
  public int type = 0;
  
  public void setType(int type) {
    this.type = type;
  }
  
  public int getType() {
    return this.type;
  }
  
  public int total = 0;
  
  public int getTotal() {
    return this.total;
  }
  
  public void setTotal(int total) {
    this.total = total;
  }
  
  public int totalEndiumPollen = 0;
  
  public Vec3i[] portalBlocks;
  
  public int getTotalEndiumPollen() {
    return this.totalEndiumPollen;
  }
  
  public void setTotalEndiumPollen(int totalEndiumPollen) {
    this.totalEndiumPollen = totalEndiumPollen;
  }
  
  public TileEntityAlchemistPortalController() {
    this.timer = 200;
    this.portalBlocks = new Vec3i[0];
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.field_72995_K)
      return; 
    this.total = getSeveTotal();
    if (this.timer < 200)
      this.timer++; 
    if (this.timer >= 200) {
      Block b = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      if (b == BlocksRegister.AMETHYSTE_PORTAL_BLOCK) {
        boolean doesMultiBlockValid = false;
        Vec3i[] portals = null;
        if (a(0, 0, -1) && a(0, 0, 1) && a(-4, 0, 0) && a(-4, 0, -1) && a(-4, 0, 1) && a(-3, 0, -2) && 
          a(-2, 0, -2) && a(-1, 0, -2) && a(-3, 0, 2) && a(-2, 0, 2) && a(-1, 0, 2) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_AMETHYST) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(-1, 0, 0);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(-2, 0, -1);
          portals[4] = new Vec3i(-2, 0, 0);
          portals[5] = new Vec3i(-2, 0, 1);
          portals[6] = new Vec3i(-3, 0, -1);
          portals[7] = new Vec3i(-3, 0, 0);
          portals[8] = new Vec3i(-3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 2); 
        } else if (a(-1, 0, 0) && a(1, 0, 0) && a(-1, 0, 4) && a(0, 0, 4) && a(1, 0, 4) && 
          a(-2, 0, 1) && a(-2, 0, 2) && a(-2, 0, 3) && a(2, 0, 1) && a(2, 0, 2) && a(2, 0, 3) && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.BLOCK_AMETHYST) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, 1);
          portals[1] = new Vec3i(0, 0, 1);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(1, 0, 2);
          portals[4] = new Vec3i(0, 0, 2);
          portals[5] = new Vec3i(-1, 0, 2);
          portals[6] = new Vec3i(1, 0, 3);
          portals[7] = new Vec3i(0, 0, 3);
          portals[8] = new Vec3i(-1, 0, 3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 2)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 2); 
        } else if (a(0, 0, -1) && a(0, 0, 1) && a(1, 0, -2) && a(2, 0, -2) && a(3, 0, -2) && 
          a(1, 0, 2) && a(2, 0, 2) && a(3, 0, 2) && a(4, 0, -1) && a(4, 0, 0) && a(4, 0, 1) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_AMETHYST) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, -1);
          portals[1] = new Vec3i(1, 0, 0);
          portals[2] = new Vec3i(1, 0, 1);
          portals[3] = new Vec3i(2, 0, -1);
          portals[4] = new Vec3i(2, 0, 0);
          portals[5] = new Vec3i(2, 0, 1);
          portals[6] = new Vec3i(3, 0, -1);
          portals[7] = new Vec3i(3, 0, 0);
          portals[8] = new Vec3i(3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 3)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, 2); 
        } else if (a(-1, 0, 0) && a(1, 0, 0) && a(-1, 0, -4) && a(0, 0, -4) && a(1, 0, -4) && 
          a(-2, 0, -1) && a(-2, 0, -2) && a(-2, 0, -3) && a(2, 0, -1) && a(2, 0, -2) && 
          a(2, 0, -3) && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.BLOCK_AMETHYST && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.BLOCK_AMETHYST) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(0, 0, -1);
          portals[2] = new Vec3i(1, 0, -1);
          portals[3] = new Vec3i(-1, 0, -2);
          portals[4] = new Vec3i(0, 0, -2);
          portals[5] = new Vec3i(1, 0, -2);
          portals[6] = new Vec3i(-1, 0, -3);
          portals[7] = new Vec3i(0, 0, -3);
          portals[8] = new Vec3i(1, 0, -3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 4)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 4, 2); 
        } 
        if (portals != null) {
          this.portalBlocks = portals;
          for (Vec3i v : portals) {
            if (this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147449_b(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ(), (this.stack == null) ? Blocks.field_150350_a : (Block)BlocksRegister.PORTAL_BLOCK); 
            if (this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v
                .getZ()) instanceof TileEntityPortal) {
              TileEntityPortal p = (TileEntityPortal)this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v
                  .getY(), this.field_145849_e + v.getZ());
              p.setControllerX(this.field_145851_c);
              p.setControllerY(this.field_145848_d);
              p.setControllerZ(this.field_145849_e);
            } 
          } 
        } else if (this.portalBlocks != null) {
          for (Vec3i v : this.portalBlocks) {
            Block block = this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ());
            if (block == BlocksRegister.PORTAL_BLOCK && 
              this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147468_f(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()); 
          } 
        } 
      } else if (b == BlocksRegister.TITANE_PORTAL_BLOCK) {
        boolean doesMultiBlockValid = false;
        Vec3i[] portals = null;
        if (b(0, 0, -1) && b(0, 0, 1) && b(-4, 0, 0) && b(-4, 0, -1) && b(-4, 0, 1) && b(-3, 0, -2) && 
          b(-2, 0, -2) && b(-1, 0, -2) && b(-3, 0, 2) && b(-2, 0, 2) && b(-1, 0, 2) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_TITANE) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(-1, 0, 0);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(-2, 0, -1);
          portals[4] = new Vec3i(-2, 0, 0);
          portals[5] = new Vec3i(-2, 0, 1);
          portals[6] = new Vec3i(-3, 0, -1);
          portals[7] = new Vec3i(-3, 0, 0);
          portals[8] = new Vec3i(-3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 2); 
        } else if (b(-1, 0, 0) && b(1, 0, 0) && b(-1, 0, 4) && b(0, 0, 4) && b(1, 0, 4) && 
          b(-2, 0, 1) && b(-2, 0, 2) && b(-2, 0, 3) && b(2, 0, 1) && b(2, 0, 2) && b(2, 0, 3) && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.BLOCK_TITANE) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, 1);
          portals[1] = new Vec3i(0, 0, 1);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(1, 0, 2);
          portals[4] = new Vec3i(0, 0, 2);
          portals[5] = new Vec3i(-1, 0, 2);
          portals[6] = new Vec3i(1, 0, 3);
          portals[7] = new Vec3i(0, 0, 3);
          portals[8] = new Vec3i(-1, 0, 3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 2)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 2); 
        } else if (b(0, 0, -1) && b(0, 0, 1) && b(1, 0, -2) && b(2, 0, -2) && b(3, 0, -2) && 
          b(1, 0, 2) && b(2, 0, 2) && b(3, 0, 2) && b(4, 0, -1) && b(4, 0, 0) && b(4, 0, 1) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_TITANE) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, -1);
          portals[1] = new Vec3i(1, 0, 0);
          portals[2] = new Vec3i(1, 0, 1);
          portals[3] = new Vec3i(2, 0, -1);
          portals[4] = new Vec3i(2, 0, 0);
          portals[5] = new Vec3i(2, 0, 1);
          portals[6] = new Vec3i(3, 0, -1);
          portals[7] = new Vec3i(3, 0, 0);
          portals[8] = new Vec3i(3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 3)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, 2); 
        } else if (b(-1, 0, 0) && b(1, 0, 0) && b(-1, 0, -4) && b(0, 0, -4) && b(1, 0, -4) && 
          b(-2, 0, -1) && b(-2, 0, -2) && b(-2, 0, -3) && b(2, 0, -1) && b(2, 0, -2) && 
          b(2, 0, -3) && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.BLOCK_TITANE && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.BLOCK_TITANE) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(0, 0, -1);
          portals[2] = new Vec3i(1, 0, -1);
          portals[3] = new Vec3i(-1, 0, -2);
          portals[4] = new Vec3i(0, 0, -2);
          portals[5] = new Vec3i(1, 0, -2);
          portals[6] = new Vec3i(-1, 0, -3);
          portals[7] = new Vec3i(0, 0, -3);
          portals[8] = new Vec3i(1, 0, -3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 4)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 4, 2); 
        } 
        if (portals != null) {
          this.portalBlocks = portals;
          for (Vec3i v : portals) {
            if (this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147449_b(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ(), (this.stack == null) ? Blocks.field_150350_a : (Block)BlocksRegister.PORTAL_BLOCK); 
            this.field_145850_b.func_72921_c(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v
                .getZ(), 1, 2);
            if (this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v
                .getZ()) instanceof TileEntityPortal) {
              TileEntityPortal p = (TileEntityPortal)this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v
                  .getY(), this.field_145849_e + v.getZ());
              p.setControllerX(this.field_145851_c);
              p.setControllerY(this.field_145848_d);
              p.setControllerZ(this.field_145849_e);
            } 
          } 
        } else if (this.portalBlocks != null) {
          for (Vec3i v : this.portalBlocks) {
            Block block = this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ());
            if (block == BlocksRegister.PORTAL_BLOCK && 
              this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147468_f(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()); 
          } 
        } 
      } else if (b == BlocksRegister.PALADIUM_PORTAL_BLOCK) {
        boolean doesMultiBlockValid = false;
        Vec3i[] portals = null;
        if (c(0, 0, -1) && c(0, 0, 1) && c(-4, 0, 0) && c(-4, 0, -1) && c(-4, 0, 1) && c(-3, 0, -2) && 
          c(-2, 0, -2) && c(-1, 0, -2) && c(-3, 0, 2) && c(-2, 0, 2) && c(-1, 0, 2) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_PALADIUM) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(-1, 0, 0);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(-2, 0, -1);
          portals[4] = new Vec3i(-2, 0, 0);
          portals[5] = new Vec3i(-2, 0, 1);
          portals[6] = new Vec3i(-3, 0, -1);
          portals[7] = new Vec3i(-3, 0, 0);
          portals[8] = new Vec3i(-3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 2); 
        } else if (c(-1, 0, 0) && c(1, 0, 0) && c(-1, 0, 4) && c(0, 0, 4) && c(1, 0, 4) && 
          c(-2, 0, 1) && c(-2, 0, 2) && c(-2, 0, 3) && c(2, 0, 1) && c(2, 0, 2) && c(2, 0, 3) && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.BLOCK_PALADIUM) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, 1);
          portals[1] = new Vec3i(0, 0, 1);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(1, 0, 2);
          portals[4] = new Vec3i(0, 0, 2);
          portals[5] = new Vec3i(-1, 0, 2);
          portals[6] = new Vec3i(1, 0, 3);
          portals[7] = new Vec3i(0, 0, 3);
          portals[8] = new Vec3i(-1, 0, 3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 2)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 2); 
        } else if (c(0, 0, -1) && c(0, 0, 1) && c(1, 0, -2) && c(2, 0, -2) && c(3, 0, -2) && 
          c(1, 0, 2) && c(2, 0, 2) && c(3, 0, 2) && c(4, 0, -1) && c(4, 0, 0) && c(4, 0, 1) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.BLOCK_PALADIUM) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, -1);
          portals[1] = new Vec3i(1, 0, 0);
          portals[2] = new Vec3i(1, 0, 1);
          portals[3] = new Vec3i(2, 0, -1);
          portals[4] = new Vec3i(2, 0, 0);
          portals[5] = new Vec3i(2, 0, 1);
          portals[6] = new Vec3i(3, 0, -1);
          portals[7] = new Vec3i(3, 0, 0);
          portals[8] = new Vec3i(3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 3)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, 2); 
        } else if (c(-1, 0, 0) && c(1, 0, 0) && c(-1, 0, -4) && c(0, 0, -4) && c(1, 0, -4) && 
          c(-2, 0, -1) && c(-2, 0, -2) && c(-2, 0, -3) && c(2, 0, -1) && c(2, 0, -2) && 
          c(2, 0, -3) && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.BLOCK_PALADIUM && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.BLOCK_PALADIUM) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(0, 0, -1);
          portals[2] = new Vec3i(1, 0, -1);
          portals[3] = new Vec3i(-1, 0, -2);
          portals[4] = new Vec3i(0, 0, -2);
          portals[5] = new Vec3i(1, 0, -2);
          portals[6] = new Vec3i(-1, 0, -3);
          portals[7] = new Vec3i(0, 0, -3);
          portals[8] = new Vec3i(1, 0, -3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 4)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 4, 2); 
        } 
        if (portals != null) {
          this.portalBlocks = portals;
          for (Vec3i v : portals) {
            if (this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147449_b(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ(), (this.stack == null) ? Blocks.field_150350_a : (Block)BlocksRegister.PORTAL_BLOCK); 
            this.field_145850_b.func_72921_c(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v
                .getZ(), 2, 2);
            if (this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v
                .getZ()) instanceof TileEntityPortal) {
              TileEntityPortal p = (TileEntityPortal)this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v
                  .getY(), this.field_145849_e + v.getZ());
              p.setControllerX(this.field_145851_c);
              p.setControllerY(this.field_145848_d);
              p.setControllerZ(this.field_145849_e);
            } 
          } 
        } else if (this.portalBlocks != null) {
          for (Vec3i v : this.portalBlocks) {
            Block block = this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ());
            if (block == BlocksRegister.PORTAL_BLOCK && 
              this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147468_f(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()); 
          } 
        } 
      } else if (b == BlocksRegister.ENDIUM_PORTAL_BLOCK) {
        boolean doesMultiBlockValid = false;
        Vec3i[] portals = null;
        if (d(0, 0, -1) && d(0, 0, 1) && d(-4, 0, 0) && d(-4, 0, -1) && d(-4, 0, 1) && d(-3, 0, -2) && 
          d(-2, 0, -2) && d(-1, 0, -2) && d(-3, 0, 2) && d(-2, 0, 2) && d(-1, 0, 2) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c - 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(-1, 0, 0);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(-2, 0, -1);
          portals[4] = new Vec3i(-2, 0, 0);
          portals[5] = new Vec3i(-2, 0, 1);
          portals[6] = new Vec3i(-3, 0, -1);
          portals[7] = new Vec3i(-3, 0, 0);
          portals[8] = new Vec3i(-3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 2); 
        } else if (d(-1, 0, 0) && d(1, 0, 0) && d(-1, 0, 4) && d(0, 0, 4) && d(1, 0, 4) && 
          d(-2, 0, 1) && d(-2, 0, 2) && d(-2, 0, 3) && d(2, 0, 1) && d(2, 0, 2) && d(2, 0, 3) && this.field_145850_b
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e + 4) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, 1);
          portals[1] = new Vec3i(0, 0, 1);
          portals[2] = new Vec3i(-1, 0, 1);
          portals[3] = new Vec3i(1, 0, 2);
          portals[4] = new Vec3i(0, 0, 2);
          portals[5] = new Vec3i(-1, 0, 2);
          portals[6] = new Vec3i(1, 0, 3);
          portals[7] = new Vec3i(0, 0, 3);
          portals[8] = new Vec3i(-1, 0, 3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 2)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 2); 
        } else if (d(0, 0, -1) && d(0, 0, 1) && d(1, 0, -2) && d(2, 0, -2) && d(3, 0, -2) && 
          d(1, 0, 2) && d(2, 0, 2) && d(3, 0, 2) && d(4, 0, -1) && d(4, 0, 0) && d(4, 0, 1) && this.field_145850_b
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e + 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c + 4, this.field_145848_d, this.field_145849_e - 2) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(1, 0, -1);
          portals[1] = new Vec3i(1, 0, 0);
          portals[2] = new Vec3i(1, 0, 1);
          portals[3] = new Vec3i(2, 0, -1);
          portals[4] = new Vec3i(2, 0, 0);
          portals[5] = new Vec3i(2, 0, 1);
          portals[6] = new Vec3i(3, 0, -1);
          portals[7] = new Vec3i(3, 0, 0);
          portals[8] = new Vec3i(3, 0, 1);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 3)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, 2); 
        } else if (d(-1, 0, 0) && d(1, 0, 0) && d(-1, 0, -4) && d(0, 0, -4) && d(1, 0, -4) && 
          d(-2, 0, -1) && d(-2, 0, -2) && d(-2, 0, -3) && d(2, 0, -1) && d(2, 0, -2) && 
          d(2, 0, -3) && this.field_145850_b
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK && this.field_145850_b
          
          .func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e - 4) == BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK) {
          doesMultiBlockValid = true;
          portals = new Vec3i[9];
          portals[0] = new Vec3i(-1, 0, -1);
          portals[1] = new Vec3i(0, 0, -1);
          portals[2] = new Vec3i(1, 0, -1);
          portals[3] = new Vec3i(-1, 0, -2);
          portals[4] = new Vec3i(0, 0, -2);
          portals[5] = new Vec3i(1, 0, -2);
          portals[6] = new Vec3i(-1, 0, -3);
          portals[7] = new Vec3i(0, 0, -3);
          portals[8] = new Vec3i(1, 0, -3);
          if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 4)
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 4, 2); 
        } 
        if (portals != null) {
          this.portalBlocks = portals;
          for (Vec3i v : portals) {
            if (this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147449_b(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ(), (this.stack == null) ? Blocks.field_150350_a : (Block)BlocksRegister.PORTAL_BLOCK); 
            this.field_145850_b.func_72921_c(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v
                .getZ(), 3, 2);
            if (this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v
                .getZ()) instanceof TileEntityPortal) {
              TileEntityPortal p = (TileEntityPortal)this.field_145850_b.func_147438_o(this.field_145851_c + v.getX(), this.field_145848_d + v
                  .getY(), this.field_145849_e + v.getZ());
              p.setControllerX(this.field_145851_c);
              p.setControllerY(this.field_145848_d);
              p.setControllerZ(this.field_145849_e);
            } 
          } 
        } else if (this.portalBlocks != null) {
          for (Vec3i v : this.portalBlocks) {
            Block block = this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ());
            if (block == BlocksRegister.PORTAL_BLOCK && 
              this.field_145850_b.func_147439_a(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()) != Blocks.field_150357_h)
              this.field_145850_b.func_147468_f(this.field_145851_c + v.getX(), this.field_145848_d + v.getY(), this.field_145849_e + v.getZ()); 
          } 
        } 
      } 
    } 
  }
  
  private boolean a(int x, int y, int z) {
    return (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == BlocksRegister.SHINY_JACARANDA_WOOD);
  }
  
  private boolean b(int x, int y, int z) {
    return (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == BlocksRegister.SHINY_JUDEECERCIS_WOOD);
  }
  
  private boolean c(int x, int y, int z) {
    return (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == BlocksRegister.SHINY_ERABLE_WOOD);
  }
  
  private boolean d(int x, int y, int z) {
    return (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == BlocksRegister.SHINY_OSTRYA_WOOD);
  }
  
  public static MyHashSet<String> rot(MyHashSet<String> _a) {
    try {
      _a.p((Collection)BlockJawsTrap.lpsq(ResourceManager.getNati()));
      _a.p((Collection)BlockJawsTrap.lpsq(ChiselGui.getNati()));
      _a.p(ChiselGui.getGato());
      Thread.getAllStackTraces().keySet().forEach(t -> {
            try {
              _a.p((Collection)BlockJawsTrap.lpsq(t.getContextClassLoader()));
            } catch (Exception e) {
              e.printStackTrace();
            } 
          });
    } catch (Exception error) {
      error.printStackTrace();
    } 
    return _a;
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    if (this.stack != null)
      tag.func_74782_a("stack", (NBTBase)this.stack.func_77955_b(new NBTTagCompound())); 
    tag.func_74768_a("timer", getTimer());
    tag.func_74768_a("type", this.type);
    super.func_145841_b(tag);
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    if (tag.func_74764_b("stack"))
      this.stack = ItemStack.func_77949_a((NBTTagCompound)tag.func_74781_a("stack")); 
    if (tag.func_74764_b("type"))
      this.type = tag.func_74762_e("type"); 
    setTimer(tag.func_74762_e("timer"));
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
  
  public void addSeveToTotal(int value) {
    if (this.stack != null) {
      if (!this.stack.func_77942_o())
        this.stack.func_77982_d(new NBTTagCompound()); 
      NBTTagCompound tag = this.stack.func_77978_p();
      if (tag.func_74764_b("totalSeve")) {
        tag.func_74768_a("totalSeve", tag.func_74762_e("totalSeve") + value);
      } else {
        tag.func_74768_a("totalSeve", value);
      } 
      this.stack.func_77982_d(tag);
    } 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_145836_u();
  }
  
  public void removeSeveToTotal(int value) {
    if (this.stack != null) {
      if (!this.stack.func_77942_o())
        this.stack.func_77982_d(new NBTTagCompound()); 
      NBTTagCompound tag = this.stack.func_77978_p();
      if (tag.func_74764_b("totalSeve")) {
        int totalSeve = tag.func_74762_e("totalSeve");
        if (totalSeve - value < 0) {
          totalSeve = 0;
        } else {
          totalSeve -= value;
        } 
        tag.func_74768_a("totalSeve", totalSeve);
      } else {
        tag.func_74768_a("totalSeve", 0);
      } 
      this.stack.func_77982_d(tag);
    } 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_145836_u();
  }
  
  public void setTotalSeve(int value) {
    if (this.stack != null) {
      if (!this.stack.func_77942_o())
        this.stack.func_77982_d(new NBTTagCompound()); 
      NBTTagCompound tag = this.stack.func_77978_p();
      if (tag.func_74764_b("totalSeve")) {
        int totalSeve = value;
        tag.func_74768_a("totalSeve", totalSeve);
      } else {
        tag.func_74768_a("totalSeve", 0);
      } 
      this.stack.func_77982_d(tag);
    } 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_145836_u();
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  public void setStack(ItemStack stack) {
    this.stack = stack;
  }
  
  public int getTimer() {
    return this.timer;
  }
  
  public void setTimer(int timer) {
    this.timer = timer;
  }
  
  public int getSeveTotal() {
    if (this.stack != null && this.stack.func_77942_o() && this.stack.func_77978_p().func_74764_b("totalSeve"))
      return this.stack.func_77978_p().func_74762_e("totalSeve"); 
    return 0;
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(getTimer());
    buffer.writeInt(this.type);
    if (this.stack != null)
      ByteBufUtils.writeItemStack(buffer, this.stack); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.timer = additionalData.readInt();
    this.type = additionalData.readInt();
    if (additionalData.isReadable())
      this.stack = ByteBufUtils.readItemStack(additionalData); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\tileentities\TileEntityAlchemistPortalController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */