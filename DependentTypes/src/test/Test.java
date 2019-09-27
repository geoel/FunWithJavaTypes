package test;

import nat.Nat;
import nat.Successor;
import nat.Zero;
import vector.Cons;
import vector.GeoVector;
import vector.Nil;

public class Test {

	public static <E,M extends Nat> GeoVector<E,Successor<M>> push(
			GeoVector<E,? extends M> vector, E a){
		return new Cons<E, M>(a, vector);
	}
	
	public static <E,M extends Nat> GeoVector<E,M> remove(
			GeoVector<E,Successor<M>> vector){
		vector.remove(vector.elementAt(0));
		return vector.getPreviousVec();
	}
	
	public static <E, M extends Nat> E head(GeoVector<E,Successor<M>> nonEmptyVector){
		/*
		 * Cheating by reusing the logic for vector implementation, but does not affect 
		 * our type checking, we would normally get array index out of bound exception 
		 * at runtime if we could apply this method to an empty vector. 
		 */
		
		return nonEmptyVector.elementAt(0);		
	}
	
    public static <E, M extends Nat> GeoVector<E,? extends Successor<? extends Nat>> fill(
            GeoVector<E,? extends Nat> vec, int i, E e) {
    	
        if (i == 1){
        	return push(vec,e);
        }
        
        return fill(push(vec, e),i-1,e);
    }
    
	public static void main(String[] args) {
		/*
		 * I can only instantiate GeoVector<Exception, Zero> as nil now due to changes in 
		 * the signature of Cons class
		 */
		GeoVector<Exception, Zero> initial = new Nil<Exception>();
		
		head(initial);    //fails, list is empty
		
		Exception e = new RuntimeException();

		head(push(initial,e));    //works, added an element
		remove(remove(push(push(initial,e),e)));    //works
		remove(remove(remove(push(push(initial,e),e))));    //fails
		head(remove(push(push(initial,e),e)));    //works
		head(push(push(push(push(initial,e),e),e),e));    //works
		head(remove(push(initial,e)));    //fails, added and removed
		
		/*
		 * But unfortunately this will give a warning only because I pass it the raw type
		 * (I remove dependencies from it). They allowed this for compatibility with JVM 
		 * 1.4 and older, at runtime type dependencies are gone anyway. I am curious 
		 * what's your opinion. The promised they'd eventually disallow this when they 
		 * released JVM 5 but here we are at JVM 8 and I can still compile this code :(
		 * 
		 * If I think of this in TT, is like saying that GeoVector is a subtype of 
		 * \Pi(n:nat, GeoVector (S n)). This is to say that I can write any GeoVector as 
		 * \lambda n:nat.GeoVector(S n) if the subtyping is subsumptive or that there is 
		 * some sort of weird not subsumptive coercion that I cannot understand. Anyway, 
		 * this is an example of terrible, disastrous break of compositionality, in the 
		 * sense that the line of code 5 lines bellow compiles.
		 */
		GeoVector gv2 = new Cons(e, initial);
		head(gv2);
		
		gv2 = initial;
		head(gv2);    // :(
		
		GeoVector<Exception, Successor<Zero>> gv3 = new Cons<Exception, Zero>(e, initial);
		head(gv3);    // works
		
		GeoVector<Exception, Successor<Successor<Zero>>> gv4 = 
				new Cons<Exception, Successor<Zero>>(e, gv3);
		head(gv4);
	}

}
