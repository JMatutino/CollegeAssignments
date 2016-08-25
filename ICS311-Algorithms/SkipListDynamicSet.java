package ics311;

import java.util.Map.Entry;

public class SkipListDynamicSet<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	private String dataStructureName;
	private SkipList skipList;
	
	public SkipListDynamicSet(String dataStructureName)
	{
		
		this.dataStructureName = dataStructureName;
		skipList = new SkipList();
	}
	
	@Override
	public String setDataStructure() {
		return dataStructureName;
	}

	@Override
	public int size() {
		return skipList.size();
	}

	@Override
	public Value insert(Key key, Value value) {
		return (Value) skipList.add(key,value);
	}

	@Override
	public Value delete(Key key) {
		return (Value) skipList.remove(key);
	}

	@Override
	public Value retrieve(Key key) {
		// TODO Auto-generated method stub
		return (Value) skipList.find(key);
	}

	@Override
	public Entry<Key, Value> minimum() {
		Entry<Key,Value> entry = new SetEntry<Key,Value>((Key) skipList.getMin(),null);
		return entry;
	}

	@Override
	public Entry<Key, Value> maximum() {
		Entry<Key,Value> entry = new SetEntry<Key,Value>((Key) skipList.getMax(),null);
		return entry;
	}

	@Override
	public Entry<Key, Value> successor(Key key) {
		Entry<Key, Value> entry = new SetEntry<Key,Value>((Key) skipList.Successor(key),null);
		return  entry;
	}

	@Override
	public Entry<Key, Value> predecessor(Key key) {
		Entry<Key, Value> entry = new SetEntry<Key,Value>((Key) skipList.Predecessor(key),null);
		return entry;
	}

}
