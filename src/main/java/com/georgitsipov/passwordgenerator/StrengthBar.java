package com.georgitsipov.passwordgenerator;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.progressbar.ProgressBar;

@CssImport(
        themeFor = "vaadin-progress-bar",
        value = "./my-styles/strengthbar-color.css"
)
public class StrengthBar extends ProgressBar {
    /**
     * Calls the super setValue method of ProgressBar and additionally updates the Color
     *
     * @param value min - 0, max - 4
     */
    @Override
    public void setValue(double value) {
        super.setValue(value);
        this.updateColor();
    }

    public StrengthBar(double min, double max, double value) {
        super(min, max, value);
    }

    /**
     * Updates the color of the StrengthBar depending on its value
     */
    private void updateColor() {
        switch ((int) this.getValue()) {
            case 0:
                // red, its not really visible, since at 0 the bar is empty
                this.getStyle().set("--progress-color", "#f90800");
                break;
            case 1:
                // orange
                this.getStyle().set("--progress-color", "#f95700");
                break;
            case 2:
                // yellow
                this.getStyle().set("--progress-color", "#f3ca00");
                break;
            case 3:
                // light green
                this.getStyle().set("--progress-color", "#b3f352");
                break;
            case 4:
                // dark green
                this.getStyle().set("--progress-color", "#2cbc35");
                break;
        }
    }
}
