package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.ariane.Ariane;
import fr.paladium.ariane.lib.dialog.ArianeDialog;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenCustomDialogGui;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class XavierDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Marin Xavier";
  
  public static final List<Lyric> LYRICS = Arrays.asList(new Lyric[] { Lyric.builder()
        .key("Bien le bonjour chère ménestrel. Chante donc avec moi et complète les paroles de cette célébrissime chanson de marin ! Pipe et jambe de bois ! C'était un très vieux grand-père Pipe en bois, pipe en terre Un valeureux grand-papa")
        
        .value("Pipe et jambe de bois")
        .build(), 
        Lyric.builder()
        .key("Il vivait en solitaire")
        .value("Pipe en bois, pipe en terre")
        .build(), 
        Lyric.builder()
        .key("Dans une baraque sans toit Pipe et jambe de bois Un jour, une jolie grand-mère Tasse de thé, p'tite cuillère Qui s'était perdue par là Crème et chocolat Découvrit dans la clairière")
        
        .value("Tasse de thé, p'tite cuillère")
        .build(), 
        Lyric.builder()
        .key("La vieille maison de bois Crème et chocolat Elle entra dans la chaumière Tasse de thé, p'tite cuillère Et puis elle s'y installa")
        
        .value("Crème et chocolat")
        .build() });
  
  private static final String FINISH_MESSAGE = "Tu chantes comme un vrai troubadour ! Mes camarades marins necomprennent pas la beauté de ces mots endiablés. Je suis content que tu ais saisi la douceur de ce texte.Voici une récompense pour célébrer ce moment. Pipe et jambe de bois !";
  
  private static final String ACCEPT_TEXT = "Je connais la chanson !";
  
  private static final String DECLINE_TEXT = "Je ne connais pas la chanson...";
  
  private static final String SING_MESSAGE = "Inscrivez ici la prochaine phrase des paroles :";
  
  private static final String DECLINE_MESSAGE = "Me voilà bien déçu, je pensais que tu voudrais chanter avec moi !";
  
  private static final Item[] REWARDS = new Item[] { ItemsRegister.PIPE, ItemsRegister.WOODEN_LEG };
  
  private static XavierDialogManager instance;
  
  private final List<IndexedDialog> dialogList;
  
  public static XavierDialogManager getInstance() {
    return instance;
  }
  
  public XavierDialogManager() {
    super("Marin Xavier", Collections.singletonList("Marin Xavier"));
    instance = this;
    this.dialogList = new ArrayList<>();
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    IndexedDialog indexed = getIndex(player, entity);
    if (indexed == null)
      return null; 
    if (isFinished(indexed))
      return getFinishDialog(player, entity); 
    String text = getKeyByIndex(indexed.getIndex());
    if (text == null)
      return null; 
    CustomDialog dialog = (new CustomDialog(this.title, text)).setCloseGui().addOption("Je connais la chanson !").addOption("Je ne connais pas la chanson...").setCallback(callback -> {
          if (callback.intValue() == 1) {
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Inscrivez ici la prochaine phrase des paroles :" });
            SoundUtils.CLICK.playSound(player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
            indexed.setDeclined(false);
          } else {
            sendDeclineDialog(player);
            indexed.setDeclined(true);
          } 
        });
    return dialog;
  }
  
  public String getKeyByIndex(int index) {
    try {
      return ((Lyric)LYRICS.get(index)).getKey();
    } catch (IndexOutOfBoundsException e) {
      return null;
    } 
  }
  
  public String getValueByIndex(int index) {
    try {
      return ((Lyric)LYRICS.get(index)).getValue();
    } catch (IndexOutOfBoundsException e) {
      return null;
    } 
  }
  
  private IndexedDialog getIndex(EntityPlayerMP player, Entity entity) {
    UUID playerUniqueId = player.func_110124_au();
    UUID npcUniqueId = entity.func_110124_au();
    if (findUniqueId(npcUniqueId) == null) {
      removePlayer(playerUniqueId);
      IndexedDialog dialog = new IndexedDialog(playerUniqueId, npcUniqueId);
      this.dialogList.add(dialog);
      return dialog;
    } 
    Optional<IndexedDialog> result = this.dialogList.stream().filter(indexed -> indexed.getEntityUniqueId().equals(npcUniqueId)).findFirst();
    return result.orElse(null);
  }
  
  public IndexedDialog getIndex(EntityPlayerMP player) {
    UUID playerUniqueId = player.func_110124_au();
    return this.dialogList.stream()
      .filter(indexed -> indexed.getPlayerUniqueId().equals(playerUniqueId))
      .findFirst()
      .orElse(null);
  }
  
  private void removePlayer(UUID playerUniqueId) {
    this.dialogList.removeIf(indexed -> indexed.getPlayerUniqueId().equals(playerUniqueId));
  }
  
  private UUID findUniqueId(UUID uniqueId) {
    for (IndexedDialog dialog : this.dialogList) {
      if (dialog.getPlayerUniqueId().equals(uniqueId))
        return dialog.getEntityUniqueId(); 
      if (dialog.getEntityUniqueId().equals(uniqueId))
        return dialog.getPlayerUniqueId(); 
    } 
    return null;
  }
  
  private UUID findUniqueId(Entity entity) {
    return findUniqueId(entity.func_110124_au());
  }
  
  private CustomDialog getDeclineDialog() {
    return (new CustomDialog(this.title, "Me voilà bien déçu, je pensais que tu voudrais chanter avec moi !"))
      .setCloseGui();
  }
  
  private void sendDeclineDialog(EntityPlayerMP player) {
    CustomDialog dialog = getDeclineDialog();
    if (dialog == null)
      return; 
    Ariane.getServer().addDialog((EntityPlayer)player, (ArianeDialog)dialog);
    PalaMod.getNetwork().sendTo((IMessage)new PacketOpenCustomDialogGui(dialog), player);
  }
  
  private boolean isFinished(IndexedDialog indexed) {
    return (indexed.getIndex() >= LYRICS.size());
  }
  
  private CustomDialog getFinishDialog(EntityPlayerMP player, Entity entity) {
    return (new CustomDialog(this.title, "Tu chantes comme un vrai troubadour ! Mes camarades marins necomprennent pas la beauté de ces mots endiablés. Je suis content que tu ais saisi la douceur de ce texte.Voici une récompense pour célébrer ce moment. Pipe et jambe de bois !"))
      .addOption("Accepter")
      .addOption("Refuser")
      .setCallback(callback -> {
          if (callback.intValue() == 1)
            giveReward(player, entity); 
        }).setCloseGui();
  }
  
  private void giveReward(EntityPlayerMP player, Entity entity) {
    World world = player.field_70170_p;
    EntityNPC npc = (EntityNPC)entity;
    ItemStack reward = new ItemStack(REWARDS[player.field_70170_p.field_73012_v.nextInt(REWARDS.length)]);
    InventoryUtils.giveOrDropitems((EntityPlayer)player, reward);
    removePlayer(player.func_110124_au());
    npc.func_70106_y();
  }
  
  public static class IndexedDialog {
    private final UUID entityUniqueId;
    
    private final UUID playerUniqueId;
    
    private int index;
    
    private boolean declined;
    
    public void setIndex(int index) {
      this.index = index;
    }
    
    public void setDeclined(boolean declined) {
      this.declined = declined;
    }
    
    public UUID getEntityUniqueId() {
      return this.entityUniqueId;
    }
    
    public UUID getPlayerUniqueId() {
      return this.playerUniqueId;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public boolean isDeclined() {
      return this.declined;
    }
    
    public IndexedDialog(UUID playerUniqueId, UUID entityUniqueId) {
      this.playerUniqueId = playerUniqueId;
      this.entityUniqueId = entityUniqueId;
      this.index = 0;
      this.declined = true;
    }
    
    public void increment() {
      this.index++;
      this.declined = true;
    }
  }
  
  public static class Lyric {
    private String key;
    
    private String value;
    
    public void setKey(String key) {
      this.key = key;
    }
    
    public void setValue(String value) {
      this.value = value;
    }
    
    public static LyricBuilder builder() {
      return new LyricBuilder();
    }
    
    public static class LyricBuilder {
      private String key;
      
      private String value;
      
      public LyricBuilder key(String key) {
        this.key = key;
        return this;
      }
      
      public LyricBuilder value(String value) {
        this.value = value;
        return this;
      }
      
      public XavierDialogManager.Lyric build() {
        return new XavierDialogManager.Lyric(this.key, this.value);
      }
      
      public String toString() {
        return "XavierDialogManager.Lyric.LyricBuilder(key=" + this.key + ", value=" + this.value + ")";
      }
    }
    
    public String getKey() {
      return this.key;
    }
    
    public String getValue() {
      return this.value;
    }
    
    public Lyric(String key, String value) {
      this.key = key;
      this.value = value;
    }
  }
  
  public static class LyricBuilder {
    private String key;
    
    private String value;
    
    public LyricBuilder key(String key) {
      this.key = key;
      return this;
    }
    
    public LyricBuilder value(String value) {
      this.value = value;
      return this;
    }
    
    public XavierDialogManager.Lyric build() {
      return new XavierDialogManager.Lyric(this.key, this.value);
    }
    
    public String toString() {
      return "XavierDialogManager.Lyric.LyricBuilder(key=" + this.key + ", value=" + this.value + ")";
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\dialogs\XavierDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */