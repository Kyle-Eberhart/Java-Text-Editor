package editor;

import java.util.Arrays;  // Kyle Eberhart CSC 300 MW 1150-120

public class A_StringEditor {
	private static final int INITIAL_CAPACITY = 1000;  // The initial size of the data array
	
	private char[] data;		// contents of string
	private int stringSize;		// size of string
	private int cursorPos;		// index of cursor position
	
	public A_StringEditor() {  // constructor
		data = new char[INITIAL_CAPACITY];
        stringSize = 0;
        cursorPos = 0;
	}
	
	
	private void resize() { //resize helper method.
	    int newCapacity = data.length * 2;
	    char[] newData = new char[newCapacity];
	    
	    for (int i = 0; i < stringSize; i++) { // copy each element from the old array to the new one
	        newData[i] = data[i];
	    }
	    
	    data = newData;  // assign the new array to data
	}

	public void insert(char c) {
		 if (stringSize >= data.length) { //ensure there is enough space in array.
	            resize();
	        }
		 
	        for (int i = stringSize; i > cursorPos; i--) {  // Shift elements to the right to make space at cursor position
	            data[i] = data[i - 1];
	        }
	        
	        data[cursorPos] = c;   // Insert the new character at the cursor position
	        
	        stringSize++;   // Update the size and move the cursor to the right
	        cursorPos++;
	    }

	
	public void moveForward() {
	    if (cursorPos < stringSize) {
	        cursorPos++;
	    }
	}
	
	public void moveBackward() {
		// Only move the cursor if it's not already at the beginning of the string
	    if (cursorPos > 0) {
	        cursorPos--;
	    }
	}
	
	public void delete() {
	    if (cursorPos < stringSize) {  // only delete if there is a character to the right of the cursor
	        for (int i = cursorPos; i < stringSize - 1; i++) { // Shift elements to the left from cursorPos + 1 to the end
	            data[i] = data[i + 1];
	        }
	        // Decrement the string size since char is removed
	        stringSize--;
	    }
	}
	
	public void backspace() {
	    if (cursorPos > 0) { // Only backspace if there is a character to the left of cursor
	        cursorPos--;  // Move cursor one position to the left
	        
	        // Shift elements to the left from cursorPos to the end
	        for (int i = cursorPos; i < stringSize - 1; i++) {
	            data[i] = data[i + 1];
	        }
	        stringSize--;   // Decrement the string size since one char is removed.
	    }
	}
	
	public String lettersBeforeCursor(int count) {
		if (count <= 0 || cursorPos == 0) {
	        return ""; // return empty string if count is 0 or negative, or cursor is at the start
	    }
	    
	    String answer = ""; // Start with an empty string
	    int collected = 0;  // Track number of characters collected
	    int index = cursorPos - 1;  // Start one position to the left of the cursor
	    
	    // Collect characters to the left of the cursor until we've reached 'count' or the start
	    while (index >= 0 && collected < count) {
	        answer = data[index] + answer;  // Prepend character to the answer
	        index--;       // Move left
	        collected++;   // Increase count of collected characters
	    }
	    return answer;
	}
	
	public String lettersAfterCursor(int count) {
		if (count <= 0 || cursorPos >= stringSize) {
	        return ""; // return empty string if count is 0 or cursor is at the end
	    }

	    String answer = ""; // Start with an empty string
	    int collected = 0; // Track number of characters collected
	    int index = cursorPos; // Start at the cursor position

	    // Collect characters to the right of the cursor until we've reached 'count' or the end
	    while (index < stringSize && collected < count) {
	        answer += data[index]; // Append character to the answer
	        index++; // Move right
	        collected++; // Increase count of collected characters
	    }

	    return answer;
	}
}
