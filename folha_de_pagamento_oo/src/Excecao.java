import java.util.Scanner;

public class Excecao {
    Scanner input = new Scanner(System.in);

    public int loadInt() {
        int auxiliar = 0;
        boolean correctInput = true;

        while(correctInput) {
            try {
                auxiliar = Integer.parseInt(input.next());
                correctInput = false;
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido!");
            }
        }

        return auxiliar;
    }

    public double loadDouble() {
        double auxiliar = 0;
        boolean correctInput = true;

        while(correctInput) {
            try {
                auxiliar = Double.parseDouble(input.next());
                correctInput = false;
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido!");
            }
        }

        return auxiliar;
    }

}
