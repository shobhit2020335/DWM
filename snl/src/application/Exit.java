package application;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Exit 
{
	
	static Stage exit_stage;
	
	public static void exit(ActionEvent e) 
	{
		Main.root.setDisable(true);
		Main.root.setOpacity(0.5);
		BoxBlur b = new BoxBlur();  
		b.setIterations(1);  
		Main.root.setEffect(b);

		Pane exit_pane  = new Pane();
		
		Label label = new Label("Are you sure you want to exit");
		label.setScaleX(1.4);
		label.setScaleY(1.4);
		label.setTranslateX(70);
		label.setTranslateY(50);
		
		
		Button yes = new Button("Yes");
		yes.setTranslateX(300);
		yes.setTranslateY(150);
		yes.setOnAction(y -> ok(y));
		
		
		Button no = new Button("NO");
		no.setTranslateX(50);
		no.setTranslateY(150);
		no.setOnAction(n -> not());
		no.setCancelButton(true);
		
		
		exit_pane.getChildren().addAll(label,yes,no);
		
		Scene exit_scene = new Scene(exit_pane,400,200);
		exit_stage = new Stage();
		exit_stage.setScene(exit_scene);
		exit_stage.setAlwaysOnTop(true);
		exit_stage.show();
		exit_stage.setOnCloseRequest(n -> not());
	}

	private static void not() 
	{
		Main.root.setDisable(false);
		Main.root.setOpacity(1);
		BoxBlur b = new BoxBlur();  
        b.setIterations(1);  
        Main.root.setEffect(b);
        Main.root.setEffect(null);
        exit_stage.close();        
	}

	private static void ok(ActionEvent E) 
	{
		System.out.println("okay");
		exit_stage.close();
		Main.Main_stage.close();
//		Main.Main_stage.r
	}

}













