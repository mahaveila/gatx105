package leetcodeii.FourSeries;

import leetcodeii.ListNode;

/**
 * Created by Erebus on 8/20/17.
 */
public class AddTwoNumbersII445  {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int len1 = getLength(l1);
        int len2 = getLength(l2);
        int offset = Math.abs(len1-len2);
        ListNode head = new ListNode(1);
        head.next = len1>=len2?helper(l1, l2, offset):helper(l2, l1, offset);
        if(head.next.val>9){
            head.next.val = head.next.val%10;
            return head;
        }
        return head.next;
    }

    private ListNode helper(ListNode l1, ListNode l2, int offset){
        if(l1==null||l2==null){
            return null;
        }
        ListNode cur = offset==0?new ListNode(l1.val+l2.val):new ListNode(l1.val);
        /**
         * FIXME: fixed, the---------------------------------------------l1.next, l2 => moving just one list
         * as same as add l1.val+l2.val v.s. l1.val, moving just one list
         */
        ListNode post = offset==0?helper(l1.next,l2.next,0):helper(l1.next,l2,offset-1);
        if(post!=null && post.val>9){
            cur.val++;
            post.val = post.val%10;
        }
        cur.next= post;
        return cur;
    }

    private int getLength(ListNode tmp){
        int len = 0;
        while(tmp!=null){
            tmp=tmp.next;
            len++;
        }
        return len;
    }
}
