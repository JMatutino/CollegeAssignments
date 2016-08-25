package ics311;

import java.util.Map.Entry;

public class ExampleSet<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	
	private String dataStructureName;
	
	public ExampleSet(String dataStructureName)
	{
		
		this.dataStructureName = dataStructureName;
	}
	
	@Override
	public String setDataStructure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Value insert(Key key, Value value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value delete(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value retrieve(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<Key, Value> minimum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<Key, Value> maximum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<Key, Value> successor(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<Key, Value> predecessor(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

}
