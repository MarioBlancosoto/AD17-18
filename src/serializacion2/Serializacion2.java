
package serializacion2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

/**
 *
 * @author mblancosoto
 */
public class Serializacion2 {
        static XMLOutputFactory factory =  XMLOutputFactory.newInstance();
        static XMLStreamWriter xw;
        public static String[] cod ={"p1","p2","p3"};
        public static String[] desc ={"parafusos","cravos","tachas"};
        public static double[] prezo ={3.0,4.0,5.0};
        static Product aux;
        static  String ruta = "/home/local/DANIELCASTELAO/mblancosoto/NetBeansProjects/Serializacion2/produtos.xml";
       
    public static void main(String[] args) {
        Product obj1 = new Product(cod[0],desc[0],prezo[0]);
        Product obj2 = new Product(cod[1],desc[1],prezo[1]);
        Product obj3 = new Product(cod[2],desc[2],prezo[2]);
       
      
        
        lerObx("/home/local/DANIELCASTELAO/mblancosoto/NetBeansProjects/Serializacion2/serial.txt");
        
    }
    
 
    
    
    public static void lerObx(String fichero){
           
            try {
                ObjectInputStream ler = new ObjectInputStream(new FileInputStream(fichero));
                 aux = (Product) ler.readObject();
                 xw = factory.createXMLStreamWriter(new FileWriter(ruta));
                 xw.writeStartDocument("1.0");
                 xw.writeStartElement("Produtos");
                 
                 
                 
                while(aux!=null){
                    System.out.println(aux.toString());
                    escribirXML(ruta,aux);
                    aux = (Product) ler.readObject();
            
                    
                }
                xw.writeEndElement();
                xw.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
    
    
    public static void escribirXML(String ruta,Product aux){
        
            
            try {
              
                
                
                
                xw.writeStartElement("Produto");
                xw.writeAttribute("Codigo ",aux.getCodigo());
                xw.writeStartElement("Nome");
                xw.writeCharacters(aux.getDescripcion());
                xw.writeEndElement();
                xw.writeStartElement("Prezo");
                xw.writeCharacters(String.valueOf(aux.getPrezo()));
                xw.writeEndElement();
                xw.writeEndElement();
               
               
                
                
                
                
          
            } catch (XMLStreamException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
        
        
    }
    
    
}
