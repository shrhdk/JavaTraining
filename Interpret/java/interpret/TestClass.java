package interpret;

public class TestClass {

    public byte byte_;
    public short short_;
    public int int_;
    public long long_;
    public float float_;
    public double double_;
    public char char_;
    public boolean boolean_;
    public String string;
    public Object object;
    public TestEnum enum_;
    public String[] array;

    public TestClass(byte byte_, short short_, int int_, long long_, float float_, double double_, char char_, boolean boolean_, String string, Object object, TestEnum enum_, String[] array) {
        this.byte_ = byte_;
        this.short_ = short_;
        this.int_ = int_;
        this.long_ = long_;
        this.float_ = float_;
        this.double_ = double_;
        this.char_ = char_;
        this.boolean_ = boolean_;
        this.string = string;
        this.object = object;
        this.enum_ = enum_;
        this.array = array;
    }

    public enum TestEnum {RED, BLUE, YELLOW,;}
}