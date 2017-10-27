package project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box; 
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class DialogDemo extends JFrame implements ActionListener
{
    
    
	//Pole wpisania liczby-------------------------------------------------
	
	JLabel etykietaLiczbyWe    = new JLabel("Podaj pierwszą liczbę:");
	JTextField poleLiczbyWe  = new JTextField("",15);
	JLabel etykietaLiczbyWe2    = new JLabel("Podaj drugą liczbę:");
	JTextField poleLiczbyWe2  = new JTextField("",15);
	
	//Pole tekstowe wielolinijkowe-----------------------------------------
	
	JLabel etykietaLiczbyWy   = new JLabel("Wynik:");
	JTextArea poleLiczbyWy = new JTextArea("Brak danych ?!",5,30);     
	
	//Cztery buttony-------------------------------------------------------
	
	JButton przyciskOdczytaj = new JButton("Oblicz"); 
	JButton przyciskCzysc = new JButton("Czysc okno");
	JButton przyciskZamknij = new JButton("Zamknij");
	JButton przyciskZatwierdzam = new JButton("Zatwierdzam");
	
	//Przyciski do działaniania + przycisk pusty (komunikat)---------------
	
	JLabel etykietaDzialanie   = new JLabel("Działanie:");
	JRadioButton przyciskPusty = new JRadioButton("",true);
	JRadioButton przyciskDodaj = new JRadioButton("+",false);
	JRadioButton przyciskOdejmij = new JRadioButton("-",false);
	JRadioButton przyciskPomnoz = new JRadioButton("*",false);
	JRadioButton przyciskPodziel = new JRadioButton("/",false);
	
	//Dwa przyciski do edycji ---------------------------------------------
	
	JLabel etykietaEdycja   = new JLabel("Edycja:   ");
	JCheckBox czyscWynik = new JCheckBox("Czyść wynik"); 
	JCheckBox czyscLiczby = new JCheckBox("Czyść liczby (pierwsza i druga)");	
	
	long czasStart;
	
	public DialogDemo()
	{
		//Odczytanie czasu poczatkowego------------------------------------ 
		
		czasStart = System.currentTimeMillis();
		
		//Ustawienie managera ukladu calego okna---------------------------
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,30));   	  
	    
		//Okienka: Plik i Edycja-------------------------------------------
		
		JMenuBar pasekMenu = new JMenuBar();
		JMenu menuPierwsze = new JMenu("Plik");
		pasekMenu.add(menuPierwsze);
		
		JMenuItem wyjdz = new JMenuItem("Zamknij");
		menuPierwsze.add(wyjdz);
		wyjdz.addActionListener(this);
		
		JMenu menuDrugie = new JMenu("Edycja");
		pasekMenu.add(menuDrugie);
		
		JMenuItem czysc = new JMenuItem("Czysc okno");
		menuDrugie.add(czysc);
		czysc.addActionListener(this);
		
		setJMenuBar(pasekMenu);
		
		//Wstawienie panelu  polem tekstowym--------------------------------
        
		JPanel panelTextWe = new JPanel();
		
		panelTextWe.setLayout(new BorderLayout());
		panelTextWe.add(etykietaLiczbyWe,BorderLayout.NORTH);
		panelTextWe.add(poleLiczbyWe,BorderLayout.CENTER);
	
		JPanel panelTextWe2 = new JPanel();
		panelTextWe2.setLayout(new BorderLayout());
		panelTextWe2.add(etykietaLiczbyWe2,BorderLayout.NORTH);
		panelTextWe2.add(poleLiczbyWe2,BorderLayout.CENTER);
	
		getContentPane().add(panelTextWe);
		getContentPane().add(panelTextWe2);
				
        //Wstawienie panelu z przyciskami działania-------------------------
		
		Box boxRGrupa = Box.createVerticalBox(); 
		boxRGrupa.add(etykietaDzialanie,BorderLayout.CENTER);
		
		boxRGrupa.add(Box.createVerticalStrut(10)); 
		
		ButtonGroup bGrupa = new ButtonGroup();	
		bGrupa.add(przyciskPusty);
		
		bGrupa.add(przyciskDodaj);
		boxRGrupa.add(przyciskDodaj,BorderLayout.CENTER);		
		bGrupa.add(przyciskOdejmij);
		boxRGrupa.add(przyciskOdejmij,BorderLayout.CENTER);	
		bGrupa.add(przyciskPomnoz);
		boxRGrupa.add(przyciskPomnoz,BorderLayout.CENTER);
		bGrupa.add(przyciskPodziel);
		boxRGrupa.add(przyciskPodziel,BorderLayout.CENTER);
		
		getContentPane().add(boxRGrupa);

		//Wstawienie panelu z przyciskami Edycji-----------------------------
		
		Box boxCGrupa = Box.createHorizontalBox(); 
		
		boxCGrupa.add(etykietaEdycja);
		czyscWynik.setSelected(true);		
		boxCGrupa.add(czyscWynik);
		czyscLiczby.setSelected(false);
		boxCGrupa.add(czyscLiczby);

					
		getContentPane().add(boxCGrupa);
	    getContentPane().add(przyciskZatwierdzam,BorderLayout.LINE_END);
		
		//Wstawienie panelu z wynikiem działania-----------------------------
		
		JPanel panelTextWy = new JPanel();
		panelTextWy.setLayout(new BorderLayout());
		panelTextWy.add(etykietaLiczbyWy,BorderLayout.NORTH);		
		panelTextWy.add(new JScrollPane(poleLiczbyWy),BorderLayout.CENTER);
		poleLiczbyWy.setEditable(false);		
		
		getContentPane().add(panelTextWy);
    
		//Wstawienie przycisku-----------------------------------------------
				
	    getContentPane().add(przyciskOdczytaj) ;
	   
	    //Ustawienie sluchacza dla wszystkich przyciskow---------------------
	    
	    przyciskOdczytaj.addActionListener(this);
	    przyciskCzysc.addActionListener(this);	         
	    przyciskZamknij.addActionListener(this);
	    przyciskZatwierdzam.addActionListener(this);
	       
	//Ustawienia okienka dialogowego-------------------------------------
	    
	}
	
	public static void showMessage(String message)
	{
	   JOptionPane.showMessageDialog(null,message,"Kalkulator",						                 
					                 JOptionPane.INFORMATION_MESSAGE);	
	}
	
	public static boolean showConfirmDialog(String message)
	{
	   int dec = JOptionPane.showConfirmDialog(null,message,"Pytanie",
			                                   JOptionPane.YES_NO_OPTION,
			                                   JOptionPane.QUESTION_MESSAGE);
	   
	   if (dec == JOptionPane.OK_OPTION) return true;
	   else return false;		   
	}
	
	//Obsluga zdarzen klikniecia na przycisk------------------------------
	
	public void actionPerformed(ActionEvent zdarzenie)  
    {	
	    if (zdarzenie.getActionCommand().equals("Oblicz"))
	    {
	    	poleLiczbyWy.setText(""); //Wyczyszczenie pola wyjsciowego
	    	
	        double number1 = 0;
			try {
					String tekst = poleLiczbyWe.getText();
					number1 = Double.parseDouble(tekst);
				} catch (NumberFormatException e) {
				DialogDemo.showMessage("Złe dane wejściowe pierwsze !");
			}
			
	        double number2 = 0;
			try {
					String tekst2 = poleLiczbyWe2.getText();	
					number2 = Double.parseDouble(tekst2);
				} catch (NumberFormatException e2) {
				DialogDemo.showMessage("Złe dane wejściowe drugie !");
			}
			
	      //Wybór działania------------------------------------------------
			
			if (przyciskPusty.isSelected())
	        {
				DialogDemo.showMessage("Wybierz działanie !");
	        }
				else
					if (przyciskDodaj.isSelected())
					{
						poleLiczbyWy.append((number1+number2)+"\n");
					}
						else
							if (przyciskOdejmij.isSelected())
							{
								poleLiczbyWy.append((number1-number2)+"\n");		        	
							}	
								else
									if (przyciskPomnoz.isSelected())
									{
										poleLiczbyWy.append((number1*number2)+"\n");
									}
										else
											if (przyciskPodziel.isSelected())
											{
												if(number2 == 0)
												{
													DialogDemo.showMessage("Nie dzielimy przez 0 !");
												}
													else
														poleLiczbyWy.append((number1/number2)+"\n");
											}
		}
	    
			else
				if (zdarzenie.getActionCommand().equals("Zatwierdzam"))
				{
			
						if (czyscWynik.isSelected()) 
							{
							poleLiczbyWy.setText("Brak danych");
							}
						
						if (czyscLiczby.isSelected()) 
			        		{
			        		poleLiczbyWe.setText("");
			        		poleLiczbyWe2.setText("");
			        		}

							
				}
					else
					{
						if (zdarzenie.getActionCommand().equals("Czysc okno"))	    
						{
							poleLiczbyWy.setText("Brak danych");    
						}
							else
							{
								//Odczytanie czasu zakonczenia
								long czasStop = System.currentTimeMillis();
		    	
								//Obliczenie czasu uzywania programu
								long liczbaSekund = (czasStop-czasStart)/1000;
								this.setVisible(false);
								DialogDemo.showMessage("Czas uzywania programu w sekundach: "+liczbaSekund);
								System.exit(0); //Zakonczenie pracy programu
							}
					}
	    
	    	repaint();
	}
	
	
	public static void main ( String[] args )
	{				
		DialogDemo dialogDemo  = new DialogDemo();
		dialogDemo.setTitle("Kalkulator v.1.0");
		dialogDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dialogDemo.setLocation(500,100);
		dialogDemo.setSize(430,600);		
		dialogDemo.setVisible(true);  
		dialogDemo.setResizable(true);
        dialogDemo.setLayout(new BorderLayout());
	}	
}