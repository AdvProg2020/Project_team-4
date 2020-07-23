package Model;

public class Wallet {
    double credit;
    double leastCredit;
    Account account;

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void getMoney(double amount){
        if(credit - amount > leastCredit)
            credit -= amount;
    }
    public void charge(double amount){
        credit += amount;
    }
}
