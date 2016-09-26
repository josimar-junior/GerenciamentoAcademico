package pos.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pos.dao.DisciplinaDAO;
import pos.dao.UsuarioDAO;
import pos.exception.DisciplinaExistenteException;
import pos.exception.QuantidadeLivrosMaximoException;
import pos.model.Aluno;
import pos.model.Disciplina;
import pos.model.Usuario;
import pos.util.Mensagens;

public class VincularAlunoDisciplinaView extends Application {

	private AnchorPane pane;
	private Label lbAluno, lbDisciplina;
	private ComboBox<Usuario> cbUsuario;
	private ComboBox<Disciplina> cbDisciplina;
	private Button btVincular;
	private Usuario usuarioSelecionado;
	private Disciplina disciplinaSelecionada;

	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(700, 100);

		lbAluno = new Label("Aluno:");
		lbAluno.setFont(Font.font("Tahoma", 15));

		cbUsuario = new ComboBox<>();
		cbUsuario.setItems(FXCollections.observableArrayList(UsuarioDAO.getUsuarios()));
		cbUsuario.getStyleClass().add("combobox");

		lbDisciplina = new Label("Disciplina:");
		lbDisciplina.setFont(Font.font("Tahoma", 15));

		cbDisciplina = new ComboBox<>();
		cbDisciplina.setItems(FXCollections.observableArrayList(DisciplinaDAO.getDisciplinas()));
		cbDisciplina.getStyleClass().add("combobox");

		btVincular = new Button("Vincular");
		btVincular.setFont(Font.font("Tahoma", 15));
		btVincular.getStyleClass().add("botao");

		pane.getChildren().addAll(lbAluno, cbUsuario, lbDisciplina, cbDisciplina, btVincular);
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

		btVincular.setLayoutX(520);
		btVincular.setLayoutY(40);

	}
	
	private void verificarDisciplina() throws DisciplinaExistenteException {
		Aluno a = (Aluno) usuarioSelecionado;
		if(a.getDisciplinas().contains(disciplinaSelecionada)) {
			throw new DisciplinaExistenteException("A disciplina já está vinculada ao aluno.");
		}
	}

	private void initListeners() {
		cbUsuario.setOnAction((event) -> {
			usuarioSelecionado = cbUsuario.getValue();
		});

		cbDisciplina.setOnAction((event) -> {
			disciplinaSelecionada = cbDisciplina.getValue();
		});

		btVincular.setOnAction((event) -> {
			try {
				verificarDisciplina();
				Aluno a = (Aluno) usuarioSelecionado;
				if (a.getQtdDisciplinas() == 4) {
					throw new QuantidadeLivrosMaximoException("O aluno está matriculado em 4 disciplinas.");
				}
				a.getDisciplinas().add(disciplinaSelecionada);
				a.setQtdDisciplinas();
				Mensagens.mensagemInformacao("Aluno vinculado à disciplina com sucesso.");
			} catch (QuantidadeLivrosMaximoException e) {
				Mensagens.mensagemErro(e.getMessage());
			} catch (DisciplinaExistenteException e) {
				Mensagens.mensagemErro(e.getMessage());
			}
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
		stage.setTitle("Vincular Aluno à Disciplina");

		stage.show();
		initLayout();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
