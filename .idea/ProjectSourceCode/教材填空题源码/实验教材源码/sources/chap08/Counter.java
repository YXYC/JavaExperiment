import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Counter extends Application {
	Button button = new Button("开始计时");
	Label time = new Label();
	public void start(Stage primaryStage){
		try{
			StackPane root = _____________;//创建根面板
			root.getChildren().addAll(button, time);
			StackPane.setAlignment(button, Pos.TOP_CENTER);
			StackPane.setAlignment(time, Pos.CENTER);
			root.getStyleClass().add("main");
			time.getStyleClass().add("counter");
			Scene scene = new Scene(root, 300, 200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			button.setOnAction((e) -> {
				new DownCounter(9).__________; // 开始倒计时

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		launch(args);
	}

	class DownCounter________________ { //从Thread继承
		int count = 0;
		public DownCounter(int count){
			this.count = count;
		}
		public void run(){
			while (count >= 0){
				Platform.runLater(new Runnable() {
					public void run(){
						String str = String.valueOf(count);
						time.setText(str);
						_________________;  //计数器减1
					}
				});
				try{
					_________________________; //线程休息1秒
				} catch (Exception e) {
				}
			}
		}

	}
}