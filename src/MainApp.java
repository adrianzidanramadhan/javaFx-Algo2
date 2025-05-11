import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;
import model.Siswa;
import utils.FileHandler;
import utils.SearchUtil;
import java.util.*;

public class MainApp extends Application {
    private ObservableList<Siswa> siswaList;
    private TableView<Siswa> table;
    private final String FILE_PATH = "siswa.csv";
    // private final String FILE_PATH = "C:/Users/ThinkPad/Documents/UTS ALGO2/UTS Algo2/siswa.csv";

    @Override
    public void start(Stage primaryStage) {
        siswaList = FXCollections.observableArrayList(FileHandler.readFromCSV(FILE_PATH));
        System.out.println("Jumlah data dari file: " + siswaList.size());


        table = new TableView<>(siswaList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Siswa, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(data -> data.getValue().namaProperty());

        TableColumn<Siswa, String> colNIM = new TableColumn<>("NIM");
        colNIM.setCellValueFactory(data -> data.getValue().nimProperty());

        TableColumn<Siswa, Number> colNilai1 = new TableColumn<>("Algoritma");
        colNilai1.setCellValueFactory(data -> data.getValue().nilai1Property());

        TableColumn<Siswa, Number> colNilai2 = new TableColumn<>("Basis Data");
        colNilai2.setCellValueFactory(data -> data.getValue().nilai2Property());

        TableColumn<Siswa, Number> colNilai3 = new TableColumn<>("Literasi Digital");
        colNilai3.setCellValueFactory(data -> data.getValue().nilai3Property());

        TableColumn<Siswa, Number> colRata = new TableColumn<>("Rata-rata");
        colRata.setCellValueFactory(data -> data.getValue().rataRataProperty());

        table.getColumns().addAll(colNama, colNIM, colNilai1, colNilai2, colNilai3, colRata);

        TextField namaField = new TextField();
        namaField.setPromptText("Nama");
        TextField nimField = new TextField();
        nimField.setPromptText("NIM");
        TextField n1Field = new TextField();
        n1Field.setPromptText("Algoritma");
        TextField n2Field = new TextField();
        n2Field.setPromptText("Basis Data");
        TextField n3Field = new TextField();
        n3Field.setPromptText("Literasi Digital");

        Button addBtn = new Button("Tambah");
        addBtn.setOnAction(e -> {
            try {
                Siswa s = new Siswa(
                    namaField.getText(), nimField.getText(),
                    Double.parseDouble(n1Field.getText()),
                    Double.parseDouble(n2Field.getText()),
                    Double.parseDouble(n3Field.getText())
                );
                siswaList.add(s);
                FileHandler.writeToCSV(FILE_PATH, siswaList);
                namaField.clear(); nimField.clear();
                n1Field.clear(); n2Field.clear(); n3Field.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button sortBtn = new Button("Urutkan Nilai Desc");
        sortBtn.setOnAction(e -> {
            siswaList.sort((a, b) -> Double.compare(b.getRataRata(), a.getRataRata()));
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Cari nama siswa");
        Button searchBtn = new Button("Cari");
        searchBtn.setOnAction(e -> {
            siswaList.sort(Comparator.comparing(Siswa::getNama));
            int idx = SearchUtil.binarySearch(new ArrayList<>(siswaList), searchField.getText());
            if (idx >= 0) table.getSelectionModel().select(idx);
        });

        Button avgBtn = new Button("Hitung Rata-rata Total");
        avgBtn.setOnAction(e -> {
            double avg = SearchUtil.avgRecursive(new ArrayList<>(siswaList));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rata-rata semua siswa: " + avg);
            alert.showAndWait();
        });

        HBox inputBox = new HBox(5, namaField, nimField, n1Field, n2Field, n3Field, addBtn);
        HBox actionBox = new HBox(5, sortBtn, searchField, searchBtn, avgBtn);
        VBox root = new VBox(10, table, inputBox, actionBox);

        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("Aplikasi Nilai Siswa");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
