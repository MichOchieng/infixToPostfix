public class main {

    private static String test1 = "(100+-200)/2*(5+-7)";
    private static String test2 = "6 + 3 * 10 - 9";
    private static String test3 = "-1+15*100-(13/13+-3^3)";
    private static String test4 = "((((((()))))))";
    private static String test5 = "())";
    private static String test6 = "(";
    private static String test7 = "(13)  *  (13)    ^3";
    private static String test8 = "-0----1";
    private static String test9 = "-5+-100/5+(8*15)-56";
    private static String test10 = "2^2^2^2";
    private static String test11 = "     ";

    private static String[] testStr = {test1,test2 ,test3 ,test4 
                                ,test5 ,test6 ,test7 ,test8 ,test9 ,test10 ,test11};
    private static double[] answers = {100,27 ,1525 ,0 
                                ,0 ,0 ,28561 ,1 ,39 ,256 ,0};

    public static void main(String[] args) {        
        calculatorTest();
    }

    // Loops through test strings in an array
    // Passes the string to the calculator
    // If the result of the postfix is equal to the corresponding value in the answer array increment the total num of correct values
    // Test passes if all values are correct
    public static void calculatorTest() {
        String temp;
        int correct = 0;
        int incorrect = 0;
        for (int i = 0; i < testStr.length; i++) {
            temp = testStr[i];
            System.out.println();
            System.out.println();
            System.out.println("Test " + (i+1));
            calculator calc = new calculator(temp);
            
            if (calc.getResult() == answers[i]) {
                correct++;
            } else {
                incorrect++;
            }            
        }

        if (incorrect >0) {
            System.out.println("Test failed");
            System.out.println(correct + " out of " + testStr.length + " correct");
        }
        else{
            System.out.println();
            System.out.println("All tests passed.");
        }

    }


}

/*

----------OUTPUT----------


Test 1
Pushing (
Pushing +
Popped +
Popped (
Pushing /
Popped /
Pushing *
Pushing (
Pushing +
Popped +
Popped (
Popped *
Postfix expression: 100 -200 + 2 / 5 -7 + *
Pushing 100.0
Pushing -200.0
Popped -200.0
Popped 100.0
Pushing -100.0
Pushing 2.0
Popped 2.0
Popped -100.0
Pushing -50.0
Pushing 5.0
Pushing -7.0
Popped -7.0
Popped 5.0
Pushing -2.0
Popped -2.0
Popped -50.0
Pushing 100.0
Popped 100.0
Result: 100.0


Test 2
Pushing +
Pushing *
Popped *
Popped +
Pushing -
Popped -
Postfix expression: 6 3 10  * + 9 -
Pushing 6.0
Pushing 3.0
Pushing 10.0
Popped 10.0
Popped 3.0
Pushing 30.0
Popped 30.0
Popped 6.0
Pushing 36.0
Pushing 9.0
Popped 9.0
Popped 36.0
Pushing 27.0
Popped 27.0
Result: 27.0


Test 3
Pushing +
Pushing *
Popped *
Popped +
Pushing -
Pushing (
Pushing /
Popped /
Pushing +
Pushing ^
Popped ^
Popped +
Popped (
Popped -
Postfix expression: -1 15 100 * + 13 13 / -3 3 ^ + -
Pushing -1.0
Pushing 15.0
Pushing 100.0
Popped 100.0
Popped 15.0
Pushing 1500.0
Popped 1500.0
Popped -1.0
Pushing 1499.0
Pushing 13.0
Pushing 13.0
Popped 13.0
Popped 13.0
Pushing 1.0
Pushing -3.0
Pushing 3.0
Popped 3.0
Popped -3.0
Pushing -27.0
Popped -27.0
Popped 1.0
Pushing -26.0
Popped -26.0
Popped 1499.0
Pushing 1525.0
Popped 1525.0
Result: 1525.0


Test 4
Pushing (
Pushing (
Pushing (
Pushing (
Pushing (
Pushing (
Pushing (
Popped (
Popped (
Popped (
Popped (
Popped (
Popped (
Popped (
Postfix expression:


Test 5
Pushing (
Popped (
Invalid infix entered.
Postfix expression:


Test 6
Pushing (
Postfix expression: 


Test 7
Pushing (
Popped (
Pushing *
Pushing (
Popped (
Pushing ^
Popped ^
Popped *
Postfix expression: 13 13 3 ^ *
Pushing 13.0
Pushing 13.0
Pushing 3.0
Popped 3.0
Popped 13.0
Pushing 2197.0
Popped 2197.0
Popped 13.0
Pushing 28561.0
Popped 28561.0
Result: 28561.0


Test 8
Pushing -
Popped -
Postfix expression: -0---1 -
Pushing -0.0
Popped -0.0
Stack is empty
Pushing 0.0
Popped 0.0
Stack is empty
Pushing 0.0
Pushing -1.0
Popped -1.0
Popped 0.0
Pushing 1.0
Popped 1.0
Result: 1.0


Test 9
Pushing +
Pushing /
Popped /
Popped +
Pushing +
Pushing (
Pushing *
Popped *
Popped (
Popped +
Postfix expression: -5 -100 5 / + 8 15 * -56 +
Pushing -5.0
Pushing -100.0
Pushing 5.0
Popped 5.0
Popped -100.0
Pushing -20.0
Popped -20.0
Popped -5.0
Pushing -25.0
Pushing 8.0
Pushing 15.0
Popped 15.0
Popped 8.0
Pushing 120.0
Pushing -56.0
Popped -56.0
Popped 120.0
Pushing 64.0
Popped 64.0
Popped -25.0
Pushing 39.0
Popped 39.0
Result: 39.0


Test 10
Pushing ^
Popped ^
Pushing ^
Popped ^
Pushing ^
Popped ^
Postfix expression: 2 2 ^ 2 ^ 2 ^
Pushing 2.0
Pushing 2.0
Popped 2.0
Popped 2.0
Pushing 4.0
Pushing 2.0
Popped 2.0
Popped 4.0
Pushing 16.0
Pushing 2.0
Popped 2.0
Popped 16.0
Pushing 256.0
Popped 256.0
Result: 256.0


Test 11
Postfix expression:

All tests passed.

*/