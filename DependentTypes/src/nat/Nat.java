package nat;

public abstract class Nat {
	private final int geoInt;
	
	protected Nat(int intVar){
		this.geoInt = intVar;
	}

	public int getGeoInt() {
		return geoInt;
	}
}
