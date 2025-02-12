
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class PasswordMain extends BorderPane {
    private Label passwordLabel, confrimPasswordLabel;
    private Label instructionLabel, instruction1Label, instruction2Label, instruction3Label, instruction4Label,
	    instruction5Label, instruction6Label;
    private TextField passwordText, confrimPasswordText;
    private Button checkPwdButton, exitButton, checkPwdsInFileButton;
    private Alert alert = new Alert(AlertType.INFORMATION);
    PasswordCheckerUtility pwdChecker;

    public PasswordMain() {
	VBox subpanel = new VBox();
	setupLabels();
	setupLabelMargins();

	HBox subpanelPwd = new HBox();
	setupPasswordField(subpanelPwd);

	subpanel.setAlignment(Pos.CENTER_LEFT);
	subpanel.getChildren().addAll(instructionLabel, instruction1Label, instruction2Label, instruction3Label,
		instruction4Label, instruction5Label, instruction6Label);

	HBox subpanelConfrimPwd = new HBox();
	setupConfirmPasswordField(subpanelConfrimPwd);

	VBox subpanel1 = new VBox();
	VBox.setMargin(subpanelPwd, new Insets(10, 10, 10, 10));
	VBox.setMargin(subpanelConfrimPwd, new Insets(10, 10, 10, 10));
	subpanel1.setAlignment(Pos.CENTER);
	subpanel1.getChildren().addAll(subpanelPwd, subpanelConfrimPwd);

	checkPwdsInFileButton = setupButton("Check Passwords in _File", "Select to read passwords from a file");
	checkPwdButton = setupButton("Check _Password", "Select to check a password.");
	exitButton = setupButton("E_xit", "Select to close the application");

	checkPwdsInFileButton.setOnAction(event -> {
	    try {
		readFile();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	checkPwdButton.setOnAction(event -> {
	    checkPassword();
	});

	// use a lambda expression for the EventHandler class for exitButton
	exitButton.setOnAction(event -> {
	    Platform.exit();
	    System.exit(0);
	});

	HBox buttonPanel = new HBox();
	HBox.setMargin(checkPwdButton, new Insets(10, 10, 10, 10));
	HBox.setMargin(checkPwdsInFileButton, new Insets(10, 10, 10, 10));
	HBox.setMargin(exitButton, new Insets(10, 10, 10, 10));
	buttonPanel.setAlignment(Pos.CENTER);
	buttonPanel.getChildren().addAll(checkPwdButton, checkPwdsInFileButton, exitButton);

	setTop(subpanel);
	setCenter(subpanel1);
	setBottom(buttonPanel);

    }

    private void setupLabels() {
	instructionLabel = new Label("Use the following rules when creating your passwords:");
	instruction1Label = new Label(
		"\t1.  Length must be greater than 6; a strong password will contain at least 10 characters");
	instruction2Label = new Label("\t2.  Must contain at least one upper case alpha character");
	instruction3Label = new Label("\t3.  Must contain at least one lower case alpha character");
	instruction4Label = new Label("\t4.  Must contain at least one numeric character");
	instruction5Label = new Label("\t5.  Must contain at least one special character");
	instruction6Label = new Label("\t6   May not have more than 2 of the same character in sequence");
    }

    private void setupLabelMargins() {
	VBox.setMargin(instructionLabel, new Insets(2, 10, 2, 10));
	VBox.setMargin(instruction1Label, new Insets(2, 10, 2, 10));
	VBox.setMargin(instruction2Label, new Insets(2, 10, 2, 10));
	VBox.setMargin(instruction3Label, new Insets(2, 10, 2, 10));
	VBox.setMargin(instruction4Label, new Insets(2, 10, 2, 10));
	VBox.setMargin(instruction5Label, new Insets(2, 10, 2, 10));
	VBox.setMargin(instruction6Label, new Insets(2, 10, 2, 10));
    }

    private void setupPasswordField(HBox subpanel) {
	passwordLabel = new Label("Password");
	passwordText = new TextField();
	HBox.setMargin(passwordLabel, new Insets(10, 10, 10, 10));
	HBox.setMargin(passwordText, new Insets(10, 10, 10, 10));
	subpanel.setAlignment(Pos.CENTER);
	subpanel.getChildren().addAll(passwordLabel, passwordText);
    }

    private void setupConfirmPasswordField(HBox subpanel) {
	confrimPasswordLabel = new Label("Re-type\nPassword");
	confrimPasswordText = new TextField();
	HBox.setMargin(confrimPasswordLabel, new Insets(10, 10, 10, 10));
	HBox.setMargin(confrimPasswordText, new Insets(10, 10, 10, 10));
	subpanel.setAlignment(Pos.CENTER);
	subpanel.getChildren().addAll(confrimPasswordLabel, confrimPasswordText);
    }

    private static Button setupButton(String text, String tooltip) {
	Button button = new Button(text);
	button.setMnemonicParsing(true);
	button.setTooltip(new Tooltip(tooltip));
	return button;
    }

    public void checkPassword() {
	// Get information
	String passwordString = passwordText.getText();
	String passwordAString = confrimPasswordText.getText();
	try {
	    if (!PasswordCheckerUtility.comparePasswordsWithReturn(passwordString, passwordAString)) {
		throw new UnmatchedException();
	    }

	    if (PasswordCheckerUtility.isValidPassword(passwordString)) {
		if (PasswordCheckerUtility.isWeakPassword(passwordString)) {
		    alert.setContentText(Exception_Messages.WEAK_PWD_MSG);
		    alert.showAndWait();
		} else {
		    alert.setContentText(Exception_Messages.VALID_PWD_MSG);
		    alert.showAndWait();
		}
	    } else {
		alert.setContentText(Exception_Messages.INVALID_PWD_MSG);
		alert.showAndWait();
	    }
	} catch (UnmatchedException ex) {
	    alert.setContentText(ex.getMessage());
	    alert.showAndWait();
	} catch (Exception ex) {
	    alert.setContentText(ex.getMessage());
	    alert.showAndWait();
	}
    }

    public void readFile() {
	FileChooser chooser = new FileChooser();
	Scanner input = null;
	File selectedFile = chooser.showOpenDialog(null);
	String results = "", title = "";
	if (selectedFile != null) {
	    ArrayList<String> passwords = new ArrayList<String>();
	    try {
		input = new Scanner(selectedFile);
		while (input.hasNext()) {
		    passwords.add(input.next());
		}
		ArrayList<String> invalidPassword = PasswordCheckerUtility.getInvalidPasswords(passwords);
		if (invalidPassword.isEmpty()) {
		    results = "All Passwords are valid!";
		    title = "Passwords";
		} else {
		    results = Exception_Messages.INVALID_PASSWORDS_MSG + "\n";
		    title = Exception_Messages.INVALID_PASSWORDS_MSG;
		}
		for (String passwordString : invalidPassword)
		    results += passwordString + "\n";
		alert.setContentText(results);
        alert.showAndWait();
	    } // end of try
	    catch (FileNotFoundException ex) {
		JOptionPane.showMessageDialog(null, ex.toString(), "File Error", JOptionPane.PLAIN_MESSAGE);
		ex.printStackTrace();
	    } finally {
		input.close();
	    }
	}
    }
}
