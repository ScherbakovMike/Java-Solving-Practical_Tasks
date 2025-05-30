package javacore;

public class UsingInstanceMethodsAndVariablesInTheInterface {

    interface SomeInterface {
        default void method() {
            //((Implementation) this).method();
            System.out.println(((Implementation) this).variable);
        }
    }

    static class Implementation implements SomeInterface {

        private int variable = 1;

//        @Override
//        public void method() {
//            System.out.println("Test");
//        }
    }

    public static void main(String[] args) {
        var instance = new Implementation();
        instance.method();
    }
}
