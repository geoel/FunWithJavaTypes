package nat;


public class Zero extends Nat {

	private Zero() {
		super(0);
	}
	
	/*
	 * This is singleton just cause there's just one zero.
	 */
	public static Zero getInstance(){
		return new Zero();
	}
}
