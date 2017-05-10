import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaCronometro extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar barraMenu = null;
	private JMenu config = null;
	private JMenuItem tempo = null, sair = null;
	private JButton iniciar = null, pausar = null, zerar = null;
	private JLabel relogio = null;
	private JPanel painelRelogio = null, painelBotoes = null;
	private int hora ;
	private int min ;
	private int seg ;
	private Timer tm;


	TelaCronometro() {
		setTitle("CronoFacto");
		setSize(400, 250);
		// setAlwaysOnTop(true);
		setResizable(false);
		setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponentes();

		setVisible(true);
	}

	private void initComponentes() {
		barraMenu = new JMenuBar();
		config = new JMenu("Configura��o");
		tempo = new JMenuItem("Tempo");
		sair = new JMenuItem("Sair");

		setJMenuBar(barraMenu);
		barraMenu.add(config);
		config.add(tempo);
		tempo.addActionListener(this);
		config.addSeparator();
		config.add(sair);
		sair.addActionListener(this);

		relogio = new JLabel("00:00:00");
		relogio.setFont(new Font("Arial", Font.BOLD, 60));

		iniciar = new JButton("Iniciar");
		iniciar.addActionListener(this);
		pausar = new JButton("Pausar");
		pausar.addActionListener(this);
		zerar = new JButton("Zerar");
		zerar.addActionListener(this);

		painelRelogio = new JPanel();
		painelBotoes = new JPanel();
		add(painelRelogio, BorderLayout.CENTER);
		add(painelBotoes, BorderLayout.SOUTH);

		painelRelogio.add(relogio);
		painelBotoes.add(iniciar);
		painelBotoes.add(pausar);
		painelBotoes.add(zerar);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sair) {
			System.exit(0);
		} else if (e.getSource() == tempo) {
			try{
			hora = Integer.parseInt(JOptionPane.showInputDialog("Digite hora"));

			min = Integer.parseInt(JOptionPane.showInputDialog("Digite minuto"));

			seg = Integer.parseInt(JOptionPane.showInputDialog("Digite segundo"));
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Você inseriu carácteres não permitidos!");
		}
			relogio.setText(String.format("%02d:%02d:%02d", hora, min, seg));
			
		} else if (e.getSource() == iniciar) {
				tm = new Timer();
				tm.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
							if(seg>0&&min==0&&hora==0){
								seg--;
								if(seg==0){
								}
							}else
							if(seg>=0&&min>0&&hora>=0){
								seg--;

								if(seg == -1){
									seg = 59;
									if(min>0){
										min--;
									} 
								}
							}else
								if(seg>=0&&min>=0&&hora>0){
									seg--;
									if(seg == -1){
										seg = 59;
										if(min>0){
											min--;
										}
										if(min==0&&seg==59){
											min = 59;
											hora--;
										}
									}
								}else
									if(hora==0&&min==0&&min==0){
									}
							relogio.setText(String.format("%02d:%02d:%02d",  hora, min, seg));
					}
				}, 1000, 1000);
		}
		else if (e.getSource() == pausar) {
			tm.cancel();
		}
		else if (e.getSource() == zerar) {
			tm.cancel();
			seg = 0; min = 0; hora = 0;
			relogio.setText(String.format("%02d:%02d:%02d",  hora, min, seg));
		}
	}
}
