package nat;


public class Successor<N extends Nat> extends Nat {

	private N previous;
	
	public Successor(N nat) {
		super(nat.getGeoInt() + 1);
		this.previous = nat;
	}
}
