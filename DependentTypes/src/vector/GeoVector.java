package vector;
import java.util.Vector;

import nat.Nat;
import nat.Successor;
import nat.Zero;


public abstract class GeoVector<E, N extends Nat> extends Vector<E> {

	private static final long serialVersionUID = 1L;
	protected Nat length;
	
	protected GeoVector(Successor<Nat> size) {
		/*
		 * This just creates a vector with a given initial cap for optimization purposes.
		 */
		super(size.getGeoInt());
		this.length = size;
	}
	
	protected GeoVector(Zero zero) {
		super(zero.getGeoInt());
		this.length = zero;
	}

	@Override
	public int size(){
		return this.length.getGeoInt();
	}
	
	public abstract <M extends Nat> GeoVector<E, M> getPreviousVec();
}
