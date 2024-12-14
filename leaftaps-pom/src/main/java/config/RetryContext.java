package config;

public class RetryContext {

    private static ThreadLocal<Boolean> isRetry = ThreadLocal.withInitial(() -> false);
    private static ThreadLocal<Integer> retryAttempt = ThreadLocal.withInitial(() -> 0);

    public static boolean isRetry() {
        return isRetry.get();
    }

    public static void setRetry(boolean retry) {
        isRetry.set(retry);
    }

    public static int getRetryAttempt() {
        return retryAttempt.get();
    }

    public static void incrementRetryAttempt() {
        retryAttempt.set(retryAttempt.get() + 1);
    }

    public static void clear() {
        isRetry.remove();
        retryAttempt.remove();
    }
}
