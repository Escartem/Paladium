package fr.paladium.announcement.server.task;

import fr.paladium.announcement.server.manager.PalaAnnouncementManager;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;

@Schedule(every = "60s", async = false)
public class AnnouncementDataRefreshTask extends ATask {
  private PalaAnnouncementManager manager = PalaAnnouncementManager.getInstance();
  
  public AnnouncementDataRefreshTask() {
    super("announcement-refresh-task");
    start();
  }
  
  public void run() {
    this.manager.importGhostData();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\task\AnnouncementDataRefreshTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */