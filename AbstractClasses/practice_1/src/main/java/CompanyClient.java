public class CompanyClient extends Client {

    @Override
    public void take(double amount) {
        super.take(amount * 1.01);
    }

    @Override
    protected String getName() {
        return "Юридическое лицо";
    }

    @Override
    protected String getInfo() {
        return "Снятие средств со счета с комиссией 1%" + "\nБаланс: " + sum;
    }
}
