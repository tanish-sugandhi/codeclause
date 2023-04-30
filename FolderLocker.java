import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FolderLocker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path of the folder to lock: ");
        String folderPath=sc.nextLine();
        System.out.println("Enter the password: ");
        String password = sc.nextLine();

        
        File folderToLock = new File(folderPath);

        try {
            
            if (!folderToLock.exists()) {
                System.out.println("Folder does not exist!");
                return;
            }

           
            File hiddenFolder = new File(folderToLock.getParentFile(), "." + folderToLock.getName());

            if (!hiddenFolder.exists()) {
                hiddenFolder.mkdir();
            }

           
            for (File file : folderToLock.listFiles()) {
                Path encryptedFilePath = Paths.get(hiddenFolder.getAbsolutePath(), file.getName() + ".locked");
                byte[] encryptedFileBytes = encryptFile(Files.readAllBytes(file.toPath()), password);
                Files.write(encryptedFilePath, encryptedFileBytes);
                file.delete();
            }

            System.out.println("Folder locked successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static byte[] encryptFile(byte[] fileBytes, String password) {
        
        return null;
     }
    }
}