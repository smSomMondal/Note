# Vertical Traversal of a Binary Tree

Given the root of a binary Tree, Find its vertical traversal starting from the leftmost level to the rightmost level.

Note: If multiple nodes are at the same horizontal distance from the root and on the same level, they should be printed in the order they appear in a level-order traversal (top-to-bottom, left-to-right).

Here, Horizontal distance is calculated from the root to a specific node by counting how many times we move left or right along the unique path from the root to that node.

The formula for Horizontal distance from the root is given by:

Horizontal Distance = Number of right moves − Number of left moves in the path from the root to that node.

Examples:  

Input:

420046755

![image](https://media.geeksforgeeks.org/wp-content/uploads/20250929155117621633/420046755.webp)
 
Output: [[4], [2], [1, 5, 6, 11], [3, 8, 9], [7], [10]]
Explanation: The below image shows the horizontal distances used to print vertical traversal starting from the leftmost level to the rightmost level

![image](https://media.geeksforgeeks.org/wp-content/uploads/20250929150919476236/420046754.webp)
 
```java

/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    
    class nodIn{
        Node n;
        int h;
        nodIn(Node a,int b){
            n=a;
            h=b;
        }
    }
    public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        // code here
        Queue<nodIn> q = new ArrayDeque<>();
        TreeMap<Integer,ArrayList<Integer>> mp = new TreeMap<>();
        
        if(root != null) q.add(new nodIn(root,0));
        while(!q.isEmpty()){
            nodIn temp=q.poll();
            int h = temp.h;
            
            ArrayList<Integer> li;
            if(!mp.containsKey(h)){
                //li=new ArrayList<>(Collections.sort());
                mp.put(h,new ArrayList<Integer>());
            }
            li = mp.get(h);
            li.add(temp.n.data);
            mp.put(h,li);
            if(temp.n.left != null) q.add(new nodIn(temp.n.left,h-1));
            if(temp.n.right != null) q.add(new nodIn(temp.n.right,h+1));
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int a : mp.keySet()){
            ans.add(mp.get(a));
        }
        
        return ans;
    }
}

```