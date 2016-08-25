package ics311;

import java.util.Map.Entry;

public class BSTDynamicSet<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	
	private String dataStructureName;
	private BinarySearchTree tree; 
	
	public BSTDynamicSet(String dataStructureName)
	{
		
		this.dataStructureName = dataStructureName;
		tree = new BinarySearchTree();
	}
	
	@Override
	public String setDataStructure() {
		return dataStructureName;
	}

	@Override
	public int size() {
		//System.out.println("Size of bsTree: "+tree.size());
		return tree.size();
	}

	@Override
	public Value insert(Key key, Value value) {
		return (Value)tree.put(key, value);
	}

	@Override
	public Value delete(Key key) {
		return (Value) tree.delete(key);
	}

	@Override
	public Value retrieve(Key key) {
		return (Value) tree.get(key);
	}

	@Override
	public Entry<Key, Value> minimum() {
		Entry<Key, Value> newEntry = new SetEntry(tree.min(),null);
		return newEntry;
	}

	@Override
	public Entry<Key, Value> maximum() {
		Entry<Key,Value> newEntry = new SetEntry(tree.max(),null);
		return newEntry;
	}

	@Override
	public Entry<Key, Value> successor(Key key) {
		Entry<Key,Value> newEntry = new SetEntry(tree.ceiling(key),null);
		return newEntry;
	}

	@Override
	public Entry<Key, Value> predecessor(Key key) {
		Entry<Key,Value> newEntry = new SetEntry(tree.floor(key),null);
		return newEntry;
	}

}
