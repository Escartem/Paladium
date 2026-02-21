package fr.paladium.palamod.modules.back2future.client.gui.inventory;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.inventory.ContainerEnchantment;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.util.glu.Project;

@SideOnly(Side.CLIENT)
public class GuiEnchantment extends GuiContainer {
  private static final ResourceLocation field_147078_C = Utils.getResource("palamod:textures/gui/container/enchanting_table.png");
  
  private static final ResourceLocation field_147070_D = Utils.getResource("textures/entity/enchanting_table_book.png");
  
  private static final ModelBook field_147072_E = new ModelBook();
  
  private final InventoryPlayer field_175379_F;
  
  private final Random field_147074_F = new Random();
  
  private final ContainerEnchantment field_147075_G;
  
  public int field_147073_u;
  
  public float field_147071_v;
  
  public float field_147069_w;
  
  public float field_147082_x;
  
  public float field_147081_y;
  
  public float field_147080_z;
  
  public float field_147076_A;
  
  ItemStack field_147077_B;
  
  private final String field_175380_I;
  
  public GuiEnchantment(InventoryPlayer p_i45502_1_, World worldIn, String p_i45502_3_) {
    super((Container)new ContainerEnchantment(p_i45502_1_, worldIn, 0, 0, 0));
    this.field_175379_F = p_i45502_1_;
    this.field_147075_G = (ContainerEnchantment)this.field_147002_h;
    this.field_175380_I = p_i45502_3_;
  }
  
  protected void func_146979_b(int mouseX, int mouseY) {
    this.field_146289_q.func_78276_b((this.field_175380_I == null) ? 
        I18n.func_135052_a("container.enchant", new Object[0]) : this.field_175380_I, 12, 5, 4210752);
    this.field_146289_q.func_78276_b(I18n.func_135052_a(this.field_175379_F.func_145825_b(), new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
  }
  
  public void func_73876_c() {
    super.func_73876_c();
    func_147068_g();
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
    super.func_73864_a(mouseX, mouseY, mouseButton);
    int var4 = (this.field_146294_l - this.field_146999_f) / 2;
    int var5 = (this.field_146295_m - this.field_147000_g) / 2;
    for (int var6 = 0; var6 < 3; var6++) {
      int var7 = mouseX - var4 + 60;
      int var8 = mouseY - var5 + 14 + 19 * var6;
      if (var7 >= 0 && var8 >= 0 && var7 < 108 && var8 < 19 && this.field_147075_G
        .func_75140_a((EntityPlayer)this.field_146297_k.field_71439_g, var6))
        this.field_146297_k.field_71442_b.func_78756_a(this.field_147075_G.field_75152_c, var6); 
    } 
  }
  
  protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
    OpenGLHelper.colour(1.0F, 1.0F, 1.0F);
    this.field_146297_k.func_110434_K().func_110577_a(field_147078_C);
    int k = (this.field_146294_l - this.field_146999_f) / 2;
    int l = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    OpenGLHelper.pushMatrix();
    OpenGLHelper.matrixMode(5889);
    OpenGLHelper.pushMatrix();
    OpenGLHelper.loadIdentity();
    ScaledResolution var6 = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
    OpenGLHelper.viewport((var6.func_78326_a() - 320) / 2 * var6.func_78325_e(), (var6
        .func_78328_b() - 240) / 2 * var6.func_78325_e(), 320 * var6.func_78325_e(), 240 * var6
        .func_78325_e());
    OpenGLHelper.translate(-0.34F, 0.23F, 0.0F);
    Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
    float var7 = 1.0F;
    OpenGLHelper.matrixMode(5888);
    OpenGLHelper.loadIdentity();
    RenderHelper.func_74519_b();
    OpenGLHelper.translate(0.0F, 3.3F, -16.0F);
    OpenGLHelper.scale(var7, var7, var7);
    float var8 = 5.0F;
    OpenGLHelper.scale(var8, var8, var8);
    OpenGLHelper.rotate(180.0F, 0.0F, 0.0F, 1.0F);
    this.field_146297_k.func_110434_K().func_110577_a(field_147070_D);
    OpenGLHelper.rotate(20.0F, 1.0F, 0.0F, 0.0F);
    float var9 = this.field_147076_A + (this.field_147080_z - this.field_147076_A) * partialTicks;
    OpenGLHelper.translate((1.0F - var9) * 0.2F, (1.0F - var9) * 0.1F, (1.0F - var9) * 0.25F);
    OpenGLHelper.rotate(-(1.0F - var9) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
    OpenGLHelper.rotate(180.0F, 1.0F, 0.0F, 0.0F);
    float var10 = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * partialTicks + 0.25F;
    float var11 = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * partialTicks + 0.75F;
    var10 = (var10 - MathHelper.func_76140_b(var10)) * 1.6F - 0.3F;
    var11 = (var11 - MathHelper.func_76140_b(var11)) * 1.6F - 0.3F;
    if (var10 < 0.0F)
      var10 = 0.0F; 
    if (var11 < 0.0F)
      var11 = 0.0F; 
    if (var10 > 1.0F)
      var10 = 1.0F; 
    if (var11 > 1.0F)
      var11 = 1.0F; 
    OpenGLHelper.enableRescaleNormal();
    field_147072_E.func_78088_a((Entity)null, 0.0F, var10, var11, var9, 0.0F, 0.0625F);
    OpenGLHelper.disableRescaleNormal();
    RenderHelper.func_74518_a();
    OpenGLHelper.matrixMode(5889);
    OpenGLHelper.viewport(0, 0, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
    OpenGLHelper.popMatrix();
    OpenGLHelper.matrixMode(5888);
    OpenGLHelper.popMatrix();
    RenderHelper.func_74518_a();
    OpenGLHelper.colour(1.0F, 1.0F, 1.0F);
    EnchantmentNameParts.field_148338_a.func_148335_a(this.field_147075_G.enchantmentSeed);
    int var12 = this.field_147075_G.func_178147_e();
    for (int i1 = 0; i1 < 3; i1++) {
      int var14 = k + 60;
      int var15 = var14 + 20;
      byte var16 = 86;
      String s = EnchantmentNameParts.field_148338_a.func_148334_a();
      this.field_73735_i = 0.0F;
      this.field_146297_k.func_110434_K().func_110577_a(field_147078_C);
      int j1 = this.field_147075_G.enchantLevels[i1];
      OpenGLHelper.colour(1.0F, 1.0F, 1.0F);
      if (j1 == 0) {
        func_73729_b(var14, l + 14 + 19 * i1, 0, 185, 108, 19);
      } else {
        String s1 = "" + j1;
        FontRenderer fontrenderer = this.field_146297_k.field_71464_q;
        int k1 = 6839882;
        if ((var12 < i1 + 1 || this.field_146297_k.field_71439_g.field_71068_ca < j1) && !this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d) {
          func_73729_b(var14, l + 14 + 19 * i1, 0, 185, 108, 19);
          func_73729_b(var14 + 1, l + 15 + 19 * i1, 16 * i1, 239, 16, 16);
          fontrenderer.func_78279_b(s, var15, l + 16 + 19 * i1, var16, (k1 & 0xFEFEFE) >> 1);
          k1 = 4226832;
        } else {
          int l1 = mouseX - k + 60;
          int i2 = mouseY - l + 14 + 19 * i1;
          if (l1 >= 0 && i2 >= 0 && l1 < 108 && i2 < 19) {
            func_73729_b(var14, l + 14 + 19 * i1, 0, 204, 108, 19);
            k1 = 16777088;
          } else {
            func_73729_b(var14, l + 14 + 19 * i1, 0, 166, 108, 19);
          } 
          func_73729_b(var14 + 1, l + 15 + 19 * i1, 16 * i1, 223, 16, 16);
          fontrenderer.func_78279_b(s, var15, l + 16 + 19 * i1, var16, k1);
          k1 = 8453920;
        } 
        fontrenderer = this.field_146297_k.field_71466_p;
        fontrenderer.func_78261_a(s1, var15 + 86 - fontrenderer.func_78256_a(s1), l + 16 + 19 * i1 + 7, k1);
        OpenGLHelper.colour(1.0F, 1.0F, 1.0F);
      } 
    } 
  }
  
  public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
    super.func_73863_a(mouseX, mouseY, partialTicks);
    boolean var4 = this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d;
    int var5 = this.field_147075_G.func_178147_e();
    for (int var6 = 0; var6 < 3; var6++) {
      int var7 = this.field_147075_G.enchantLevels[var6];
      int var8 = this.field_147075_G.field_178151_h[var6];
      int var9 = var6 + 1;
      if (func_146978_c(60, 14 + 19 * var6, 108, 17, mouseX, mouseY) && var7 > 0 && var8 >= 0) {
        ArrayList<Object> var10 = Lists.newArrayList();
        if (var8 >= 0 && Enchantment.field_77331_b[var8 & 0xFF] != null) {
          String var11 = Enchantment.field_77331_b[var8 & 0xFF].func_77316_c((var8 & 0xFF00) >> 8);
          var10.add(EnumChatFormatting.WHITE.toString() + EnumChatFormatting.ITALIC.toString() + 
              I18n.func_135052_a("container.enchant.clue", new Object[] { var11 }));
        } 
        if (!var4) {
          if (var8 >= 0)
            var10.add(""); 
          if (this.field_146297_k.field_71439_g.field_71068_ca < var7) {
            var10.add(EnumChatFormatting.RED.toString() + 
                I18n.func_135052_a("container.enchant.level.required", new Object[0]) + ": " + this.field_147075_G.enchantLevels[var6]);
          } else {
            String var11 = "";
            if (var9 == 1) {
              var11 = I18n.func_135052_a("container.enchant.lapis.one", new Object[0]);
            } else {
              var11 = I18n.func_135052_a("container.enchant.lapis.many", new Object[] { Integer.valueOf(var9) });
            } 
            if (var5 >= var9) {
              var10.add(EnumChatFormatting.GRAY.toString() + "" + var11);
            } else {
              var10.add(EnumChatFormatting.RED.toString() + "" + var11);
            } 
            if (var9 == 1) {
              var11 = I18n.func_135052_a("container.enchant.level.one", new Object[0]);
            } else {
              var11 = I18n.func_135052_a("container.enchant.level.many", new Object[] { Integer.valueOf(var9) });
            } 
            var10.add(EnumChatFormatting.GRAY.toString() + "" + var11);
          } 
        } 
        drawHoveringText(var10, mouseX, mouseY, this.field_146289_q);
        break;
      } 
    } 
  }
  
  public void func_147068_g() {
    ItemStack var1 = this.field_147002_h.func_75139_a(0).func_75211_c();
    if (!ItemStack.func_77989_b(var1, this.field_147077_B)) {
      this.field_147077_B = var1;
      do {
        this.field_147082_x += (this.field_147074_F.nextInt(4) - this.field_147074_F.nextInt(4));
      } while (this.field_147071_v <= this.field_147082_x + 1.0F && this.field_147071_v >= this.field_147082_x - 1.0F);
    } 
    this.field_147073_u++;
    this.field_147069_w = this.field_147071_v;
    this.field_147076_A = this.field_147080_z;
    boolean var2 = false;
    for (int var3 = 0; var3 < 3; var3++) {
      if (this.field_147075_G.enchantLevels[var3] != 0)
        var2 = true; 
    } 
    if (var2) {
      this.field_147080_z += 0.2F;
    } else {
      this.field_147080_z -= 0.2F;
    } 
    this.field_147080_z = MathHelper.func_76131_a(this.field_147080_z, 0.0F, 1.0F);
    float var5 = (this.field_147082_x - this.field_147071_v) * 0.4F;
    float var4 = 0.2F;
    var5 = MathHelper.func_76131_a(var5, -var4, var4);
    this.field_147081_y += (var5 - this.field_147081_y) * 0.9F;
    this.field_147071_v += this.field_147081_y;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\gui\inventory\GuiEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */