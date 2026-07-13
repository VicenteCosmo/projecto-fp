/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Login.java;
Data: 05.06.2026.
*****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JPanel implements ActionListener
{
    public boolean isLogged;

    private JPanel container;
    private JTextField nomeF;
    private JPasswordField passwordF;
    
    private JButton btnEntrar;
    private JButton btnEsqueciSenha;

    private Apresentacao app;

    public Login(Apresentacao app)
    {
        this.app = app;
        this.setLayout(new GridBagLayout());

        container = new JPanel(new GridBagLayout());

        Font font = new Font("Segoe UI", Font.PLAIN, 20);

	//Atalhos
	JPanel pnlAtalho = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
	pnlAtalho.setOpaque(false);

        JButton btnJanelaAtalhos = new JButton("Atalhos");
        btnJanelaAtalhos.setMnemonic(KeyEvent.VK_H);
        btnJanelaAtalhos.addActionListener(e -> app.mostrarJanelaAtalhos(this));

        JButton btnJanelaSair = new JButton("Sair");
        btnJanelaSair.setMnemonic(KeyEvent.VK_S);
        btnJanelaSair.addActionListener(e -> app.fecharSistemaConfirmando(this));

        try {
            btnJanelaAtalhos.setIcon(new ImageIcon("image/edit24.png"));
            btnJanelaSair.setIcon(new ImageIcon("image/cancel24.png"));
        } catch(Exception e) { }

        pnlAtalho.add(btnJanelaAtalhos);
        pnlAtalho.add(btnJanelaSair);

       //Logo 
        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        try 
	{
            ImageIcon logoIcon = new ImageIcon("image/certificate-icon.png");
            
            Image img = logoIcon.getImage();
            Image novaImg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(novaImg));
        } 
	catch(Exception e) 
	{
            System.out.println("Aviso: Não foi possível carregar o logótipo no topo do formulário.");
        }

        // Campo Nome
        JLabel nome = new JLabel("Nome", SwingConstants.RIGHT);
        nome.setFont(font);
        nome.setForeground(Color.decode("#2d3436"));
        nomeF = new JTextField(15);
        nomeF.setFont(font);

        // Campo Password
        JLabel password = new JLabel("Password", SwingConstants.RIGHT);
        password.setFont(font);
        password.setForeground(Color.decode("#2d3436"));
        passwordF = new JPasswordField(15);
        passwordF.setFont(font);
        
        // Botão Entrar (Atalho ALT + E)
        btnEntrar = new JButton("Entrar");
        btnEntrar.setMnemonic(KeyEvent.VK_E); 
        btnEntrar.setToolTipText("Pressione ALT + E para autenticar");
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorderPainted(false);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnEntrar.setBackground(Color.decode("#d0e3fb"));
        btnEntrar.addActionListener(this);

        // Botão Esqueci a Senha (Atalho ALT + R)
        btnEsqueciSenha = new JButton("Recuperar Senha");
        btnEsqueciSenha.setMnemonic(KeyEvent.VK_R);
        btnEsqueciSenha.setToolTipText("Pressione ALT + R caso tenha esquecido a senha");
        btnEsqueciSenha.setFocusPainted(false);
        btnEsqueciSenha.setBorderPainted(false);
        btnEsqueciSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnEsqueciSenha.setBackground(Color.decode("#d0e3fb"));
        btnEsqueciSenha.setForeground(Color.decode("#2d3436"));
        btnEsqueciSenha.addActionListener(this);

        try {
            btnEntrar.setIcon(new ImageIcon("image/edit24.png")); 
            btnEntrar.setIconTextGap(8);
            btnEsqueciSenha.setIcon(new ImageIcon("image/cancel24.png")); 
            btnEsqueciSenha.setIconTextGap(6);
        } catch(Exception e) {
            System.out.println("Aviso: Não foi possível carregar os ícones do login.");
        }

        btnEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEntrar.setBackground(Color.WHITE);
                btnEntrar.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 20));
                btnEntrar.setBackground(Color.decode("#d0e3fb"));
            }
        });

        btnEsqueciSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEsqueciSenha.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnEsqueciSenha.setForeground(Color.decode("#2d3436"));
            }
        });

        // Configuração posicional das células (GridBagConstraints)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 8, 20, 8); 
        container.add(lblLogo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        container.add(nome, gbc);

        // Input Nome
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(nomeF, gbc);

        // Label Password (LINHA 2)
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        container.add(password, gbc);

        // Input Password
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        container.add(passwordF, gbc);

        // Painel para alinhar os dois botões horizontalmente na linha inferior
        JPanel pnlBotoesLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnlBotoesLogin.setOpaque(false);
        pnlBotoesLogin.add(btnEntrar);
        pnlBotoesLogin.add(btnEsqueciSenha);

        // Adiciona o grupo de botões ao container central (LINHA 3)
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(15, 8, 8, 8);
        container.add(pnlBotoesLogin, gbc);

        container.setOpaque(false);
        container.setBackground(Color.decode("#d0e3fb"));

        // Centralizador Principal
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.insets = new Insets(100, 100, 100, 100);

        this.setBackground(Color.decode("#d0e3fb"));
        this.add(container, gbcMain);
	this.add(pnlAtalho);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnEsqueciSenha) {
            app.cardLayout.show(app.container, "PasswordForm");
            return;
        }

        if (e.getSource() == btnEntrar) {
            String txtNome = nomeF.getText().trim();
            String txtPass = new String(passwordF.getPassword()).trim();

            if (txtNome.isEmpty() || txtPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos do ecrã!", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                return;
            }

            AutenticacaoFile authFile = new AutenticacaoFile(new AutenticacaoModelo());
            boolean sucesso = authFile.autenticar(txtNome, txtPass);

            if (sucesso) {
                this.isLogged = true;
                nomeF.setText("");
                passwordF.setText("");
                app.cardLayout.show(app.container, "DASHBOARD");
            }
		
        }
    }
}
