import java.util.concurrent.CompletableFuture;

public class Main {

    public static CompletableFuture<Boolean> canInitiatePayment() {
        CompletableFuture<Boolean> promise = new CompletableFuture<>();

        // Simulate an asynchronous operation
        new Thread(() -> {
            try {
                // Some asynchronous operation
                Thread.sleep(1000);

                // Simulate a condition
                boolean condition = /* Your condition here, e.g., check if intent is not null */ true;

                // Resolve the promise based on the condition
                promise.complete(condition);
            } catch (InterruptedException e) {
                // Handle exception if needed
                promise.completeExceptionally(e);
            }
        }).start();

        return promise;
    }

    public static void main(String[] args) {
        // Example usage
        canInitiatePayment()
                .thenAccept(result -> System.out.println("Can initiate payment: " + result))
                .exceptionally(throwable -> {
                    System.err.println("An error occurred: " + throwable.getMessage());
                    return null;
                });

        // Keep the main thread alive for the asynchronous operation to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
