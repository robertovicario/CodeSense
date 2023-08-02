package src.runner.lang;

public abstract class RunnerBase {
    protected String code;

    public RunnerBase(String code) {
        this.code = code;
    }

    public abstract long computeTime();

    public abstract long computeSpace();
}
