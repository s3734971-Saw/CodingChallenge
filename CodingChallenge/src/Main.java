import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class which handles the inputs given by a user to analyses financial transaction records
 * for a particular account in a given time frame. It utilises method from other class to read the
 * data from csv file provided and perform the calculations from that data as per input entered by user.
 */

public class Main {

    private String FilePath = "Tests/tests.csv";

    ArrayList<Transaction> record = TransactionRecord.transactionRecords;

    private void run() {
        TransactionRecord.readJobsFromCSV( FilePath );
    }

    public static void main(String[] args) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        Transaction tr = new Transaction();
        Scanner sc = new Scanner( System.in );
        Main m = new Main();
        m.run();
        System.out.println( "AccountId: " );
        String s1 = sc.nextLine();
        tr.setFromAccountId( s1 );
        System.out.println( "From: " );
        String s2 = sc.nextLine();
        LocalDateTime fromDate = LocalDateTime.parse( s2, DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );
        System.out.println( "To: " );
        String s3 = sc.nextLine();
        LocalDateTime toDate = LocalDateTime.parse( s3, DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );
        System.out.println( "\nOUTPUT: " );
        m.checkTransactions( s1, fromDate, toDate );
        sc.close();

    }

    /**
     * Method to check the transactions. Accepts the accountID in the form of string and two LocalDateTime arguments
     * which represents the from and to date to enter to look after the transactions in a given time frame
     * and perform the calculation to find the relative account balance of that account. It does count the number of
     * transactions recorded everytime when funds were transferred to / from an account in a given time frame
     * but the calculations to find the relative account balance are done only when the transaction type is "Payment",
     * and if the transaction type is "Reversal" the transaction is recorded and counted but its omitted for the
     * calculations purpose. The program will terminate by giving you the output as required which includes the number
     * of transactions for that particular account and the relative account balance, but it does not account for funds
     * that were in that account prior to the timeframe.
     */
    private void checkTransactions(String accountId, LocalDateTime from, LocalDateTime to) {

        int numOfTran = 0;
        double finalAccBalance;
        double transferFromAcc = 0.0d;
        double transferToAcc = 0.0d;

        for (Transaction t : record) {
            if ((t.getCreateAt().isAfter( from ) || (t.getCreateAt().isEqual( from ))) && ((t.getCreateAt().isBefore( to ) || t.getCreateAt().isEqual( to )))) {
                if ((t.getFromAccountId().contains( accountId ))) {
                    numOfTran++;

                    if ((t.getTransactionType().contains( "PAYMENT" ))) {
                        t.setAmount( transferFromAcc - t.getAmount() );
                        transferFromAcc = t.getAmount();
                    }
                }
                if (t.getToAccountId().contains( accountId )) {
                    numOfTran++;
                    if ((t.getTransactionType().contains( "PAYMENT" ))) {
                        t.setAmount( transferToAcc + t.getAmount() );
                        transferToAcc = t.getAmount();
                    }
                }
            }
        }
        finalAccBalance = transferFromAcc + transferToAcc;
        System.out.println( "Relative transferFromAcc for the period is: " + NumberFormat.getCurrencyInstance().format( finalAccBalance ) );
        System.out.println( "Number of transactions included is: " + numOfTran );
    }

}
