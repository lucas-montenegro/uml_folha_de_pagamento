public abstract class Payment {
    public void updateActualPayment(double money, Employee employee){
        employee.setActualPayment(employee.getActualPayment() + money);
    }

    public void calculateNextPayment(PaymentSchedule paymentSchedule, Calendario calendario, Employee employee) {
        int daysToPayment = 0, dayOfPayment = 1;
        int initialDay = calendario.getInitialDay();
        int dayOfWeek = calendario.getDayOfWeek();
        int day = calendario.getDay();
        int month = calendario.getMonth();
        int scheduleOption = employee.getScheduleOption();
        if(scheduleOption == 1) dayOfPayment = employee.getPaymentSchedule().getDayMonth();
        else if(scheduleOption == 2) dayOfPayment = employee.getPaymentSchedule().getDayBiWeekly();
        else if(scheduleOption == 3) dayOfPayment = employee.getPaymentSchedule().getDayWeekly();

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
        employee.setDaysToPayment(daysToPayment);
    }
}
