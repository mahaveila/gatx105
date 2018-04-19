package leetcodeii.list;

import leetcodeii.ListNode;
import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/9/18.
 */
public class ReverseKGroupLinkedList implements Tracker{

    public static void main(String [] args){
        ReverseKGroupLinkedList rev = new ReverseKGroupLinkedList();

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null){
            return null;
        }
        ListNode knode = reverseKGroup(nextK(head, k), k);
        //now we only needs to reverse current/first k nodes
        ListNode cur = head;
        ListNode rev = null;
        while(k-->0 && cur!=null){
            ListNode next = cur.next;

            //cur it off, and insert in front of rev
            if(rev==null){
                cur.next = knode;
            } else {
                cur.next = rev;
            }
            rev = cur;
            cur = next;
        }
        return rev;
    }

    private ListNode nextK(ListNode head, int k){
        if(head==null){
            return null;
        }
        if(k==0){
            return head.next;
        }
        return nextK(head.next, k-1);
    }

}
