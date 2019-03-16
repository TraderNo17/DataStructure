import java.util.ArrayList;

public class aaa {

}
if(node==null) throw new KeyNotFoundException();
if(temp.equals(node.key)) {
	if(node.left == null && node.right==null) {
		node = null;
		return node;
	}
	if(node.left == null) {
		node = node.right;
		return node;
	}
	if(node.right == null) {
		node = node.left;
		return node;
	}
	BSTNode<K,V> tempNode = node.right;
	while(tempNode.left != null) {
		tempNode = tempNode.left;
	}
	node.value = tempNode.value;
	node = remove(node,tempNode.key);
	return node;
}else if(temp.compareTo(node.key)<0) {
	node.left = this.remove(node.left, temp);
}else {
	node.right = this.remove(node.right, temp);
}




ArrayList<K> list = new ArrayList<K>();
if(root == null) return list;
list.add(root.key);
K temp;
int i = 0;
while(i<numKeys){
	temp = list.get(i);
	try {
		if(getKeyOfLeftChildOf(temp)!=null)
			list.add(getKeyOfLeftChildOf(temp));
	} catch (IllegalNullKeyException | KeyNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		if(getKeyOfRightChildOf(temp)!=null)
			list.add(getKeyOfRightChildOf(temp));
	} catch (IllegalNullKeyException | KeyNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	i++;
}
return list;


public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
	int cmp = key.compareTo(root.key);
    if (cmp < 0) {
    	super.remove(key);
        if (getHeight(root.right) - getHeight(root.left) == 2) {
            BSTNode<K,V> r =  root.right;
            if (getHeight(r.left) > getHeight(r.right)) {
            	root.right = leftRotation(root.right);
            	root = rightRotation(root);
            }
            else
                root = rightRotation(root);
        }
    } else if (cmp > 0) {
    	super.remove(key);
        if (getHeight(root.left) - getHeight(root.right) == 2) {
            BSTNode<K,V> l =  root.left;
            if (getHeight(l.right) > getHeight(l.left)) {
            	root.left = rightRotation(root.left);
            	root = leftRotation(root);
            }
            else
                root = leftRotation(root);
        }
    } else {
        if ((root.left!=null) && (root.right!=null)) {
            if (getHeight(root.left) > getHeight(root.right)) {
                BSTNode<K,V> max = root.max(root.left);
                root.key = max.key;
                super.remove(max.key);
                if (getHeight(root.left.left) - getHeight(root.left.right) == 2) {
    	            BSTNode<K,V> l =  root.left.left;
    	            if (getHeight(l.right) > getHeight(l.left)) {
    	            	root.left.left = rightRotation(root.left.right);
    	            	root.left = leftRotation(root.left);
    	            }
    	            else
    	                root.left = leftRotation(root.left);
    	        }
            } else {
                BSTNode<K,V> min = root.min(root.right);
                root.key = min.key;
                super.remove(min.key);
                if (getHeight(root.right.right) - getHeight(root.right.left) == 2) {
    	            BSTNode<K,V> r =  root.right.right;
    	            if (getHeight(r.right.left) > getHeight(r.right.right)) {
    	            	root.right.right = leftRotation(root.right.right);
    	            	root.right = rightRotation(root.right);
    	            }
    	            else
    	                root.right = rightRotation(root.right);
    	        }
            }
        } else {
            root = (root.left!=null) ? root.left : root.right;
        }
    }
	return true;
}