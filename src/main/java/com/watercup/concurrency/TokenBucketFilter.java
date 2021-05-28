package com.watercup.concurrency;

public class TokenBucketFilter {

    private int MAX_TOKENS;

    private long lastRequestTime;
    long possibleTokens;

    public TokenBucketFilter(int capacity) {
        this.MAX_TOKENS = capacity;
        this.lastRequestTime = System.currentTimeMillis();
    }

    public synchronized void getToken() throws InterruptedException {
        long now = System.currentTimeMillis();
        possibleTokens += (now - lastRequestTime) / 1000;

        if (possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        if (possibleTokens == 0) {
            Thread.sleep(1000);
        } else {
            possibleTokens--;
        }

        lastRequestTime = System.currentTimeMillis();
        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (lastRequestTime/1000) + " second" );
    }
}
