public class Assalaried extends Employee implements Payment{
        public void updateActualPayment(double money){
            setActualPayment(money);
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
            System.out.printf("Days to Payment: %d\n", daysToPayment);
            setDaysToPayment(daysToPayment);
        }
    }
