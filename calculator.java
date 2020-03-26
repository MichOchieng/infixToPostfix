public class calculator {
    private stack infixStack = new stack();
    private doubleStack postfixStack = new doubleStack();
    private String infix;
    private String postfix = "";    
    private int index = 0;
    private boolean negativeNum = false;
    private double result;
    
    public calculator(String infix) {
        this.infix = infix;
        getPostfix();
        if (postfix != "") {
            calculate();
        }
    }

    public void tokenizer(String str, int i) {
        char temp = str.charAt(i);
        // Grabs value
        // Adds a negative to the value where applicable
        if (temp == '-' && i > 0) {
            char previous = str.charAt(i - 1);
            // First element
            // Previous element is an operator
            // Previous element is a bracket
            if (isOperator(previous) || previous == '(' || previous == ')') {
                postfix += temp;
            }
            if (i == str.length() - 1) {
                i++;
            } else {
                i++;
                temp = str.charAt(i);
            }
        } else if (temp == '-' && i == 0) {
            postfix += temp;
            if (i == str.length() - 1) {
                i++;
            } else {
                i++;
                temp = str.charAt(i);
            }
        }
        // Adds operand values to postfix
        while (Character.isLetterOrDigit(temp) && i <= str.length() - 1) {
            postfix += temp;
            // Avoids infinite loop
            if (i == str.length() - 1) {
                i++;
                continue;
            }
            // Avoids ArrayIndexOutOfBoundsException
            else {
                i++;
                temp = str.charAt(i);
            }
        }
        // Resets the index to where the value ends
        index = i - 1;
        // Adds spaces after values only if the value isn't a negative operator
        if (temp != '-') {
            postfix += " ";
        }

    }
    
    private boolean isOperator(char c) {
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

    // Determines if a char x is higher precednece than the element at the top of
    // the stack
    private boolean highPrecedence(char c) {        
        if (precedence(c) > precedence(infixStack.peek())) {
            return true;
        } else {
            return false;
        }
    }
    // Returns the level of precedence for each operator
    private int precedence(char c) {
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

    private void getPostfix() {
        char temp;
        for (int i = index; i < infix.length(); i++) {
            temp = infix.charAt(i);
            // Checks to see if the char is an operand or operator
            // If operand -> add to postfix
            if (Character.isLetterOrDigit(temp) || temp == '-') {
                // Grabs values and moves temp value accordingly
                // If a negative number is present sets negativeNum true so that the
                // Negative operator isn't pushed to the stack
                if (temp == '-' && i > 0) {

                    char previous = infix.charAt(i - 1);
                    // Checks to see if the incoming '-' operator is an operator or
                    // part of an operand
                    if (isOperator(previous) || previous == '(' || previous == ')') {
                        negativeNum = true;
                    }

                }
                // Confirms that the incoming '-' operator is part of an operand
                // if its the first element in the infix
                else if (temp == '-' && i == 0) {
                    negativeNum = true;
                }
                tokenizer(infix, i);
                i = index;
                
            }
            // Check for an incoming operator
            if (isOperator(temp) && !negativeNum) {
                // Pushes operator to empty stack
                if (infixStack.listSize == 0) {
                    infixStack.push(temp);
                } else {
                    // If operator is higher or equal precedence than the top of stack push to stack
                    if (highPrecedence(temp)) {
                        infixStack.push(temp);
                    } else {
                        // If operator is lower precedence keep popping stack into postfix
                        // until stack is empty or top element on stack is of higher/equal precedence
                        while (infixStack.listSize != 0 && !highPrecedence(temp)) {
                            postfix += infixStack.pop();
                            postfix += " ";
                        }
                        infixStack.push(temp);
                    }
                }
            }
            // If temp is an opening bracket push to stack
            else if (temp == '(') {
                infixStack.push(temp);
            } else if (temp == ')') {
                if (infixStack.listSize == 0) {
                    System.out.println("Invalid infix entered.");
                    break;
                } else {
                    // Pops the top of the stack until an opening bracket is found then pops the
                    // opening bracket.
                    while (infixStack.listSize != 0 && infixStack.peek() != '(') {
                        postfix += infixStack.pop();
                        postfix += " ";
                    }
                    infixStack.pop();
                }
            }
            // Once at the end of the infix pops everything from stack to postfix
            if (i == infix.length() - 1) {
                while (infixStack.listSize != 0 && infixStack.peek() != '(') {
                    if (infixStack.peek() == '(') {
                        System.out.println("Invalid infix entered.");
                        break;
                    } else {
                        postfix += infixStack.pop();
                        postfix += " ";
                    }
                }
            }
            // Resets for next loop
            negativeNum = false;            
        }
        System.out.println("Postfix expression: " + postfix);
    }

    // Determines what operation will be preformed in calculating postfix
    // Then pushes the result to the stack
    private void operation(double a, double b, char opp) {
        int operator = precedence(opp);
        switch (operator) {
            case 2:
                postfixStack.push((a + b));
                break;
            case 1:
                postfixStack.push((a - b));
                break;
            case 4:
                postfixStack.push((a / b));
                break;
            case 5:
                postfixStack.push((Math.pow(a, b)));
                break;
            case 3:
                postfixStack.push((a * b));
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
    }

    private void calculate() {
        char temp;        
        for (int i = 0; i < postfix.length(); i++) {
            temp = postfix.charAt(i);
            if (Character.isDigit(temp) || temp == '-') {
                // Creates a string and adds each number of the given value
                // Turns the string into a double and pushes to stack
                String value = "";
                // Accounts for negative numbers
                if (temp == '-' && i < postfix.length() - 1) {
                    if (Character.isDigit(postfix.charAt(i + 1))) {
                        value += temp;
                        i++;
                        temp = postfix.charAt(i);
                    }
                }
                while (Character.isDigit(temp)) {
                    value += temp;
                    i++;
                    temp = postfix.charAt(i);
                }
                // Only pushes a x to the stack if there is a number in value
                if (value != "") {
                    double x = Double.parseDouble(value);
                    postfixStack.push(x);
                }
            }
            if (isOperator(temp)) {
                // Pop last two elements from the stack
                double a = postfixStack.pop();
                double b = postfixStack.pop();
                // Evaluate and push to stack
                operation(b, a, temp);
            } else {
                continue;
            }
        }
        if (postfixStack.listSize > 1) {
            // Pop last two elements from the stack
            double a = postfixStack.pop();
            double b = postfixStack.pop();
            // Add any remaining elements
            operation(b, a, '+');
        }
        result = postfixStack.pop();
        System.out.println("Result: " + result);
    }

    // Returns the result of the postfix expresion
    public double getResult() {
        return result;
    }

    // Stack and nodes used for calculating Postfix expression
    public class doubleNode{
        private doubleNode next;
        private doubleNode previous;
        private double val;
        
        public doubleNode(double val){
            this.val = val;
        }
        // Used for creating dummy nodes
        public doubleNode(){}

        public void setNext(doubleNode node){
            this.next = node;
        }
    
        public doubleNode getNext(){
            return next;
        }
    
        public doubleNode getPrevious(){
            return previous;
        }
    
        public void setPrevious(doubleNode node){
            this.previous = node;
        }
        
        public double getVal(){
            return this.val;
        }
    }

    public class doubleStack extends linkedlist{
        public doubleNode headNode = new doubleNode();
        public doubleNode tailNode;
        
        public void push(double c){
            doubleNode temp = new doubleNode(c);  
            System.out.println("Pushing " + c);          
            if(headNode.getNext() == null){
                headNode.setNext(temp);            
                listSize++;
            }else{
                temp.setNext(headNode.getNext());
                headNode.setNext(temp);
                listSize++;            
            }   
        }
        
        public double pop(){
            if(headNode.getNext() == null){
                System.out.println("Stack is empty");
                listSize--;
            }
            else{
                doubleNode temp = headNode.getNext();
                headNode.setNext(temp.getNext());
                temp.setNext(null);
                System.out.println("Popped " + temp.getVal());
                listSize--;
                return temp.getVal();
            }
            return 0.0;
        }
    }
}