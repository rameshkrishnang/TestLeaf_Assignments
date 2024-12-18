package com.testleaf.web.element.decorated;

import com.testleaf.web.element.Button;

import java.util.logging.Logger;

public class SeButtonSnapDecorator implements Button {

    private final Button button;
    private static final Logger logger = Logger.getLogger(SeButtonSnapDecorator.class.getName());

    public SeButtonSnapDecorator(Button button) {
        this.button = button;
    }

    @Override
    public void click() {
        button.click();
        logger.info("Snap is done");
    }

    @Override
    public boolean isEnabled() {
        return button.isEnabled();
    }

    @Override
    public boolean isVisible() {
        return button.isVisible();
    }

    @Override
    public String getAttribute(String attribute) {
        return button.getAttribute(attribute);
    }

    @Override
    public String getText() {
        return button.getText();
    }
}
