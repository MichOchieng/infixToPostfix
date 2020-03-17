public class linkedlist {
    public node headNode = new node();
    public node tailNode;
    public int listSize = 0;   
    
    // Adds new nodes to the front of the linked list
    public void addValue(node n){
        if(headNode.getNext() == null){
            headNode.setNext(n);            
            listSize++;
        }else{
            n.setNext(headNode.getNext());
            headNode.setNext(n);
            listSize++;            
        }            
    }

    public void printList() {
        node temp = headNode;               
        while(temp != null) {
            System.out.println(temp.getVal());
            temp = temp.getNext();
        }        
    }
}