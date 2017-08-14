package eu.forapp.generic;

public class Pair<A extends Comparable<A>, B extends Comparable<B>> implements Comparable<Pair<A, B>> {
    private final A a;
    private final B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;
        if (!a.equals(pair.a)) return false;
        return b.equals(pair.b);
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }

    @Override
    public int compareTo(Pair<A, B> pair) {
        int result = a.compareTo(pair.a);
        if(result == 0){
            result = b.compareTo(pair.b);
        }
        return result;
    }
}
