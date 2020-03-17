public class stack extends linkedlist{
    
    public void push(char c){
        node temp = new node(c);
        super.addValue(temp);
    }

    public char pop(){
        if(headNode.getNext() == null){
            System.out.println("Stack is empty");
            listSize--;
        }
        else{
            node temp = headNode.getNext();
            headNode.setNext(temp.getNext());
            temp.setNext(null);
            System.out.println("Popped " + temp.getVal());
            listSize--;
            return temp.getVal();
        }
    }

    public void viewStack(){
        super.printList();
        System.out.println("There are " + listSize + " elements in this stack.");
    }

    public void peek(){
        return headNode.getNext().getVal();
    }
}