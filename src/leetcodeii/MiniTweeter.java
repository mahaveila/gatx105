package leetcodeii;

import java.util.*;

/**
 * Created by Erebus on 4/17/18.
 */
public class MiniTweeter implements Tracker{


    public class FriendshipService {

        Map<Integer, Set<Integer>> followings;
        Map<Integer, Set<Integer>> followers;

        public FriendshipService() {
            // do intialization if necessary
            followings = new HashMap<>();
            followers = new HashMap<>();
        }

        private boolean contains(Map<Integer, Set<Integer>> map, int id, int target){
            return map.containsKey(id) && map.get(id).contains(target);
        }

        private void remove(Map<Integer, Set<Integer>> map, int id, int target){
            if(contains(map, id, target)){
                map.get(id).remove(target);
            }
        }

        private List<Integer> getNeighbor(Map<Integer, Set<Integer>> map, int id){
            Set<Integer> set = map.get(id);
            List<Integer> res = new ArrayList<>();
            if(set!=null && !set.isEmpty()){
                res.addAll(set);
            }
            return res;
        }

        private void add(Map<Integer, Set<Integer>> map, int key, int nb){
            if(!map.containsKey(key)){
                map.put(key, new HashSet<>());
            }
            map.get(key).add(nb);
        }

        /*
         * @param user_id: An integer
         * @return: all followers and sort by user_id
         */
        public List<Integer> getFollowers(int user_id) {
            // write your code here
            return getNeighbor(followers, user_id);
        }

        /*
         * @param user_id: An integer
         * @return: all followings and sort by user_id
         */
        public List<Integer> getFollowings(int user_id) {
            // write your code here
            return getNeighbor(followings, user_id);
        }

        /*
         * @param from_user_id: An integer
         * @param to_user_id: An integer
         * @return: nothing
         */
        public void follow(int from_user_id, int to_user_id) {
            // write your code here
            add(followings, from_user_id, to_user_id);
            add(followers, from_user_id, to_user_id);
        }

        /*
         * @param from_user_id: An integer
         * @param to_user_id: An integer
         * @return: nothing
         */
        public void unfollow(int from_user_id, int to_user_id) {
            // write your code here
            remove(followers, from_user_id, to_user_id);
            remove(followings, from_user_id, to_user_id);
        }
    }

    public static void main(String [] args){
        MiniTweeter mt = new MiniTweeter();
        mt.cout(mt.postTweet(1, "LintCode is good!").text);
        List<Tweet> feed = mt.getNewsFeed(1);
        for(Tweet t : feed){
            mt.cout(t.text);
        }

        List<Tweet> timeline = mt.getTimeline(1);
        for(Tweet t : timeline){
            mt.cout(t.text);
        }

        mt.follow(2, 1);
        List<Tweet> feed2 = mt.getNewsFeed(2);
        for(Tweet t : feed2){
            mt.cout(t.text);
        }
        mt.unfollow(2, 1);
        feed2 = mt.getNewsFeed(2);
        for(Tweet t : feed2){
            mt.cout(t.text);
        }

    }


    public static class Tweet {
      public int id;
      public int user_id;
      public String text;
      public static Tweet create(int user_id, String tweet_text) {
          // This will create a new tweet object,
          // and auto fill id
          Tweet t = new Tweet();
          t.user_id = user_id;
          t.text = tweet_text;
          return t;
      }
    }

    public static class Node{
        Tweet tweet;
        Node pre;
        Node next;
        public Node(Tweet tweet){
            this.tweet = tweet;
        }
    }

    //use pull model
    Map<Integer, Set<Integer>> followings;
    Map<Integer, Node> timelines;
    int time;

    //re-follow: what happens? if starting fresh feeds, the push
    //if retrieve all history, probably use pull is better


    public MiniTweeter() {
        time = 0;
        // do intialization if necessary
        followings = new HashMap<>();
        timelines = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        // write your code here
        Tweet t = Tweet.create(user_id, tweet_text);
        t.id = time++;
        if(!timelines.containsKey(user_id)){
            timelines.put(user_id, new Node(t));
        } else {
            Node n = timelines.get(user_id);
            Node next = new Node(t);
            n.next = next;
            next.pre = n;
            timelines.put(user_id, next); //update the tail
        }
        if(!followings.containsKey(user_id)){
            //start follow himself
            followings.put(user_id, new HashSet<>());
            followings.get(user_id).add(user_id);
        }
        return t;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        // write your code here
        Set<Integer> following = followings.get(user_id);
        List<Tweet> res = new ArrayList<>();
        if(following == null || following.size()<1){
            return res;
        }
        PriorityQueue<Node> q = new PriorityQueue<>(1, (n1, n2)->n2.tweet.id - n1.tweet.id);

        //pulling the news feed.
        for(int following_id : following){
            if(timelines.containsKey(following_id)){
                //add tail to the q
                q.offer(timelines.get(following_id));
            }
        }

        while(res.size()<10 && !q.isEmpty()){
            Node cur = q.poll();
            //polled latest one
            res.add(cur.tweet);
            if(cur.pre!=null){
                q.offer(cur.pre);
            }
        }

        return res;
    }



    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        // write your code here
        Node n = timelines.get(user_id);
        List<Tweet> res = new ArrayList<>();
        while(n!=null && res.size()<10){
            res.add(n.tweet);
            n = n.pre;
        }
        return res;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        // write your code here
        if(!followings.containsKey(from_user_id)){
            followings.put(from_user_id, new HashSet<>());
        }
        followings.get(from_user_id).add(to_user_id);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        // write your code here
        if(from_user_id == to_user_id
                || !followings.containsKey(from_user_id)
                || !followings.get(from_user_id).contains(to_user_id)){
            return; //cannot unfollow himself
        }
        followings.get(from_user_id).remove(to_user_id);
    }

}
