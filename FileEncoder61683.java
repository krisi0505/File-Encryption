import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileEncoder61683 implements FileEncoderFN{
    private boolean primeNumber(int number) {
                if (number == 0) {
                    return false;
                }
                if (number == 1 || number == 2){
                    return true;
                }
                
                boolean isPrime = true;
		int limit = (int) Math.sqrt(number) + 1;
		
                for (int i = 2; i <= limit; i++) {
			if (number % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}
    
    @Override
    public void encode(String sourceFile, String destinationFile, LinkedList<Character> key) {
        try {
            FileInputStream source = new FileInputStream(sourceFile);
            File file = new File(sourceFile);
            FileOutputStream destination = new FileOutputStream(destinationFile);
            int b = 0;
            
            for(int i = 0; i < file.length(); i++)
            {
                b = source.read();
                
                if(primeNumber(i)){
                    destination.write(b);
                } else {
                    destination.write(key.get(b));
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileEncoder61683.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileEncoder61683.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    @Override
    public void decode(String encodedFile, String destinationFile, LinkedList<Character> key) {
        FileInputStream source = null;
        try {
            source = new FileInputStream(encodedFile);
            File file = new File(encodedFile);
            FileOutputStream destination = new FileOutputStream(destinationFile);
            int b=0;
            
            for(int i = 0; i < file.length(); i++)
            {
                b = source.read();
                
                if(primeNumber(i)){
                    destination.write(b);
                } else {
                    destination.write(key.indexOf((char)b));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileEncoder61683.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileEncoder61683.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                source.close();
            } catch (IOException ex) {
                Logger.getLogger(FileEncoder61683.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
