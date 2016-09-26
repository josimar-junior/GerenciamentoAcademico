package pos.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GerenciamentoAcademicoView extends Application {

	private AnchorPane pane;
	private Button btAlunos, btDisciplinas, btVincularAlunoDisciplina, btCadastrarNotaDisciplina, btAlunoDaDisciplina;

	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(500, 500);

		btAlunos = new Button("ALUNOS");
		btAlunos.setFont(Font.font("Tahoma", 15));
		btAlunos.getStyleClass().add("botao");
		btAlunos.setMinHeight(40);

		btDisciplinas = new Button("DISCIPLINAS");
		btDisciplinas.setFont(Font.font("Tahoma", 15));
		btDisciplinas.getStyleClass().add("botao");
		btDisciplinas.setMinHeight(40);

		btVincularAlunoDisciplina = new Button("VINCULAR ALUNO À DISCIPLINA");
		btVincularAlunoDisciplina.setFont(Font.font("Tahoma", 15));
		btVincularAlunoDisciplina.getStyleClass().add("botao");
		btVincularAlunoDisciplina.setMinHeight(40);

		btCadastrarNotaDisciplina = new Button("CADASTRAR NOTA DA DISCIPLINA");
		btCadastrarNotaDisciplina.setFont(Font.font("Tahoma", 15));
		btCadastrarNotaDisciplina.getStyleClass().add("botao");
		btCadastrarNotaDisciplina.setMinHeight(40);

		btAlunoDaDisciplina = new Button("LISTAR ALUNOS DA DISCIPLINA");
		btAlunoDaDisciplina.setFont(Font.font("Tahoma", 15));
		btAlunoDaDisciplina.getStyleClass().add("botao");
		btAlunoDaDisciplina.setMinHeight(40);

		pane.getChildren().addAll(btAlunos, btDisciplinas, btVincularAlunoDisciplina, btCadastrarNotaDisciplina,
				btAlunoDaDisciplina);
	}

	private void initLayout() {
		btAlunos.setLayoutX((pane.getWidth() - btAlunos.getWidth())/2);
		btAlunos.setLayoutY(80);

		btDisciplinas.setLayoutX((pane.getWidth() - btDisciplinas.getWidth())/2);
		btDisciplinas.setLayoutY(140);

		btVincularAlunoDisciplina.setLayoutX((pane.getWidth() - btVincularAlunoDisciplina.getWidth())/2);
		btVincularAlunoDisciplina.setLayoutY(200);

		btCadastrarNotaDisciplina.setLayoutX((pane.getWidth() - btCadastrarNotaDisciplina.getWidth())/2);
		btCadastrarNotaDisciplina.setLayoutY(260);

		btAlunoDaDisciplina.setLayoutX((pane.getWidth() - btAlunoDaDisciplina.getWidth())/2);
		btAlunoDaDisciplina.setLayoutY(320);

	}
	
	private void initListeners() {
		btAlunos.setOnAction((event) -> {
			try {
				new AlunosView().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		btDisciplinas.setOnAction((event) -> {
			try {
				new DisciplinaView().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		btVincularAlunoDisciplina.setOnAction((event) -> {
			try {
				new VincularAlunoDisciplinaView().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		btCadastrarNotaDisciplina.setOnAction((event) -> {
			try {
				new CadastrarNotasDisciplinaView().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		btAlunoDaDisciplina.setOnAction((event) -> {
			try {
				new ListarAlunosDisciplinaView().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("/pos/css/style.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Gerenciamento Acadêmico");

		stage.show();
		initLayout();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
