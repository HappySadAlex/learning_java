public abstract class Client {

    protected double sum = 0;

    public double getAmount() {
        return sum;
    }

    public void put(double amount) {
        //TODO: реализуйте метод и удалите todo
        if(amount >= 0){
            sum += amount;
        }
    }

    public void take(double amount) {
        //TODO: реализуйте метод и удалите todo
        if(amount <= sum){
            sum -= amount;
        }
    }

    protected abstract String getName();
    protected abstract String getInfo();
}
