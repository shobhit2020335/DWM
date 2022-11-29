package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class winner {

	public static void win(int i) {
		Pane p=new Pane();
//		Image im=new Image(getClass().getResourceAsStream("winner.jpg"));
		ImageView iv=new ImageView();
		iv.setImage(Main.winnerimg);
		iv.setFitWidth(400);
		iv.setFitHeight(300);
		Label l =new Label();
		l.setScaleX(3);
		l.setScaleY(3);
		l.setText("PLAYER "+i+" WON");
		
		l.setTranslateX(170);
		l.setTranslateY(100);
		Button b=new Button("  EXIT  ");
		b.setStyle("-fx-color: red;");
		b.setTranslateX(343);
		b.setTranslateY(273);
		b.setOnAction(e->{
//			Main.main(null);
			Main.Main_stage.close();
		});
		p.getChildren().addAll(iv,l,b);
		
		
		Scene scene=new Scene(p,400,300);
		Main.Main_stage.setScene(scene);
		
	}
}
