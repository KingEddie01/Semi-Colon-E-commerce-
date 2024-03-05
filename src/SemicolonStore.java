import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SemicolonStore {
    private static final LocalDate date = LocalDate.now();

    private static String CashierName;

    private static double discount1;


    private static int counter;

    private static double vat;

    private static double total;

    private static double finalDiscount;
    private static double TotalPayment;
    private static double FinalBalance;
    private static String customerName;


    private static double BillTotal;

    private static double subTotal;
    private static double AmountPaid;

    static final Scanner input = new Scanner(System.in);
    public static ArrayList<String> purchasedItems = new ArrayList<>();
    public static ArrayList<Double> unitPriceOfItem = new ArrayList<>();
    public static ArrayList<Integer> numberOfItemsPurchased = new ArrayList<>();

    public static void main(String[] args) {
        purchase1();

    }

    public static void purchase1() {
        System.out.println("What is customer's name ");
        String name = input.nextLine();
        customerName = name;
        if (name.matches("^\\D+$*")) {
            purchase2();
        } else {
            System.out.println("invalid name");
            purchase1();
        }
    }

    public static void purchase2() {
        System.out.println("What did the user buy? ");
        String items = input.nextLine();
        if (items.matches("\\D*")) {
            purchasedItems.add(items);
            purchase3();
        } else {
            System.out.println("invalid input");
            purchase2();
        }
    }

    public static void purchase3() {
        System.out.println("How many pieces? ");
        String quantity = input.nextLine();
        if (quantity.matches("^\\d{1,9}")) {
            numberOfItemsPurchased.add(Integer.parseInt(String.valueOf(Integer.parseInt(quantity))));
            purchase4();
        } else {
            System.out.println("Invalid input");
            purchase3();
        }
    }

    public static void purchase4() {
        System.out.println("How much per unit? ");
        String unitPrice = input.nextLine();
        if (unitPrice.matches("\\d{1,9}\\.\\d{1,9}")) {
            unitPriceOfItem.add(Double.parseDouble(String.valueOf(Double.parseDouble(unitPrice))));
            double price = Double.parseDouble(unitPrice);
            purchase5();
        } else {
            System.out.println("Invalid input");
            purchase4();
        }
    }

    public static void purchase5() {
        System.out.println("Add more items? ");
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            purchase2();
        }
        if (response.equalsIgnoreCase("No")) {
            CashierDetails();
        }
    }

    public static void CashierDetails() {
        System.out.println("What is your name? ");
        String cashierName = input.nextLine();
        CashierName = cashierName;
        if (cashierName.matches("^\\D+$*")) {
            Discount();
        } else {
            System.out.println("Invalid input");
            CashierDetails();
        }
    }

    public static void Discount() {
        System.out.println("How much discount will customer get? ");
        String discount = input.nextLine();
        discount1 = Double.parseDouble(discount);
        if (discount.matches("^\\d*")) {
            Receipt();
        } else {
            System.out.println("Invalid input");
            Discount();
        }
    }


    public static void Receipt() {
        storeDetails();
        System.out.println("=".repeat(70));
        System.out.printf("%10s%10s%10s%11s", "ITEM", "QTY", "PRICE", "\tTOTAL(NGN)");
        System.out.print("\n" + "-".repeat(70));
        fillReceipt();
    }

    public static void fillReceipt() {
        for (counter = 0; counter < purchasedItems.size(); counter++) {
            total = numberOfItemsPurchased.get(counter) * unitPriceOfItem.get(counter);
            System.out.printf("%n%10s%10d%11.2f%11.2f", purchasedItems.get(counter), numberOfItemsPurchased.get(counter), unitPriceOfItem.get(counter), total);
            subTotal += total;
        }
        finalDiscount = (discount1 * subTotal) / 100;
        vat = (subTotal * 17.50) / 100;
        BillTotal = (subTotal - finalDiscount) + vat;

        System.out.println();
        Calculate2();

    }


    public static void Calculate2() {
        System.out.print( "-".repeat(70));
        System.out.printf("%n%30s%11.2f%n%30s%11.2f%n%30s%11.2f", "SUB TOTAL  : ", subTotal, "DISCOUNT : ", finalDiscount, "VAT @ 17.50 : ", vat);
        System.out.println("\n" + "=".repeat(70));
        System.out.printf("%30s%11.2f", "BILL TOTAL : ", BillTotal);
        System.out.println("\n" + "=".repeat(70));
        NotReceipt();

    }

    public static void NotReceipt() {
        System.out.printf("%s%2.2f", "THIS IS NOT A RECEIPT KINDLY PAY ", BillTotal);
        System.out.println("\n" + "=".repeat(70));
        Payment();
    }

    public static void Payment() {
        System.out.println("HOW MUCH DID CUSTOMER GIVE YOU ? ");
        TotalPayment = input.nextDouble();
        if (TotalPayment >= BillTotal) {
            FinalBalance = TotalPayment - BillTotal;
            Last_Display();
        } else {
            System.out.println("KINDLY PAY FULL AMOUNT");
            Payment();
        }

    }

    public static void Last_Display() {
        storeDetails();
        System.out.println("=".repeat(70));
        System.out.printf("%10s%10s%10s%11s", "ITEM", "QTY", "PRICE", "\tTOTAL(NGN)");
        System.out.print("\n" + "-".repeat(70));
        for (counter = 0; counter < purchasedItems.size(); counter++) {
            total = numberOfItemsPurchased.get(counter) * unitPriceOfItem.get(counter);
            System.out.printf("%n%10s%10d%11.2f%11.2f", purchasedItems.get(counter), numberOfItemsPurchased.get(counter)
                    , unitPriceOfItem.get(counter), total);
        }
        System.out.print("\n" + "-".repeat(70));
        System.out.printf("%n%30s%11.2f%n%30s%11.2f%n%30s%11.2f", "SUB TOTAL  : ", subTotal, "DISCOUNT : ",
                finalDiscount, "VAT @ 17.50 : ", vat);
        System.out.println("\n" + "=".repeat(70));
        System.out.printf("%30s%11.2f", "BILL TOTAL : ", BillTotal);
        System.out.println("\n" + "=".repeat(70));
        System.out.printf("%30s%11.2f", "AMOUNT PAID : ", TotalPayment);
        System.out.printf("%n%30s%11.2f", "BALANCE : ", FinalBalance);
        System.out.printf("%n%s","\t\tTHANKS FOR YOUR PATRONAGE");
        }


    public static void  storeDetails(){
        String telephoneNumber = "08166063784";
        String branch = "MAIN BRANCH";
        String location = "321 HERBERT MACAULAY WAY, SABO YABA, LAGOS";
        String storeName = "SEMICOLON STORE";
        System.out.println("STORE NAME : " + storeName + "\nBRANCH :" + branch + "\nLOCATION : " + location +
                "\nTELEPHONE : " + telephoneNumber + "\nDATE : " + date +
                "\nCASHIER NAME : " + CashierName + "\nCUSTOMER NAME : " + customerName);
    }

}






















