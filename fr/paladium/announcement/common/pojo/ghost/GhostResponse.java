package fr.paladium.announcement.common.pojo.ghost;

import java.util.List;

public class GhostResponse {
  private List<GhostPost> posts;
  
  public String toString() {
    return "GhostResponse(posts=" + getPosts() + ")";
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object<GhostPost> $posts = (Object<GhostPost>)getPosts();
    return result * 59 + (($posts == null) ? 43 : $posts.hashCode());
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof GhostResponse;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof GhostResponse))
      return false; 
    GhostResponse other = (GhostResponse)o;
    if (!other.canEqual(this))
      return false; 
    Object<GhostPost> this$posts = (Object<GhostPost>)getPosts(), other$posts = (Object<GhostPost>)other.getPosts();
    return !((this$posts == null) ? (other$posts != null) : !this$posts.equals(other$posts));
  }
  
  public void setPosts(List<GhostPost> posts) {
    this.posts = posts;
  }
  
  public List<GhostPost> getPosts() {
    return this.posts;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\pojo\ghost\GhostResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */