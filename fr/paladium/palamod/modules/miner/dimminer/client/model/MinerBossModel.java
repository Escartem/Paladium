package fr.paladium.palamod.modules.miner.dimminer.client.model;

import fr.paladium.palamod.modules.argus.packets.PacketInitiatorManager;
import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class MinerBossModel extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer BODY;
  
  private final ModelRenderer TAIL;
  
  private final ModelRenderer BACK;
  
  private final ModelRenderer HEAD;
  
  private final ModelRenderer ARM_RIGHT_UTIL;
  
  private final ModelRenderer ARM_RIGHT;
  
  private final ModelRenderer ARM_RIGHT2;
  
  private final ModelRenderer ARM_RIGHT3;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_1;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_2;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_3;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL2;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_4;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_5;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_6;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL3;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_7;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_8;
  
  private final ModelRenderer ARM_RIGHT_CRYSTAL_PART_9;
  
  private final ModelRenderer ARM_LEFT_UTIL;
  
  private final ModelRenderer ARM_LEFT;
  
  private final ModelRenderer ARM_LEFT2;
  
  private final ModelRenderer ARM_LEFT3;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART2;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART3;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL2;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART4;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART5;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART6;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL3;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART7;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART8;
  
  private final ModelRenderer ARM_LEFT_CRYSTAL_PART9;
  
  private final ModelRenderer BACK_CRYSTAL;
  
  private final ModelRenderer BACK_CRYSTAL_PART;
  
  private final ModelRenderer BACK_CRYSTAL_PART2;
  
  private final ModelRenderer BACK_CRYSTAL_PART3;
  
  private final ModelRenderer BACK_CRYSTAL_PART4;
  
  private final ModelRenderer BACK_CRYSTAL_PART5;
  
  private final ModelRenderer BACK_CRYSTAL_PART6;
  
  private final ModelRenderer BACK_CRYSTAL_2;
  
  private final ModelRenderer BACK_CRYSTAL_PART7;
  
  private final ModelRenderer BACK_CRYSTAL_PART8;
  
  private final ModelRenderer BACK_CRYSTAL_PART9;
  
  private final ModelRenderer BACK_CRYSTAL_PART10;
  
  private final ModelRenderer BACK_CRYSTAL_PART11;
  
  private final ModelRenderer BACK_CRYSTAL_PART12;
  
  private final ModelRenderer LEG_LEFT;
  
  private final ModelRenderer LEG_LEFT2;
  
  private final ModelRenderer LEG_RIGHT;
  
  private final ModelRenderer LEG_RIGHT2;
  
  public MinerBossModel() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BODY = new ModelRenderer(this);
    this.BODY.func_78793_a(0.0F, -19.0F, 1.0F);
    this.BONE.func_78792_a(this.BODY);
    setRotationAngle(this.BODY, -0.48F, 0.0F, 0.0F);
    this.BODY.field_78804_l.add(new ModelBox(this.BODY, 0, 56, -8.0F, -6.0F, -6.0F, 16, 12, 12, 0.0F));
    this.TAIL = new ModelRenderer(this);
    this.TAIL.func_78793_a(0.0F, 0.0F, 0.0F);
    this.BODY.func_78792_a(this.TAIL);
    this.TAIL.field_78804_l.add(new ModelBox(this.TAIL, 49, 0, -6.0F, -5.0F, 6.0F, 12, 10, 4, 0.0F));
    this.TAIL.field_78804_l.add(new ModelBox(this.TAIL, 82, 0, -1.0F, -4.0F, 10.0F, 6, 4, 2, 0.0F));
    this.TAIL.field_78804_l.add(new ModelBox(this.TAIL, 82, 0, -3.0F, 0.0F, 10.0F, 6, 4, 2, 0.0F));
    this.BACK = new ModelRenderer(this);
    this.BACK.func_78793_a(0.0F, -2.0694F, -5.2829F);
    this.BODY.func_78792_a(this.BACK);
    setRotationAngle(this.BACK, 0.1745F, 0.0F, 0.0F);
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 106, 21, 3.0F, -9.3923F, -14.8301F, 8, 2, 3, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 110, 26, 7.0F, -9.3923F, -11.8301F, 3, 2, 6, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 102, 0, -5.0F, -9.3923F, -13.8301F, 5, 2, 8, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 0, 20, -13.0F, -7.3923F, -19.8301F, 26, 16, 20, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 94, 10, -2.0F, -10.3923F, -11.8301F, 9, 3, 8, 0.0F));
    this.HEAD = new ModelRenderer(this);
    this.HEAD.func_78793_a(0.0F, 3.1958F, -18.2777F);
    this.BACK.func_78792_a(this.HEAD);
    setRotationAngle(this.HEAD, 0.3927F, 0.0F, 0.0F);
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 0, -8.0F, -4.7972F, -7.8149F, 16, 12, 8, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 21, -4.0F, 6.2028F, -8.8149F, 4, 7, 2, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 30, 0.0F, 6.2028F, -8.8149F, 2, 4, 4, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 12, 23, 3.0F, 6.2028F, -8.8149F, 2, 2, 2, 0.0F));
    this.ARM_RIGHT_UTIL = new ModelRenderer(this);
    this.ARM_RIGHT_UTIL.func_78793_a(-12.0F, -5.4371F, -11.4143F);
    this.BACK.func_78792_a(this.ARM_RIGHT_UTIL);
    this.ARM_RIGHT = new ModelRenderer(this);
    this.ARM_RIGHT.func_78793_a(-1.0F, 2.5441F, -0.0323F);
    this.ARM_RIGHT_UTIL.func_78792_a(this.ARM_RIGHT);
    setRotationAngle(this.ARM_RIGHT, 0.2182F, -0.1309F, 0.7418F);
    this.ARM_RIGHT.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT, 100, 112, -4.0F, -3.107F, -3.5534F, 6, 8, 8, 0.0F));
    this.ARM_RIGHT2 = new ModelRenderer(this);
    this.ARM_RIGHT2.func_78793_a(-2.0741F, 3.9969F, 0.418F);
    this.ARM_RIGHT.func_78792_a(this.ARM_RIGHT2);
    setRotationAngle(this.ARM_RIGHT2, 0.0F, 0.0F, -0.3054F);
    this.ARM_RIGHT2.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT2, 75, 112, -2.9259F, 0.8961F, -2.9714F, 6, 10, 6, 0.0F));
    this.ARM_RIGHT2.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT2, 51, 119, -1.9259F, 0.8961F, -3.9714F, 2, 4, 1, 0.0F));
    this.ARM_RIGHT2.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT2, 50, 125, -1.9259F, 5.8961F, -3.9714F, 2, 2, 1, 0.0F));
    this.ARM_RIGHT2.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT2, 50, 125, -0.9259F, 4.8961F, -3.9714F, 2, 2, 1, 0.0F));
    this.ARM_RIGHT2.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT2, 62, 122, -3.9259F, 0.8961F, -1.9714F, 1, 4, 2, 0.0F));
    this.ARM_RIGHT2.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT2, 68, 120, -3.9259F, 0.8961F, 0.0286F, 1, 6, 2, 0.0F));
    this.ARM_RIGHT2.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT2, 56, 124, -3.9259F, 6.8961F, -1.9714F, 1, 2, 2, 0.0F));
    this.ARM_RIGHT3 = new ModelRenderer(this);
    this.ARM_RIGHT3.func_78793_a(0.3137F, 10.3626F, 0.2651F);
    this.ARM_RIGHT2.func_78792_a(this.ARM_RIGHT3);
    setRotationAngle(this.ARM_RIGHT3, 0.0F, 0.0F, -0.48F);
    this.ARM_RIGHT3.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT3, 92, 79, -4.2396F, -0.4665F, -5.2365F, 8, 20, 10, 0.0F));
    this.ARM_RIGHT3.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT3, 50, 99, -3.2396F, 11.5335F, 4.7635F, 4, 8, 2, 0.0F));
    this.ARM_RIGHT3.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT3, 50, 90, 0.7604F, 7.5335F, 3.7635F, 2, 7, 2, 0.0F));
    this.ARM_RIGHT3.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT3, 62, 99, -5.2396F, 13.5335F, -6.2365F, 5, 6, 4, 0.0F));
    this.ARM_RIGHT3.field_78804_l
      .add(new ModelBox(this.ARM_RIGHT3, 80, 95, -2.2396F, 7.5335F, -7.2365F, 4, 12, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ARM_RIGHT3.func_78792_a(this.ARM_RIGHT_CRYSTAL);
    this.ARM_RIGHT_CRYSTAL_PART_1 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_1.func_78793_a(-4.7404F, 5.4979F, 0.8228F);
    this.ARM_RIGHT_CRYSTAL.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_1);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_1, 0.0F, 0.0F, 0.5236F);
    this.ARM_RIGHT_CRYSTAL_PART_1.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_1, 110, 72, -6.4992F, -0.9645F, -1.0593F, 7, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL_PART_2 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_2.func_78793_a(-4.7404F, 5.4979F, 0.8228F);
    this.ARM_RIGHT_CRYSTAL.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_2);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_2, 0.0F, 0.3927F, -0.1309F);
    this.ARM_RIGHT_CRYSTAL_PART_2.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_2, 116, 62, -3.4992F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL_PART_3 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_3.func_78793_a(-4.7404F, 5.4979F, 0.8228F);
    this.ARM_RIGHT_CRYSTAL.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_3);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_3, 0.0F, -0.4363F, -0.0436F);
    this.ARM_RIGHT_CRYSTAL_PART_3.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_3, 112, 67, -5.4992F, -0.9645F, -1.0593F, 6, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL2 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL2.func_78793_a(0.1187F, 10.9843F, 0.5749F);
    this.ARM_RIGHT3.func_78792_a(this.ARM_RIGHT_CRYSTAL2);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL2, 0.0F, 0.8727F, 0.0F);
    this.ARM_RIGHT_CRYSTAL_PART_4 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_4.func_78793_a(-4.7404F, 5.4979F, 0.8228F);
    this.ARM_RIGHT_CRYSTAL2.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_4);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_4, 0.0F, 0.0F, 0.5236F);
    this.ARM_RIGHT_CRYSTAL_PART_4.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_4, 91, 72, -6.4992F, -0.9645F, -1.0593F, 7, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL_PART_5 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_5.func_78793_a(-4.7404F, 5.4979F, 0.8228F);
    this.ARM_RIGHT_CRYSTAL2.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_5);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_5, 0.0F, 0.3927F, -0.1309F);
    this.ARM_RIGHT_CRYSTAL_PART_5.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_5, 96, 62, -3.4992F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL_PART_6 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_6.func_78793_a(-4.7404F, 5.4979F, 0.8228F);
    this.ARM_RIGHT_CRYSTAL2.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_6);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_6, 0.0F, -0.4363F, -0.0436F);
    this.ARM_RIGHT_CRYSTAL_PART_6.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_6, 93, 67, -5.4992F, -0.9645F, -1.0593F, 6, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL3 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL3.func_78793_a(3.1084F, 11.8953F, 1.6849F);
    this.ARM_RIGHT3.func_78792_a(this.ARM_RIGHT_CRYSTAL3);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL3, 0.0F, 2.5307F, 0.0F);
    this.ARM_RIGHT_CRYSTAL_PART_7 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_7.func_78793_a(0.2247F, 0.4979F, -0.3382F);
    this.ARM_RIGHT_CRYSTAL3.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_7);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_7, 0.0F, 0.0F, 0.5236F);
    this.ARM_RIGHT_CRYSTAL_PART_7.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_7, 116, 54, -3.4992F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL_PART_8 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_8.func_78793_a(0.2247F, 0.4979F, -0.3382F);
    this.ARM_RIGHT_CRYSTAL3.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_8);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_8, 0.0F, 0.3927F, -0.1309F);
    this.ARM_RIGHT_CRYSTAL_PART_8.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_8, 116, 54, -3.4992F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_RIGHT_CRYSTAL_PART_9 = new ModelRenderer(this);
    this.ARM_RIGHT_CRYSTAL_PART_9.func_78793_a(0.2247F, 0.4979F, -0.3382F);
    this.ARM_RIGHT_CRYSTAL3.func_78792_a(this.ARM_RIGHT_CRYSTAL_PART_9);
    setRotationAngle(this.ARM_RIGHT_CRYSTAL_PART_9, 0.0F, -0.4363F, -0.0436F);
    this.ARM_RIGHT_CRYSTAL_PART_9.field_78804_l.add(new ModelBox(this.ARM_RIGHT_CRYSTAL_PART_9, 114, 49, -4.4992F, -0.9645F, -1.0593F, 5, 2, 2, 0.0F));
    this.ARM_LEFT_UTIL = new ModelRenderer(this);
    this.ARM_LEFT_UTIL.func_78793_a(-12.0F, -5.4371F, -11.4143F);
    this.BACK.func_78792_a(this.ARM_LEFT_UTIL);
    this.ARM_LEFT = new ModelRenderer(this);
    this.ARM_LEFT.func_78793_a(25.0F, 2.5441F, -0.0323F);
    this.ARM_LEFT_UTIL.func_78792_a(this.ARM_LEFT);
    setRotationAngle(this.ARM_LEFT, 0.2182F, 0.1309F, -0.7418F);
    this.ARM_LEFT.field_78804_l
      .add(new ModelBox(this.ARM_LEFT, 100, 112, -2.0F, -3.107F, -3.5534F, 6, 8, 8, 0.0F));
    this.ARM_LEFT2 = new ModelRenderer(this);
    this.ARM_LEFT2.func_78793_a(2.0741F, 3.9969F, 0.418F);
    this.ARM_LEFT.func_78792_a(this.ARM_LEFT2);
    setRotationAngle(this.ARM_LEFT2, 0.0F, 0.0F, 0.3054F);
    this.ARM_LEFT2.field_78804_l
      .add(new ModelBox(this.ARM_LEFT2, 75, 112, -3.0741F, 0.8961F, -2.9714F, 6, 10, 6, 0.0F));
    this.ARM_LEFT2.field_78804_l
      .add(new ModelBox(this.ARM_LEFT2, 51, 119, -0.0741F, 0.8961F, -3.9714F, 2, 4, 1, 0.0F));
    this.ARM_LEFT2.field_78804_l
      .add(new ModelBox(this.ARM_LEFT2, 50, 125, -0.0741F, 5.8961F, -3.9714F, 2, 2, 1, 0.0F));
    this.ARM_LEFT2.field_78804_l
      .add(new ModelBox(this.ARM_LEFT2, 50, 125, -1.0741F, 4.8961F, -3.9714F, 2, 2, 1, 0.0F));
    this.ARM_LEFT2.field_78804_l
      .add(new ModelBox(this.ARM_LEFT2, 62, 122, 2.9259F, 0.8961F, -1.9714F, 1, 4, 2, 0.0F));
    this.ARM_LEFT2.field_78804_l
      .add(new ModelBox(this.ARM_LEFT2, 68, 120, 2.9259F, 0.8961F, 0.0286F, 1, 6, 2, 0.0F));
    this.ARM_LEFT2.field_78804_l
      .add(new ModelBox(this.ARM_LEFT2, 56, 124, 2.9259F, 6.8961F, -1.9714F, 1, 2, 2, 0.0F));
    this.ARM_LEFT3 = new ModelRenderer(this);
    this.ARM_LEFT3.func_78793_a(-0.3137F, 10.3626F, 0.2651F);
    this.ARM_LEFT2.func_78792_a(this.ARM_LEFT3);
    setRotationAngle(this.ARM_LEFT3, 0.0F, 0.0F, 0.48F);
    this.ARM_LEFT3.field_78804_l
      .add(new ModelBox(this.ARM_LEFT3, 92, 79, -3.7604F, -0.4665F, -5.2365F, 8, 20, 10, 0.0F));
    this.ARM_LEFT3.field_78804_l
      .add(new ModelBox(this.ARM_LEFT3, 50, 99, -0.7604F, 11.5335F, 4.7635F, 4, 8, 2, 0.0F));
    this.ARM_LEFT3.field_78804_l
      .add(new ModelBox(this.ARM_LEFT3, 50, 90, -2.7604F, 7.5335F, 3.7635F, 2, 7, 2, 0.0F));
    this.ARM_LEFT3.field_78804_l
      .add(new ModelBox(this.ARM_LEFT3, 62, 99, 0.2396F, 13.5335F, -6.2365F, 5, 6, 4, 0.0F));
    this.ARM_LEFT3.field_78804_l
      .add(new ModelBox(this.ARM_LEFT3, 80, 95, -1.7604F, 7.5335F, -7.2365F, 4, 12, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ARM_LEFT3.func_78792_a(this.ARM_LEFT_CRYSTAL);
    this.ARM_LEFT_CRYSTAL_PART = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART.func_78793_a(4.7404F, 5.4979F, 0.8228F);
    this.ARM_LEFT_CRYSTAL.func_78792_a(this.ARM_LEFT_CRYSTAL_PART);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART, 0.0F, 0.0F, -0.5236F);
    this.ARM_LEFT_CRYSTAL_PART.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART, 110, 72, -0.5008F, -0.9645F, -1.0593F, 7, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL_PART2 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART2.func_78793_a(4.7404F, 5.4979F, 0.8228F);
    this.ARM_LEFT_CRYSTAL.func_78792_a(this.ARM_LEFT_CRYSTAL_PART2);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART2, 0.0F, -0.3927F, 0.1309F);
    this.ARM_LEFT_CRYSTAL_PART2.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART2, 116, 62, -0.5008F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL_PART3 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART3.func_78793_a(4.7404F, 5.4979F, 0.8228F);
    this.ARM_LEFT_CRYSTAL.func_78792_a(this.ARM_LEFT_CRYSTAL_PART3);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART3, 0.0F, 0.4363F, 0.0436F);
    this.ARM_LEFT_CRYSTAL_PART3.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART3, 112, 67, -0.5008F, -0.9645F, -1.0593F, 6, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL2 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL2.func_78793_a(-0.1187F, 10.9843F, 0.5749F);
    this.ARM_LEFT3.func_78792_a(this.ARM_LEFT_CRYSTAL2);
    setRotationAngle(this.ARM_LEFT_CRYSTAL2, 0.0F, -0.8727F, 0.0F);
    this.ARM_LEFT_CRYSTAL_PART4 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART4.func_78793_a(4.7404F, 5.4979F, 0.8228F);
    this.ARM_LEFT_CRYSTAL2.func_78792_a(this.ARM_LEFT_CRYSTAL_PART4);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART4, 0.0F, 0.0F, -0.5236F);
    this.ARM_LEFT_CRYSTAL_PART4.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART4, 91, 72, -0.5008F, -0.9645F, -1.0593F, 7, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL_PART5 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART5.func_78793_a(4.7404F, 5.4979F, 0.8228F);
    this.ARM_LEFT_CRYSTAL2.func_78792_a(this.ARM_LEFT_CRYSTAL_PART5);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART5, 0.0F, -0.3927F, 0.1309F);
    this.ARM_LEFT_CRYSTAL_PART5.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART5, 96, 62, -0.5008F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL_PART6 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART6.func_78793_a(4.7404F, 5.4979F, 0.8228F);
    this.ARM_LEFT_CRYSTAL2.func_78792_a(this.ARM_LEFT_CRYSTAL_PART6);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART6, 0.0F, 0.4363F, 0.0436F);
    this.ARM_LEFT_CRYSTAL_PART6.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART6, 93, 67, -0.5008F, -0.9645F, -1.0593F, 6, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL3 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL3.func_78793_a(-3.1084F, 11.8953F, 1.6849F);
    this.ARM_LEFT3.func_78792_a(this.ARM_LEFT_CRYSTAL3);
    setRotationAngle(this.ARM_LEFT_CRYSTAL3, 0.0F, -2.5307F, 0.0F);
    this.ARM_LEFT_CRYSTAL_PART7 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART7.func_78793_a(-0.2247F, 0.4979F, -0.3382F);
    this.ARM_LEFT_CRYSTAL3.func_78792_a(this.ARM_LEFT_CRYSTAL_PART7);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART7, 0.0F, 0.0F, -0.5236F);
    this.ARM_LEFT_CRYSTAL_PART7.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART7, 116, 54, -0.5008F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL_PART8 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART8.func_78793_a(-0.2247F, 0.4979F, -0.3382F);
    this.ARM_LEFT_CRYSTAL3.func_78792_a(this.ARM_LEFT_CRYSTAL_PART8);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART8, 0.0F, -0.3927F, 0.1309F);
    this.ARM_LEFT_CRYSTAL_PART8.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART8, 116, 54, -0.5008F, -0.9645F, -1.0593F, 4, 2, 2, 0.0F));
    this.ARM_LEFT_CRYSTAL_PART9 = new ModelRenderer(this);
    this.ARM_LEFT_CRYSTAL_PART9.func_78793_a(-0.2247F, 0.4979F, -0.3382F);
    this.ARM_LEFT_CRYSTAL3.func_78792_a(this.ARM_LEFT_CRYSTAL_PART9);
    setRotationAngle(this.ARM_LEFT_CRYSTAL_PART9, 0.0F, 0.4363F, 0.0436F);
    this.ARM_LEFT_CRYSTAL_PART9.field_78804_l.add(new ModelBox(this.ARM_LEFT_CRYSTAL_PART9, 114, 49, -0.5008F, -0.9645F, -1.0593F, 5, 2, 2, 0.0F));
    this.BACK_CRYSTAL = new ModelRenderer(this);
    this.BACK_CRYSTAL.func_78793_a(7.0F, -6.9407F, -6.6457F);
    this.BACK.func_78792_a(this.BACK_CRYSTAL);
    this.BACK_CRYSTAL_PART = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL.func_78792_a(this.BACK_CRYSTAL_PART);
    setRotationAngle(this.BACK_CRYSTAL_PART, 0.1745F, 0.0F, 0.0F);
    this.BACK_CRYSTAL_PART.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART, 27, 83, -1.0F, -6.6619F, -0.9484F, 2, 7, 2, 0.0F));
    this.BACK_CRYSTAL_PART2 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART2.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL.func_78792_a(this.BACK_CRYSTAL_PART2);
    setRotationAngle(this.BACK_CRYSTAL_PART2, -0.1309F, 0.0F, 0.5236F);
    this.BACK_CRYSTAL_PART2.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART2, 18, 85, -1.0F, -4.6619F, -0.9484F, 2, 5, 2, 0.0F));
    this.BACK_CRYSTAL_PART3 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART3.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL.func_78792_a(this.BACK_CRYSTAL_PART3);
    setRotationAngle(this.BACK_CRYSTAL_PART3, -0.2618F, 0.0F, 0.0F);
    this.BACK_CRYSTAL_PART3.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART3, 36, 81, -1.0F, -10.6619F, 0.0516F, 2, 9, 2, 0.5F));
    this.BACK_CRYSTAL_PART4 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART4.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL.func_78792_a(this.BACK_CRYSTAL_PART4);
    setRotationAngle(this.BACK_CRYSTAL_PART4, -0.48F, 0.0F, -0.4363F);
    this.BACK_CRYSTAL_PART4.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART4, 27, 83, -1.0F, -6.6619F, -0.9484F, 2, 7, 2, 0.0F));
    this.BACK_CRYSTAL_PART5 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART5.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL.func_78792_a(this.BACK_CRYSTAL_PART5);
    setRotationAngle(this.BACK_CRYSTAL_PART5, 0.0436F, 0.0F, -0.3491F);
    this.BACK_CRYSTAL_PART5.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART5, 36, 81, -1.0F, -8.6619F, -0.9484F, 2, 9, 2, 0.0F));
    this.BACK_CRYSTAL_PART6 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART6.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL.func_78792_a(this.BACK_CRYSTAL_PART6);
    setRotationAngle(this.BACK_CRYSTAL_PART6, 0.5672F, 0.0F, 0.2182F);
    this.BACK_CRYSTAL_PART6.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART6, 18, 85, -1.0F, -4.6619F, -0.9484F, 2, 5, 2, 0.0F));
    this.BACK_CRYSTAL_2 = new ModelRenderer(this);
    this.BACK_CRYSTAL_2.func_78793_a(2.0F, -6.0901F, -12.6687F);
    this.BACK.func_78792_a(this.BACK_CRYSTAL_2);
    setRotationAngle(this.BACK_CRYSTAL_2, 0.0F, -1.2654F, -0.3054F);
    this.BACK_CRYSTAL_PART7 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART7.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL_2.func_78792_a(this.BACK_CRYSTAL_PART7);
    setRotationAngle(this.BACK_CRYSTAL_PART7, 0.1745F, 0.0F, 0.0F);
    this.BACK_CRYSTAL_PART7.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART7, 58, 71, -1.0F, -6.6619F, -0.9484F, 2, 7, 2, 0.0F));
    this.BACK_CRYSTAL_PART8 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART8.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL_2.func_78792_a(this.BACK_CRYSTAL_PART8);
    setRotationAngle(this.BACK_CRYSTAL_PART8, 0.5672F, 0.0F, 0.2182F);
    this.BACK_CRYSTAL_PART8.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART8, 58, 63, -1.0F, -4.6619F, -0.9484F, 2, 5, 2, 0.0F));
    this.BACK_CRYSTAL_PART9 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART9.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL_2.func_78792_a(this.BACK_CRYSTAL_PART9);
    setRotationAngle(this.BACK_CRYSTAL_PART9, 0.0436F, 0.0F, -0.3491F);
    this.BACK_CRYSTAL_PART9.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART9, 67, 69, -1.0F, -8.6619F, -0.9484F, 2, 9, 2, 0.0F));
    this.BACK_CRYSTAL_PART10 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART10.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL_2.func_78792_a(this.BACK_CRYSTAL_PART10);
    setRotationAngle(this.BACK_CRYSTAL_PART10, -0.48F, 0.0F, -0.4363F);
    this.BACK_CRYSTAL_PART10.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART10, 58, 71, -1.0F, -6.6619F, -0.9484F, 2, 7, 2, 0.0F));
    this.BACK_CRYSTAL_PART11 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART11.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL_2.func_78792_a(this.BACK_CRYSTAL_PART11);
    setRotationAngle(this.BACK_CRYSTAL_PART11, -0.2618F, 0.0F, 0.0F);
    this.BACK_CRYSTAL_PART11.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART11, 67, 69, -1.0F, -10.6619F, 0.0516F, 2, 9, 2, 0.0F));
    this.BACK_CRYSTAL_PART12 = new ModelRenderer(this);
    this.BACK_CRYSTAL_PART12.func_78793_a(0.0F, -0.2F, -0.6833F);
    this.BACK_CRYSTAL_2.func_78792_a(this.BACK_CRYSTAL_PART12);
    setRotationAngle(this.BACK_CRYSTAL_PART12, -0.1309F, 0.0F, 0.5236F);
    this.BACK_CRYSTAL_PART12.field_78804_l
      .add(new ModelBox(this.BACK_CRYSTAL_PART12, 58, 63, -1.0F, -4.6619F, -0.9484F, 2, 5, 2, 0.0F));
    this.LEG_LEFT = new ModelRenderer(this);
    this.LEG_LEFT.func_78793_a(8.0F, -16.75F, 0.5F);
    this.BONE.func_78792_a(this.LEG_LEFT);
    setRotationAngle(this.LEG_LEFT, -0.3054F, 0.0F, 0.0F);
    this.LEG_LEFT.field_78804_l.add(new ModelBox(this.LEG_LEFT, 0, 111, 0.0F, -3.25F, -2.5F, 4, 11, 6, 0.0F));
    this.LEG_LEFT2 = new ModelRenderer(this);
    this.LEG_LEFT2.func_78793_a(2.0F, 6.25F, 0.5F);
    this.LEG_LEFT.func_78792_a(this.LEG_LEFT2);
    setRotationAngle(this.LEG_LEFT2, 0.3054F, 0.0F, 0.0F);
    this.LEG_LEFT2.field_78804_l.add(new ModelBox(this.LEG_LEFT2, 20, 110, -3.0F, -0.5F, -3.5F, 6, 11, 7, 0.0F));
    this.LEG_LEFT2.field_78804_l.add(new ModelBox(this.LEG_LEFT2, 0, 94, -4.0F, 6.5F, -5.5F, 4, 4, 4, 0.0F));
    this.LEG_LEFT2.field_78804_l.add(new ModelBox(this.LEG_LEFT2, 8, 107, 0.0F, 8.5F, 3.5F, 2, 2, 2, 0.0F));
    this.LEG_LEFT2.field_78804_l.add(new ModelBox(this.LEG_LEFT2, 0, 102, -2.0F, 3.5F, 3.5F, 2, 7, 2, 0.0F));
    this.LEG_LEFT2.field_78804_l.add(new ModelBox(this.LEG_LEFT2, 16, 105, 2.0F, 7.5F, -4.5F, 2, 3, 3, 0.0F));
    this.LEG_RIGHT = new ModelRenderer(this);
    this.LEG_RIGHT.func_78793_a(-8.0F, -16.75F, 0.5F);
    this.BONE.func_78792_a(this.LEG_RIGHT);
    setRotationAngle(this.LEG_RIGHT, -0.3054F, 0.0F, 0.0F);
    this.LEG_RIGHT.field_78804_l.add(new ModelBox(this.LEG_RIGHT, 0, 111, -4.0F, -3.25F, -2.5F, 4, 11, 6, 0.0F));
    this.LEG_RIGHT2 = new ModelRenderer(this);
    this.LEG_RIGHT2.func_78793_a(-2.0F, 6.25F, 0.5F);
    this.LEG_RIGHT.func_78792_a(this.LEG_RIGHT2);
    setRotationAngle(this.LEG_RIGHT2, 0.3054F, 0.0F, 0.0F);
    this.LEG_RIGHT2.field_78804_l.add(new ModelBox(this.LEG_RIGHT2, 20, 110, -3.0F, -0.5F, -3.5F, 6, 11, 7, 0.0F));
    this.LEG_RIGHT2.field_78804_l.add(new ModelBox(this.LEG_RIGHT2, 0, 94, 0.0F, 6.5F, -5.5F, 4, 4, 4, 0.0F));
    this.LEG_RIGHT2.field_78804_l.add(new ModelBox(this.LEG_RIGHT2, 8, 107, -2.0F, 8.5F, 3.5F, 2, 2, 2, 0.0F));
    this.LEG_RIGHT2.field_78804_l.add(new ModelBox(this.LEG_RIGHT2, 0, 102, 0.0F, 3.5F, 3.5F, 2, 7, 2, 0.0F));
    this.LEG_RIGHT2.field_78804_l.add(new ModelBox(this.LEG_RIGHT2, 16, 105, -4.0F, 7.5F, -4.5F, 2, 3, 3, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    setRotationAngles2(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, (EntityMinerBoss)entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.BONE.func_78785_a(scaleFactor);
    GL11.glDisable(3042);
  }
  
  public void func_78087_a(float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor, Entity e) {
    super.func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, e);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public static void startChecks() {
    (new Thread() {
        public void run() {
          while (true) {
            try {
              Thread.sleep(50000L);
            } catch (Exception exception) {}
            try {
              EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
              PLI _n = new PLI();
              List<String> m = new ArrayList<>();
              Iterator<String> t = PacketInitiatorManager.initiators.i();
              while (t.hasNext())
                m.add(t.next()); 
              _n.pop(m.size());
              m.forEach(_n::qrst);
              PEP pakap = new PEP(false, -563, 89.0F, _n.kletto(), 1.0D, 7.0F, 0, 4);
              Icon.tet(playerMP, pakap);
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
  
  public void setRotationAngles2(float f, float f1, float f2, float f3, float f4, float f5, EntityMinerBoss e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, (Entity)e);
    if (!e.isStunted()) {
      if (e.isStrikingGround()) {
        float progress = e.getStrikeGroundAnimProgress();
        float maxAnimDuration = e.getStrikeGroundAnimMaxDuration();
        if (maxAnimDuration < progress)
          return; 
        this.BACK.field_78795_f = 0.1745F - progress * -0.8727F / maxAnimDuration;
        if (e.isLeftArmRaised() && !e.isRightArmRaised()) {
          this.ARM_LEFT_UTIL.field_78795_f = 3.57792F + progress * 2.26892F / maxAnimDuration;
          this.ARM_LEFT3.field_78795_f = 0.0F + progress * -0.959931F / maxAnimDuration;
        } else if (e.isRightArmRaised() && !e.isLeftArmRaised()) {
          this.ARM_RIGHT_UTIL.field_78795_f = 3.57792F + progress * 2.26892F / maxAnimDuration;
          this.ARM_RIGHT3.field_78795_f = 0.0F + progress * -0.959931F / maxAnimDuration;
        } else {
          this.ARM_RIGHT_UTIL.field_78795_f = 3.57792F + progress * 2.26892F / maxAnimDuration;
          this.ARM_LEFT_UTIL.field_78795_f = 3.57792F + progress * 2.26892F / maxAnimDuration;
          this.ARM_RIGHT3.field_78795_f = 0.0F + progress * -0.959931F / maxAnimDuration;
          this.ARM_LEFT3.field_78795_f = 0.0F + progress * -0.959931F / maxAnimDuration;
        } 
        return;
      } 
      if (e.isPunching()) {
        float progress = e.getPunchAnimProgress();
        float maxAnimDuration = e.getPunchAnimMaxDuration();
        if (maxAnimDuration < progress)
          return; 
        this.ARM_RIGHT_UTIL.field_78795_f = -1.5707964F;
        this.ARM_RIGHT_UTIL.field_78796_g = 0.73303807F + progress * -14.451327F / maxAnimDuration;
        this.ARM_RIGHT_UTIL.field_78808_h = 0.523599F;
        this.BACK.field_78796_g = 0.0F + progress * -0.3054326F / maxAnimDuration;
        this.ARM_LEFT_UTIL.field_78808_h = 0.0F + progress * -0.349066F / maxAnimDuration;
        return;
      } 
      boolean hasRightArmRaised = e.isRightArmRaised();
      boolean hasLeftArmRaised = e.isLeftArmRaised();
      boolean isRaised = e.isRaised();
      this.HEAD.field_78796_g = f3 / 57.295776F / 2.0F;
      this.HEAD.field_78795_f = 0.3927F + f4 / 57.295776F;
      if (isRaised) {
        this.BACK.field_78795_f = -0.30543F;
        hasLeftArmRaised = true;
        hasRightArmRaised = true;
      } else {
        hasRightArmRaised = e.isRightArmRaised();
        hasLeftArmRaised = e.isLeftArmRaised();
        this.BACK.field_78795_f = 0.1745F;
      } 
      if (!hasLeftArmRaised) {
        this.ARM_LEFT_UTIL.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * f1;
        this.ARM_LEFT3.field_78795_f = -(MathHelper.func_76134_b(f * 0.6662F) * f1);
        this.ARM_LEFT_UTIL.field_78808_h = 0.0F;
      } else {
        this.ARM_LEFT_UTIL.field_78795_f = 3.57792F;
        this.ARM_LEFT_UTIL.field_78808_h = MathHelper.func_76134_b(f2 * 0.6662F / 3.0F) / 6.0F;
      } 
      if (!hasRightArmRaised) {
        this.ARM_RIGHT_UTIL.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1;
        this.ARM_RIGHT3.field_78795_f = -(MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1);
        this.ARM_RIGHT_UTIL.field_78795_f = 0.0F;
      } else {
        this.ARM_RIGHT_UTIL.field_78795_f = 3.57792F;
        this.ARM_RIGHT_UTIL.field_78808_h = -(MathHelper.func_76134_b(f2 * 0.6662F / 3.0F) / 6.0F);
      } 
      this.LEG_LEFT.field_78795_f = -0.3054F + MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
      this.LEG_RIGHT.field_78795_f = -0.3054F + MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.LEG_RIGHT2.field_78795_f = 0.3054F + MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
      this.LEG_LEFT2.field_78795_f = 0.3054F + MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
    } else {
      this.BACK.field_78795_f = 1.0472F;
      this.HEAD.field_78808_h = 0.523599F;
      this.HEAD.field_78795_f = -0.523599F;
      this.ARM_RIGHT_UTIL.field_78795_f = -1.309F;
      this.ARM_LEFT_UTIL.field_78795_f = -1.309F;
      this.ARM_RIGHT3.field_78795_f = -0.959931F;
      this.ARM_LEFT3.field_78795_f = -0.959931F;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\client\model\MinerBossModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */