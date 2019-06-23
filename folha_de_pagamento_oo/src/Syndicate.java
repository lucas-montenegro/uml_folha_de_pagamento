import java.util.ArrayList;
import java.util.Scanner;

public class Syndicate {
    private int optionSyndicate;
    private int idSyndicate;
    private double syndicateTax;
    private double serviceTax;
    Scanner input = new Scanner(System.in);

    public int getIdSyndicate() {
        return idSyndicate;
    }

    public void setIdSyndicate(int idSyndicate) {
        this.idSyndicate = idSyndicate;
    }

    public int getOptionSyndicate() {
        return optionSyndicate;
    }

    public void setOptionSyndicate(int optionSyndicate) {
        this.optionSyndicate = optionSyndicate;
    }

    public double getSyndicateTax() {
        return syndicateTax;
    }

    public void setSyndicateTax(double syndicateTax) {
        this.syndicateTax = syndicateTax;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
    }

    public void resetServiceTax() {
        setSyndicateTax(0);
    }

    public void addServiceTax(ArrayList<Employee> employees) {
        System.out.println("Digite o id do funcionário que deseja lançar a taxa de serviço:");
        int idToVerify = input.nextInt();
        int code = 0;
        for(Employee e : employees) {
            if(e.getId() == idToVerify) {
                code = 1;
                break;
            }
        }
        if(code == 0) System.out.println("Não é possível lançar a taxa de serviço!");
        else {
            System.out.println("Digite a taxa de serviço do funcionário:");
            double serviceTax = input.nextDouble();
            setServiceTax(serviceTax);
            System.out.println("Taxa de serviço adicionada com sucesso!");
        }
    }
}
