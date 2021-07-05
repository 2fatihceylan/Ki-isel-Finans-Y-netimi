package sample;

public class Currency {
    private String currency,currencycode,forexbuying,forexselling,banknotebuying,banknoteselling;

    public Currency(){

    }
    public Currency(String c){

        this.currency=c;
    }


    public Currency(String c,String ccode,String fb,String fs,String bb,String bs){
        this.currency=c;
        this.currencycode=ccode;
        this.forexbuying=fb;
        this.forexselling=fs;
        this.banknotebuying=bb;
        this.banknoteselling=bs;

    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String getForexbuying() {
        return forexbuying;
    }

    public void setForexbuying(String forexbuying) {
        this.forexbuying = forexbuying;
    }

    public String getForexselling() {
        return forexselling;
    }

    public void setForexselling(String forexselling) {
        this.forexselling = forexselling;
    }

    public String getBanknotebuying() {
        return banknotebuying;
    }

    public void setBanknotebuying(String banknotebuying) {
        this.banknotebuying = banknotebuying;
    }

    public String getBanknoteselling() {
        return banknoteselling;
    }

    public void setBanknoteselling(String banknoteselling) {
        this.banknoteselling = banknoteselling;
    }
}
