package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.events.EventsManager;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class NiGuemeu extends ALuckyEvent {
  public static List<Quest> quests = new ArrayList<>();
  
  private static Random random = new Random();
  
  static {
    quests.add(new Quest("Le fabricant n’en veut pas. L’acheteur ne s’en sert pas. Et l’utilisateur ne le voit pas. Que suis-je ?", "cercueil"));
    quests.add(new Quest("Je suis le seul aliment qu’on met au frigo, mais qui reste toujours chaud. Qui suis-je ?", "piment"));
    quests.add(new Quest("Qu’est-ce qu’on peut écraser avec le pied droit mais pas avec le pied gauche ?", "pied gauche"));
    quests.add(new Quest("Qu'est-ce qui peut remplir une pièce entière sans prendre de place ?", "lumiere"));
    quests.add(new Quest("Je n'ai que deux mots mais des milliers de lettres. Qui suis-je ?", "poste"));
    quests.add(new Quest("Qui a deux aiguilles, mais ne pique pas ?", "montre"));
    quests.add(new Quest("Qu'est-ce qui t'appartient mais que tout le monde utilise ?", "nom"));
    quests.add(new Quest("Je parle sans bouche et j'entends sans oreilles. Je n'ai pas de corps, mais je m'anime grâce au vent. Qui suis-je ?", "echo"));
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    A a;
    EventsManager.currentQuests.put(player.func_110124_au().toString(), a = new A(getRandomQuest(), System.currentTimeMillis() + 600000L));
    ItemStack tomeStack = new ItemStack(Items.field_151164_bB);
    NBTTagList bookPages = new NBTTagList();
    bookPages.func_74742_a((NBTBase)new NBTTagString("Dans ce livre, tu trouveras une énigme. Si tu souhaites y répondre, tu n'as qu'a écrire la réponse sur un panneau. Tu as 10 minutes à partir de maintenant."));
    bookPages.func_74742_a((NBTBase)new NBTTagString("Si tu réponds correctement, tu seras dûment récompensé en revanche si tu te trompes, fait attention à ce qu'il peut se passer"));
    bookPages.func_74742_a((NBTBase)new NBTTagString(a.questToRespond.question));
    tomeStack.func_77983_a("pages", (NBTBase)bookPages);
    tomeStack.func_77983_a("author", (NBTBase)new NBTTagString("???"));
    tomeStack.func_77983_a("title", (NBTBase)new NBTTagString("Une petite énigme ?"));
    tomeStack.func_151001_c(getName());
    if (!player.field_71071_by.func_70441_a(tomeStack))
      player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, tomeStack)); 
  }
  
  private static Quest getRandomQuest() {
    return quests.get(random.nextInt(quests.size()));
  }
  
  public String getName() {
    return "Hey ! Ni guemeu";
  }
  
  public int getRarity() {
    return 75;
  }
  
  public String getTexture() {
    return "hey_ni_guemeu";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public static class A {
    public NiGuemeu.Quest questToRespond;
    
    public long maxTime;
    
    public A(NiGuemeu.Quest questToRespond, long maxTime) {
      this.questToRespond = questToRespond;
      this.maxTime = maxTime;
    }
  }
  
  public static class Quest {
    public String question;
    
    public String answer;
    
    public Quest(String question, String answer) {
      this.question = question;
      this.answer = answer;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\NiGuemeu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */