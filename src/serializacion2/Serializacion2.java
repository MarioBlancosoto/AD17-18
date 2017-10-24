
package serializacion2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mblancosoto
 */
public class Serializacion2 {

        public static String[] cod ={"p1","p2","p3"};
        public static String[] desc ={"parafusos","cravos","tachas"};
        public static double[] prezo ={3.0,4.0,5.0};
        
    public static void main(String[] args) {
        Product obj1 = new Product(cod[0],desc[0],prezo[0]);
        Product obj2 = new Product(cod[1],desc[1],prezo[1]);
        Product obj3 = new Product(cod[2],desc[2],prezo[2]);
       
        escribirObx(obj1,obj2,obj3,"/home/local/DANIELCASTELAO/mblancosoto/NetBeansProjects/Serializacion2/serial.txt");
        
        lerObx("/home/local/DANIELCASTELAO/mblancosoto/NetBeansProjects/Serializacion2/serial.txt");
    }
    
    public static void escribirObx(Product p1,Product p2,Product p3,String fichero){
       
        
        
            try {
                ObjectOutputStream ruta = new ObjectOutputStream(new FileOutputStream(fichero));
            
              ruta.writeObject(p1);
              ruta.writeObject(p2);
              ruta.writeObject(p3);
              ruta.writeObject(null);
              ruta.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    
    public static void lerObx(String fichero){
           
            try {
                ObjectInputStream ler = new ObjectInputStream(new FileInputStream(fichero));
                Product aux = (Product) ler.readObject();
                while(aux!=null){
                    System.out.println(aux.toString());
                    aux = (Product) ler.readObject();
                    
                }
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
}
