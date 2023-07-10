//Slide.java
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
public class Slide extends Application {
	String[]imageUrls = {// 资源图片
		"res/hangmu.jpg",
		"res/tiangong.jpg",
		"res/kongjing500.jpg",
	};
	Image[] images = new Image[3];
	ImageView imageView = new ImageView();
	int currentIndex =0; // 当前显示的是哪个图片
	public void start(Stage primaryStage){
		try	{
			BorderPane root = new BorderPane();			
			Button btnOpen = new Button("下一张");		
			Label lb=new Label("JavaFX中显示图片:");
			// 按钮单击事件的处理
			btnOpen.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event)	{
					Slide.this.showNext();					
				}				
			});		
			// 加载图片
			for(int i=0; i<images.length; i++)	{
				images[i] = new Image(imageUrls[i]);
			}
			// 保持比例，适应窗口显示
			imageView.setPreserveRatio(true);
			imageView.setFitWidth(400);
			imageView.setFitHeight(320);
			btnOpen.setMaxWidth(400);
			root.setTop(lb);
			root.setBottom(btnOpen); // 下面显示按钮
			root.setCenter(imageView); // 中间显示图片
			Scene scene = new Scene(root, 400, 300);
			primaryStage.setScene(scene);
			primaryStage.show();
			showNext();
		} catch (Exception e)		{
			e.printStackTrace();
		}
	}
	public void showNext()	{
		currentIndex ++;
		if(currentIndex >=3) currentIndex = 0;
		imageView.setImage( images[ currentIndex ]);
	}
	public static void main(String[] args)	{
		launch(args);
	}
}