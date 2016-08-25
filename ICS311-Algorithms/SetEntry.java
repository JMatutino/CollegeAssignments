/**
 * 
 */
package ics311;

import java.util.Map.Entry;

/**
 * Sets the private state to the arguments.
 * @author Robert
 * @param <Key> The key under which the value of the object is stored in a set.
 * @param <Value>  The value or data stored in a set under the key.
 *
 */
public class SetEntry<Key, Value> implements Entry<Key, Value> {

	private Key key;
	private Value value;
	
	/**
	* Sets the values of the key the value of data stored in a set.
	* @param  value that will overwrite the current value stored.
	* @param  key The key under which this data is stored.
	*/
    public SetEntry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

	
	/**
	* Returns the key under which the value of the object is stored in a set.
	*
	* @return  key The key under which this data is stored.  
	*/
    @Override
	public Key getKey() {
		
		return key;
	}

    /**
	* Returns the value of the object the is stored under the key in a set.
	*
	* @return  value The data currently stored under the given key.  
	*/
	@Override
	public Value getValue() {
		
		return value;
	}
	/**
	* Changes the current value to the argument's value.
	* @param  value that will overwrite the current value stored.
	* @return  value The previous value stored which has been replaced.  
	*/
	@Override
	public Value setValue(Value value) {
		Value oldValue = this.value;
		this.value = value;
		return oldValue;
	}

}
