/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Analise.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.RandomAccessFile;

	
public class CertificadoModelo
{
	//atributos
	public int id;
	public StringBufferModelo numeroCertificado, slug;
	public DataModelo dataInicioCurso, dataFimCurso, dataEmissao, dataValidade;
	
	//constructores
	public CertificadoModelo()
	{
		id = 0;
		numeroCertificado = new StringBufferModelo(50);
		slug = new StringBufferModelo(60);
		dataInicioCurso = new DataModelo();
		dataFimCurso = new DataModelo();
		dataEmissao = new DataModelo();
		dataValidade = new DataModelo();
	}
	public CertificadoModelo(
		int id,
                String numeroCertificado,
                String slug,
                String dataInicioCurso,
                String dataFimCurso,
                String dataEmissao,
                String dataValidade

	)
	{
		this.id = id;
                this.numeroCertificado = new StringBufferModelo(numeroCertificado, 50);
                this.slug = new StringBufferModelo(slug, 60);
                this.dataInicioCurso = new DataModelo(dataInicioCurso);
                this.dataFimCurso = new DataModelo(dataFimCurso);
                this.dataEmissao = new DataModelo(dataEmissao);
                this.dataValidade = new DataModelo(dataValidade);

	}
	
	//metodos get
	public int getId()
	{
		return id;
	}
	public String getNumeroCertificado()
	{
		return numeroCertificado.toStringEliminatingSpaces();
	}
	public String getSlug()
	{
		return slug.toStringEliminatingSpaces();
	}
	public String getDataInicioCurso()
	{
		return dataInicioCurso.toString().trim();
	}
	public String getDataFimCurso()
	{
		return dataFimCurso.toString().trim();
	}
	public String getDataEmissao()
	{
		return dataEmissao.toString().trim();
	}
	public String getDataValidade()
	{
		return dataValidade.toString().trim();
	}
	//metodos set
	public void setId(int newId)
	{
		this.id = newId;
	}
	public void setNumeroCertificado(String newNumeroCertificado)
	{
		this.numeroCertificado = new StringBufferModelo(newNumeroCertificado, 50);
	}
	public void setSlug(String newSlug)
	{
		this.slug = new StringBufferModelo(newSlug, 60);
	}
	public void setDataInicioCurso(String newDataInicioCurso)
	{
		this.dataInicioCurso = new DataModelo(newDataInicioCurso);
	}
	public void setDataFimCurso(String newDataFimCurso)
	{
		this.dataFimCurso = new DataModelo(newDataFimCurso);
	}
	public void setDataEmissao(String newDataEmissao)
	{
		this.dataEmissao = new DataModelo(newDataEmissao);
	}
	public void setDataValidade(String newDataValidade)
	{
		this.dataValidade = new DataModelo(newDataValidade);
	}

	//metodo toString
	public String toString()
	{
		String str = "Dados do Modelo Certificado\n\n";

		str += "ID: " + this.getId() + "\n";
		str += "Número do Certificado: " + this.getNumeroCertificado() + "\n";
		str += "Data de Início do Curso: " + getDataInicioCurso() + "\n";
		str += "Data de Fim do Curso: " + getDataFimCurso() + "\n";
		str += "Data de Emissão do Certificado: " + getDataEmissao() + "\n";
		str += "Data de Validade do Certificado: " + getDataValidade() + "\n";

		return str;
	}


/*	public static void main(String args[])
	{
		
		try
		{
			// 1. Criamos um objeto vazio (com o construtor padrão) para receber a leitura
			CertificadoModelo modelo = new CertificadoModelo();

			// 2. Abre o ficheiro binário em modo de leitura e escrita
			RandomAccessFile file = new RandomAccessFile("certificados.dat", "rw");
			
			// 3. Posiciona o ponteiro no início do ficheiro (caso queira ler desde o início)
				file.seek(0);

			// Se no método write gravou o ID antes do slug, descomente a linha abaixo para saltar os 4 bytes do ID:
			// file.readInt(); 

			// 4. CHAMADA OBRIGATÓRIA ao método read do StringBufferModelo através do atributo slug
			modelo.slug.read(file);

			// 5. Fecha o ficheiro de forma segura
			file.close();

			// 6. Apresenta o resultado limpo (recorrendo ao método getSlug() que já faz o trim())
			String mensagem = "Slug lido do ficheiro: " + modelo.getSlug();
			JOptionPane.showMessageDialog(null, mensagem);
		}
		catch (Exception e)
		{
			// Captura falhas de leitura ou fim inesperado de ficheiro (EOFException)
			JOptionPane.showMessageDialog(null, "Erro ao ler o ficheiro: " + e.getMessage());
		}

	}*/
}
