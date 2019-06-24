import java.util.ArrayList;

public class Hourly extends Employee implements Payment {
    public void updateActualPayment(double money){
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
                int auxDayOfWeek = (dayOfWeek + (31 - day) + 1) % 7;
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


    public void timecard(ArrayList<Employee> employees) {
        allEmployees(employees);
        Hourly employee = new Hourly();
        System.out.println("Digite o id do funcionário que deseja lançar o cartão de ponto(apenas horistas):");
        int idToVerify = input.nextInt();
        int code = 0;
        for(Employee e : employees) {
            if(e.getId() == idToVerify && e instanceof Hourly) {
                employee = (Hourly) e;
                code = 1;
                break;
            }
        }
        if(code == 0) System.out.println("Não é possível lançar o cartão de ponto!");
        else {
            double money = 0;
            double salary = employee.getSalary();
            System.out.println("Digite a hora de entrada do funcionário:");
            int entry_hour = input.nextInt();
            System.out.println("Digite o minuto de entrada do funcionário:");
            int entry_minute = input.nextInt();
            System.out.println("Digite a hora de saída do funcionário:");
            int exit_hour = input.nextInt();
            System.out.println("Digite o minuto de saída do funcionário:");
            int exit_minute = input.nextInt();

            int total_hours;

            if (exit_hour == entry_hour) {
                total_hours = 24;
            } else if (entry_hour > exit_hour) {
                total_hours = 24 - entry_hour + exit_hour;
            } else {
                total_hours = exit_hour - entry_hour;
            }
            if (entry_minute > exit_minute) {
                total_hours--;
            }
            if (total_hours > 8) {
                money += salary * 8 + (salary * 1.5 * (total_hours - 8));
            } else {
                money += salary * total_hours;
            }

            employee.updateActualPayment(money);
            System.out.println("Cartão de ponto adicionado com sucesso!");
        }
    }

}
