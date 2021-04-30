package com.pyh.structure;

import com.pyh.collection.MStack;

import java.util.HashMap;
import java.util.Map;

/**
 * 类ExpressionExcutor的实现描述：一个简单的表达式求值
 *
 * @author panyinghua 2021-4-29 19:31
 */
public class ExpressionExcutor {

    private MStack<Character> opStack = new MStack<>();
    private MStack<Integer> numStack = new MStack<>();

    /**
     * 运算符优先级，数字越高，运算优先级越高
     */
    private static Map<Character, Integer> opPriorityMap = new HashMap();

    static {
        opPriorityMap.put('+',1);
        opPriorityMap.put('-',1);
        opPriorityMap.put('*',2);
        opPriorityMap.put('/',2);
    }


    public static void main(String[] args) {
        ExpressionExcutor ee = new ExpressionExcutor();
        System.out.println(ee.execute("3+5*6-8"));
    }



    /**
     * 求解表达式，这里使用栈来实现表达式的求值
     * 限制的表达式只有加减乘除
     * @param expression 表达式的字符串表示，如 3+5*6-8
     * @return
     */
    public int execute(String expression) {
        int len = expression.length();

        int index = 0;
        MultiValue mv = null;

        while(index<len) {
            mv = getNextNum(expression, index);
            if(null != mv) {
                numStack.push(mv.getValue());
                index = mv.getKey();
            } else {
                // 获取到的不是数字(同时注意的是需要将index的位置往后移一位)
                char op = expression.charAt(index++);
                // 操作符入栈逻辑
                // 如果当前操作符的优先级 高于 栈里边的栈顶的操作符优先级，则入栈
                // 如果当前操作符的优先级 等于或者低于 栈里边的栈顶的操作符优先级，则取出数字栈的两个数字，再取出当前操作符栈顶的操作符进行计算，然后压入操作数栈,然后再进行下一轮的比较

                int currentOpPrio = opPriorityMap.get(op);
                Character preOp = null;
                while((preOp=opStack.peek())!=null && currentOpPrio<=opPriorityMap.get(preOp)) {
                    // 将栈顶的操作符出栈
                    opStack.pull();
                    numStack.push(execute(numStack.pull(),numStack.pull(), preOp));
                }
                // 经过while的循环，现在操作符栈中的操作符的优先级肯定都是低于当前解析出来的操作符op，所以当前操作符可以入栈了
                opStack.push(op);

            }
        }

        // 上述的循环结束之后，栈中可能存在的情况是：两个栈都还有运算符，那么 操作符栈里边从栈顶到栈底的运算符的优先级是从高到低，而且相邻的操作符运算优先级不会一样（前边的代码内层while循环保证了操作符栈中不会存在两个优先级一样的运算法）
        // 处理最后的结果
        Character preOp = null;
        while((preOp=opStack.pull())!=null) {
            numStack.push(execute(numStack.pull(),numStack.pull(), preOp));
        }

        // 最后的结果肯定是操作数栈中的栈顶元素
        return numStack.pull();
    }

    /**
     * 运算
     * @param num1 先出栈的操作数
     * @param num2 后出栈的操作数
     * @param preOp
     * @return
     */
    private int execute(Integer num1, Integer num2, char preOp) {
        int result = 0;

        switch(preOp){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2-num1;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num2/num1;
                break;
        }

        return result;
    }

    private MultiValue getNextNum(String expression, int index) {
        int start = index;
        int num = 0;
        int diff = expression.charAt(start)-'0';
        // index对应的字符不是数字，则返回空
        if(diff<0||diff>=10) {
            return null;
        }

        while(start<expression.length() && (diff = expression.charAt(start)-'0') >= 0 && diff < 10) {
            num = num*10+diff;
            start++;
        }

        return new MultiValue(start, num);
    }

    private static class MultiValue {
        private int key;
        private int value;

        public MultiValue(int k, int v) {
            this.key = k;
            this.value = v;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }
}
