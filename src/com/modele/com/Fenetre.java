package com.modele.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Fenetre extends JFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int espace=5;
int mx = -100;
int my= -100;
Random rand = new Random();
int [][]mines=new int[16][9];
int [][] case_voisine =new int[16][9];
boolean [][] revélé= new boolean[16][9];
boolean [][] Flagges = new boolean[16][9];
int voisin=0;
///int doge = rand.nextInt(5);
	//ceci est un commentaire d'essai	
	public Fenetre() {
		this.setTitle("DEMINEUR");
		this.setSize(1200,850);
		 //Instanciation d'un objet JPanel
        JPanel couleur = new JPanel();
 
        //Définition de la couleur de fond
        //couleur.setBackground(Color.DARK_GRAY);  
 
        //On informe  notre JFrame que notre JPanel qui sera son contentPane
        this.setContentPane(couleur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
				if (rand.nextInt(100)< 20) {
					mines[i][j] =1;
					
				}else {
					mines[i][j]=0;
					
				}
				revélé[i][j]=false;
			}
		}
		
		for (int i =0;i<16; i++) {
			for (int j=0 ; j<9; j++) {
				for (int m =0;m<16; m++) {
					for (int n=0 ; n<9; n++) {
						if(!(m==i && n==j))
						if(isN(i,j,m,n)==true)
							voisin++;
						
					
			      }
					
				}
				case_voisine[i][j]=voisin;
			}
		}
		
		
				
		Grille grille =new Grille();
		// on relie la grille à notre fenetre
		this.setContentPane(grille);//
		
		Bouger bouger= new Bouger();
		this.addMouseMotionListener(bouger);
		
		Click click = new Click();
		this.addMouseListener(click);
		
	}

	/**
	 * Tout ce qu'on mettra dans cette méthode va 
	 * apparaitre dans notre fenetre 
	 */
	public class Grille extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 1220, 830);
			
			for (int i =0;i<16; i++) {
				for (int j=0 ; j<9; j++) {
					g.setColor(Color.GRAY);
					/**
					 * s'il y'a une mine, la case doit être égale à 1 et coloré en jaune.
					 */		
					if (mines[i][j]== 1) {
					g.setColor(Color.yellow);}
					if(mx>= espace+i*80 && mx < i*80+80-espace && my>= espace+j*80+106 && my< j*80+186-espace) {
						g.setColor(Color.green);
					}
					g.fillRect(espace +i*80,espace +j*80+80, 80-2*espace,80-2*espace);
				}
			}		
			
		}
	}
		
	
	public class Bouger implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
		
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			mx = e.getX();
			my = e.getY();
			/*System.out.println("Votre souris a bougé");
			System.out.println("X: " + mx+",Y: "+ my);*/
			
		}
	}
		

		public class Click implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				if (DanslaboiteX()!=-1 && DanslaboiteY()!=-1) {
				revélé[DanslaboiteX()][DanslaboiteY()]=true;
				}
				if(DanslaboiteX()!=-1 && DanslaboiteY()!=-1) {
					
					
					// code à revoir 
					System.out.println("la souris est dans le [" +DanslaboiteX()+ ","+DanslaboiteY()+"],Nombrede mines voisine:" 
				+ case_voisine[DanslaboiteX()][DanslaboiteY()]);
				}else {
					System.out.println(" le pointeur n'est pas dans la boite");
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}		
	
		
	}
		
		public int DanslaboiteX() {
			for (int i =0;i<16; i++) {
				for (int j=0 ; j<9; j++) {
					if(mx>= espace+i*80 && mx < i*80+80-espace && my>= espace+j*80+106 && my< j*80+186-espace) {
						return i;
					}
				}
			}
			return -1;
		}
		public int DanslaboiteY() {
			for (int i =0;i<16; i++) {
				for (int j=0 ; j<9; j++) {
					if(mx>= espace+i*80 && mx < i*80+80-espace && my>= espace+j*80+106 && my< j*80+186-espace) {
						return j;
					}
				}
			}
			return -1;
		}
		public boolean isN(int mX,int mY, int cX, int cY) {
			if ((mX-cX<2) &&( mX - cX > -2) && (mY - cY<2) && (mY - cY<-2)&&mines[cX][cY]==1) {
			return true;
			}
			return false;
		}
			
	}
	

