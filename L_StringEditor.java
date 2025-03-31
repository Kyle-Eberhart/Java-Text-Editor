package editor;  // Kyle Eberhart CSC 300 MW 1150-120

public class L_StringEditor {
	private class Node {
		private char c;
		private Node next;
		private Node prev;
	}
	
	private Node stringStart; // dummy node fixed to head of list
	private Node cursorPos;		// The position of the cursor
	
	public L_StringEditor() { // constructor
		stringStart = new Node();
		cursorPos = new Node();
		stringStart.prev = null;
		stringStart.next = cursorPos;
		
		cursorPos = stringStart.next;
		cursorPos.next = null;
		cursorPos.prev = stringStart;
	}
	
	public void insert(char c) {
	    Node newNode = new Node();
	    newNode.c = c;

	    // Insert newNode before cursorPos
	    newNode.next = cursorPos;
	    newNode.prev = cursorPos.prev;

	    if (cursorPos.prev != null) {
	        cursorPos.prev.next = newNode;
	    } else {
	        stringStart.next = newNode; // Update head if we're inserting at the start
	    }
	    cursorPos.prev = newNode;

	    // Keep cursor at the same position (right after the newly inserted node)
	    cursorPos = newNode.next;
	}

	public void moveForward() {
	    if (cursorPos != null && cursorPos.next != null) {
	        // Move the character at cursorPos to the left of the cursor
	        Node afterCursor = cursorPos.next;
	        
	        // Update links to move afterCursor to the left of cursorPos
	        cursorPos.next = afterCursor.next;
	        if (afterCursor.next != null) {
	            afterCursor.next.prev = cursorPos;
	        }
	        
	        afterCursor.prev = cursorPos.prev;
	        afterCursor.next = cursorPos;
	        
	        if (cursorPos.prev != null) {
	            cursorPos.prev.next = afterCursor;
	        } else {
	            stringStart.next = afterCursor;
	        }
	        
	        cursorPos.prev = afterCursor;
	    }
	}
	
	
	public void moveBackward() {
	    if (cursorPos != stringStart && cursorPos.prev != null) {
	        // Move the character before cursorPos to the right of the cursor
	        Node beforeCursor = cursorPos.prev;
	        
	        // Update links to move beforeCursor to the right of cursorPos
	        cursorPos.prev = beforeCursor.prev;
	        if (beforeCursor.prev != null) {
	            beforeCursor.prev.next = cursorPos;
	        } else {
	            stringStart.next = cursorPos;
	        }
	        
	        beforeCursor.next = cursorPos.next;
	        beforeCursor.prev = cursorPos;
	        
	        if (cursorPos.next != null) {
	            cursorPos.next.prev = beforeCursor;
	        }
	        
	        cursorPos.next = beforeCursor;
	    }
	}
	
	
	
	public void delete() {
		if (cursorPos != null && cursorPos != stringStart && cursorPos.next != null) {
			Node nodeToDel = cursorPos.next; // point to node to be deleted
			cursorPos.next = nodeToDel.next; // Link cursorPos to node after nodeToDel.
			
			if (nodeToDel.next != null) { // if node after nodeToDel exists, link its prev to cursorPos.
				nodeToDel.next.prev = cursorPos;
			}
		}
	}
	
	public void backspace() {
		if (cursorPos != null && cursorPos != stringStart && cursorPos.prev != null) {
			Node nodeToBs = cursorPos.prev; // point to node to be backspace deleted
			cursorPos.prev = nodeToBs.prev; // Link cursorPos.prev to nodeToBs.prev to remove nodeToBs from list.
			
			if (nodeToBs.prev != null) { // if node exists before nodeToBs, link its .next to cursorPos.
				nodeToBs.prev.next = cursorPos;
			}
		}
	}
	
	public String lettersBeforeCursor(int count) {
		if (count <= 0 || cursorPos == null || cursorPos == stringStart) {
			return ""; // return empty string if count is 0 or negative;
		}
		
		String answer = ""; //start with empty string.
		Node ptr = cursorPos.prev; //pointer starts from node left of cursor.
		int collected = 0;
		
		while (ptr != null && ptr != stringStart && collected < count) { // traverse list backward and collect chars.
			answer = ptr.c + answer; // append each char to the answer string.
			ptr = ptr.prev;
			collected ++;  
		}
		return answer;
	}
	
	
	public String lettersAfterCursor(int count) {
		if (count <= 0 || cursorPos == null) {
			return ""; // return empty string if count is 0 or negative;
		}
		
		String answer = ""; //start with empty string.
		Node ptr = cursorPos.next; //pointer starts from node right of cursor.
		int collected = 0;
		
		while (ptr != null && collected < count) { // traverse list forward and collect chars.
			answer = answer + ptr.c; // append each char to the answer string.
			ptr = ptr.next;
			collected ++;  
		}
		return answer;
	}
}
