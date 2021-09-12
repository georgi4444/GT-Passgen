package com.georgitsipov.passwordgenerator.views.passwordgenerator;

import com.georgitsipov.passwordgenerator.PasswordGenerator;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PWA(name = "Password Generator", shortName = "Password Generator", enableInstallPrompt = false)
@Theme(themeFolder = "passwordgenerator", variant = Lumo.DARK)
@PageTitle("Password Generator")
@Route(value = "")
public class PasswordGeneratorView extends Div {
    private static final int PASSWORD_LENGTH_MIN = 6;
    private static final int PASSWORD_LENGTH_MAX = 100;

    public PasswordGeneratorView() {
        addClassNames("password-generator-view", "flex", "flex-col", "h-full", "items-center", "p-l", "box-border");

        // header
        Image img = new Image("images/lock.png", "lock");
        img.setWidth("100px");
        H1 header = new H1("Password Generator");
        add(img, header);

        // Vertical layout for contents
        VerticalLayout content = new VerticalLayout();
        content.setWidth(header.getWidth());
        add(content);

        // Password field with copy button
        TextField passwordField = new TextField();
        passwordField.setAutoselect(true);
        Button copyButton = new Button("Copy", VaadinIcon.COPY.create(),
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText($0) ",
                        passwordField.getValue()));
        content.add(new HorizontalLayout(passwordField, copyButton));

        // Generate Password button
        Button generateButton = new Button("Generate Password");
        content.add(generateButton);

        // Password length field
        NumberField numberField = new NumberField("Length");
        numberField.setValue(6d);
        numberField.setHasControls(true);
        numberField.setMin(PASSWORD_LENGTH_MIN);
        numberField.setMax(PASSWORD_LENGTH_MAX);
        content.add(numberField);

        // Include options
        Checkbox allCheckbox = new Checkbox("Select all");
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        Set<String> items = new LinkedHashSet<>(
                Arrays.asList("Include uppercase letters", "Include lowercase letters", "Include numbers", "Include symbols"));
        checkboxGroup.setItems(items);
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        checkboxGroup.select(items);
        allCheckbox.setValue(true);
        content.add(allCheckbox, checkboxGroup);

        // Event listeners
        copyButton.addClickListener(
                e -> UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText($0) ", passwordField.getValue())
        );

        checkboxGroup.addValueChangeListener(event -> {
            if (event.getValue().size() == items.size()) {
                allCheckbox.setValue(true);
                allCheckbox.setIndeterminate(false);
            } else if (event.getValue().size() == 0) {
                allCheckbox.setValue(false);
                allCheckbox.setIndeterminate(false);
            } else
                allCheckbox.setIndeterminate(true);
        });

        allCheckbox.addValueChangeListener(event -> {
            if (allCheckbox.getValue()) {
                checkboxGroup.setValue(items);
            } else {
                checkboxGroup.deselectAll();
            }
        });


        generateButton.addClickListener(buttonClickEvent -> {
            PasswordGenerator passwordGenerator = new PasswordGenerator();

            // parse options
            passwordGenerator.setAll(false);
            for (String option : checkboxGroup.getSelectedItems()) {
                switch (option) {
                    case "Include uppercase letters":
                        passwordGenerator.setUppercase(true);
                        break;
                    case "Include lowercase letters":
                        passwordGenerator.setLowercase(true);
                        break;
                    case "Include numbers":
                        passwordGenerator.setNumbers(true);
                        break;
                    case "Include symbols":
                        passwordGenerator.setSymbols(true);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + option);
                }
            }

            if (checkboxGroup.getSelectedItems().size() != 0) {
                passwordGenerator.setLength(numberField.getValue());
                passwordField.setValue(passwordGenerator.generatePassword());
            } else {
                // If no options pop dialog window
                Dialog noOptionsDialog = new Dialog();
                Button OKButton = new Button("OK!", event -> noOptionsDialog.close());
                noOptionsDialog.add(new VerticalLayout(new Text("Select at least one option."), OKButton));
                noOptionsDialog.open();
            }
        });
    }
}
