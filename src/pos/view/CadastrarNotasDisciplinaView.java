package pos.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pos.dao.DisciplinaDAO;
import pos.dao.NotaDAO;
import pos.dao.UsuarioDAO;
import pos.exception.QuantidadeLivrosMaximoException;
import pos.model.Aluno;
import pos.model.Disciplina;
import pos.model.Nota;
import pos.model.Usuario;
import pos.util.Mensagens;

public class CadastrarNotasDisciplinaView extends Application {

	private AnchorPane pane;
	private Label lbAluno, lbDisciplina, lbNota1, lbNota2;
	private TextField tfNota1, tfNota2;
	private ComboBox<Usuario> cbUsuario;
	private ComboBox<Disciplina> cbDisciplina;
	private Button btCadastrar;
	private Usuario usuarioSelecionado;
	private Disciplina disciplinaSelecionada;

	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(600, 300);

		lbAluno = new Label("Aluno:");
		lbAluno.setFont(Font.font("Tahoma", 15));

		cbUsuario = new ComboBox<>();
		cbUsuario.setItems(FXCollections.observableArrayList(UsuarioDAO.getUsuarios()));
		cbUsuario.getStyleClass().add("combobox");

		lbDisciplina = new Label("Disciplina:");
		lbDisciplina.setFont(Font.font("Tahoma", 15));

		cbDisciplina = new ComboBox<>();
		cbDisciplina.getStyleClass().add("combobox");

		lbNota1 = new Label("Nota 1:");
		lbNota1.setFont(Font.font("Tahoma", 15));

		tfNota1 = new TextField();
		tfNota1.setPrefWidth(200);
		tfNota1.setFont(Font.font("Tahoma", 15));
		tfNota1.setMaxWidth(80);

		lbNota2 = new Label("Nota 2:");
		lbNota2.setFont(Font.font("Tahoma", 15));

		tfNota2 = new TextField();
		tfNota2.setPrefWidth(200);
		tfNota2.setFont(Font.font("Tahoma", 15));
		tfNota2.setMaxWidth(80);

		btCadastrar = new Button("Cadastrar");
		btCadastrar.setFont(Font.font("Tahoma", 15));
		btCadastrar.getStyleClass().add("botao");

		// initTable();

		pane.getChildren().addAll(lbAluno, cbUsuario, lbDisciplina, cbDisciplina, btCadastrar, lbNota1, lbNota2,
				tfNota1, tfNota2);
	}

	private void initLayout() {
		lbAluno.setLayoutX(20);
		lbAluno.setLayoutY(20);

		cbUsuario.setLayoutX(20);
		cbUsuario.setLayoutY(40);

		lbDisciplina.setLayoutX(250);
		lbDisciplina.setLayoutY(20);

		cbDisciplina.setLayoutX(250);
		cbDisciplina.setLayoutY(40);

		lbNota1.setLayoutX(20);
		lbNota1.setLayoutY(90);

		tfNota1.setLayoutX(20);
		tfNota1.setLayoutY(110);

		lbNota2.setLayoutX(250);
		lbNota2.setLayoutY(90);

		tfNota2.setLayoutX(250);
		tfNota2.setLayoutY(110);

		btCadastrar.setLayoutX(20);
		btCadastrar.setLayoutY(170);

		// table.setPrefSize(pane.getWidth(), 347);
		// table.setLayoutX(0);
		// table.setLayoutY(250);
	}

	private void initListeners() {
		cbUsuario.setOnAction((event) -> {
			usuarioSelecionado = cbUsuario.getValue();
			cbDisciplina.setItems(FXCollections.observableArrayList(((Aluno) usuarioSelecionado).getDisciplinas()));
		});

		cbDisciplina.setOnAction((event) -> {
			disciplinaSelecionada = cbDisciplina.getValue();
		});

		btCadastrar.setOnAction((event) -> {
			Aluno a = (Aluno) usuarioSelecionado;
			double nota1 = Double.parseDouble(tfNota1.getText());
			double nota2 = Double.parseDouble(tfNota2.getText());
			
			Nota nota = new Nota(a, disciplinaSelecionada, nota1, nota2);
			NotaDAO.cadastrar(nota);
			Mensagens.mensagemInformacao("Notas cadastradas com sucesso.");
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		// table.setItems(listaAlunos);
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("/pos/css/style.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Cadastrar Notas das Disciplinas");

		stage.show();
		initLayout();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
