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

    public void addSale(ArrayList<Employee> employees) {
        allEmployees(employees);
        System.out.println("Digite o id do funcionário que deseja lançar a venda(apenas comissionados):");
        int idToVerify = input.nextInt();
        int code = 0;
        for(Employee e : employees) {
            if(e.getId() == idToVerify && e instanceof Comissioned) {
                code = 1;
                break;
            }
        }
        if(code == 0) System.out.println("Não é possível lançar a venda!");
        else {
            double sale = input.nextDouble();
            double commission = getCommission();
            updateActualPayment(sale * commission);
            System.out.println("Venda lançada com sucesso!");
        }
    }
}
