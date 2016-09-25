

class X {
	protected String str;
}

class Y extends X {

}
class z{
	public static void main(String[] args){
		X[] xa;
		Y[] ya = new Y[10];

		xa = ya;
		ya = (Y[])xa;
	}
}