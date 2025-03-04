package lab05;

import java.util.Arrays;

/**
 * Array-based implementation of the CustomStack interface.
 * @param <T> the type of elements in the stack
 */

public class ArrayStack<T extends Number> implements Stack<T> {
	// Constants for stack configuration
	private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    // Static variables for tracking across all instances
    private static int totalStacks = 0;
    private static int totalElements = 0;

    // Instance variables
    private Object[] elements;  // Using Object[] since generic arrays aren't directly supported
    private int top;  // Index of the top element
    private int operationCount;  // Tracks operations on this stack
    private final int stackId;  // Unique identifier for this stack instance

    
    /**
     * Creates a new ArrayStack with default capacity.
     */
    public ArrayStack() {
    	// TODO: Initialize the elements array with the DEFAULT_CAPACITY
        this.elements = new Object[DEFAULT_CAPACITY];
        // TODO: Set top to -1 (indicating an empty stack)
        this.top = -1;
        // TODO: Initialize operationCount to 0
        this.operationCount = 0;
        // TODO: Assign a unique stackId by incrementing totalStacks
        this.stackId = ++totalStacks;
    }

    /**
     * Adds an element to the top of the stack.
     * @param element the element to add
     */
    @Override
    public void push(T element) {
    	// TODO: Increment operationCount
    	operationCount++;
    	// TODO: Check if the array is full (top == elements.length - 1)
    	if (top == elements.length - 1) {
            resize();
        }
    	// TODO: Add the element to the top of the stack
        elements[++top] = element;
        // TODO: Increment totalElements
        totalElements++;
    }

    /**
     * Removes and returns the top element from the stack.
     * @return the top element, or null if the stack is empty
     */
    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
    	// TODO: Increment operationCount
        operationCount++;
        // TODO: Check if the stack is empty (isEmpty())
        if (isEmpty()) {
            return null;
        }
        // TODO: Retrieve the top element
        T removedElement = (T) elements[top];
        // TODO: Clear the reference in the array to help garbage collection
        elements[top--] = null;
        // TODO: Decrement totalElements
        totalElements--;
        // TODO: Return the popped element
        return removedElement;
    }

    /**
     * Returns but does not remove the top element from the stack.
     * @return the top element, or null if the stack is empty
     */
    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
    	// TODO: Increment operationCount
        operationCount++;
        // TODO: Check if the stack is empty (isEmpty())
        if (isEmpty()) {
            return null;
        }
        // TODO: Return the top element without removing it
        return (T) elements[top]; 
    }

    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
    	// TODO: Increment operationCount
        operationCount++;
        // TODO: Return true if the stack is empty (top == -1)
        return top == -1;
    }

    /**
     * Returns the number of elements in the stack.
     * @return the number of elements
     */
    @Override
    public int size() {
    	// TODO: Increment operationCount
        operationCount++;
        // TODO: Return the number of elements in the stack (top + 1)
        return top + 1;
    }

    /**
     * Resizes the array when it becomes full.
     */
    private void resize() {
    	// TODO: Calculate the new capacity using GROWTH_FACTOR
    	// TODO: Create a new array with the new capacity
    	// TODO: Copy elements from the old array to the new array
    	// TODO: Update the elements reference to point to the new array
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Adds the top two elements and pushes the result back onto the stack.
     * Works only for numeric types.
     */
    public void addTopTwo() {
    	// TODO: Check if the stack has at least two elements (size() < 2)
        if (size() < 2) {
            return;
        }

        // TODO: Pop the top two elements
        T num1 = pop();
        T num2 = pop();

        // TODO: Add the two numbers and determine the appropriate type for the result
        Number sum = num1.doubleValue() + num2.doubleValue();

        // TODO: If the original elements were Integers, push the result as Integer
        if (num1 instanceof Integer && num2 instanceof Integer) {
            push((T) Integer.valueOf(sum.intValue()));
        // TODO: Otherwise, treat the result as a Double
        } else {
            push((T) Double.valueOf(sum.doubleValue()));
        }
    }

    /**
     * Gets statistics for this stack instance.
     */
    public String getStats() {
        return "Stack #" + stackId + ": Size=" + size() + ", Operations=" + operationCount;
    }

    /**
     * Gets statistics across all ArrayStack instances.
     */
    public static String getGlobalStats() {
        return "Total stacks: " + totalStacks + ", Total elements: " + totalElements;
    }
}
