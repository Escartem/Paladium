package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.asm;

import java.util.Iterator;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class EmoteCosmeticClassTransformer implements IClassTransformer {
  public byte[] transform(String name, String transformedName, byte[] basicClass) {
    if ("net.minecraft.client.model.ModelBiped".equals(transformedName)) {
      String funcName = "setRotationAngles";
      String obfName = "a";
      String funcDesc = "(FFFFFFLnet/minecraft/entity/Entity;)V";
      String obfDesc = "(FFFFFFLsa;)V";
      if (EmoteCosmeticLoadingPlugin.runtimeDeobfEnabled)
        funcName = "func_78087_a"; 
      ClassReader reader = new ClassReader(basicClass);
      ClassNode node = new ClassNode();
      reader.accept((ClassVisitor)node, 0);
      for (MethodNode method : node.methods) {
        if ((method.name.equals(funcName) || "a".equals(method.name)) && ("(FFFFFFLnet/minecraft/entity/Entity;)V".equals(method.desc) || "(FFFFFFLsa;)V".equals(method.desc))) {
          Iterator<AbstractInsnNode> iterator = method.instructions.iterator();
          while (iterator.hasNext()) {
            AbstractInsnNode anode = iterator.next();
            if (anode.getOpcode() == 177) {
              InsnList newInstructions = new InsnList();
              newInstructions.add((AbstractInsnNode)new VarInsnNode(25, 7));
              newInstructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
              newInstructions.add((AbstractInsnNode)new MethodInsnNode(184, "fr/paladium/palashop/provider/cosmetic/factory/impl/emote/client/render/world/EmoteCosmeticRenderer", "render", "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/model/ModelBiped;)V"));
              method.instructions.insertBefore(anode, newInstructions);
            } 
          } 
          ClassWriter writer = new ClassWriter(3);
          node.accept((ClassVisitor)writer);
          return writer.toByteArray();
        } 
      } 
    } 
    return basicClass;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\asm\EmoteCosmeticClassTransformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */