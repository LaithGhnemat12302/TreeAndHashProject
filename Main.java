package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main extends Application {
	private Alert error = new Alert(AlertType.ERROR);
	private Alert sucsses = new Alert(AlertType.INFORMATION);
	private Alert warning = new Alert(AlertType.WARNING);
	AVL<Department> departments = new AVL<Department>();

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		mainInterface(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Departments System");
		primaryStage.getIcons().add(new Image("download.png"));
		primaryStage.show();
	}

	private void readFromFiles(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setVgap(20);
		pane.setHgap(10);
		pane.setAlignment(Pos.CENTER);
		FileChooser passFileChooser = new FileChooser();
		passFileChooser.setTitle("select Passengers File");
		passFileChooser.setInitialDirectory(new File("."));
		passFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));

		ImageView passBrowseIcone = new ImageView("external-browse-file-folder-blue-vinzence-studio.png");
		passBrowseIcone.setFitWidth(25);
		passBrowseIcone.setFitHeight(25);
		Label passLabel = new Label("Select Dapartments Files: ");
		passLabel.setTextFill(Color.WHEAT);
		Button passButton = new Button("Browse", passBrowseIcone);
		Fill(passButton);
		passButton.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 30; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> mainInterface(primaryStage));

		HBox passBox = new HBox(20, passLabel, passButton);
		passBox.setAlignment(Pos.CENTER);

		VBox all = new VBox(40, passBox, back);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		passButton.setOnAction(e -> read(passFileChooser.showOpenDialog(primaryStage)));

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void mainInterface(Stage primaryStage) {

		String css = "-fx-color:lightblue;-fx-background-radius: 50; -fx-min-width:350; -fx-min-height:60";
		Button read = new Button("Read Data");
		mainFill(read);
		read.setStyle(css);
		read.setOnAction(e -> readFromFiles(primaryStage));

		Button department = new Button("Departments");
		mainFill(department);
		department.setStyle(css);
		department.setOnAction(e -> department(primaryStage));

		Button students = new Button("Students");
		mainFill(students);
		students.setStyle(css);
		students.setOnAction(e -> student(primaryStage));

		VBox all = new VBox(30, read, department, students);
		all.setAlignment(Pos.CENTER);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));

		Scene s = new Scene(all, 1000, 600);

		primaryStage.setScene(s);
	}

	private void mainFill(Button bt) {
		bt.setOnMouseMoved(e -> {

			bt.setStyle("-fx-color:ROYALBLUE;-fx-background-radius: 50; -fx-min-width:350; -fx-min-height:60");
		});

		bt.setOnMouseExited(e -> {
			bt.setStyle("-fx-color:lightblue;-fx-background-radius: 50; -fx-min-width:350; -fx-min-height:60");
		});
	}

	private void student(Stage primaryStage) {
		String css = "-fx-color:lightblue;-fx-background-radius: 50; -fx-min-width:350; -fx-min-height:60";

		Button insertStu = new Button("Insert new student to a department");
		mainFill(insertStu);
		insertStu.setStyle(css);
		insertStu.setOnAction(e -> insertStu(primaryStage));

		Button searchStu = new Button("Search Student in a Department");
		mainFill(searchStu);
		searchStu.setStyle(css);
		searchStu.setOnAction(e -> searchStu(primaryStage));

		Button deleteStu = new Button("Delete a Student from a Department");
		mainFill(deleteStu);
		deleteStu.setStyle(css);
		deleteStu.setOnAction(e -> deleteStu(primaryStage));

		Button print = new Button("Print all Students in a Department");
		mainFill(print);
		print.setStyle(css);
		print.setOnAction(e -> printStudents(primaryStage));

		Button save = new Button("Save changes on Students");
		mainFill(save);
		save.setStyle(css);
		save.setOnAction(e -> save(primaryStage));

		Button hashSize = new Button("Hash size");
		mainFill(hashSize);
		hashSize.setStyle(css);
		hashSize.setOnAction(e -> hashSize(primaryStage));

		Button back = new Button("Return to Main Menue");
		mainFill(back);
		back.setStyle(css);
		back.setOnAction(e -> mainInterface(primaryStage));

		VBox all = new VBox(30, insertStu, searchStu, deleteStu, hashSize, save, back);
		all.setAlignment(Pos.CENTER);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));

		Scene s = new Scene(all, 1000, 600);

		primaryStage.setScene(s);
	}

	private void hashSize(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setVgap(40);
		pane.setHgap(20);
		pane.setAlignment(Pos.CENTER);

		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);
		TextField depNameField = new TextField();

		pane.addRow(0, depName, depNameField);

		Label printLabel = new Label("Hash Size:");
		printLabel.setTextFill(Color.WHITE);
		TextField print = new TextField();

		pane.addRow(1, printLabel, print);

		depNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				print.setText("department NOT Exist !");
			} else {
				print.setText(i.getData().getStudents().getSize() + "");
			}
		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> student(primaryStage));

		VBox all = new VBox(40, pane, back);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void save(Stage primaryStage) {
		Node<Department> curr = departments.getRoot();
		FileWriter out;
		try {
			File f = new File("departments.txt");
			out = new FileWriter(f, false);
			PrintWriter printer = new PrintWriter(out);
			printer.print("");
			printer.close();
			out.close();
			out = new FileWriter(f, true);
			printer = new PrintWriter(out);
			print(curr, printer);
			printer.close();
			out.close();
		} catch (IOException e) {
			error.setContentText(e.getMessage());
			error.show();
		}
	}

	private void print(Node<Department> curr, PrintWriter out) {
		if (curr != null) {
			print(curr.getLeft(), out);
			printToFile(curr.getData(), out);
			print(curr.getRight(), out);
		}
	}

	private void printToFile(Department data, PrintWriter out) {
		out.println(data.getName() + "/" + data.getName() + ".txt");
		try {
			File f = new File(data.getName() + ".txt");
			FileWriter dep = new FileWriter(f, true);
			PrintWriter printer = new PrintWriter(dep);
			printer.println(data.printStudents());
			printer.close();

		} catch (IOException e) {
			error.setContentText(e.getMessage());
			error.show();
		}
	}

	private void printStudents(Stage primaryStage) {
		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);
		TextField depNameField = new TextField();

		HBox name = new HBox(20, depName, depNameField);
		name.setAlignment(Pos.CENTER);

		TextArea print = new TextArea();
		print.setMaxWidth(500);

		depNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				print.setText("department NOT Exist !");
			} else {
				print.setText(i.getData().getStudents().toString());
			}
		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> student(primaryStage));

		VBox all = new VBox(40, name, print, back);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void deleteStu(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setVgap(40);
		pane.setHgap(20);
		pane.setAlignment(Pos.CENTER);

		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);
		TextField depNameField = new TextField();

		pane.addRow(0, depName, depNameField);

		Label studentName = new Label("Student Name:");
		studentName.setTextFill(Color.WHITE);
		TextField studentNameField = new TextField();
		studentNameField.setEditable(false);

		pane.addRow(1, studentName, studentNameField);

		Label studentIDLabel = new Label("Student ID:");
		studentIDLabel.setTextFill(Color.WHITE);
		TextField StudentID = new TextField();
		StudentID.setEditable(false);
		pane.addRow(2, studentIDLabel, StudentID);

		Label studentAvgLabel = new Label("Student AVG:");
		studentAvgLabel.setTextFill(Color.WHITE);
		TextField StudentAVG = new TextField();
		StudentAVG.setEditable(false);
		pane.addRow(3, studentAvgLabel, StudentAVG);

		Label studentGenerLabel = new Label("Student Gender:");
		studentGenerLabel.setTextFill(Color.WHITE);
		TextField StudentGender = new TextField();
		StudentGender.setEditable(false);
		pane.addRow(4, studentGenerLabel, StudentGender);

		ImageView removeIcone = new ImageView("cancel--v1.png");
		removeIcone.setFitWidth(35);
		removeIcone.setFitHeight(35);
		Button remove = new Button("remove", removeIcone);
		Fill(remove);
		remove.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");

		depNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				studentNameField.setText("department NOT Exist !");
				studentNameField.setEditable(false);
				remove.setDisable(true);
			} else {
				studentNameField.setText("");
				studentNameField.setEditable(true);
				remove.setDisable(false);
			}
		});

		studentNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			int j = i.getData().getStudents().search(new Student(studentNameField.getText()));
			if (j != -1) {
				StudentID.setText(i.getData().getStudents().get(j).getData().getID() + "");
				StudentAVG.setText(i.getData().getStudents().get(j).getData().getAvg() + "");
				if (Character.toUpperCase(i.getData().getStudents().get(j).getData().getGender()) == 'M')
					StudentGender.setText("Male");
				else
					StudentGender.setText("Female");

				remove.setDisable(false);

				remove.setOnAction(e1 -> {
					i.getData().getStudents().delete(j);
				});

			} else
				remove.setDisable(true);

		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> student(primaryStage));

		VBox all = new VBox(40, pane, back);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void Fill(Button bt) {
		bt.setOnMouseMoved(e -> bt.setStyle(
				"-fx-color:ROYALBLUE;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;") );

		bt.setOnMouseExited(e -> bt.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;"));

	}

	private void searchStu(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setVgap(40);
		pane.setHgap(20);
		pane.setAlignment(Pos.CENTER);

		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);
		TextField depNameField = new TextField();

		pane.addRow(0, depName, depNameField);

		Label studentName = new Label("Student Name:");
		studentName.setTextFill(Color.WHITE);
		TextField studentNameField = new TextField();
		studentNameField.setEditable(false);

		pane.addRow(1, studentName, studentNameField);

		Label studentIDLabel = new Label("Student ID:");
		studentIDLabel.setTextFill(Color.WHITE);
		TextField StudentID = new TextField();
		StudentID.setEditable(false);
		pane.addRow(2, studentIDLabel, StudentID);

		Label studentAvgLabel = new Label("Student AVG:");
		studentAvgLabel.setTextFill(Color.WHITE);
		TextField StudentAVG = new TextField();
		StudentAVG.setEditable(false);
		pane.addRow(3, studentAvgLabel, StudentAVG);

		Label studentGenerLabel = new Label("Student Gender:");
		studentGenerLabel.setTextFill(Color.WHITE);
		TextField StudentGender = new TextField();
		StudentGender.setEditable(false);
		pane.addRow(4, studentGenerLabel, StudentGender);

		depNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				studentNameField.setText("department NOT Exist !");
				studentNameField.setEditable(false);
			} else {
				studentNameField.setText("");
				studentNameField.setEditable(true);
			}
		});

		studentNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			int j = i.getData().getStudents().search(new Student(studentNameField.getText()));
			if (j != -1) {
				StudentID.setText(i.getData().getStudents().get(j).getData().getID() + "");
				StudentAVG.setText(i.getData().getStudents().get(j).getData().getAvg() + "");
				if (Character.toLowerCase(i.getData().getStudents().get(j).getData().getGender()) == 'M')
					StudentGender.setText("Male");
				else
					StudentGender.setText("Female");

			}

		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> student(primaryStage));

		VBox all = new VBox(40, pane, back);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void insertStu(Stage primaryStage) {
		GridPane pane = new GridPane();
		pane.setVgap(40);
		pane.setHgap(20);
		pane.setAlignment(Pos.CENTER);

		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);
		TextField depNameField = new TextField();

		pane.addRow(0, depName, depNameField);

		Label studentName = new Label("Student Name:");
		studentName.setTextFill(Color.WHITE);
		TextField studentNameField = new TextField();
		studentNameField.setEditable(false);

		pane.addRow(1, studentName, studentNameField);

		Label studentIDLabel = new Label("Student ID:");
		studentIDLabel.setTextFill(Color.WHITE);
		TextField StudentID = new TextField();
		StudentID.setEditable(false);
		pane.addRow(2, studentIDLabel, StudentID);

		Label studentAvgLabel = new Label("Student AVG:");
		studentAvgLabel.setTextFill(Color.WHITE);
		TextField studentAVG = new TextField();
		studentAVG.setEditable(false);
		pane.addRow(3, studentAvgLabel, studentAVG);

		Label studentGenerLabel = new Label("Student Gender:");
		studentGenerLabel.setTextFill(Color.WHITE);
		TextField StudentGender = new TextField();
		StudentGender.setEditable(false);
		pane.addRow(4, studentGenerLabel, StudentGender);

		ImageView addIcone = new ImageView("add--v1.png");
		addIcone.setFitWidth(35);
		addIcone.setFitHeight(35);
		Button add = new Button("add", addIcone);
		Fill(add);
		add.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");

		depNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				studentNameField.setText("department NOT Exist !");
				studentNameField.setEditable(false);
				studentAVG.setEditable(false);
				StudentID.setEditable(false);
				StudentGender.setEditable(false);
			} else {
				studentNameField.setText("");
				studentNameField.setEditable(true);
				studentAVG.setEditable(true);
				StudentID.setEditable(true);
				StudentGender.setEditable(true);
			}
		});

		add.setOnAction(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			i.getData().addStudent(new Student(studentNameField.getText(), Integer.parseInt(StudentID.getText()),
					Double.parseDouble(studentAVG.getText()), StudentGender.getText().charAt(0)));
		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> student(primaryStage));

		HBox control = new HBox(10, add, back);
		control.setAlignment(Pos.CENTER);

		VBox all = new VBox(40, pane, control);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void deleteDep(Stage primaryStage) {
		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);

		TextField depNameField = new TextField();

		HBox name = new HBox(20, depName, depNameField);
		name.setAlignment(Pos.CENTER);

		ImageView removeIcone = new ImageView("cancel--v1.png");
		removeIcone.setFitWidth(35);
		removeIcone.setFitHeight(35);
		Button remove = new Button("remove", removeIcone);
		Fill(remove);
		remove.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");

		remove.setOnAction(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				error.setContentText("This department is NOT Exist !!");
				error.show();
				depNameField.clear();
			} else {
				departments.delete(new Department(depNameField.getText()));
				sucsses.setContentText("Deleted !");
				sucsses.show();
				depNameField.clear();
			}
		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> department(primaryStage));

		HBox control = new HBox(15, remove, back);
		control.setAlignment(Pos.CENTER);

		VBox all = new VBox(40, name, control);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void searchDep(Stage primaryStage) {
		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);
		TextField depNameField = new TextField();

		HBox name = new HBox(20, depName, depNameField);
		name.setAlignment(Pos.CENTER);

		TextArea print = new TextArea();
		print.setMaxWidth(500);

		depNameField.setOnKeyTyped(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				print.setText("department NOT Exist !");
			} else {
				print.setText(i.getData().getStudents().toString());
			}
		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> department(primaryStage));

		VBox all = new VBox(40, name, print, back);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void insertDep(Stage primaryStage) {
		Label depName = new Label("Department Name:");
		depName.setTextFill(Color.WHITE);

		TextField depNameField = new TextField();

		HBox name = new HBox(20, depName, depNameField);
		name.setAlignment(Pos.CENTER);

		ImageView addIcone = new ImageView("add--v1.png");
		addIcone.setFitWidth(35);
		addIcone.setFitHeight(35);
		Button add = new Button("add", addIcone);
		Fill(add);
		add.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");

		add.setOnAction(e -> {
			Node<Department> i = departments.find(new Department(depNameField.getText()));
			if (i == null) {
				departments.insert(new Department(depNameField.getText()));
				sucsses.setContentText("Added !");
				sucsses.show();
				depNameField.clear();
			} else {
				error.setContentText("This department is already Exist !!");
				error.show();
				depNameField.clear();
			}
		});

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> department(primaryStage));

		HBox control = new HBox(15, add, back);
		control.setAlignment(Pos.CENTER);

		VBox all = new VBox(40, name, control);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void printDepartments(Stage primaryStage) {
		TextArea print = new TextArea(departments.inOrdar());
		print.setMaxWidth(500);

		ImageView backIcone = new ImageView("return.png");
		backIcone.setFitWidth(35);
		backIcone.setFitHeight(35);
		Button back = new Button("Back", backIcone);
		Fill(back);
		back.setStyle(
				"-fx-color:lightblue;-fx-background-radius: 50; -fx-font-family: 'Comic Sans MS';-fx-font-size: 20px;");
		back.setOnAction(e -> department(primaryStage));

		VBox all = new VBox(40, print, back);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));
		all.setAlignment(Pos.CENTER);

		Scene s = new Scene(all, 1000, 600);
		primaryStage.setScene(s);
	}

	private void department(Stage primaryStage) {
		String css = "-fx-color:lightblue;-fx-background-radius: 50; -fx-min-width:350; -fx-min-height:60";

		Button insertDep = new Button("Add Department");
		mainFill(insertDep);
		insertDep.setStyle(css);
		insertDep.setOnAction(e -> insertDep(primaryStage));

		Button searchDep = new Button("Search Department");
		mainFill(searchDep);
		searchDep.setStyle(css);
		searchDep.setOnAction(e -> searchDep(primaryStage));

		Button deleteDep = new Button("Delete Department");
		mainFill(deleteDep);
		deleteDep.setStyle(css);
		deleteDep.setOnAction(e -> deleteDep(primaryStage));

		Button print = new Button("Print all Departments");
		mainFill(print);
		print.setStyle(css);
		print.setOnAction(e -> printDepartments(primaryStage));

		Button back = new Button("Return to Main Menue");
		mainFill(back);
		back.setStyle(css);
		back.setOnAction(e -> mainInterface(primaryStage));

		Label treeHeight = new Label("Tree height = " + departments.height());
		treeHeight.setTextFill(Color.WHITE);

		VBox all = new VBox(30, insertDep, searchDep, deleteDep, print, back, treeHeight);
		all.setAlignment(Pos.CENTER);
		all.setBackground(new Background(new BackgroundImage(new Image("background.png"), null, null, null, null)));

		Scene s = new Scene(all, 1000, 600);

		primaryStage.setScene(s);
	}

	private void read(File showOpenDialog) {
		try {
			Scanner scan = new Scanner(showOpenDialog);
			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split("/");
				Department newDep = new Department(tokens[0].trim());
				File f = new File(tokens[1].trim());
				if (f.exists())
					readStudents(newDep, tokens[1].trim());
				departments.insert(newDep);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			error.setContentText(e.getMessage());
			error.show();
		}
	}

	private void readStudents(Department newDep, String string) {
		try {
			File input = new File(string);
			Scanner scan = new Scanner(input);
			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split("/");
				newDep.addStudent(new Student(tokens[0].trim(), Integer.parseInt(tokens[1].trim()),
						Double.parseDouble(tokens[2].trim()), tokens[3].trim().charAt(0)));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			warning.setContentText("Department: " + newDep.getName() + " Does NOT have students.");
			warning.show();
		}

	}
}