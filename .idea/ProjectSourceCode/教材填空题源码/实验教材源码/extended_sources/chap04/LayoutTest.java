//LayoutTest.java
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
public class LayoutTest extends Application {
	public void start(Stage primaryStage)	{
		try	{
			// 根节点: HBox
			HBox hbox = new HBox();
			Button b1 = new Button("中国");
			Button b2 = new Button("China");
			Button b3 = new Button("中华人民共和国");
			// 把按钮添加到 HBox
			hbox.getChildren().addAll(b1, b2, b3);
			Scene scene = new Scene(hbox, 400, 50);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e)	{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)	{
		launch(args);
	}
}