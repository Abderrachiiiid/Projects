public class GeometricProgression extends Progression {

    protected long base;

    /** Constructs progression 1, 2, 4, 8, 16, ... **/
    public GeometricProgression() {
        this(2, 1);
    } // start at 1 with base of 2

    /** Constructs progression 1, b, b^2, b^3, b^4, ... for base b. **/
    public GeometricProgression(long b) {
        this(b, 1);
    } // start at 1

    /** Constructs geometric progression with arbitrary base and start. **/
    public GeometricProgression(long b, long start) {
        super(start);
        this.base = b;
    }

    /** Multiplies the current value by the geometric base. **/
    @Override
    protected void advance() {
        this.current *= this.base; // multiply current by the geometric base
    }
}
