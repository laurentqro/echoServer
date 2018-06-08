import java.util.concurrent.Executor;

public class SynchronousExecutor implements Executor {
    @Override public void execute(Runnable runnable) {
        runnable.run();
    }
}
