import java.util.ArrayList;

public class Comissioned extends Employee implements Payment{
    private double commission;

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void updateActualPayment(double money) {
        setActualPayment(getActualPayment() + money);
    }

    public void calculateNextPayment(PaymentSchedule paymentSchedule, Calendario calendario) {
        int daysToPayment = 0, dayOfPayment = 1;
        int initialDay = calendario.getInitialDay();
        int dayOfWeek = calendario.getDayOfWeek();
        int day = calendario.getDay();
        int month = calendario.getMonth();
        int scheduleOption = getScheduleOption();
        if(scheduleOption == 1) dayOfPayment = getPaymentSchedule().getDayMonth();
        else if(scheduleOption == 2) dayOfPayment = getPaymentSchedule().getDayBiWeekly();
        else if(scheduleOption == 3) dayOfPayment = getPaymentSchedule().getDayWeekly();

        if (scheduleOption == 3) {
            if (calendario.getDayOfWeek() % 7 == dayOfPayment % 7) {
                daysToPayment = 7;
            } else if ((dayOfWeek % 7) > (dayOfPayment % 7)) {
                daysToPayment = (dayOfPayment - (dayOfWeek % 7)) + 14;
            } else if ((dayOfWeek % 7) < (dayOfPayment % 7)) {
                daysToPayment = (dayOfPayment - (dayOfWeek % 7)) + 7;
            }
        } else if (scheduleOption == 2) {
            if (dayOfWeek % 7 == dayOfPayment % 7) {
                daysToPayment = 14;
            } else if ((dayOfWeek % 7) > (dayOfPayment % 7)) {
                daysToPayment = (dayOfPayment - (dayOfWeek % 7)) + 21;
            } else if ((dayOfWeek % 7) < (dayOfPayment % 7)) {
                daysToPayment = (dayOfPayment - (dayOfWeek % 7)) + 14;
            }
        } else if (scheduleOption == 1) {
            int [][] calendary = calendario.getCalendary();
            if (month < 12) {
                if (dayOfPayment == -1) {
                    daysToPayment = (calendary[month - 1][2] - day) + calendary[month][1];
                } else {
                    daysToPayment = (calendary[month - 1][2] - day) + dayOfPayment;
                }
            } else {
                int auxDayOfWeek = dayOfWeek + (31 - day) + 1;
                calendario.initializeCalendary(auxDayOfWeek);
                if (dayOfPayment == -1) {
                    daysToPayment = (31 - day) + calendary[1][1];
                } else {
                    daysToPayment = (31 - day) + dayOfPayment;
                }
                calendario.initializeCalendary(initialDay);
            }
        }
        setDaysToPayment(daysToPayment);
    }

    public void addSale(ArrayList<Employee> employees) {
        allEmployees(employees);
        Comissioned employee = new Comissioned();
        System.out.println("Digite o id do funcionário que deseja lançar a venda(apenas comissionados):");
        int idToVerify = input.nextInt();
        int code = 0;
        for(Employee e : employees) {
            if(e.getId() == idToVerify && e instanceof Comissioned) {
                employee = (Comissioned) e;
                code = 1;
                break;
            }
        }
        if(code == 0) System.out.println("Não é possível lançar a venda!");
        else {
            System.out.println("Digite o valor da venda:");
            double sale = input.nextDouble();
            double commission = employee.getCommission();
            employee.updateActualPayment((sale * commission) / 100);
            System.out.println("Venda lançada com sucesso!");
        }
    }
}
