public class Level {
    private int amount;
    private final int[] variants = new int[]{500, 1000, 2000, 3000, 5000, 10000, 15000,
            25000, 50000, 100000, 200000, 400000, 800000, 1500000, 3000000};

    public Level() {
        amount = 0;
    }

    public void increment() {
        if (amount < variants.length)
            amount++;
    }

    public int getAmount() {
        return variants[amount];
    }
}
