public class node {

    private node next;
    private node previous;
    private char val;

    public node(char val){
        this.val = val;
    }
    // For creating a headnode
    public node(){}

    public void setNext(node node){
        this.next = node;
    }

    public node getNext(){
        return next;
    }

    public node getPrevious(){
        return previous;
    }

    public void setPrevious(node node){
        this.previous = node;
    }

    public char getVal(){
        return val;
    }
    
}