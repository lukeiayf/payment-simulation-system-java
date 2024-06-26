package application;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PayPalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter payment data: ");
        System.out.println("Number: ");
        int number = sc.nextInt();
        System.out.println("Date (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), fmt);
        System.out.println("Value: ");
        double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.println("Enter number of installments: ");
        int n = sc.nextInt();

        ContractService contractService = new ContractService(new PayPalService());
        contractService.processContract(contract, n);

        System.out.println("Installments: ");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }


        sc.close();

    }
}