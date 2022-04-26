/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
import java.util.*;
import java.time.LocalDateTime;

/*******************************************************************************
 * Instance třídy {@code FBFeed} představují ...
 * The {@code FBFeed} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class FBFeed
{
    private ArrayList<FBPost> posts;
    
    public FBFeed(){
        posts = new ArrayList<>();
    }
    
    public FBFeed(ArrayList<FBPost> posts){
        this.posts = posts;
    }
    
    public FBFeed(FBFeed feed){
        this.posts = feed.getPosts();
    }
    
    public ArrayList<FBPost> getPosts(){
        return posts;
    }
    
    public void setPosts(ArrayList<FBPost> posts){
        this.posts = posts;
    }
    
    public void addPost(FBPost post){
        posts.add(post);
    }
    
    public int numberOfPosts(String user){
        int number = 0;
        for( FBPost post : posts){
            if (post.getUsername().equals(user)) number++;
        }
        return number;
    }
    
    public ArrayList<FBPost> postsOf(String user){
        ArrayList<FBPost> postsOfUser = new ArrayList<>();
        for( FBPost post : posts){
            if (post.getUsername().equals(user)) postsOfUser.add(post);
        }
        return postsOfUser;
    }
    
    public ArrayList<FBPost> postsOf(String user, LocalDateTime start, LocalDateTime end){
        ArrayList<FBPost> postsOfUser = new ArrayList<>();
        for( FBPost post : posts){
            if (post.getUsername().equals(user) && 
            (post.getData().isAfter(start) || post.getData().isEqual(start)) &&
            (post.getData().isBefore(end) || post.getData().isEqual(end))) 
            postsOfUser.add(post);
        }
        return postsOfUser;
    }
    
    public FBPost getPost(int id){
        for( FBPost post : posts){
            if (post.getId() == id) 
            return post;
        }
        return null;
    }
    
    public void comment(FBPost post, String comment){
        List<String> comments = new ArrayList<>();
        comments = post.getComentarios();
        comments.add(comment);
        post.setComentarios(comments);
    }
    
    public void comment(int postId, String comment){
        FBPost post = getPost(postId);
        comment(post, comment);
    }
    
    public void like(FBPost post){
        post.setLikes(post.getLikes()+1);
    }
    
    public void like(int postId){
        FBPost post = getPost(postId);
        like(post);
    }
    
    public List<Integer> top5comments(){
       Collections.sort(posts);
       ArrayList<Integer> topPosts = new ArrayList<>();
       int topPostsSize = (posts.size()>5)? 5 : posts.size();
       
       for(int i = 0; i<topPostsSize; i++) topPosts.add(posts.get(i).getId());
       
       return topPosts;
    }
}
