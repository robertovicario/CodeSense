package src.core.lang;

public abstract class LangBase {
    protected String code;

    public LangBase(String code) {
        this.code = code;
    }

    public abstract long computeTime();

    public abstract long computeSpace();
}
