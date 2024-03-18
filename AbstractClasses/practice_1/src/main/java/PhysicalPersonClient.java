public class PhysicalPersonClient extends Client {

    @Override
    protected String getName() {
        return "Физическое лицо";
    }

    @Override
    protected String getInfo() {
        return "Пополнение и списание происходит без комиссии" + "\nБаланс: " + sum;
    }
}
