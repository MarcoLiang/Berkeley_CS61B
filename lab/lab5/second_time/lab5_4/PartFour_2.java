class PartFour_2 {
    public static void main(String[] args) {
        SubClass subVariable = new SubClass();
        SuperClass sc = ((SuperClass) subVariable);//equal to SuperClass sc = subVariable
        sc.method(0);
        SubClass sub = (SubClass)sc;
        sub.a();
        sub.method(0);
        SuperClass superC = new SuperClass();
        superC.method(0);
        
        
        
    }
}