public class IndividualEntrepreneurClient extends Client {

    @Override
    public void put(double amount) {
        if(amount < 1000){
            super.put(amount * 0.99);
        }
        else {
            super.put(amount * 0.995);
        }
    }

    @Override
    protected String getName() {
        return "Индивидуальный предприниматель";
    }

    @Override
    protected String getInfo() {
        return "Пополнение с комиссией 1%, если сумма меньше 1000 рублей, и с комиссией 0,5%," +
                " если сумма больше или равна 1000 рублей" + "\nБаланс: " + sum;
    }
}
