
public final class Pair<T, U>
{

	//FROM Professor Pedro Guerreiro
  // see https://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/tuple/ImmutablePair.html
  // and http://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/tuple/Pair.html
  
  public final T _1;
  public final U _2;
  
  public Pair(final T x, final U y)
  {
    _1 = x;
    _2 = y;
  }
  
  public static <A, B> Pair<A, B> of(final A x, final B y)
  {
    return new Pair<A, B>(x, y);
  }
  
  @Override
  public String toString()
  {
    return "<" + _1 + "," + _2 + ">";
  }
  
}
