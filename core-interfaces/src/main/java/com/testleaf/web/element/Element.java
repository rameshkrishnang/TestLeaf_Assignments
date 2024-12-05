package com.testleaf.web.element;

public interface Element {

	/**
	 * Checks if the element is currently enabled and can be interacted with.
	 *
	 * @return true if the element is enabled; false otherwise.
	 */
	boolean isEnabled();

	/**
	 * Checks if the element is visible on the web page.
	 *
	 * @return true if the element is visible; false otherwise.
	 */
	boolean isVisible();

	/**
	 * Retrieves the value of a specified attribute of the element.
	 *
	 * @param attribute the name of the attribute to retrieve (e.g., "id", "class", "name").
	 * @return the value of the specified attribute as a String, or null if the attribute does not exist.
	 */
	String getAttribute(String attribute);

	/**
	 * Retrieves the visible text of the element.
	 *
	 * @return the text content of the element as a String.
	 */
	String getText();
}
