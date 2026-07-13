/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Settings.java;
Data: 05.06.2026.
*****************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Settings extends JPanel
{
    private Apresentacao app;
    private JButton btnAlterarPassword;
    private JButton btnVoltar;

    public Settings(Apresentacao app)
    {
        this.app = app;
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(240, 242, 245));

        JPanel pnlCardConfig = new JPanel(new GridBagLayout());
        pnlCardConfig.setBackground(Color.WHITE);
        pnlCardConfig.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel lblTitulo = new JLabel("Configurações de Segurança");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(45, 52, 54));

        JLabel lblDescricao = new JLabel("Mantenha a integridade do sistema gerindo as suas credenciais.");
        lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDescricao.setForeground(Color.GRAY);

        // --- Instanciação dos Botões com Ícones ---

        // Botão Atualizar Palavra-Passe
        btnAlterarPassword = new JButton("Atualizar Palavra-Passe");
        btnAlterarPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAlterarPassword.setBackground(new Color(30, 144, 255)); // Azul do sistema
        btnAlterarPassword.setForeground(Color.WHITE);
        btnAlterarPassword.setFocusPainted(false);
        btnAlterarPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAlterarPassword.setPreferredSize(new Dimension(240, 40));

        try {
            ImageIcon iconKey = new ImageIcon("image/edit24.png"); 
            btnAlterarPassword.setIcon(iconKey);
            btnAlterarPassword.setIconTextGap(10); 
        } catch(Exception e) {
            System.out.println("Aviso: Não foi possível carregar o ícone do botão: " + e.getMessage());
        }

        // --- Eventos de Ação ---
        btnAlterarPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.cardLayout.show(app.container, "PasswordForm");
            }
        });

        // Efeito Hover no botão principal
        btnAlterarPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAlterarPassword.setBackground(new Color(25, 118, 210));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnAlterarPassword.setBackground(new Color(30, 144, 255));
            }
        });

        // --- Montagem do GridBagLayout interno do Card ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        pnlCardConfig.add(lblTitulo, gbc);

        gbc.gridy = 1;
        pnlCardConfig.add(lblDescricao, gbc);

        // Sub-painel para alinhar os botões lado a lado de forma harmónica
        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnlBotoes.setOpaque(false);
        pnlBotoes.add(btnAlterarPassword);

        gbc.gridy = 2;
        gbc.insets = new Insets(25, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        pnlCardConfig.add(pnlBotoes, gbc);

        // Centralizador geral para o Card não ficar esticado na janela
        JPanel pnlCentralizador = new JPanel(new GridBagLayout());
        pnlCentralizador.setBackground(new Color(240, 242, 245));
        pnlCentralizador.add(pnlCardConfig);

        this.add(pnlCentralizador, BorderLayout.CENTER);
    }
}
