/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: PasswordForm.java;
Data: 05.06.2026.
*****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordForm extends JPanel implements ActionListener
{
    private JPanel container;
    private JButton btnSalvar;
    private JButton btnCancelar;

    private JPasswordField txtPasswordNova;
    private JPasswordField txtPasswordConfirmar;

    private AutenticacaoFile file;
    private Apresentacao app;

    public PasswordForm(Apresentacao app)
    {
        this.app = app;
        this.setLayout(new GridBagLayout());

        container = new JPanel(new GridBagLayout());
        file = new AutenticacaoFile(new AutenticacaoModelo());

        Font font = new Font("Segoe UI", Font.PLAIN, 20);

        JLabel logoLb = new JLabel();

	ImageIcon logoIcon = new ImageIcon("image/certificate-icon.png");

	Image img = logoIcon.getImage();
        Image novaImg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        logoLb.setIcon(new ImageIcon(novaImg));

        // L 0: Nova Palavra-Passe
        JLabel lblNova = new JLabel("Nova Senha:", SwingConstants.RIGHT);
        lblNova.setFont(font);
        lblNova.setForeground(Color.decode("#2d3436"));
        txtPasswordNova = new JPasswordField(15);
        txtPasswordNova.setFont(font);

        // L 1: Confirmar Nova Palavra-Passe
        JLabel lblConfirmar = new JLabel("Confirmar:", SwingConstants.RIGHT);
        lblConfirmar.setFont(font);
        lblConfirmar.setForeground(Color.decode("#2d3436"));
        txtPasswordConfirmar = new JPasswordField(15);
        txtPasswordConfirmar.setFont(font);

        // Botão Salvar (Atalho ALT + A)
        btnSalvar = new JButton("Atualizar");
        btnSalvar.setMnemonic(KeyEvent.VK_A); // Tecla de atalho: ALT + A
        btnSalvar.setToolTipText("Pressione ALT + A para atualizar");
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBorderPainted(false);
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnSalvar.setBackground(Color.decode("#95afc0"));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(this);

        // Botão Cancelar (Atalho ALT + C)
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setMnemonic(KeyEvent.VK_C); // Tecla de atalho: ALT + C
        btnCancelar.setToolTipText("Pressione ALT + C para cancelar");
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnCancelar.setBackground(Color.decode("#eb4d4b"));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(this);

        try {
            btnSalvar.setIcon(new ImageIcon("image/edit24.png"));
            btnSalvar.setIconTextGap(8);
            btnCancelar.setIcon(new ImageIcon("image/cancel24.png"));
            btnCancelar.setIconTextGap(8);
        } catch(Exception e) {
            System.out.println("Aviso: Ícones do formulário não encontrados no caminho especificado.");
        }

        // Efeitos Visuais de Hover nos botões
        btnSalvar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btnSalvar.setBackground(Color.decode("#535c68")); }
            @Override
            public void mouseExited(MouseEvent e) { btnSalvar.setBackground(Color.decode("#95afc0")); }
        });
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btnCancelar.setBackground(Color.decode("#ff7675")); }
            @Override
            public void mouseExited(MouseEvent e) { btnCancelar.setBackground(Color.decode("#eb4d4b")); }
        });

        // Configuração do posicionamento GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);

        // Adicionando o Logótipo no topo (L 0)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        container.add(logoLb, gbc);

        // Adicionando L 1: Nova Senha
        gbc.gridx = 0; gbc.gridy = 1; 
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        container.add(lblNova, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        container.add(txtPasswordNova, gbc);

        // Adicionando L 2: Confirmar Senha
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.LINE_END;
        container.add(lblConfirmar, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        container.add(txtPasswordConfirmar, gbc);

        // Painel horizontal para os botões com ícones ficarem lado a lado
        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnlBotoes.setOpaque(false);
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnCancelar);

        // Adicionando o grupo de botões ao painel interno (L 3)
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(25, 8, 8, 8);
        container.add(pnlBotoes, gbc);

        container.setOpaque(false);

        // Configuração estrutural do painel exterior (Centralização)
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.insets = new Insets(50, 50, 50, 50);

        this.setBackground(Color.decode("#d0e3fb"));
        this.add(container, gbcMain);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnCancelar) {
            limparCampos();
            app.cardLayout.show(app.container, "DASHBOARD");
            return;
        }

        // Converte os arrays de caracteres em Strings limpas
        String senhaNova = new String(txtPasswordNova.getPassword()).trim();
        String senhaConfirmar = new String(txtPasswordConfirmar.getPassword()).trim();

        try {
            // 1. VALIDAÇÃO: Verifica se os campos estão vazios
            if (senhaNova.isEmpty() || senhaConfirmar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, introduza a nova palavra-passe em ambos os campos!");
                return;
            }

            // 2. VALIDAÇÃO: Confirma se as duas senhas digitadas são idênticas
            if (!senhaNova.equals(senhaConfirmar)) {
                JOptionPane.showMessageDialog(null, "A nova senha e a confirmação não coincidem!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. GRAVAÇÃO: Atualiza o arquivo binário diretamente sem pedir a senha antiga
            AutenticacaoModelo modelo = new AutenticacaoModelo();
            modelo.setPassword(senhaNova);
	    modelo.setNome("Vicente Cosmo");
            
            // Tenta resgatar dados de utilizador pré-existentes se necessário
            try 
	    {
                modelo.salvar();

		app.cardLayout.show(app.container, "LOGIN");
            } 
	    catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha ao ressetar a senha. \nPor favor, tente novamete!");
            }

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "ERRO: Falha ao aceder ao ficheiro binário de credenciais.\n" + exc.getMessage(), "Erro Técnico", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtPasswordNova.setText("");
        txtPasswordConfirmar.setText("");
    }
}
