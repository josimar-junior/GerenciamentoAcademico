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
import pos.dao.UsuarioDAO;
import pos.model.Aluno;
import pos.model.Usuario;
import pos.model.table.ItensPropertyAluno;
import pos.util.Mensagens;

public class AlunosView extends Application{

	private AnchorPane pane;
	private Label lbMatricula, lbNome;
	private TextField tfMatricula, tfNome;
	private Button btSalvar;
	private TableView<ItensPropertyAluno> table;
	private static ObservableList<ItensPropertyAluno> listaAlunos = FXCollections.observableArrayList();
	
	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(600, 600);
		
		lbMatricula = new Label("Matrícula:");
		lbMatricula.setFont(Font.font("Tahoma", 15));
		
		tfMatricula = new TextField();
		tfMatricula.setPrefWidth(200);
		tfMatricula.setFont(Font.font("Tahoma", 15));
		
		lbNome = new Label("Nome:");
		lbNome.setFont(Font.font("Tahoma", 15));
		
		tfNome = new TextField();
		tfNome.setPrefWidth(200);
		tfNome.setFont(Font.font("Tahoma", 15));
		
		btSalvar = new Button("Salvar");
		btSalvar.setFont(Font.font("Tahoma", 15));
		btSalvar.getStyleClass().add("botao");
		
		initTable();
		
		pane.getChildren().addAll(lbMatricula, lbNome, tfMatricula, tfNome, btSalvar, table);
	}
	
	private void initLayout() {
		lbMatricula.setLayoutX(20);
		lbMatricula.setLayoutY(20);
		
		tfMatricula.setLayoutX(20);
		tfMatricula.setLayoutY(40);
		
		lbNome.setLayoutX(250);
		lbNome.setLayoutY(20);

		tfNome.setLayoutX(250);
		tfNome.setLayoutY(40);
		
		btSalvar.setLayoutX(520);
		btSalvar.setLayoutY(40);
		
		table.setPrefSize(pane.getWidth(), 347);
		table.setLayoutX(0);
		table.setLayoutY(250);
	}
	
	private void initListeners() {
		btSalvar.setOnAction((event) -> {
			String matricula = tfMatricula.getText();
			String nome = tfNome.getText();
			
			Aluno usuario = new Aluno(matricula, nome);
			UsuarioDAO.salvar(usuario);
			preencherTabela();
			limparCampos();
			Mensagens.mensagemInformacao("Aluno cadastrado com sucesso.");
		});
	}
	
	private void preencherTabela() {
		List<Usuario> usuarios = UsuarioDAO.getUsuarios();
		listaAlunos.clear();
		for (Usuario u : usuarios) {
			listaAlunos.add(new ItensPropertyAluno(u.getMatricula(), u.getNome()));
		}
	}
	
	private void limparCampos() {
		TextField[] tfCampos = {tfMatricula, tfNome};
		for (TextField tf : tfCampos) {
			tf.setText("");
		}
	}
	
	private void initTable() {
		table = new TableView<>();
		
		table.setPlaceholder(new Label("Nenhum aluno encontrado."));

		TableColumn<ItensPropertyAluno, String> matricula = new TableColumn("Matrícula");
		TableColumn<ItensPropertyAluno, String> nome = new TableColumn("Nome");

		matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
		matricula.setSortable(false);

		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		nome.setSortable(false);

		matricula.setPrefWidth(200);
		nome.setPrefWidth(400);

		table.getColumns().addAll(matricula, nome);
	}

	
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		table.setItems(listaAlunos);
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("/pos/css/style.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Cadastro e Consulta de Alunos");

		stage.show();
		initLayout();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
