import java.util.Stack;

public class calculator {
    private stack stack = new stack();
    private String infix;
    private String postfix = "";
    private int result;

    public calculator(String infix){
        this.infix = infix;
        getPostfix();
        calculate();
    }

    private boolean isOperator(char c){
        switch (c) {
            case '+':
                return true;
            case '-':
                return true;    
            case '/':
                return true;
            case '^':
                return true;
            case '*':
                return true;     
            default:
                return false;
        }
    }

    // Determines if a char x is higher precednece than the element at the top of the stack
    private boolean highPrecedence(char c){
        if (precedence(c) < precedence(stack.peek()) || precedence(c) == precedence(stack.peek())) {
            return true;
        } else {
            return false;
        }
    }

    private int precedence(char c){
        switch (c) {
            case '+':
                return 2;
            case '-':
                return 1;    
            case '/':
                return 4;
            case '^':
                return 5;
            case '*':
                return 3;     
            default:
                return 0;
        }
    }

    public void getPostfix(){
        char temp;
        for (int i = 0; i < infix.length(); i++) {
            temp = infix.charAt(i);
            // Checks to see if the char is an operand or operator
            // If operand -> add to postfix
            if(Character.isLetterOrDigit(temp)){
                postfix+=temp;
            }
            else if (isOperator(temp)) {
                // Pushes operator to empty stack
                if (stack.listSize == 0) {
                    stack.push(temp);
                } else {
                    if (highPrecedence(temp)) {
                        stack.push(temp);
                    } else {
                        // do something
                    }
                }
            }
            // If temp is an opening bracket push to stack
            else if (temp == '(') {
                stack.push(temp);
            }            
            else if (temp == ')'){
                if(stack.listSize == 0){
                    System.out.println("Invalid infix entered.");
                }
                else{
                    // Pops the top of the stack until an opening bracket is found then pops the opening bracket.
                    while (stack.listSize != 0 && stack.peek() != '(') {
                        postfix+=stack.pop();
                    }
                    stack.pop();
                }
            }
            while (stack.listSize != 0) {
                if (stack.peek() == '(') {
                    System.out.println("Invalid infix entered.");
                } else {
                    postfix+=stack.pop();
                }
            }
        }
    }

    public void calculate(){

    }

    
}