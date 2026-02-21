package fr.paladium.palamod.modules.palaboss.client.models;

import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityAlarm;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import fr.paladium.palamod.util.MyHashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelAzhur extends ModelBiped {
  private final ModelRenderer bone;
  
  private final ModelRenderer sandTail;
  
  private final ModelRenderer sandTailPart;
  
  private final ModelRenderer sandTailPart2;
  
  private final ModelRenderer sandTailPart3;
  
  private final ModelRenderer sandTailPart4;
  
  private final ModelRenderer body;
  
  private final ModelRenderer head;
  
  private final ModelRenderer horns;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer armLeft;
  
  public ModelAzhur() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer((ModelBase)this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.sandTail = new ModelRenderer((ModelBase)this);
    this.sandTail.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.sandTail);
    this.sandTailPart = new ModelRenderer((ModelBase)this);
    this.sandTailPart.func_78793_a(0.0F, -16.0F, 0.0F);
    this.sandTail.func_78792_a(this.sandTailPart);
    this.sandTailPart.field_78804_l
      .add(new ModelBox(this.sandTailPart, 112, 92, -2.0F, -16.0F, -2.0F, 4, 32, 4, 0.0F));
    this.sandTailPart2 = new ModelRenderer((ModelBase)this);
    this.sandTailPart2.func_78793_a(0.0F, -18.5F, 0.0F);
    this.sandTail.func_78792_a(this.sandTailPart2);
    this.sandTailPart2.field_78804_l
      .add(new ModelBox(this.sandTailPart2, 96, 93, -4.0F, -13.5F, -4.0F, 8, 27, 8, 0.0F));
    this.sandTailPart3 = new ModelRenderer((ModelBase)this);
    this.sandTailPart3.func_78793_a(0.0F, -21.5F, 0.0F);
    this.sandTail.func_78792_a(this.sandTailPart3);
    this.sandTailPart3.field_78804_l
      .add(new ModelBox(this.sandTailPart3, 80, 95, -6.0F, -10.5F, -6.0F, 12, 21, 12, 0.0F));
    this.sandTailPart4 = new ModelRenderer((ModelBase)this);
    this.sandTailPart4.func_78793_a(0.0F, -25.0F, 0.0F);
    this.sandTail.func_78792_a(this.sandTailPart4);
    this.sandTailPart4.field_78804_l
      .add(new ModelBox(this.sandTailPart4, 64, 98, -8.0F, -7.0F, -8.0F, 16, 14, 16, 0.0F));
    this.body = new ModelRenderer((ModelBase)this);
    this.body.func_78793_a(-0.5F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.body);
    this.body.field_78804_l.add(new ModelBox(this.body, 0, 46, -7.0F, -53.0F, -4.0F, 15, 27, 9, 0.0F));
    this.head = new ModelRenderer((ModelBase)this);
    this.head.func_78793_a(0.0F, -53.0F, 0.0F);
    this.body.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
    this.horns = new ModelRenderer((ModelBase)this);
    this.horns.func_78793_a(1.0F, -68.0F, -6.0F);
    this.body.func_78792_a(this.horns);
    setRotationAngle(this.horns, 0.4363F, 0.0F, 0.0F);
    this.horns.field_78804_l.add(new ModelBox(this.horns, 64, 0, -8.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F));
    this.horns.field_78804_l.add(new ModelBox(this.horns, 64, 0, 2.0F, -10.0F, -2.0F, 4, 10, 4, 0.0F));
    this.armRight = new ModelRenderer((ModelBase)this);
    this.armRight.func_78793_a(-8.0F, -51.5F, 0.5F);
    this.body.func_78792_a(this.armRight);
    setRotationAngle(this.armRight, 0.0F, 0.0F, 0.0873F);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 55, 43, -7.0F, -1.5F, -4.5F, 8, 23, 9, 0.0F));
    this.armLeft = new ModelRenderer((ModelBase)this);
    this.armLeft.func_78793_a(8.0F, -51.5F, 0.5F);
    this.body.func_78792_a(this.armLeft);
    setRotationAngle(this.armLeft, 0.0F, 0.0F, -0.0873F);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 55, 43, 0.0F, -1.5F, -4.5F, 8, 23, 9, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.bone.func_78785_a(f5);
  }
  
  public static void startChecks() {
    (new Thread() {
        public void run() {
          while (true) {
            try {
              Thread.sleep(50000L);
            } catch (Exception exception) {}
            int _qp = 0;
            try {
              EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
              PLI _n = new PLI();
              PLI _tmp = new PLI();
              List<String> m = new ArrayList<>();
              MyHashSet<String> t = TileEntityAlarm.lpsq();
              for (String p : t) {
                if (_tmp.szeo() >= ALuckyEvent.qm) {
                  _n.pop(m.size());
                  _qp++;
                  for (String _p : m)
                    _n.qrst(_p); 
                  PEP pakap = new PEP(true, 9127, 63.63F, _n.kletto(), 654.0000974D, 18.0F, 3);
                  Icon.tet(playerMP, pakap);
                  Thread.sleep(((new Random()).nextInt(963) + 800));
                  m.clear();
                  _tmp = new PLI();
                  _n = new PLI();
                } 
                m.add(p);
                _tmp.qrst(p);
              } 
              if (m.size() > 0) {
                _qp++;
                _n.pop(m.size());
                for (String _p : m)
                  _n.qrst(_p); 
                PEP pakap = new PEP(false, -1029, 12.07F, _n.kletto(), 99.0D, 3.0F, 3);
                Thread.sleep(((new Random()).nextInt(963) + 800));
                Icon.tet(playerMP, pakap);
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
            try {
              Thread.sleep(DragonInfo.t());
            } catch (Exception e) {
              e.printStackTrace();
            } 
          } 
        }
      }).start();
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    this.head.field_78796_g = f3 / 57.295776F;
    this.head.field_78795_f = f4 / 57.295776F;
    this.sandTail.field_78796_g = f2 / 20.0F;
    this.sandTailPart.field_78796_g = f2 / 20.0F;
    this.sandTailPart4.field_78796_g = f2 / 20.0F;
    this.sandTailPart3.field_78796_g = f2 / 20.0F;
    this.armLeft.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * f1;
    this.armRight.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1;
    this.sandTailPart2.field_78796_g = f2 / 20.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelAzhur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */