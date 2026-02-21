package fr.paladium.pet.server.commands.skill;

import fr.paladium.palaconfiguration.server.utils.ConfigurationModUtils;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.FreeSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.NumberSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palaforgeutils.lib.subcommand.utils.SubCommandUtils;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.skill.Skill;
import fr.paladium.pet.server.skill.skill.SkillType;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SkillSubCommand extends ASubCommand {
  public static final String NAME = "skill";
  
  public static final String PERMISSION = "palapet.command.skill";
  
  public static final String SKILL_ARG_NAME = "(skill)";
  
  private static SkillSubCommand instance;
  
  private String[] skillNames;
  
  public static SkillSubCommand getInstance() {
    return instance;
  }
  
  public SkillSubCommand() {
    instance = this;
    this.skillNames = new String[0];
    ABaseSubCommand aBaseSubCommand1 = StringSubCommand.create("equip").build(this);
    ABaseSubCommand aBaseSubCommand2 = FreeSubCommand.create("(skill)", "", this.skillNames).build((ASubCommand)aBaseSubCommand1);
    ABaseSubCommand aBaseSubCommand3 = NumberSubCommand.create("(slot)", "Définir un skill (slot)").build((ASubCommand)aBaseSubCommand2, equip());
    aBaseSubCommand3.getBuilder().sender(new SenderType[] { SenderType.PLAYER });
    ABaseSubCommand aBaseSubCommand4 = StringSubCommand.create("use").build(this);
    ABaseSubCommand aBaseSubCommand5 = NumberSubCommand.create("(slot)", "Utiliser un skill (slot)").build((ASubCommand)aBaseSubCommand4, use());
    aBaseSubCommand5.getBuilder().sender(new SenderType[] { SenderType.PLAYER });
    ABaseSubCommand aBaseSubCommand6 = StringSubCommand.create("use-name").build(this);
    ABaseSubCommand aBaseSubCommand7 = FreeSubCommand.create("(skill)", "", this.skillNames).build((ASubCommand)aBaseSubCommand6, useName());
    aBaseSubCommand7.getBuilder().sender(new SenderType[] { SenderType.PLAYER });
    StringSubCommand.create("list", "Liste des missions")
      .build(this, list());
    ABaseSubCommand aBaseSubCommand8 = StringSubCommand.create("info").build(this);
    FreeSubCommand.create("(skill)", "informations d'un skill", this.skillNames)
      .build((ASubCommand)aBaseSubCommand8, name());
  }
  
  private ISubCallback name() {
    return (sender, data) -> {
        SkillConfig config = SkillConfig.get();
        String id = data.getFreeArg();
        Optional<Skill> result = config.findSkillById(id);
        if (!result.isPresent()) {
          PetTranslateEnum.MESSAGE_SKILL_NO_FOUND.message(sender, new Object[] { id });
          return true;
        } 
        Skill skill = result.get();
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        ConfigurationModUtils.sendJsonObject(sender, skill);
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        return true;
      };
  }
  
  private ISubCallback list() {
    return (sender, data) -> {
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        SkillConfig config = SkillConfig.get();
        ChatUtils.sendColoredMessage(sender, new String[] { "§6Liste des skills" });
        for (String skillName : this.skillNames) {
          Optional<Skill> result = config.findSkillById(skillName);
          String prefix = "";
          if (result.isPresent()) {
            Skill skill = result.get();
            prefix = (skill.getType() == SkillType.ACTIVE) ? "§7(§aACTIF§7)" : "§7(§cPASSIF§7)";
          } 
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- " + prefix + " §e" + skillName });
        } 
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        return true;
      };
  }
  
  private ISubCallback useName() {
    return (sender, data) -> {
        EntityPlayerMP player = (EntityPlayerMP)sender;
        PetPlayer pet = PetPlayer.get((EntityPlayer)player);
        String skillId = data.getFreeArg();
        SkillConfig config = SkillConfig.get();
        Optional<Skill> result = config.findSkillById(skillId);
        if (!result.isPresent()) {
          PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
          return true;
        } 
        Skill skill = result.get();
        if (skill.getType() != SkillType.ACTIVE) {
          PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
          return true;
        } 
        SkillData fakeData = new SkillData(skillId, 0L, 0L);
        skill.handle(player, pet, fakeData);
        return true;
      };
  }
  
  private ISubCallback use() {
    return (sender, data) -> {
        EntityPlayerMP player = (EntityPlayerMP)sender;
        PetPlayer pet = PetPlayer.get((EntityPlayer)player);
        int slot = data.getInteger();
        SkillData skillData = pet.getSkill(slot);
        if (skillData == null) {
          PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
          return true;
        } 
        SkillConfig config = SkillConfig.get();
        Optional<Skill> result = config.findSkillById(skillData.getSkillId());
        if (!result.isPresent()) {
          PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
          return true;
        } 
        Skill skill = result.get();
        if (skill.getType() != SkillType.ACTIVE) {
          PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
          return true;
        } 
        skill.handle(player, pet, skillData);
        pet.setLastSkillUsage(System.currentTimeMillis());
        pet.sync();
        return true;
      };
  }
  
  private ISubCallback equip() {
    return (sender, data) -> {
        EntityPlayerMP player = (EntityPlayerMP)sender;
        PetPlayer pet = PetPlayer.get((EntityPlayer)player);
        String skillId = data.getFreeArg();
        int slot = data.getInteger();
        SkillData skillData = pet.getSkill(slot);
        if (skillData == null || skillId == null) {
          PetTranslateEnum.MESSAGE_NO_SLOT.message((ICommandSender)player);
          return true;
        } 
        SkillConfig config = SkillConfig.get();
        Optional<Skill> result = config.findSkillById(skillId);
        if (!result.isPresent()) {
          PetTranslateEnum.MESSAGE_NO_SKILL.message((ICommandSender)player);
          return true;
        } 
        Skill skill = result.get();
        skillData.changeSlotBypass(skill);
        PetTranslateEnum.MESSAGE_CHANGE_SKILL.message((ICommandSender)player, new Object[] { Integer.valueOf(slot), skill.getName((EntityPlayer)player) });
        return true;
      };
  }
  
  public void updateSkills(String[] skills) {
    this.skillNames = skills;
    for (ASubCommand sub : SubCommandUtils.extractSubCommands(this)) {
      if (sub.func_71517_b().equalsIgnoreCase("(skill)"))
        sub.getBuilder().freeArgument(skills); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\skill\SkillSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */