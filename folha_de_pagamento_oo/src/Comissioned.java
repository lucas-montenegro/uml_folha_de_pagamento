import java.util.ArrayList;

public class Comissioned extends Employee {
    private double commission;

    public double getCommission() { return commission; }

    public void setCommission(double commission) { this.commission = commission; }

    public void addSale(ArrayList<Employee> employees) {
        double doubleAux = 0;
        allEmployees(employees);
        Comissioned employee = new Comissioned();
        System.out.println("Digite o id do funcionário que deseja lançar a venda(apenas comissionados):");
        int idToVerify = getExcecao().loadInt();
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
            while(true) {
                doubleAux = getExcecao().loadDouble();
                if(doubleAux >= 0) {
                    break;
                }
                System.out.println("Digite um valor válido para a venda!");
            }
            double sale = doubleAux;
            double commission = employee.getCommission();
            employee.getGenericPayment().updateActualPayment((sale * commission) / 100, employee);
            System.out.println("Venda lançada com sucesso!");
        }
    }
}
