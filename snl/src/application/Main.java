package application;
	
import java.util.Scanner;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	
	static Pane root; 
	int random;
	Button play1;
	Button play2;
	Button exit;
	
	Image up ;
	Image down;
	ImageView arrow;
	ImageView dice;
	Image dice_img;
//	Image [] dice_value ;
	static Image winnerimg;
//	static Image menuimg;
//	int random ;
	boolean turn = true;
	
	ImageView player1;
	ImageView player2;
	
	int position_p1; 
	int position_p2; 
	
	int snakes_head[] = {43,50,56,73,84,87,98 };
	int snakes_tail[] = {17,5,8,15,58,49,40};
	int ladders_down[] = {2,6,20,52,57,71};
	int ladders_up[]    = {23,45,59,72,96,92};
	int ladders_x_move[] = {1,-1,1,0,1,-1};
	int ladders_y_move [] = {2,4,4,2,4,2};
	static Stage Main_stage;
	static boolean win=false;
	static boolean win1=false;
	static Scene startmenu;   
	Image[] array;
	
	
	
	@Override
	public void start(Stage primaryStage) {
		AudioClip bckg = new AudioClip(this.getClass().getResource("bckg.wav").toString());
		bckg.play();
		bckg.setVolume(0.1);
		bckg.setCycleCount(Timeline.INDEFINITE);
		Image menuimg=new Image(getClass().getResourceAsStream("menupage.jpg"));
		winnerimg = new Image(getClass().getResourceAsStream("winner.jpg"));
		Pane p=new Pane();
		ImageView iv=new ImageView();
		iv.setImage(menuimg);
		iv.setFitWidth(512);
		iv.setFitHeight(512);
		

		Button b=new Button("START");
		b.setStyle("-fx-color: brown;");
		b.setScaleX(2);
		b.setScaleY(2);
		b.setTranslateX(256);
		b.setTranslateY(226);
	
		
		Button c=new Button("EXIT");
		c.setStyle("-fx-color: brown;");
		c.setScaleX(2);
		c.setScaleY(2);
		c.setTranslateX(256);
		c.setTranslateY(286);
		c.setOnAction(e->{
			System.out.println("menu to exit");
			primaryStage.close();
		});
		p.getChildren().addAll(iv,c,b);
		
		
		Scene scene=new Scene(p,512,512);
		b.setOnAction(e->{
			System.out.println("menu to game");
			primaryStage.setScene(menu());
		});
		Main_stage=primaryStage;
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public Scene menu() {
		
		root = new Pane();
		
	
		
		Image background_image = new Image(getClass().getResourceAsStream("er.jpeg"));
		ImageView background = new ImageView(background_image);
		root.getChildren().add(background);
		
//		menuimg=new Image(getClass().getResourceAsStream("menupage.jpg"));
		
		Rectangle r = new Rectangle();
		r.setFill(Color.BLUE);
		r.setHeight(720);
		r.setWidth(720);
		r.setTranslateX(80);
		r.setTranslateY(15);
		root.getChildren().add(r);
		
		ImageView immgv = new ImageView();
		Image i = new Image(getClass().getResourceAsStream("a.jpg"));
		immgv.setImage(i);
		root.getChildren().add(immgv);
		immgv.setTranslateX(90);
		immgv.setTranslateY(25);
		
		player1 = new ImageView();
		Image player1_img = new Image(getClass().getResourceAsStream("q.png"));
		player1.setImage(player1_img);
		root.getChildren().add(player1);
		player1.setTranslateX(17);
		player1.setTranslateY(30+(70*9));
		
		player2 = new ImageView();
		Image player2_img = new Image(getClass().getResourceAsStream("bluetok.png"));
		player2.setImage(player2_img);
		root.getChildren().add(player2);
		player2.setTranslateX(-17);
		player2.setTranslateY(30+(70*9));
		
		
		play1 = new Button("Player 1");
		play1.setStyle("-fx-color: red;");
		play1.setTranslateX(940);
		play1.setTranslateY(150);
		play1.setScaleX(1.6);
		play1.setScaleY(1.6);
		play1.setOnAction(A -> player1(A));
		play2 = new Button("Player 2");
		play2.setStyle("-fx-color: blue;");
		play2.setTranslateX(940);
		play2.setTranslateY(550);
		play2.setScaleX(1.6);
		play2.setScaleY(1.6);
		play2.setOnAction(A -> player2(A));
		exit = new Button("Exit");
		exit.setTranslateX(1090);
		exit.setTranslateY(700);
		exit.setStyle("-fx-color: red;");
		exit.setOnAction(e -> Exit.exit(e));
		
		
		up  = new Image(getClass().getResourceAsStream("arr1.png"));
		
		down=  new Image(getClass().getResourceAsStream("arr.png"));
		arrow = new ImageView();
		arrow.setImage(down);
//		arrow.fitHeightProperty().add(100);
//		arrow.fitWidthProperty().add(100);
		arrow.setFitWidth(100);
		arrow.setFitHeight(100);
		arrow.setTranslateX(920);
		arrow.setTranslateY(220);
		TranslateTransition uparr = new TranslateTransition();
		uparr.setByY(-40);
		uparr.setNode(arrow);
		uparr.setAutoReverse(true);
		uparr.setDuration(Duration.seconds(0.5));
		uparr.setInterpolator(Interpolator.EASE_BOTH);
		uparr.setCycleCount(Timeline.INDEFINITE);
		uparr.play();
		
		
//		Image d1 = new Image(getClass().getResourceAsStream("d11.jpeg"));
		   Image i1 = new Image(getClass().getResourceAsStream("dice1 (1).jpg"));
		   Image  i2 = new Image(getClass().getResourceAsStream("dice2 (1).jpg"));   
		   Image  i3 = new Image(getClass().getResourceAsStream("dice3 (1).jpg"));   
		   Image i4 = new Image(getClass().getResourceAsStream("dice4 (2).jpg"));   
		   Image i5 = new Image(getClass().getResourceAsStream("dice5 (4).jpg"));   
		   Image i6 = new Image(getClass().getResourceAsStream("dice6 (1).jpg"));   
	        
	        Image[] array1 = {i1,i2,i3,i4,i5,i6};
	        array=array1;
	 
		
//		Image[] tmp = {d1};
//		dice_value = tmp;
		
//		dice_img = new Image(getClass().getResourceAsStream("x.gif"));
		dice = new ImageView();
//		dice.setImage(dice_img);
		dice.setX(930);
		dice.setY(330);
		root.getChildren().addAll(play1,play2,exit,dice,arrow);
		
		Scene startmenu = new Scene(root,1150,750);
		startmenu.setFill(Color.BLACK);
//		Scene temp=menu.menu();
//		primaryStage.setScene(temp);
//		primaryStage.show();
//		Main_stage=primaryStage;
		
			return startmenu;
//		return null;
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	private boolean player1(ActionEvent a) 
	{
		
//		Animation a=new Animation();
		if(!turn)
		{
			return false;
		}
		arrow.setImage(up);
		arrow.setY(225);
		turn = false ;
		int random=random();
//		dice.setImage(array[random]);
		System.out.println("in player 1    "+ random);
		
		if(position_p1==0)
		{
			if(random==1)
			{
				TranslateTransition t = new TranslateTransition();
				t.setNode(player1);
				t.setByX(80);
				t.setByY(-10);
				t.setDuration(Duration.seconds(0.4));
				t.play();
				position_p1++;
			}
			return false ;	
		}
		SequentialTransition sq1 = new SequentialTransition();
		SequentialTransition sq2 = new SequentialTransition();
		
		for(int i=0;i<random;i++)
		{
			
			int direction = 0;
			if(position_p1%10==0)
			{
				position_p1++;
				TranslateTransition t2 = new TranslateTransition();
				t2.setNode(player1);
				t2.setByY(-70);
				t2.setDuration(Duration.seconds(0.4));
				sq2.getChildren().add(t2);
				
				TranslateTransition t = new TranslateTransition();
				t.setDuration(Duration.seconds(0.4));
				t.setNode(player1);
				t.setByX(0);
				sq1.getChildren().add(t);
			}
			else
			{
				if((position_p1%20)<10)
				{
					direction = 70;
				}
				if((position_p1%20)>10)
				{
					direction = -70;
				} 
				position_p1++;
				TranslateTransition t = new TranslateTransition();
				t.setDuration(Duration.seconds(0.4));
				t.setNode(player1);
				t.setByX(direction);
				sq1.getChildren().add(t);
				
				TranslateTransition t2 = new TranslateTransition();
				t2.setAutoReverse(true);
				t2.setNode(player1);
				t2.setByY(-20);
				t2.setDuration(Duration.seconds(0.2));
				t2.setCycleCount(2);
				sq2.getChildren().add(t2);
			}
			if(position_p1==100) {
				System.out.println("winner p1");
//				winner.win(1);
				win=true;
				break;
			}
		}
		System.out.println(" p1 position -> "+position_p1);
		System.out.println(" p2 position -> "+position_p2);
		Transition ladder = check_ladders_p1();
		if(ladder!=null)
		{
			sq2.getChildren().add(ladder);
		}
//		Transition snake = check_snakes_p1();
//		if(snake!=null)
//		{
//			System.out.println("adding");
//			sq2.getChildren().add(snake);
//		}
		else
		{
			System.out.println(" not adding");			
		}
		sq1.play();
		sq2.play();
		sq2.setOnFinished(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{
						Transition snake = check_snakes_p1();
						if(snake!=null)
						{
							System.out.println("adding");
							snake.play();
						}
						if(win) {
							winner.win(1);
						}
					}
				});
		System.out.println(" p1 position -> "+position_p1);
		System.out.println(" p2 position -> "+position_p2);
		return true;
	}
		
	private boolean player2(ActionEvent a) 
	{
		
		if(turn)
		{
			return false;
		}
		arrow.setImage(down);
		arrow.setY(0);
		turn = true;

		
		int random=random();
//		dice.setImage(array[random]);
		System.out.println("in player 2    "+ random);
		
		if(position_p2==0)
		{
			if(random==1)
			{
				TranslateTransition t = new TranslateTransition();
				t.setNode(player2);
				t.setByX(90);
				t.setByY(-10);
				t.setDuration(Duration.seconds(0.4));
				t.play();
				position_p2++;
			}
			return false ;	
		}
		SequentialTransition sq1 = new SequentialTransition();
		SequentialTransition sq2 = new SequentialTransition();
		
		for(int i=0;i<random;i++)
		{
			
			int direction = 0;
			if(position_p2%10==0)
			{
				position_p2++;
				TranslateTransition t2 = new TranslateTransition();
				t2.setNode(player2);
				t2.setByY(-70);
				t2.setDuration(Duration.seconds(0.4));
				sq2.getChildren().add(t2);
				
				TranslateTransition t = new TranslateTransition();
				t.setDuration(Duration.seconds(0.4));
				t.setNode(player2);
				t.setByX(0);
				sq1.getChildren().add(t);
			}
			else
			{
				if((position_p2%20)<10)
				{
					direction = 70;
				}
				if((position_p2%20)>10)
				{
					direction = -70;
				} 
				position_p2++;
				TranslateTransition t = new TranslateTransition();
				t.setDuration(Duration.seconds(0.4));
				t.setNode(player2);
				t.setByX(direction);
				sq1.getChildren().add(t);
				
				TranslateTransition t2 = new TranslateTransition();
				t2.setAutoReverse(true);
				t2.setNode(player2);
				t2.setByY(-20);
				t2.setDuration(Duration.seconds(0.2));
				t2.setCycleCount(2);
				sq2.getChildren().add(t2);
			}
			if(position_p2==100) {
				System.out.println("winner p2");
				win1=true;
				break;
			}
		}
		System.out.println(" p1 position -> "+position_p1);
		System.out.println(" p2 position -> "+position_p2);
		Transition ladder = check_ladders_p2();
		if(ladder!=null)
		{
			sq2.getChildren().add(ladder);
		}
//		Transition snake = check_snakes_p1();
//		if(snake!=null)
//		{
//			System.out.println("adding");
//			sq2.getChildren().add(snake);
//		}
		else
		{
			System.out.println(" not adding");			
		}
		sq1.play();
		sq2.play();
		sq2.setOnFinished(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{
						Transition snake = check_snakes_p2();
						if(snake!=null)
						{
							System.out.println("adding");
							snake.play();
						}
						if(win1) {
							winner.win(2);
						}
					}
				});
		
		System.out.println(" p1 position -> "+position_p1);
		System.out.println(" p2 position -> "+position_p2);
		return true;
		
	}
	
	public int  random()
	{
//		 int random=0;
//		winner.win(1);
//		Scanner sc=new Scanner (System.in);
//		random=sc.nextInt();
			random = (int)((Math.random()*6)+1) ;
			AudioClip dicesound = new AudioClip(this.getClass().getResource("dice.mpeg").toString());
			dicesound.play();
			dicesound.setVolume(0.1);
			dicesound.setCycleCount(1);
	    Timeline t=new Timeline(new KeyFrame(Duration.seconds(0.1), e->{
	    int r = (int)(Math.random()*6);
//	    	System.out.println(random);
	    	dice.setImage(array[r]);
	    	
	    }));  
	    t.setCycleCount(12);
	    t.play();
	    t.setOnFinished(e-> {
	    	dice.setImage(array[random-1]);
	    });
//	    t.setDelay(Duration.seconds(0.1));
	    
	        
		return random;
	}
	
	public Transition check_ladders_p1()
	{
		for(int i=0;i<ladders_up.length;i++)
		{
			if(position_p1 == ladders_down[i])
			{
				position_p1 = ladders_up[i]; 
				TranslateTransition t2 = new TranslateTransition();
				t2.setNode(player1);
				t2.setByY(ladders_y_move[i]*(-70));
				t2.setByX(ladders_x_move[i]*70);
				t2.setDuration(Duration.seconds(01));
				return t2;
			}
		}
		return null;
	}
	
	public TranslateTransition check_ladders_p2()
	{
		for(int i=0;i<ladders_up.length;i++)
		{
			if(position_p2 == ladders_down[i])
			{
				position_p2 = ladders_up[i]; 
				TranslateTransition t2 = new TranslateTransition();
				t2.setNode(player2);
				t2.setByY(ladders_y_move[i]*(-70));
				t2.setByX(ladders_x_move[i]*70);
				t2.setDuration(Duration.seconds(01));
				return t2;
			}
		}
		return null;
	}
	
	public Transition check_snakes_p1()
	{
		System.out.println("checking snakes....");
		
		
		
		if(position_p1 == snakes_head[0])
		{

			Polyline path  = new Polyline();
			
			path.getPoints().addAll(new Double[] {
	                270.4,401.6,
	                322.4,408.8,
	                365.6,425.6,
	                390.4,448.0,
	                378.6,481.6,
	                337.6,513.6,
	                317.6,548.0,
	                309.6,588.8,
	                336.4,601.6
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player1);
			position_p1 = snakes_tail[0];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;
		}
			
		else if(position_p1 == snakes_head[1])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
					 700.0,420.0,
		                690.0,445.0,
		                655.0,500.0,
		                555.0,532.0,
		                500.0,660.0,
		                450.0,680.0,
		                405.0,690.0
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player1);
			position_p1 = snakes_tail[1];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;
		}
		else if(position_p1 == snakes_head[2])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
					   391.2,324.8,
		                464.0,318.4,
		                540.0,330.4,
		                596.8,382.4,
		                604.0,443.2,
		                571.2,500.0,
		                562.4,557.6,
		                589.6,618.4,
		                605.6,673.6
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player1);
			position_p1 = snakes_tail[2];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p1 == snakes_head[3])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
					   576.0,194.4,
		                528.0,200.0,
		                492.8,232.0,
		                480.8,296.0,
		                502.4,376.8,
		                504.8,458.4,
		                444.0,504.8,
		                431.2,552.0,
		                459.2,597.6

			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player1);
			position_p1 = snakes_tail[3];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p1 == snakes_head[4])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
	                322.4,122.4,
	                403.2,132.8,
	                460.0,165.6,
	                428.8,208.0,
	                357.6,222.4,
	                284.0,249.6,
	                268.8,305.6
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player1);
			position_p1 = snakes_tail[4];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p1 == snakes_head[5])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
	                542.4,113.6,
	                616.0,126.4,
	                675.2,152.8,
	                714.4,193.6,
	                726.4,262.4,
	                704.0,314.4,
	                667.2,388.0

			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player1);
			position_p1 = snakes_tail[5];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p1 == snakes_head[6])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
	                252.8,46.4,
	                187.2,62.4,
	                157.6,112.0,
	                155.2,172.0,
	                184.0,243.2,
	                194.4,311.2,
	                168.0,368.0,
	                121.6,414.4,
	                108.8,456.8
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player1);
			position_p1 = snakes_tail[6];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		
		return null;
		
	}
	
	
	
	
	public Transition check_snakes_p2()
	{
		System.out.println("checking snakes....");
		
		
		
		if(position_p2 == snakes_head[0])
		{

			Polyline path  = new Polyline();
			
			path.getPoints().addAll(new Double[] {
	                270.4,401.6,
	                322.4,408.8,
	                365.6,425.6,
	                390.4,448.0,
	                378.6,481.6,
	                337.6,513.6,
	                317.6,548.0,
	                309.6,588.8,
	                336.4,601.6
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player2);
			position_p2 = snakes_tail[0];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;
		}
			
		else if(position_p2 == snakes_head[1])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
					 700.0,420.0,
		                690.0,445.0,
		                655.0,500.0,
		                555.0,532.0,
		                500.0,660.0,
		                450.0,680.0,
		                405.0,690.0
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player2);
			position_p2 = snakes_tail[1];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;
		}
		else if(position_p2 == snakes_head[2])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
					   391.2,324.8,
		                464.0,318.4,
		                540.0,330.4,
		                596.8,382.4,
		                604.0,443.2,
		                571.2,500.0,
		                562.4,557.6,
		                589.6,618.4,
		                605.6,673.6
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player2);
			position_p2 = snakes_tail[2];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p2 == snakes_head[3])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
					   576.0,194.4,
		                528.0,200.0,
		                492.8,232.0,
		                480.8,296.0,
		                502.4,376.8,
		                504.8,458.4,
		                444.0,504.8,
		                431.2,552.0,
		                459.2,597.6

			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player2);
			position_p2 = snakes_tail[3];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p2 == snakes_head[4])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
	                322.4,122.4,
	                403.2,132.8,
	                460.0,165.6,
	                428.8,208.0,
	                357.6,222.4,
	                284.0,249.6,
	                268.8,305.6
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player2);
			position_p2 = snakes_tail[4];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p2 == snakes_head[5])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
	                542.4,113.6,
	                616.0,126.4,
	                675.2,152.8,
	                714.4,193.6,
	                726.4,262.4,
	                704.0,314.4,
	                667.2,388.0

			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player2);
			position_p2 = snakes_tail[5];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		else if(position_p2 == snakes_head[6])
		{
			Polyline path  = new Polyline();
			path.getPoints().addAll(new Double[] {
	                252.8,46.4,
	                187.2,62.4,
	                157.6,112.0,
	                155.2,172.0,
	                184.0,243.2,
	                194.4,311.2,
	                168.0,368.0,
	                121.6,414.4,
	                108.8,456.8
			});

			PathTransition pt = new PathTransition();
			pt.setPath(path);
			pt.setDuration(Duration.seconds(2));
			pt.setNode(player2);
			position_p2 = snakes_tail[6];
			pt.setInterpolator(Interpolator.EASE_BOTH);
			return pt;

		}
		
		return null;
		
	}
}
