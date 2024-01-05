import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExercicioDeArquivos {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Products> list = new ArrayList<>();

        System.out.println("Enter file path: ");
        String sourceFileStr = sc.nextLine();

        File sourceFile = new File(sourceFileStr);

        //System.out.println(sourceFile);

        String sourceFolderStr = sourceFile.getParent();

       // System.out.println(sourceFolderStr);

        boolean success = new File(sourceFolderStr + "\\out").mkdir();

        String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

        System.out.println(targetFileStr);

        try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
            String itemCsv = br.readLine();
            while (itemCsv != null) {
                String[] fields = itemCsv.split(",");
                String name = fields[0];
                double price = Double.parseDouble(fields[1]);
                int quantity =  Integer.parseInt(fields[2]);
                list.add(new Products(name, price, quantity));
                itemCsv = br.readLine();

            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
                for (Products item: list){
                    bw.write(item.getName() + "," + String.format("%.2f", item.total()));
                    bw.newLine();
                }
                System.out.println(targetFileStr + " CREATED");
            }catch (IOException e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }


        sc.close();
    }
}
