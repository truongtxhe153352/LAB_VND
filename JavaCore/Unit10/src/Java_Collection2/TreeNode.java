package Java_Collection2;

import java.util.ArrayList;
import java.util.List;

public class TreeNode <T>{
    private T value;
    private TreeNode<T> nextSibling;
    private TreeNode<T> firstChil;
    private TreeNode<T> parent;

    public TreeNode(T v){
        value = v;
    }
    public TreeNode<T> getNextSibling(){return nextSibling;}
    public TreeNode<T> getParent(){return parent;}
    public TreeNode<T> getFirstChil(){return firstChil;}
    public T getValue(){return value;}

    public void addChild(TreeNode<T> child){
        if (child == null) return;
        child.parent = parent;
        if (firstChil == null){
            firstChil = child;
        } else {
            TreeNode<T> temp = firstChil;
            while (temp.nextSibling != null){
                temp = temp.nextSibling;
            }
            temp.nextSibling = child;
        }
    }

    public TreeNode<T> addChild(T value){
       TreeNode<T> node = new TreeNode<T>(value);
        addChild(node);
        return node;
    }

    public List<T> chonloc(TreeNodeFilter<T> filter){
        List<T> list = new ArrayList<>();
        TreeNodeUtils.visit(this,filter,list);
        return list;
    }
}
