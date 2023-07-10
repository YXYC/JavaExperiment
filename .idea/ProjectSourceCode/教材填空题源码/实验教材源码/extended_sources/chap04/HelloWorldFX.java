//HelloWorldFX.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class HelloWorldFX extends Application {
    public void start(Stage primaryStage) throws Exception {
        Button btnHello = new Button("Hello");
        btnHello.setOnAction(event->{  
            btnHello.setText("Hello World, I am JavaFX!");
        });
        BorderPane pane = new BorderPane();
        pane.setCenter(btnHello);
        // 将pane加入到Scene中
        Scene scene = new Scene(pane, 500, 500);
        // 设置stage的scene，然后显示stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }
    public static void main(String[] args) {
        // JavaFX中main方法必须需要调用launch方法
        launch(args);
    }
}