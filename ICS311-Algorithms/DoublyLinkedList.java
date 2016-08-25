package ics311;

import java.util.Map.Entry;

public class DoublyLinkedList <Key extends Comparable<Key>,Value> {
	private Link first; 

	private Link last; 
	private int size;
	

	public DoublyLinkedList() {
		first = null; 
		last = null;
		size = 0;
	}

	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return size;
	}

	public Value insert(Key key) {	
		Link o = new Link(key);	
		Link previous = first;	

		if (first == null) { // list is empty	
			first = o;
			size++;
			return null;	
		}	
		if (first.key.compareTo(key) >= 0) { // item needs to go at first spot of list	
			o.next = first;	
			first.previous = o;	
			first = new Link(key);
			size++;
			return null;	
		}	
		while (previous.next!=null && previous.next.key.compareTo(key)<0) { 	
			// traverse until end of list or spot is found	
			previous = previous.next;	
		}	
		if (previous.next==null) { // check if at end of list	
			previous.next = o;	
			o.previous = previous;
			last = o;
			size++;
			return null;	
		} else { // implement insertion	
			o.next = previous.next;	
			o.previous = previous;
			previous.next = o;
			size++;
			return null;	
		}	
	}

	public boolean insertAfter(Key key, long dd) { 
		Link current = first; 
		while (current.key.compareTo(key) != 0){
			current = current.next;
			if (current == null)
				return false; // cannot find it
		}
		Link newLink = new Link(dd); // make new link

		if (current == last) // if last link,
		{
			newLink.next = null; 
			last = newLink; 
		} else // not last link,
		{
			newLink.next = current.next; 

			current.next.previous = newLink;
		}
		newLink.previous = current; 
		current.next = newLink; 
		return true; // found it, insert
	}

	public Value deleteKey(Key key){
		Link current = first; 
		while (current.key.compareTo(key) != 0)
		{
			current = current.next;
			if (current == null)
				return null; // cannot find it
		}
		if (current == first){ // found it; first item?
			first = current.next;
		}
		else{
			current.previous.next = current.next;
		}

		if (current == last){ // last item?
			last = current.previous; 
		}
		else
			// not last
			current.next.previous = current.previous;
		size--;
		return (Value) current.val; // return value
	}

	public void displayForward() {
		System.out.print("List (first to last): ");
		Link current = first; // start at beginning
		while (current != null) // until end of list,
		{
			current.displayLink();
			current = current.next; // move to next link
		}
		System.out.println("");
	}

	public void displayBackward() {
		System.out.print("List : ");
		Link current = last;
		while (current != null){
			current.displayLink();
			current = current.previous;
		}
		System.out.println("");
	}

	public Value get(Key key) { 
		Link current = first; 
		while (current.key.compareTo(key) != 0){
			current = current.next;
			if (current == null)
				return null; // cannot find it
		}

		return (Value) current.val; // found it
	}

	public Link find(Key key) { 
		Link current = first; 
		while (current.key.compareTo(key) != 0){
			current = current.next;
			if (current == null)
				return null; // cannot find it
		}
		return current;
	}
	
	public Entry<Key,Value> successor(Key key){
		Link found = find(key);
		Entry<Key,Value> entry = null;
		if(found.next != null)
		{
			entry = new SetEntry<Key,Value>((Key)found.next.key,(Value)found.next.val);
			return entry;
		}
		return null;
		
	}
	
	public Entry<Key,Value> predecessor(Key key){
		Link found = find(key);
		Entry<Key,Value> entry = null;
		if(found.previous != null)
		{
			entry = new SetEntry<Key,Value>((Key)found.previous.key,(Value)found.previous.val);
			return entry;
		}
		return null;
	}
	
	public Entry<Key,Value> max(){
		Entry<Key,Value> entry = null;
		if(last != null){
			entry = new SetEntry<Key,Value>((Key)last.key,(Value)last.val);
			return entry;
		}
		return null;
	}
	
	public Entry<Key,Value> min(){
		Entry<Key,Value> entry = null;
		if(first != null)
		{
			entry = new SetEntry<Key,Value>((Key)first.key,(Value)first.val);
			return entry;
		}
		return null;
		
	}

	class Link <Key extends  Comparable<Key>,Value> {
		public Key key; // data item
		public Value val;

		public Link next; // next link in list

		public Link previous; // previous link in list


		public Link(Key d)
		{
			key = d;
		}

		public void displayLink(){
			System.out.print(key + " ");
		}
	}
}
