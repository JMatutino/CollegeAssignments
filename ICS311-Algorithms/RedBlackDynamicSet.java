package ics311;

import java.util.Map.Entry;
import java.util.TreeMap;

public class RedBlackDynamicSet<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	
	private String dataStructureName;
	private TreeMap<Key,Value> rbTree;
	
	public RedBlackDynamicSet(String dataStructureName)
	{
		
		this.dataStructureName = dataStructureName;
		rbTree = new TreeMap<Key,Value>();
	}
	
	@Override
	public String setDataStructure() {
		return dataStructureName;
	}

	@Override
	public int size() {
		return rbTree.size();
	}

	@Override
	public Value insert(Key key, Value value) {
		return rbTree.put(key,value);
	}

	@Override
	public Value delete(Key key) {
		return rbTree.remove(key);
	}

	@Override
	public Value retrieve(Key key) {
		return rbTree.get(key);
	}

	@Override
	public Entry<Key, Value> minimum() {
		return rbTree.firstEntry();
	}

	@Override
	public Entry<Key, Value> maximum() {
		return rbTree.lastEntry();
	}

	@Override
	public Entry<Key, Value> successor(Key key) {
		return rbTree.higherEntry(key);
	}

	@Override
	public Entry<Key, Value> predecessor(Key key) {
		return rbTree.lowerEntry(key);
	}

}
