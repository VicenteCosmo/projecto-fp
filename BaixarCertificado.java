/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: BaixarCertificado.java;
Data: 13.07.2026.
*****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BaixarCertificado extends JPanel 
{
    private Apresentacao app;
    private JPanel pnlCertificado;
    private JButton btnBaixar;
    private JButton btnVoltar;

    // Dados dinâmicos do Certificado
    private String idCertificado;
    private String nomeEstudante;
    private String nomeCurso; 
    private String numCertificado;
    private String dataInicio;
    private String dataFim;
    private String dataEmissao;
    private String dataValidade;

    public BaixarCertificado(Apresentacao app, String id, String nome, String curso, String numeroCert, String dInicio, String dFim, String dEmissao, String dValidade) 
    {
        this.app = app;
        this.idCertificado = id;
        this.nomeEstudante = nome;
        this.nomeCurso = curso;
        this.numCertificado = numeroCert;
        this.dataInicio = dInicio;
        this.dataFim = dFim;
        this.dataEmissao = dEmissao;
        this.dataValidade = dValidade;

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(240, 242, 245));

        // CORPO DO CERTIFICADO
        pnlCertificado = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Moldura interior dourada fina da imagem
                g2d.setColor(new Color(212, 175, 55)); // dourado
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect(20, 20, getWidth() - 40, getHeight() - 40);
            }
        };
        pnlCertificado.setBackground(Color.WHITE);
        pnlCertificado.setLayout(new GridBagLayout());
        pnlCertificado.setPreferredSize(new Dimension(850, 460));

        // Fontes
        Font fontTitulo = new Font("Segoe UI", Font.BOLD, 26);
        Font fontSub = new Font("Segoe UI", Font.ITALIC, 18);
        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontValor = new Font("Segoe UI", Font.BOLD, 14);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 20, 12, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Texto de Aprovação
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lblAprovacao = new JLabel("\"Approved by Liberia Maritime Authority\"", SwingConstants.CENTER);
        lblAprovacao.setFont(fontSub);
        lblAprovacao.setForeground(new Color(45, 52, 54));
        pnlCertificado.add(lblAprovacao, gbc);

        //Título da Verificação, Badge Verde
        gbc.gridy = 1;
        JPanel pnlStatus = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        pnlStatus.setOpaque(false);
        JLabel lblVerif = new JLabel("Certification Verification:");
        lblVerif.setFont(fontTitulo);
        JLabel lblBadge = new JLabel(" Verified ");
        lblBadge.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblBadge.setForeground(Color.WHITE);
        lblBadge.setBackground(new Color(0, 150, 43));
        lblBadge.setOpaque(true);
        lblBadge.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        pnlStatus.add(lblVerif);
        pnlStatus.add(lblBadge);
        pnlCertificado.add(pnlStatus, gbc);

        //Painel de Dados em Duas Colunas (GridLayout Simétrico)
        // 4 Linhas, 2 Colunas para organizar as 8 informações
        JPanel pnlDados = new JPanel(new GridLayout(4, 2, 40, 15));
        pnlDados.setOpaque(false);

        // Linha 1
        pnlDados.add(criarBlocoDado("Certificate Holder's Name:", nomeEstudante, fontLabel, fontValor));
        pnlDados.add(criarBlocoDado("Certificate No:", numCertificado, fontLabel, fontValor));

        // Linha 2
        pnlDados.add(criarBlocoDado("Course / Categoria:", nomeCurso, fontLabel, fontValor)); 
        pnlDados.add(criarBlocoDado("Course Start Date:", dataInicio, fontLabel, fontValor));

        // Linha 3
        pnlDados.add(criarBlocoDado("Course End Date:", dataFim, fontLabel, fontValor));
        pnlDados.add(criarBlocoDado("ID No:", idCertificado, fontLabel, fontValor));

        // Linha 4
        pnlDados.add(criarBlocoDado("Certificate Issuing Date:", dataEmissao, fontLabel, fontValor));
        pnlDados.add(criarBlocoDado("Certificate Expiry Date:", dataValidade, fontLabel, fontValor));

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlCertificado.add(pnlDados, gbc);

        // PAINEL DE CONTROLO DE ACÇÕES (Botões no fundo)
        JPanel pnlAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pnlAcoes.setBackground(new Color(240, 242, 245));

        btnBaixar = new JButton("Baixar Certificado (Imagem)");
        btnBaixar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBaixar.setBackground(new Color(30, 144, 255));
        btnBaixar.setForeground(Color.WHITE);
        btnBaixar.setFocusPainted(false);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        pnlAcoes.add(btnBaixar);
        pnlAcoes.add(btnVoltar);

        // Ouvintes das ações
        btnBaixar.addActionListener(e -> exportarParaImagem());
        btnVoltar.addActionListener(e -> app.cardLayout.show(app.container, "DASHBOARD"));

        // Centralizador do layout
        JPanel pnlCentralizador = new JPanel(new GridBagLayout());
        pnlCentralizador.setBackground(new Color(240, 242, 245));
        pnlCentralizador.add(pnlCertificado);

        this.add(pnlCentralizador, BorderLayout.CENTER);
        this.add(pnlAcoes, BorderLayout.SOUTH);
    }

    // Cria as labels duplas empilhadas por campo
    private JPanel criarBlocoDado(String label, String valor, Font fLabel, Font fValor) {
        JPanel painel = new JPanel(new BorderLayout(5, 2));
        painel.setOpaque(false);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(fLabel);
        lbl.setForeground(Color.GRAY);
        
        JLabel val = new JLabel(valor);
        val.setFont(fValor);
        val.setForeground(Color.BLACK);
        
        painel.add(lbl, BorderLayout.NORTH);
        painel.add(val, BorderLayout.CENTER);
        return painel;
    }

    // Guarda a renderização gráfica do Swing em formato PNG
    private void exportarParaImagem() {
        try {
            BufferedImage imagem = new BufferedImage(pnlCertificado.getWidth(), pnlCertificado.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imagem.createGraphics();
            pnlCertificado.paint(g);
            g.dispose();

            String nomeArquivo = "Certificado_" + numCertificado + ".png";
            ImageIO.write(imagem, "png", new File(nomeArquivo));

            JOptionPane.showMessageDialog(this, "Certificado guardado com sucesso como:\n" + nomeArquivo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar o documento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
