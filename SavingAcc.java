package bank.management.system;

public class SavingAcc extends AbstractAccount {
    @Override
    public int totalTransactions(){
        return 5;
    }

    @Override
    public int totalOverdraft(){
        return 0;
    }
}
