/****************************************************************
Projecto de Fundamentos de Programação II;
Tema: Sistema de Gestão Certificados;
Nome: Viente Cosmo, N. 36479;
File Name: AutenticacaoModelo.java;
Data: 05.06.2026.
****************************************************************/

import SwingComponents.*;
import javax.swing.*;
import java.io.*;


	
public class AutenticacaoModelo implements RegistGeneric
{
	//atributos
	public int id;
	public StringBufferModelo nome, password;

	//constructores
	public AutenticacaoModelo()
	{
		id = 0;
		nome = new StringBufferModelo(50);
		password = new StringBufferModelo(50);
	}
	public AutenticacaoModelo(
		int id,
                String nome,
                String password
	)
	{
		this.id = id;
                this.nome = new StringBufferModelo(nome, 50);
                this.password = new StringBufferModelo(password, 50);
	}
	
	//metodos get
	public int getId()
	{
		return id;
	}
	public String getNome()
	{
		return nome.toStringEliminatingSpaces();
	}
	public String getPassword()
	{
		return password.toStringEliminatingSpaces();
	}
	//metodos set
	public void setId(int newId)
	{
		this.id = newId;
	}
	public void setNome(String newNome)
	{
		this.nome = new StringBufferModelo(newNome, 50);
	}
	public void setPassword(String newPassword)
	{
		this.password = new StringBufferModelo(newPassword, 50);
	}

	//metodo toString
	public String toString()
	{
		String str = "Dados do Modelo Estudante\n\n";

		str += "ID: " + this.getId() + "\n";
		str += "Nome: " + this.getNome() + "\n";

		return str;
	}

	public long sizeof()
	{
		long sizeof = 0;
		
		try
		{
			sizeof = 4 + 100 + 100;
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
			nome.write(stream);
			password.write(stream);
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
                        nome.read(stream);
                        password.read(stream);
                }
                catch(IOException ex)
                {
                        ex.printStackTrace();
                }

        }

	//Ligar o modelo ao ficheiro
	public void salvarDados()
	{
		AutenticacaoFile file = new AutenticacaoFile(this);
		file.salvarDados();
	}

	public void salvar()
        {
                AutenticacaoFile file = new AutenticacaoFile(this);
                file.salvar();
        }

	public void atualizarDadosPorId(int id)
        {
                AutenticacaoFile file = new AutenticacaoFile(this);
                file.atualizarDadosPorId(id);
        }
	
	public boolean autenticar(String nome, String pass)
	{
		boolean isReg = false;
		AutenticacaoFile file = new AutenticacaoFile(this);
                isReg = file.autenticar(nome, pass);
		
		return isReg;
	}


}
