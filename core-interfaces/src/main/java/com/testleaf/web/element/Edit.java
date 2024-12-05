package com.testleaf.web.element;

public interface Edit extends Element {

	/**
	 * Types the specified text into the editable element.
	 *
	 * @param text the text to type into the element.
	 */
	void type(String text);

	/**
	 * Retrieves the current value of the editable element.
	 *
	 * @return the current value of the element as a String.
	 */
	String getValue();

	/**
	 * Clears the existing value in the editable element.
	 */
	void clear();

	/**
	 * Simulates pressing the Enter key while focused on the editable element.
	 */
	void pressEnter();
}
