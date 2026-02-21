package fr.paladium.palamod.util;

import fr.paladium.palamod.Constants;
import fr.paladium.palamod.config.PConfigs;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadInfo;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class PalaWatchdog extends Thread {
  private final Thread thread;
  
  private long sameTrace = 0L;
  
  private boolean alert = false;
  
  private String lastTrace;
  
  public PalaWatchdog(Thread watchedThread) {
    super("PalaWatchdog/1");
    this.thread = watchedThread;
    Constants.logger.info("[PalaWatchdog] Enabled.");
    Constants.logger.info("[PalaWatchdog] AutoReboot: " + PConfigs.threadLockAutoReboot + " | Time: " + PConfigs.threadLockRebootTime);
    start();
  }
  
  private String getTrace() {
    StringBuilder builder = new StringBuilder();
    StackTraceElement[] trace = this.thread.getStackTrace();
    if (trace == null || trace.length == 0 || trace[0] == null)
      return null; 
    for (StackTraceElement str : trace)
      builder.append(str.toString() + "\n"); 
    return builder.toString();
  }
  
  private String getThreadDumps(String currentStack) {
    StringBuilder dumps = new StringBuilder();
    ThreadInfo[] threads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
    dumps.append("DUMP START: " + LocalDateTime.now().toString() + "\n\n");
    for (ThreadInfo thread : threads) {
      dumps.append("---------------------------\n");
      dumps.append("Thread: " + thread.getThreadName() + "\n");
      dumps.append("PID: " + thread.getThreadId() + " | Suspended: " + thread.isSuspended() + "\n");
      dumps
        .append("Native: " + thread.isInNative() + " | State: " + thread.getThreadState() + "\n");
      dumps.append("Blocked time: " + thread.getBlockedTime() + " | Count: " + thread
          .getBlockedCount() + "\n");
      dumps.append("Stack:\n");
      if (thread.getThreadName().equals(this.thread.getName())) {
        dumps.append(currentStack + "\n");
      } else {
        StackTraceElement[] stack = thread.getStackTrace();
        for (int line = 0; line < stack.length; line++)
          dumps.append(stack[line].toString() + "\n"); 
      } 
    } 
    dumps.append("\nEND OF DUMP");
    return dumps.toString();
  }
  
  private void sendAlert(String newTrace) {
    System.out.println("[PalaWatchdog] Found minecraft server thread threadlock:");
    System.out.println(newTrace);
    try {
      OperatingSystemMXBean osBean = ManagementFactory.<OperatingSystemMXBean>getPlatformMXBean(OperatingSystemMXBean.class);
      String name = (InetAddress.getLocalHost() != null && InetAddress.getLocalHost().getHostName() != null) ? InetAddress.getLocalHost().getHostName() : "Inconnu";
      DiscordWebhook webhook = new DiscordWebhook(PConfigs.threadLockWebhook);
      webhook.setAvatarUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/OOjs_UI_icon_alert-warning.svg/2048px-OOjs_UI_icon_alert-warning.svg.png");
      webhook.setUsername("PalaWatchdog");
      DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
      embed.setTitle("Threadlock détecté");
      embed.setAuthor("PalaWatchdog", "", "");
      embed.setThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/OOjs_UI_icon_alert-warning.svg/2048px-OOjs_UI_icon_alert-warning.svg.png");
      embed.addField("Serveur", name, true);
      embed.addField("Environnement", Constants.MOD_ENV.name(), true);
      Hastebin bin = new Hastebin();
      String url = bin.post(getThreadDumps(newTrace), true);
      embed.setDescription(url);
      embed.setUrl(url);
      webhook.addEmbed(embed);
      webhook.execute();
    } catch (Exception error) {
      error.printStackTrace();
    } 
  }
  
  private boolean isBlocked(String trace) {
    if (this.thread.getState() == Thread.State.TIMED_WAITING)
      return false; 
    if (this.thread.getState() == Thread.State.BLOCKED)
      return true; 
    return (trace != null && trace.equals(this.lastTrace));
  }
  
  public void run() {
    while (true) {
      String newTrace = getTrace();
      if (isBlocked(newTrace)) {
        this.sameTrace += 100L;
        if (this.sameTrace >= 10000L) {
          if (!this.alert) {
            sendAlert(newTrace);
            this.alert = true;
          } 
          if (PConfigs.threadLockAutoReboot && this.sameTrace >= PConfigs.threadLockRebootTime)
            Runtime.getRuntime().halt(0); 
        } 
      } else {
        this.sameTrace = 0L;
        this.alert = false;
      } 
      this.lastTrace = getTrace();
      try {
        Thread.sleep(100L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\PalaWatchdog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */