package bank.management.system;

class CurrentAcc extends AbstractAccount {
    @Override
    public int totalTransactions(){
        return 1000;
    }

    public int totalOverdraft(){
        return 500000;
    }
}
