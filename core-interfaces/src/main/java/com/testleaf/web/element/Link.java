package com.testleaf.web.element;

public interface Link extends Element {

	/**
	 * Simulates a click action on the link.
	 */
	void click();

	/**
	 * Retrieves the URL (href attribute) associated with the link.
	 *
	 * @return the URL of the hyperlink as a String.
	 */
	String getHref();
}
