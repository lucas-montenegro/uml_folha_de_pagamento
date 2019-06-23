public interface Payment {
    void updateActualPayment(double money);
    void calculateNextPayment(PaymentSchedule paymentSchedule, Calendario calendario);
}
