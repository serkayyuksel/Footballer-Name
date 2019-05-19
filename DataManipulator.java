/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;

/**
 *
 * @author KrystofViktora
 */
public class DataManipulator {
    private String file;
    private DoublyLinkedList<Dvd> linkedList = new DoublyLinkedList<>();
    
    /**
     * Constructor takes URL of file in String
     * @param file 
     */
    public DataManipulator(String file){
        this.file = file;
    }
    
    /**
     * Method which reads a file and calls another methods fileToList & invetoryToList()
     * @param listGUI
     * @param list 
     */
    public void loadData(javax.swing.JList listGUI, javax.swing.DefaultListModel list, boolean asc) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
               fileToList(line, list);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            Logger.getLogger(DataManipulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        inventoryToListAsc(listGUI, list);
    }
    
    /**
     * Adds all the DVD's into LinkedList
     * @param line
     * @param list 
     */
    public void fileToList(String line, javax.swing.DefaultListModel list) {
        String[] data = line.split(",");
        ArrayList<String> songs = new ArrayList<>();
        
        for (int i = 2; i < data.length; i++) {
            data[i] = data[i].replaceAll("\\.", "");
            songs.add(data[i]);
        }
        
        Dvd dvd = new Dvd(data[0], data[1], songs);
        linkedList.add(dvd);
    }
    
    /**
     * Transfers LinkedList into GUI List
     * @param listGUI
     * @param list 
     */
    public void inventoryToListAsc(javax.swing.JList listGUI, javax.swing.DefaultListModel list) {
        DoublyLinkedList.Node tmp = linkedList.getHead();
        list.clear();
        listGUI.removeAll();
        
        while(tmp != null){
            list.addElement(tmp.element);
            tmp = tmp.next;
        }
    }
    
    /**
     * Transfers LinkedList into GUI List
     * @param listGUI
     * @param list 
     */
    public void inventoryToListDesc(javax.swing.JList listGUI, javax.swing.DefaultListModel list) {
        DoublyLinkedList.Node tmp = linkedList.getTail();
        list.clear();
        listGUI.removeAll();
        
        int count = 0;
        while(tmp.prev != null){
            
            list.addElement(tmp.element);
            tmp = tmp.prev;
            if(count == linkedList.size()-1 )
                return;
            count++;
            
        }
        list.addElement(tmp.element);
    }
    
    
    /**
     * Returns LinkedList
     * @return 
     */
    public DoublyLinkedList<Dvd> getLinkedList() {
        return this.linkedList;
    }
    
    /**
     * Method that Saves JList to file on closing app
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    public void saveFile() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file, false));
        String result = "";
        
        DoublyLinkedList.Node tmp = linkedList.getHead();
        
        while(tmp != null){
            result += tmp.element.adSoyad + ", ";
            result += tmp.element.DogumTarihi + ", ";
            ArrayList<String> songs = tmp.element.getSongs();
            for (int i = 0; i < songs.size(); i++) {
                result += songs.get(i) + ", ";
            }
            
            result += "." + System.lineSeparator();
            
            tmp = tmp.next;
        }
        
        out.write(result);
        out.close();
    }
}
