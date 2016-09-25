class PartFour {
    public static void main(String[] args) {
        SubClass subVariable = new SubClass();
        
        ((SuperClass) subVariable).method(0);//call subclass method, even cast
        SuperClass sc = ((SuperClass) subVariable);
        sc.method(0);
        //subVariable.a(); 
        SuperClass superVariable = new SuperClass();
        //((SubClass) superVariable).method(0);//Run-time error: super cannot be cast to sub

        SuperClass hybrid = new SubClass();//type: super; class: sub
        ((SuperClass) hybrid).method(0);//call subclass method
        
        //(SubClass)hybrid.a();
        //hybrid.a();
        
        
        //hybrid = (SubClass)hybrid; //static type of hybrid can never change
        //hybrid.a();
        
       
        
    }
}