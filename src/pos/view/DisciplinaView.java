package pos.view;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pos.dao.DisciplinaDAO;
import pos.model.Disciplina;
import pos.model.table.ItensPropertyDisciplina;
import pos.util.Mensagens;

public class DisciplinaView extends Application{

	private AnchorPane pane;
	private Label lbNome;
	private TextField tfNome;
	private Button btSalvar;
	private TableView<ItensPropertyDisciplina> table;
	private static ObservableList<ItensPropertyDisciplina> listaDisciplinas = FXCollections.observableArrayList();
	
	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(600, 600);
		
		lbNome = new Label("Nome:");
		lbNome.setFont(Font.font("Tahoma", 15));
		
		tfNome = new TextField();
		tfNome.setPrefWidth(200);
		tfNome.setFont(Font.font("Tahoma", 15));
		
		btSalvar = new Button("Salvar");
		btSalvar.setFont(Font.font("Tahoma", 15));
		btSalvar.getStyleClass().add("botao");
		
		initTable();
		
		pane.getChildren().addAll(lbNome, tfNome, btSalvar, table);
	}
	
	private void initLayout() {
		lbNome.setLayoutX(20);
		lbNome.setLayoutY(20);
		
		tfNome.setLayoutX(20);
		tfNome.setLayoutY(40);
		
		btSalvar.setLayoutX(250);
		btSalvar.setLayoutY(40);
		
		table.setPrefSize(pane.getWidth(), 347);
		table.setLayoutX(0);
		table.setLayoutY(250);
	}
	
	private void initListeners() {
		btSalvar.setOnAction((event) -> {
			String nome = tfNome.getText();
			
			Disciplina d = new Disciplina(nome);
			DisciplinaDAO.salvar(d);
			preencherTabela();
			limparCampos();
			Mensagens.mensagemInformacao("Disciplina cadastrada com sucesso.");
		});

	}
	
	private void preencherTabela() {
		List<Disciplina> disciplinas = DisciplinaDAO.getDisciplinas();
		listaDisciplinas.clear();
		for (Disciplina d : disciplinas) {
			listaDisciplinas.add(new ItensPropertyDisciplina(d.getNome()));
		}
	}
	
	private void limparCampos() {
		TextField[] tfCampos = {tfNome};
		for (TextField tf : tfCampos) {
			tf.setText("");
		}
	}
	
	private void initTable() {
		table = new TableView<>();
		
		table.setPlaceholder(new Label("Nenhuma disciplina encontrada."));

		TableColumn<ItensPropertyDisciplina, String> nome = new TableColumn("Nome");

		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		nome.setSortable(false);

		nome.setPrefWidth(600);

		table.getColumns().add(nome);
	}

	
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		table.setItems(listaDisciplinas);
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("/pos/css/style.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Cadastro e Consulta de Disciplinas");

		stage.show();
		initLayout();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
