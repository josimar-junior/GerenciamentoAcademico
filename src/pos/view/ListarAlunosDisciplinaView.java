package pos.view;

import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pos.dao.DisciplinaDAO;
import pos.dao.NotaDAO;
import pos.model.Disciplina;
import pos.model.Nota;
import pos.model.table.ItensPropertyAlunoDisciplina;
import pos.model.table.ItensPropertyDisciplina;

public class ListarAlunosDisciplinaView extends Application {
	
	private AnchorPane pane;
	private Label lbDisciplina;
	private ComboBox<Disciplina> cbDisciplina;
	private TableView<ItensPropertyAlunoDisciplina> table;
	private static ObservableList<ItensPropertyAlunoDisciplina> listaAlunoDisciplinas = FXCollections.observableArrayList();
	private Disciplina disciplinaSelecionada;
	
	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(700, 500);

		lbDisciplina = new Label("Disciplina:");
		lbDisciplina.setFont(Font.font("Tahoma", 15));

		cbDisciplina = new ComboBox<>();
		cbDisciplina.setItems(FXCollections.observableArrayList(DisciplinaDAO.getDisciplinas()));
		cbDisciplina.getStyleClass().add("combobox");

		 initTable();

		pane.getChildren().addAll(lbDisciplina, cbDisciplina, table);
	}
	
	private void initLayout() {

		lbDisciplina.setLayoutX((pane.getWidth() - lbDisciplina.getWidth())/2);
		lbDisciplina.setLayoutY(20);

		cbDisciplina.setLayoutX((pane.getWidth() - cbDisciplina.getWidth())/2);
		cbDisciplina.setLayoutY(40);

		 table.setPrefSize(pane.getWidth(), 347);
		 table.setLayoutX(0);
		 table.setLayoutY(250);
	}
	
	private void initTable() {
		table = new TableView<>();
		
		table.setPlaceholder(new Label("Nenhum resultado encontrado."));

		TableColumn<ItensPropertyAlunoDisciplina, String> nome = new TableColumn("Nome");
		TableColumn<ItensPropertyAlunoDisciplina, Double> nota1 = new TableColumn("Nota 1");
		TableColumn<ItensPropertyAlunoDisciplina, Double> nota2 = new TableColumn("Nota 2");
		TableColumn<ItensPropertyAlunoDisciplina, Double> media = new TableColumn("Média");

		nome.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
		nome.setSortable(false);
		
		nota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
		nota1.setSortable(false);
		
		nota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
		nota2.setSortable(false);
		
		media.setCellValueFactory(new PropertyValueFactory<>("media"));
		media.setSortable(false);

		nome.setPrefWidth(310);
		nota1.setPrefWidth(130);
		nota2.setPrefWidth(130);
		media.setPrefWidth(130);

		table.getColumns().addAll(nome, nota1, nota2, media);
	}
	
	private void initListeners() {
		cbDisciplina.setOnAction((event) -> {
			disciplinaSelecionada = cbDisciplina.getValue();
			preencherTabela();
		});
	}
	
	private void preencherTabela() {
		listaAlunoDisciplinas.clear();
		
		List<Nota> notas = NotaDAO.getNotas();
		List<Nota> lista = notas.stream().filter(n -> n.getDisciplina().equals(disciplinaSelecionada)).collect(Collectors.toList());
		
		for(Nota nota : lista) {
			listaAlunoDisciplinas.add(new ItensPropertyAlunoDisciplina(nota.getAluno().getNome(), nota.getNota1(), nota.getNota2(), (nota.getNota1() + nota.getNota2())/2));
		}
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		listaAlunoDisciplinas.clear();
		table.setItems(listaAlunoDisciplinas);
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("/pos/css/style.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Vincular Aluno à Disciplina");

		stage.show();
		initLayout();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
