/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: Analise.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.*;


	
public class CertificadoModelo implements RegistGeneric
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

	public long sizeof()
	{
		long sizeof = 0;
		
		try
		{
			sizeof = 4 + 100 + 120 + 12 + 12 + 12 + 12;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return sizeof;

	}

	public void write(RandomAccessFile stream)
	{
		try
                {
                        stream.writeInt(id);
			numeroCertificado.write(stream);
			slug.write(stream);
			dataInicioCurso.write(stream);
			dataFimCurso.write(stream);
			dataEmissao.write(stream);
			dataValidade.write(stream);
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                }

	}

	public void read(RandomAccessFile stream)
        {
                try
                {
                        id = stream.readInt();
                        numeroCertificado.read(stream);
                        slug.read(stream);
                        dataInicioCurso.read(stream);
                        dataFimCurso.read(stream);
                        dataEmissao.read(stream);
                        dataValidade.read(stream);
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                }

        }

	//Ligar o modelo ao ficheiro
	public void salvarDados()
	{
		CertificadoFile file = new CertificadoFile(this);
		file.salvarDados();
	}

}
