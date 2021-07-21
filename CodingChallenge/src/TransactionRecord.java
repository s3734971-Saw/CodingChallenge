import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TransactionRecord {

    public static ArrayList<Transaction> transactionRecords = new ArrayList<Transaction>();

    public ArrayList<Transaction> getTransactionRecords() {
        return transactionRecords;
    }

    public static void readTransactionsFromCSV(String filename) {

        try {
            File file = new File( filename );

            if (file.exists() && !file.isDirectory()) {

                Scanner sc = new Scanner( file );

                String header = sc.nextLine();

                while (sc.hasNext()) {
                    String row = sc.nextLine();
                    String[] reform = row.split( "," );
                    String transactionId = reform[0];
                    String fromAccountId = reform[1];
                    String toAccountId = reform[2];
                    String createAtDay = reform[3].substring( 1, 3 );
                    String createAtMonth = reform[3].substring( 4, 6 );
                    String createAtYear = reform[3].substring( 7, 11 );
                    String createTime = reform[3].substring( 12 );
                    String number = reform[4];
                    String transactionType = reform[5];
                    String relatedTransaction = "";
                    try {
                        double amount = Double.parseDouble( number );
                        LocalDate createAtDate = LocalDate.parse( createAtYear + "-" + createAtMonth + "-" + createAtDay );
                        LocalTime createAtTime = LocalTime.parse( createTime );
                        LocalDateTime createDateAndTime = LocalDateTime.of( createAtDate, createAtTime );

                        if (transactionType.contains( "REVERSAL" )) {
                            for (Transaction ti : transactionRecords) {
                                if (((ti.getToAccountId().equals( fromAccountId )) && (ti.getFromAccountId().equals( toAccountId ))) && (ti.getAmount() == amount))
                                    relatedTransaction = ti.getTransactionId();
                            }
                        } else {
                            relatedTransaction = " ";
                        }

                    // adding each transaction read from csv file to the list of transaction.
                        transactionRecords.add( new Transaction( transactionId, fromAccountId, toAccountId, createDateAndTime, amount, transactionType, relatedTransaction ) );
                    } catch (Exception e) {
                        System.out.println( "Record data parse error." );
                        e.printStackTrace();

                    }

                }
            } else {
                System.out.println( "File Not Found!" );
                System.out.println( "WARNING!! Please Provide Proper File Name & File Directory" );

                System.exit( 0 );
            }

        } catch (FileNotFoundException e) {
            System.out.println( "Something went wrong with the Records File" );
            e.printStackTrace();

        }
    }
}



