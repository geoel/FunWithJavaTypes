package vector;

import nat.Nat;
import nat.Zero;

/*
 * You cannot(cimpiler wise) put an N that does not extend nat here, but zero extends 
 * nat, so narrowing is fine. Also you cannot leave generics out(compile wise).
 */
public class Nil<E> extends GeoVector<E, Zero> {

	private static final long serialVersionUID = 1L;	
	
	public Nil() {
		super(Zero.getInstance());
	}


	@Override
	public <M extends Nat> GeoVector<E, M> getPreviousVec() {
		return null;
	}

}
