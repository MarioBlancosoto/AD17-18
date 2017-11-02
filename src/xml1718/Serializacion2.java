
package xml1718;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

/**
 *
 * @author mblancosoto
 */
public class Serializacion2 {
        static XMLOutputFactory factory =  XMLOutputFactory.newInstance();
        static XMLInputFactory fic = XMLInputFactory.newInstance();
        static XMLStreamWriter xw;
        static XMLStreamReader sr;
        static ArrayList pr = new ArrayList();
        public static String[] cod ={"p1","p2","p3"};
        public static String[] desc ={"parafusos","cravos","tachas"};
        public static double[] prezo ={3.0,4.0,5.0};
        static Product aux;
        static  String ruta = "/home/local/DANIELCASTELAO/mblancosoto/NetBeansProjects/Xml1718/produtos.xml";
       
    public static void main(String[] args) {
        Product obj1 = new Product(cod[0],desc[0],prezo[0]);
        Product obj2 = new Product(cod[1],desc[1],prezo[1]);
        Product obj3 = new Product(cod[2],desc[2],prezo[2]);
        
      
        
       // lerObx("/home/local/DANIELCASTELAO/mblancosoto/NetBeansProjects/Xml1718serial.txt");
       leerXML();
        
    }
    
 
    
    
    public static void lerObx(String fichero){
           
            try {
                ObjectInputStream ler = new ObjectInputStream(new FileInputStream(fichero));
                 aux = (Product) ler.readObject();
                 xw = factory.createXMLStreamWriter(new FileWriter(ruta));
                 //iniciamos el startdocument aquí dado que tiene que iterar 3 veces para cad producto
                 //y no inicie el documento 3 veces
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
              
                
                //cuerpo del documento a imprimir,recogiendo cada atributo del producto
                
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
    
    public static void leerXML(){
           
               Product pro = null;
               //iniciams el producto como null antes de empezar a leer
            try { 
                sr = fic.createXMLStreamReader(new FileReader(ruta));
                
                while(sr.hasNext()){
                   
                  sr.next();
                      
                   if (sr.isStartElement()){
                       
                        if (sr.getLocalName().equals("Produto")) { 
                            //tenemos que inicializar el producto aquí dado que cada producto empieza aquí
                            pro= new Product();
                                pro.setCodigo(sr.getAttributeValue(0));
                             }
                
                        if(sr.getLocalName().equals("Nome")){
                            pro.setDescripcion(sr.getElementText());
                        }if(sr.getLocalName().equals("Prezo")){
                            
                            pro.setPrezo(Double.parseDouble(sr.getElementText()));
                           pr.add(pro);
                        }
                            
                            
                        
                        
                        
                   }                  
                      
                     
                  
                    
                }
                for(int i=0;i<pr.size();i++){
                    System.out.println(pr.get(i));
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XMLStreamException ex) {
                Logger.getLogger(Serializacion2.class.getName()).log(Level.SEVERE, null, ex);
            }
          
                
                
                
                
                
                
                
                
                
           
        
        
    }
    
}

