package fr.paladium.palamod.modules.alchimiste.common.tileentities;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.world.PWorld;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityExtractor extends TileEntity {
  private boolean isEnabled;
  
  private boolean isModified = false;
  
  public boolean isModified() {
    return this.isModified;
  }
  
  public void setModified(boolean isModified) {
    this.isModified = isModified;
  }
  
  private int levelClimb = 0;
  
  private int angle;
  
  public int getLevelClimb() {
    return this.levelClimb;
  }
  
  public void setLevelClimb(int levelClimb) {
    this.levelClimb = levelClimb;
  }
  
  private int usage = 144;
  
  public int getUsage() {
    return this.usage;
  }
  
  public void setUsage(int usage) {
    this.usage = usage;
  }
  
  private int maxUsage = 144;
  
  private ItemStack stack;
  
  public int getMaxUsage() {
    return this.maxUsage;
  }
  
  public void setMaxUsage(int maxUsage) {
    this.maxUsage = maxUsage;
  }
  
  private int seveLevel = 0;
  
  private int timer;
  
  public TileEntityExtractor() {
    this.isEnabled = false;
    this.stack = null;
    this.seveLevel = 0;
  }
  
  public void func_145845_h() {
    if (this.stack == null)
      setEnabled(false); 
    if (this.usage <= 0) {
      ItemStack extractorItemStack = new ItemStack((Block)BlocksRegister.EXTRACTOR);
      NBTTagCompound compound = new NBTTagCompound();
      compound.func_74768_a("use", this.usage);
      compound.func_74768_a("maxUse", this.maxUsage);
      extractorItemStack.func_77982_d(compound);
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      return;
    } 
    int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE, z = Integer.MAX_VALUE;
    switch (getAngle()) {
      case 0:
        x = this.field_145851_c + 1;
        y = this.field_145848_d;
        z = this.field_145849_e;
        break;
      case 1:
        x = this.field_145851_c;
        y = this.field_145848_d;
        z = this.field_145849_e + 1;
        break;
      case 2:
        x = this.field_145851_c - 1;
        y = this.field_145848_d;
        z = this.field_145849_e;
        break;
      case 3:
        x = this.field_145851_c;
        y = this.field_145848_d;
        z = this.field_145849_e - 1;
        break;
    } 
    if (x == Integer.MAX_VALUE || y == Integer.MAX_VALUE || z == Integer.MAX_VALUE)
      setEnabled(false); 
    if (this.field_145850_b.func_147439_a(x, y, z) == null || this.field_145850_b.func_147437_c(x, y, z))
      setEnabled(false); 
    if (isEnabled()) {
      if (!this.stack.func_77942_o()) {
        NBTTagCompound nBTTagCompound = new NBTTagCompound();
        String str = "null";
        if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_OSTRYA) {
          str = "Ostrya";
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_ERABLE) {
          str = "Erable";
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_JACARANDA) {
          str = "Jacaranda";
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_JUDEECERCIS) {
          str = "Judeecercis";
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == BlocksRegister.BLOCK_WOOD) {
          str = ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).getSeveType();
        } 
        nBTTagCompound.func_74778_a("seveType", str);
        this.stack.func_77982_d(nBTTagCompound);
      } 
      NBTTagCompound tag = this.stack.func_77978_p();
      if (!tag.func_74764_b("seveType") || this.stack.func_77960_j() == 0 || this.field_145850_b.func_147438_o(x, y, z) == null) {
        String str = null;
        if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_OSTRYA) {
          str = "Ostrya";
          if (tag.func_74764_b("seveType")) {
            String seveType2 = tag.func_74779_i("seveType");
            if (!seveType2.equals(str)) {
              setEnabled(false);
              return;
            } 
          } 
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_ERABLE) {
          str = "Erable";
          if (tag.func_74764_b("seveType")) {
            String seveType2 = tag.func_74779_i("seveType");
            if (!seveType2.equals(str)) {
              setEnabled(false);
              return;
            } 
          } 
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_JACARANDA) {
          str = "Jacaranda";
          if (tag.func_74764_b("seveType")) {
            String seveType2 = tag.func_74779_i("seveType");
            if (!seveType2.equals(str)) {
              setEnabled(false);
              return;
            } 
          } 
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == PWorld.LOG_JUDEECERCIS) {
          str = "Judeecercis";
          if (tag.func_74764_b("seveType")) {
            String seveType2 = tag.func_74779_i("seveType");
            if (!seveType2.equals(str)) {
              setEnabled(false);
              return;
            } 
          } 
          this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.BLOCK_WOOD);
          ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).setSeveType(str);
        } else if (this.field_145850_b.func_147439_a(x, y, z) == BlocksRegister.BLOCK_WOOD) {
          str = ((TileEntityWood)this.field_145850_b.func_147438_o(x, y, z)).getSeveType();
        } 
        if (str != null) {
          tag.func_74778_a("seveType", str);
          this.stack.func_77982_d(tag);
        } 
      } 
      tag = this.stack.func_77978_p();
      if (!(this.field_145850_b.func_147438_o(x, y, z) instanceof TileEntityWood))
        return; 
      TileEntityWood wood = (TileEntityWood)this.field_145850_b.func_147438_o(x, y, z);
      String seveType = tag.func_74779_i("seveType");
      if (!seveType.equals(wood.getSeveType())) {
        setEnabled(false);
        return;
      } 
      if (this.stack.func_77960_j() == 6)
        setEnabled(false); 
      if (wood.getSeveLevel() == 0) {
        this.field_145850_b.func_147449_b(x, y, z, BlocksRegister.DEAD_WOOD);
        setEnabled(false);
        return;
      } 
      if (this.timer < 100) {
        this.timer += 10;
      } else {
        if (this.stack.func_77960_j() < 6) {
          this.isModified = true;
          this.usage--;
          if (this.usage < 0)
            this.usage = 0; 
          this.levelClimb++;
          this.stack.func_77964_b(this.stack.func_77960_j() + 1);
          if (this.usage <= 0) {
            ItemStack extractorItemStack = new ItemStack((Block)BlocksRegister.EXTRACTOR);
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74768_a("use", this.usage);
            compound.func_74768_a("maxUse", this.maxUsage);
            extractorItemStack.func_77982_d(compound);
            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return;
          } 
        } 
        this.timer = 0;
        if (wood.getSeveLevel() > 0) {
          wood.setSeveLevel(wood.getSeveLevel() - 1);
          this.field_145850_b.func_147471_g(wood.field_145851_c, wood.field_145848_d, wood.field_145849_e);
        } 
      } 
    } else {
      this.timer = 0;
    } 
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public void func_145841_b(NBTTagCompound tag) {
    super.func_145841_b(tag);
    tag.func_74768_a("angle", this.angle);
    tag.func_74757_a("isModified", this.isModified);
    tag.func_74757_a("isEnabled", this.isEnabled);
    tag.func_74768_a("levelClim", this.levelClimb);
    tag.func_74768_a("usage", this.usage);
    tag.func_74768_a("maxUse", this.maxUsage);
    if (this.stack != null)
      tag.func_74782_a("storedItem", (NBTBase)this.stack.func_77955_b(new NBTTagCompound())); 
  }
  
  public void func_145839_a(NBTTagCompound tag) {
    super.func_145839_a(tag);
    this.angle = tag.func_74762_e("angle");
    this.isModified = tag.func_74767_n("isModified");
    this.isEnabled = tag.func_74767_n("isEnabled");
    this.levelClimb = tag.func_74762_e("levelClim");
    this.usage = tag.func_74762_e("usage");
    this.maxUsage = tag.func_74762_e("maxUse");
    if (tag.func_74764_b("storedItem"))
      this.stack = ItemStack.func_77949_a((NBTTagCompound)tag.func_74781_a("storedItem")); 
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
  
  public void setEnabled(boolean enabled) {
    this.isEnabled = enabled;
  }
  
  public boolean isEnabled() {
    return this.isEnabled;
  }
  
  public void setAngle(int angle) {
    this.angle = angle;
  }
  
  public int getAngle() {
    return this.angle;
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  public void setStack(ItemStack stack) {
    this.stack = stack;
  }
  
  public int getSeveLevel() {
    return this.seveLevel;
  }
  
  public void setSeveLevel(int seveLevel) {
    this.seveLevel = seveLevel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\tileentities\TileEntityExtractor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */