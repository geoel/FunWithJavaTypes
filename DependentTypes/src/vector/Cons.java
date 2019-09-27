package vector;

import nat.Nat;
import nat.Successor;

/*
 * You cannot(compiler wise) put an N that does not extend nat here, but successor 
 * extends nat, so narrowing is fine. Also you cannot leave generics out(compile wise).
 */
public class Cons<E, M extends Nat> extends GeoVector<E, Successor<M>> {

	private static final long serialVersionUID = 1L;
	private final GeoVector<E,? extends M> previousVec;
	
	public Cons(E a, GeoVector<E,? extends M> vector) {
		super(new Successor<Nat>(vector.length));
		this.previousVec = vector;
		vector.add(a);
	}
 
	@Override
	public GeoVector<E, ? extends M> getPreviousVec() {
		return this.previousVec;
	}
}
