package src.core.lang;

public abstract class CoreBase {
    protected String code;

    public CoreBase(String code) {
        this.code = code;
    }

    public abstract long computeTime();

    public abstract long computeSpace();
}
