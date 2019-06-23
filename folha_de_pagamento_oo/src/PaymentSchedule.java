import java.util.ArrayList;
import java.util.Scanner;

public class PaymentSchedule {
    private int dayWeekly;
    private int dayBiWeekly;
    private int dayMonth;

    Scanner input = new Scanner(System.in);

    public int getDayWeekly() {
        return dayWeekly;
    }

    public void setDayWeekly(int dayWeekly) {
        this.dayWeekly = dayWeekly;
    }

    public int getDayBiWeekly() {
        return dayBiWeekly;
    }

    public void setDayBiWeekly(int dayBiWeekly) {
        this.dayBiWeekly = dayBiWeekly;
    }

    public int getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(int dayMonth) {
        this.dayMonth = dayMonth;
    }

    public void joinPaymentSchedule(ArrayList<Employee> employees, ArrayList<PaymentSchedule> paymentSchedule) {
        System.out.println("Digite o id do funcionário que deseja aderir à uma agenda de pagamento:");
        int idToVerify = input.nextInt();
        int code = 0, option;
        for(Employee e : employees) {
            if(e.getId() == idToVerify) {
                for(PaymentSchedule k: paymentSchedule) {
                    System.out.println("Agenda de pagamentos:");
                    System.out.printf("Dia Mensal: %d\n", k.getDayMonth());
                    System.out.printf("Dia Bi-Semanal: %d\n", k.getDayBiWeekly());
                    System.out.printf("Dia Semanal: %d\n", k.getDayWeekly());
                    System.out.printf("Aderir a esta agenda?\n(0) - Não\n(1) - Sim\n");
                    option = input.nextInt();
                    if(option == 1) {
                        e.setPaymentSchedule(k);
                        System.out.println("Qual o tipo de agenda que o funcionário irá seguir?");
                        System.out.printf("(1) - Mensal\n(2) - Bi-semanal\n(3) - Semanal\n");
                        option = input.nextInt();
                        if(option == 1) e.setScheduleOption(1);
                        else if(option == 2) e.setScheduleOption(2);
                        else if(option == 3) e.setScheduleOption(3);
                        code = 0;
                        break;
                    }
                }
                break;
            }
        }
        if(code == 0) {
            System.out.println("Não foi possível aderir à uma agenda de pagamento!");
        }
        else {
            System.out.println("O funcionário agora possui uma nova agenda de pagamento!");
        }
    }


    public void addPaymentSchedule() {
        System.out.println("Digite o dia que será pago mensalmente, podendo escolher até o dia 28, caso deseje que o pagamento seja no último dia útil do mês digite -1:");
        int dayMonth = input.nextInt();
        setDayMonth(dayMonth);

        System.out.println("Digite o dia que será pago bi-semanalmente:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        int dayBiWeekly = input.nextInt();
        setDayBiWeekly(dayBiWeekly);

        System.out.println("Digite o dia que será pago semanalmente:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        int dayWeekly = input.nextInt();
        setDayWeekly(dayWeekly);
    }


}
