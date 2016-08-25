package ics311;

import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.ListIterator;

public class DLLDynamicSet<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	private DoublyLinkedList list;
	private String dataStructureName;
	
	public DLLDynamicSet(String dataStructureName)
	{
		
		this.dataStructureName = dataStructureName;
		list = new DoublyLinkedList();
	}
	
	@Override
	public String setDataStructure() {
		return dataStructureName;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Value insert(Key key,Value value) {	
			return (Value)list.insert(key);
	 	}	

	@Override
	public Value delete(Key key) {
		return (Value) list.deleteKey(key);
	}

	
	public Value retrieve(Key key) {
		return (Value) list.get(key);
	}

	@Override
	public Entry<Key, Value> minimum() {
		return list.min();
	}

	@Override
	public Entry<Key, Value> maximum() {
		return list.max();
	}

	@Override
	public Entry<Key, Value> successor(Key key) {
		return list.successor(key);
	}

	@Override
	public Entry<Key, Value> predecessor(Key key) {
		return list.predecessor(key);
	}

}
