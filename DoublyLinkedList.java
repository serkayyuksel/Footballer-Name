/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.NoSuchElementException;

/**
 *
 * @author KrystofViktora
 * @param <E>
 */
public class DoublyLinkedList<E> {
    private Node head;
    private Node tail;
    private int size;
     
    /**
     * LinkedList constructor
     */
    public DoublyLinkedList() {
        size = 0;
    }

    /**
     * This class keeps track of each element information
     */
    public class Node {
        Dvd element;
        Node next;
        Node prev;
 
        public Node(Dvd element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
    
    /**
     * Returns the size of the linked list
     */
    public int size() { 
        return size; 
    }
     
    /**
     * Returns whether the list is empty or not
     */
    public boolean isEmpty() { 
        return size == 0; 
    }
     
    /**
     * Adds element at the starting of the linked list
     */
    public void addFirst(Dvd element) {
     
        Node tmp = new Node(element, head, null);
        if(head != null ) {head.prev = tmp;}
        head = tmp;
        if(tail == null) { tail = tmp;}
        size++;
        System.out.println("adding: "+element);
    }
     
    /**
     * Adds element at the end of the linked list
     */
    public void addLast(Dvd element) {
         
        Node tmp = new Node(element, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
        System.out.println("adding: "+element);
    }
    
    /**
     * Adds DVD into Linked List sorted by Name first then Release date
     */
    public void add(Dvd element) {
        Node tmp = new Node(element, null, tail);
        Node current = head;
        Node previous = null;

        if(head == null) { 
            head = tmp;
            size++;
            return;
        }
        
        do {
            if ( current.element.compareWith(element) == 1) {
                tmp.next = current;
                current.prev = tmp;
                if(previous != null) {
                    previous.next = tmp;
                    tmp.prev = previous;
                }
                else if(previous == null) {
                    head = tmp;
                }
                size++;
                return;
            }
            else if(current.element.compareWith(element) == 0 && current.next == null) {
                current.next = tmp;
                tail = tmp;
                tmp.prev = current;
                size++;
                return;
            }
            else if(current.element.compareWith(element) == 0 && current.next != null) {
                previous = current;
                current = current.next;
            }    
        }
        while(current != null); 
        
        size++;
    }
  
    
    /**
     * This method walks forward through the linked list
     */
    public void iterateForward(){
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.next;
        }
    }
     
    /**
     * This method walks backward through the linked list
     */
    public void iterateBackward(){
        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null){
           System.out.println(tmp.element);  
           tmp = tmp.prev;
            
        }
    }
     
    /**
     * This method removes element from the start of the linked list
     * @return
     */
    public Dvd removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = head;
        head = head.next;
        head.prev = null;
        size--;
        System.out.println("deleted: "+tmp.element);
        return tmp.element;
    }
     
    /**
     * this method removes element from the end of the linked list
     * @return
     */
    public Dvd removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        System.out.println("deleted: "+tmp.element);
        return tmp.element;
    }
    
    public void removeAt(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            if(head.next == null) {
                head = null;
                return;
            }
            head = head.next;
            head.prev = null;
            size--;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node previous = current.prev;
            Node next = current.next;
            previous.next = current.next;
            next.prev = previous;
            size--;
        }
    }
    
    /** 
     * Returns head of the Linked list
     * @return 
     */
    public Node getHead() {
        return this.head;
    }
    
    /** 
     * Returns tail of the Linked list
     * @return 
     */
    public Node getTail() {
        return this.tail;
    }
    
    /**
     * Takes index as argument and return data at index
     */
    public Dvd getAt(int index)
    {
        Node current = head;
        int count = 0; /* index of Node we are
                          currently looking at */

        while (current != null) {
            if (count == index)
                return current.element;
            count++;
            current = current.next;
        }
 
        /* if we get to this line, the caller was asking
        for a non-existent element so we assert fail */
        assert(false);
        return null;
    }
    
    /* Function to reverse a Doubly Linked List */
    public void reverse() {
        Node temp = null;
        Node current = head;
 
        /* swap next and prev for all nodes of 
         doubly linked list */
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
 
        /* Before changing head, check for the cases like empty 
         list and list with only one node */
        if (temp != null) {
            head = temp.prev;
        }
    }
}
