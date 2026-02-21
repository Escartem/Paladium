package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.objects.ChunkAnalyzerData;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiChunkJobAnalyser extends GuiScreen {
  private final HashMap<Block, ChunkAnalyzerData> jobsBlocks = new HashMap<>();
  
  private int scroll = 0;
  
  public void func_73866_w_() {
    this.field_146292_n.clear();
    int w = this.field_146294_l / 2;
    if (w > 200)
      w = 200; 
    for (AbstractJob job : JobRegistry.getInstance().getJobs()) {
      for (AbstractObjective<?> o : (Iterable<AbstractObjective<?>>)job.getObjectives()) {
        for (Map.Entry<?, ObjectiveValue> entry : (Iterable<Map.Entry<?, ObjectiveValue>>)o.getObjectives().entrySet()) {
          Object key = entry.getKey();
          ObjectiveValue value = entry.getValue();
          if (value.getType() != ObjectiveType.BREAK_BLOCK)
            continue; 
          if (key instanceof ItemStack) {
            Block block = Block.func_149634_a(((ItemStack)key).func_77973_b());
            if (block == null)
              continue; 
            this.jobsBlocks.put(block, ChunkAnalyzerData.builder()
                .jobType(job.getType())
                .objectiveValue(entry.getValue())
                .amount(0)
                .build());
          } 
        } 
      } 
    } 
    for (int x = 0; x < 16; x++) {
      for (int y = 255; y > 0; y--) {
        for (int z = 0; z < 16; z++) {
          int ox = this.field_146297_k.field_71439_g.field_70176_ah * 16;
          int oz = this.field_146297_k.field_71439_g.field_70164_aj * 16;
          Block b = this.field_146297_k.field_71439_g.field_70170_p.func_147439_a(x + ox, y, z + oz);
          if (!b.equals(Blocks.field_150350_a))
            if (this.jobsBlocks.containsKey(b)) {
              ChunkAnalyzerData data = this.jobsBlocks.get(b);
              data.increment();
            }  
        } 
      } 
    } 
    this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - w / 2, this.field_146295_m / 5 * 4 + 20, w, 20, "Nombre d'experience total: " + 
          formatDouble(calculateTotalExperience())));
  }
  
  public double calculateTotalExperience() {
    double total = 0.0D;
    for (ChunkAnalyzerData data : this.jobsBlocks.values()) {
      if (data.getAmount() == 0)
        continue; 
      return data.getAmount() * data.getObjectiveValue().getGivedExperience();
    } 
    return 0.0D;
  }
  
  public double calculateExperience(Block block) {
    if (!this.jobsBlocks.containsKey(block))
      return 0.0D; 
    ChunkAnalyzerData data = this.jobsBlocks.get(block);
    if (data.getAmount() == 0)
      return 0.0D; 
    return data.getAmount() * data.getObjectiveValue().getGivedExperience();
  }
  
  public String formatDouble(double number) {
    DecimalFormat decimalFormat = new DecimalFormat("0.##");
    return decimalFormat.format(number);
  }
  
  protected void func_146284_a(GuiButton button) {
    super.func_146284_a(button);
  }
  
  public void func_73863_a(int mouseX, int mouseY, float ticks) {
    int scale = (new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d)).func_78325_e();
    int decrem = 10;
    GL11.glPushMatrix();
    GuiUtils.drawImageTransparent((this.field_146294_l / 4), (this.field_146295_m / 5), new ResourceLocation("palamod", "textures/gui/LuckyBlock/chunkanalyzer.png"), (this.field_146294_l / 2), (this.field_146295_m / 5 * 3));
    GL11.glEnable(3089);
    GL11.glScissor(this.field_146294_l / 4 * scale, (this.field_146295_m / 5 + 5) * scale, this.field_146294_l / 2 * scale, (this.field_146295_m / 5 * 3 - 10) * scale);
    int y = 0;
    for (Block b : this.jobsBlocks.keySet()) {
      ChunkAnalyzerData data = this.jobsBlocks.get(b);
      if (data.getAmount() == 0)
        continue; 
      func_73731_b(this.field_146297_k.field_71466_p, "x" + ((ChunkAnalyzerData)this.jobsBlocks.get(b)).getAmount() + " | " + calculateExperience(b) + " " + TTT.format(data.getJobType().getName(), new Object[0]), this.field_146294_l / 3, this.field_146295_m / 5 + 10 + y - this.scroll, -1);
      func_73731_b(this.field_146297_k.field_71466_p, b.func_149732_F(), this.field_146294_l / 4 + this.field_146294_l / 2 - 20 - this.field_146297_k.field_71466_p
          .func_78256_a(b.func_149732_F()), this.field_146295_m / 5 + 10 + y - this.scroll, -1);
      try {
        GuiUtils.renderItemStackIntoGUI(new ItemStack(b), (this.field_146294_l / 3 - 18), (this.field_146295_m / 5 + 10 + y - this.scroll - 5));
      } catch (Exception exception) {}
      y += 16;
    } 
    GL11.glDisable(3089);
    GL11.glPopMatrix();
    super.func_73863_a(mouseX, mouseY, ticks);
  }
  
  public void func_146274_d() {
    float s = -Math.signum(Mouse.getEventDWheel());
    this.scroll = (int)(this.scroll + 5.0F * s);
    if (this.field_146295_m / 5 + 10 + this.jobsBlocks.size() * 16 - this.scroll < this.field_146295_m / 5 * 4)
      this.scroll = this.field_146295_m / 5 + 10 + this.jobsBlocks.size() * 16 - this.field_146295_m / 5 * 4; 
    if (this.scroll < 0)
      this.scroll = 0; 
    super.func_146274_d();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\GuiChunkJobAnalyser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */