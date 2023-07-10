package ExperimentSourceCode_6.fill;

public class WildCardDemoApp {
    public static void main(String args[]){
        System.out.println("Creating 'Long' stack:");
        NumberStack<Long> longStack=new NumberStack<Long>();
        longStack.push(5L);
        longStack.push(10L);
        System.out.println("Creating 'Float' stack:");
        NumberStack<Float> floatStack=new NumberStack<Float>();
        floatStack.push(5.2f);
        floatStack.push(7.8f);
        System.out.println("Creating 'Number' stack:");
        NumberStack<Number> numberStack=new NumberStack<Number>();
        numberStack.push(3.33);
        numberStack.push(20L);
        numberStack.push(5.6f);
        System.out.println("\nDumping 'Long' stack");
        dumpStack(longStack);
        System.out.println("\nDumping 'Float' stack");
        dumpStack(floatStack);
        System.out.println("\nDumping 'Number' stack");
        dumpStack(numberStack);
    }
    static void dumpStack(NumberStack<?> stack){
        for(Number n:stack.getStack()){
            System.out.println(n);
        }
    }
}
class NumberStack<T extends Number> {
    private Number stack[]=new Number[3];
    private int ptr=-1;
    public Number[] getStack(){
        return stack;
    }
    void push(T data){
        ptr++;
        stack[ptr]=data;
    }
    T pop(){
        return (T) stack[ptr--];
    }
}