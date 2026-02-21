package fr.paladium.announcement.server.manager;

import com.google.gson.Gson;
import fr.paladium.announcement.common.pojo.ghost.GhostPost;
import fr.paladium.announcement.common.pojo.ghost.GhostResponse;
import fr.paladium.announcement.common.pojo.parsed.ContentType;
import fr.paladium.announcement.common.pojo.parsed.PaladiumContent;
import fr.paladium.announcement.common.pojo.parsed.PaladiumPost;
import fr.paladium.announcement.server.config.PalaAnnouncementConfig;
import fr.paladium.announcement.server.config.pojo.AnnouncementCategory;
import fr.paladium.announcement.server.config.pojo.GhostApi;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PalaAnnouncementManager {
  private static PalaAnnouncementManager instance;
  
  private String apiUrl;
  
  public String getApiUrl() {
    return this.apiUrl;
  }
  
  private List<PaladiumPost> cachedPosts = new ArrayList<>();
  
  public List<PaladiumPost> getCachedPosts() {
    return this.cachedPosts;
  }
  
  public PalaAnnouncementManager() {
    instance = this;
  }
  
  public static PalaAnnouncementManager getInstance() {
    if (instance == null)
      instance = new PalaAnnouncementManager(); 
    return instance;
  }
  
  public void importGhostData() {
    try {
      URL url = new URL(this.apiUrl);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json");
      int responseCode = connection.getResponseCode();
      if (responseCode == 200) {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null)
          response.append(line); 
        in.close();
        Gson gson = new Gson();
        GhostResponse ghostResponse = (GhostResponse)gson.fromJson(response.toString(), GhostResponse.class);
        List<GhostPost> allPosts = ghostResponse.getPosts();
        this.cachedPosts.clear();
        for (GhostPost post : allPosts) {
          boolean categoryFound = false;
          for (AnnouncementCategory category : PalaAnnouncementConfig.get().getCategories()) {
            for (String keyword : category.getTitleKeywords()) {
              if (post.getTitle().toLowerCase().contains(keyword.toLowerCase()) && post.getVisibility().equalsIgnoreCase("public")) {
                PaladiumPost paladiumPost = new PaladiumPost(post.getTitle());
                paladiumPost.setDate(convertDateToMillis(post.getPublished_at()));
                paladiumPost.setType(category.getAnnouncementType());
                parseHtml(post.getHtml(), paladiumPost);
                this.cachedPosts.add(paladiumPost);
                categoryFound = true;
                break;
              } 
            } 
            if (categoryFound)
              break; 
          } 
        } 
        this.cachedPosts.sort((post1, post2) -> Long.compare(post2.getDate(), post1.getDate()));
        System.out.println("[PalaAnnouncement] Successfully imported data: " + this.cachedPosts.size() + " posts cached.");
      } else {
        System.err.println("[PalaAnnouncement] Failed to fetch data from Ghost API. Response code: " + responseCode);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void setupConfig(GhostApi ghostApi) {
    this.apiUrl = ghostApi.getUrl() + "?key=" + ghostApi.getKey() + "&limit=" + ghostApi.getLimit();
  }
  
  private void parseHtml(String htmlContent, PaladiumPost paladiumPost) {
    String decodedHtml = htmlContent.replace("\\u003C", "<").replace("\\u003E", ">").replace("\\\"", "\"");
    Document doc = Jsoup.parse(decodedHtml);
    traverseElements(doc.body().children(), 0, paladiumPost.getContents());
  }
  
  private void traverseElements(Elements elements, int indentLevel, List<PaladiumContent> parentContents) {
    for (Element element : elements) {
      String imgSrc;
      PaladiumContent content = null;
      switch (element.tagName()) {
        case "h1":
          content = new PaladiumContent(ContentType.MAIN_TITLE, element.text().toUpperCase());
          break;
        case "h2":
          content = new PaladiumContent(ContentType.BIG_TITLE, element.text().toUpperCase());
          break;
        case "h3":
          content = new PaladiumContent(ContentType.TITLE, element.text().toUpperCase());
          break;
        case "p":
          content = new PaladiumContent(ContentType.PARAGRAPH, element.text());
          break;
        case "ul":
          content = new PaladiumContent(ContentType.LIST, null);
          traverseElements(element.children(), indentLevel + 1, content.getChildren());
          break;
        case "li":
          content = new PaladiumContent(ContentType.LIST_ITEM, element.text());
          traverseElements(element.children(), indentLevel + 1, content.getChildren());
          break;
        case "img":
          imgSrc = element.attr("src");
          content = new PaladiumContent(ContentType.IMAGE, imgSrc);
          break;
        default:
          traverseElements(element.children(), indentLevel, parentContents);
          break;
      } 
      if (content != null)
        parentContents.add(content); 
    } 
  }
  
  public void printCachedPosts() {
    for (PaladiumPost post : this.cachedPosts) {
      System.out.println();
      System.out.println("\nTitle: " + post.getTitle());
      printContents(post.getContents(), 0);
    } 
  }
  
  private void printContents(List<PaladiumContent> contents, int indentLevel) {
    String indent = generateIndent(indentLevel);
    for (PaladiumContent content : contents) {
      switch (content.getType()) {
        case TITLE:
          System.out.println(indent + content.getContent());
          continue;
        case PARAGRAPH:
          System.out.println(indent + "-> " + content.getContent());
          continue;
        case LIST:
          System.out.println(indent + "List:");
          printContents(content.getChildren(), indentLevel + 1);
          continue;
        case LIST_ITEM:
          System.out.println(indent + "-> " + content.getContent());
          printContents(content.getChildren(), indentLevel + 1);
          continue;
        case IMAGE:
          System.out.println(indent + content.getContent());
          continue;
      } 
      printContents(content.getChildren(), indentLevel);
    } 
  }
  
  private String generateIndent(int indentLevel) {
    StringBuilder indent = new StringBuilder();
    for (int i = 0; i < indentLevel; i++)
      indent.append("  "); 
    return indent.toString();
  }
  
  private static long convertDateToMillis(String dateStr) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
      OffsetDateTime dateTime = OffsetDateTime.parse(dateStr, formatter);
      return dateTime.toInstant().toEpochMilli();
    } catch (DateTimeParseException e) {
      System.err.println("Erreur de parsing pour la date: " + dateStr);
      return -1L;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\manager\PalaAnnouncementManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */