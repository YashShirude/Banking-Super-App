package bank.management.system;

public abstract class AbstractAccount {
    public static int minBalance = 10000;

    public static float rate = 6.25f;
    public static float tenureInFloat;

    public static float fdCalculator(int principal, int tenure) {
        tenureInFloat = (float) tenure / 12;
        return principal + (principal*rate*tenureInFloat/100);
    }

    public static float rdCalculator(int principal, int tenure) {
        int totalPrincipal = principal * tenure;
        return (float)(totalPrincipal * Math.pow(1 + rate/100, tenureInFloat));
    }

    abstract int totalOverdraft();
    abstract int totalTransactions();
}
