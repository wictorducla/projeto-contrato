package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entidades.Colaborador;
import entidades.Contrato;
import entidades.ContratoAssalariado;
import entidades.ContratoComissionado;
import entidades.ContratoHorista;

public class App {

	static List<Colaborador> listaColaboradores = new ArrayList<>();
	static List<Contrato> listaContratos = new ArrayList<>();
	static Scanner ler = new Scanner(System.in);

	public static void main(String[] args) throws ParseException {
		do {
			System.out.println("---**Seletor de Opções**---\n");
			System.out.println("1 - Inserir colaborador");
			System.out.println("2 - Registrar contrato");
			System.out.println("3 - Consultar contrato");
			System.out.println("4 - Encerrar contrato");
			System.out.println("5 - Listar colaboradores ativos");
			System.out.println("6 - Consultar contrato do colaborador");
			System.out.println("7 - Lançar vendas comissionadas");
			System.out.println("8 - Emitir folha de pagamento");
			System.out.println("0 - Finalizar");
			System.out.print("Opção: ");
			int opcao = ler.nextInt();
			switch (opcao) {
			case 1:
				InserirColaborador();
				break;
			case 2:
				registrarContrato();
				break;
			case 3:
				consultarContrato();
				break;
			case 4:
				encerrarContrato();
				break;
			case 5:
				listarColaborador();
				break;
			case 6:
				consultarContratoColaborador();
				break;
			case 7:
				lancarVendasComissionadas();
				break;
			case 8:
				emitirFolhaPagamento();
				break;
			case 0:
				System.out.println("Programa finalizado");
				return;
			default:
				System.out.println("Opção incorreta");
				break;
			}
		} while (true);

	}

	public static Colaborador pesquisarColaborador(String matricula) {
		for (Colaborador colaborador : listaColaboradores) {
			if (colaborador.getMatricula() == matricula) {
				return colaborador;
			}
		}
		return null;
	}

	public static Contrato pesquisarContrato(String matricula) {
		for (Contrato contrato : listaContratos) {
			if (contrato.getColaborador().getMatricula() == matricula) {
				return contrato;
			}
		}
		return null;
	}
	
	public static Contrato pesquisarContrato(int id) {
		for (Contrato contrato : listaContratos) {
			if (contrato.getId() == id) {
				return contrato;
			}
		}
		return null;
	}

	public static int retornarIdade(String data) throws ParseException {
		Date atual = new Date();
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		Calendar c = Calendar.getInstance();
		Calendar cAtual = Calendar.getInstance();

		c.setTime(date);
		cAtual.setTime(atual);

		int idade = cAtual.get(Calendar.YEAR) - c.get(Calendar.YEAR);

		if (cAtual.get(Calendar.MONTH) + 1 == c.get(Calendar.MONTH) + 1) {
			if (cAtual.get(Calendar.DATE) + 1 >= c.get(Calendar.DATE)) {

			} else {
				idade--;
			}
		} else {
			if (cAtual.get(Calendar.MONTH) + 1 > c.get(Calendar.MONTH) + 1) {
			} else {
				idade--;
			}
		}
		return idade;
	}

	public static void InserirColaborador() throws ParseException {

		while (true) {
			String matricula = "";
			String cpf = "";
			String nome = "";
			System.out.println("\nDigite 0 para finalizar\n");

			try {
				System.out.print("Digite a matrícula do colaborador: ");
				matricula = ler.next();
			} catch (Exception e) {
				System.out.println("Digite um número válido! ");
			}

			if (matricula.equals("0")) {
				System.out.println("Programa encerrado!! ");
				return;
			}

			Colaborador colaboradorEncontrado = pesquisarColaborador(matricula);

			if (colaboradorEncontrado != null) {
				System.out.println("Colaborador já cadastrado!! ");
				continue;
			}

			System.out.print("Digite o CPF do colaborador: ");
			cpf = ler.next();

			if (validarCpf(cpf) == false) {
				System.out.println("CPF inválido!! ");
				continue;
			}

			System.out.println("Digite o nome do colaborador: ");
			nome = ler.next();

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			System.out.print("Digite a data de nascimento do colaborador (DD/MM/YYYY): ");
			String dataInformada = ler.next();
			Date dataNascimento = formato.parse(dataInformada);

			if (retornarIdade(dataInformada) < 18) {
				System.out.println("Impossível cadastrar, menor de 18 anos! ");
				continue;

			}

			listaColaboradores.add(new Colaborador(matricula, cpf, nome, dataNascimento));
		}
	}

	public static void registrarContrato() throws ParseException {
		while (true) {
			System.out.println("Selecione o tipo de contrato que deseja registrar\n");
			System.out.println("---**Tipos de contratos**---\n");
			System.out.println("1 - Contrato assalariado");
			System.out.println("2 - Contrato comissionado");
			System.out.println("3 - Contrato horista");
			System.out.println("0 - Finalizar registro");
			System.out.print("Digite a opção: ");
			int opcao = ler.nextInt();
			switch (opcao) {

			case 1:
				registrarContratoAssalariado();
				break;
			case 2:
				registrarContratoComissionado();
				break;
			case 3:
				registrarContratoHorista();
				break;
			case 0:
				return;
			default:
				System.out.println("Opção incorreta");
				break;
			}
		}
	}

	public static void consultarContrato() {

		System.out.println(" **Consultar contrato** ");

		while (true) {

			int id = 0;

			try {
				System.out.println("Digite o ID: ");
				id = ler.nextInt();

			}

			catch (Exception e) {
				System.out.println("Digite um número válido! ");

			}

			Contrato contratoEncontrado = pesquisarContrato(id);
			
			System.out.println("Contrato");
			System.out.println("ID: " + contratoEncontrado.getId());
			System.out.println("Data de inicio: " + contratoEncontrado.getDataInicio());
			System.out.println("Data de encerramento: " + contratoEncontrado.getDataEncerramento());
			System.out.println("Situação: " + contratoEncontrado.isAtivo());

			if (contratoEncontrado instanceof ContratoAssalariado) {
				System.out.println("Tipo de contrato: Contrato assalariado");
			}

			if (contratoEncontrado instanceof ContratoComissionado) {
				System.out.println("Tipo de contrato: Contrato comissionado");
			}

			if (contratoEncontrado instanceof ContratoHorista) {
				System.out.println("Tipo de contrato: Contrato horista");
			}

			System.out.println("Colaborador	");
			System.out.println("Matricula: " + contratoEncontrado.getColaborador().getMatricula());
			System.out.println("Nome: " + contratoEncontrado.getColaborador().getNome());
			System.out.println("CPF: " + contratoEncontrado.getColaborador().getCpf());
			System.out.println("Situação: " + contratoEncontrado.getColaborador().isSituacao());

		}
	}
	
	public static void encerrarContrato() throws ParseException {
		System.out.println(" **Encerrar contrato** ");

		while (true) {

			String matricula = "";

			try {
				System.out.println("Digite a matrícula: ");
				matricula = ler.next();
			}
			catch (Exception e) {
				System.out.println("Digite um número válido! ");
			}
			Colaborador colaboradorEncontrado = pesquisarColaborador(matricula);

			if (colaboradorEncontrado == null) {
				System.out.println("Colaborador não cadastrado! Não há contrato a ser exibido!");
				continue;
			}
			
			Contrato contratoEncontrado = pesquisarContrato(matricula);
			
			contratoEncontrado.getDataEncerramento();
			if (contratoEncontrado == null || contratoEncontrado.isAtivo() == false) {
				System.out.println("O contrato já está encerrado ou não existe");
			}
			
			SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Digite a data de Encerramento (DD/MM/YYYY): ");
			String dataInformadaEncerramento = ler.next();
			Date dataEncerramento = formato2.parse(dataInformadaEncerramento);
			
			contratoEncontrado.setDataEncerramento(dataEncerramento);
			
			contratoEncontrado.encerrarContrato();
			contratoEncontrado.getColaborador().desativar();
			
			System.out.println("Atribuido data de encerramento ao contrato e encerramento efetuado com sucesso!");
			
		}
	}

	public static void listarColaborador() {
		for (Colaborador colaborador : listaColaboradores) {
			if (colaborador.isSituacao() == true) {
				System.out.println(" **COLABORADOR** ");
				System.out.println("Matricula: " + colaborador.getMatricula());
				System.out.println("Nome: " + colaborador.getNome());
				System.out.println("CPF: " + colaborador.getCpf());
			} else {
				int cont = 0;
				cont ++;
				if (cont == listaColaboradores.size()) {
					System.out.println("Nenhum colaborador está ativo");
				}
			}
		}
	}
	
	public static void consultarContratoColaborador() {
		System.out.println("Informar a matricula ou CPF do colaborador: ");
		String identificador = ler.next();
		
		Contrato contratoEncontrado = pesquisarContrato(identificador);
		
		System.out.println(" **COLABORADOR** ");
		System.out.println("Matricula: " + contratoEncontrado.getColaborador().getMatricula());
		System.out.println("Nome: " + contratoEncontrado.getColaborador().getNome());
		System.out.println("CPF: " + contratoEncontrado.getColaborador().getCpf());
		System.out.println("Situação: " + contratoEncontrado.getColaborador().isSituacao());
		System.out.println("\n **CONTRATO** ");
		System.out.println("ID: " + contratoEncontrado.getId());
		if (contratoEncontrado instanceof ContratoAssalariado) {
			System.out.println("Tipo de contrato: Contrato assalariado");
		}
		if (contratoEncontrado instanceof ContratoComissionado) {
			System.out.println("Tipo de contrato: Contrato comissionado");
		}
		if (contratoEncontrado instanceof ContratoHorista) {
			System.out.println("Tipo de contrato: Contrato horista");
		}
		System.out.println("Data de inicio: " + contratoEncontrado.getDataInicio());
		System.out.println("Data de encerramento: " + contratoEncontrado.getDataEncerramento());
		if (contratoEncontrado.isAtivo() == true) {
			System.out.println("Contrato ativo");
		}else {
			System.out.println("Contrato inativo");
		}
		
	}
	
	public static void lancarVendasComissionadas() {
		
	}
	
	public static void emitirFolhaPagamento() {
		
	}
	
	public static boolean validarCpf(String cpf) {
		// verifica CPFs formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11)) {
			return (false);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico
			}
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}
			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
				return (true);
			} else {
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static void registrarContratoAssalariado() throws ParseException {

		System.out.println("** Registrar Contrato assalariado **");

		if (listaColaboradores.isEmpty()) {
			System.out.println("Não há colaborador na lista! ");
			return;
		}

		String matricula = "";
		float salarioMensal;
		float percInsalubridade;
		float percPericulosidade;

		SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Digite a data de início (DD/MM/YYYY): ");
		String dataInformadaInicio = ler.next();
		Date dataInicio = formato1.parse(dataInformadaInicio);

		SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Digite a data de Encerramento (DD/MM/YYYY): ");
		String dataInformadaEncerramento = ler.next();
		Date dataEncerramento = formato2.parse(dataInformadaEncerramento);

		try {
			System.out.println("Digite a matrícula do colaborador: ");
			matricula = ler.next();
		} catch (Exception e) {
			System.out.println("Digite um número válido! ");
			ler.next();
		}

		Colaborador colaboradorEncontrado = pesquisarColaborador(matricula);

		if (colaboradorEncontrado == null) {
			System.out.println("Colaborador não cadastrado!! ");
			return;
		}

		System.out.println("Digite o salário mensal: ");
		salarioMensal = ler.nextFloat();

		System.out.println("Digite o percentual de insalubridade: ");
		percInsalubridade = ler.nextFloat();

		System.out.println("Digite o percentual de periculosidade: ");
		percPericulosidade = ler.nextFloat();

		ContratoAssalariado contratoAssalariado = new ContratoAssalariado(dataInicio, dataEncerramento,
				colaboradorEncontrado, salarioMensal, percInsalubridade, percPericulosidade);

		System.out.println("O id do contrato é: " + contratoAssalariado.getId());

		listaContratos.add(contratoAssalariado);

	}

	public static void registrarContratoComissionado() throws ParseException {

		System.out.println("** Registrar Contrato comissionado**");

		if (listaColaboradores.isEmpty()) {
			System.out.println("Não há colaborador na lista! ");
			return;
		}

		String matricula = "";
		float percentualComissao;
		float ajudaCusto;

		SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Digite a data de início: ");
		System.out.println("Formato da data dd/MM/yyyy");
		String dataInformadaInicio = ler.next();
		Date dataInicio = formato1.parse(dataInformadaInicio);

		SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Digite a data de Encerramento: ");
		System.out.println("Formato da data dd/MM/yyyy");
		String dataInformadaEncerramento = ler.next();
		Date dataEncerramento = formato2.parse(dataInformadaEncerramento);

		try {
			System.out.println("Digite a matrícula do colaborador: ");
			matricula = ler.next();
		} catch (Exception e) {
			System.out.println("Digite um número válido! ");
			ler.next();
		}

		Colaborador colaboradorEncontrado = pesquisarColaborador(matricula);

		if (colaboradorEncontrado == null) {
			System.out.println("Colaborador não cadastrado!! ");
			return;
		}

		System.out.println("Digite o percentual de comissão: ");
		percentualComissao = ler.nextFloat();

		System.out.println("Digite a ajuda de custo: ");
		ajudaCusto = ler.nextFloat();

		ContratoComissionado contratoComissionado = (new ContratoComissionado(dataInicio, dataEncerramento,
				colaboradorEncontrado, percentualComissao, ajudaCusto));

		System.out.println("O id do contrato é: " + contratoComissionado.getId());

		listaContratos.add(contratoComissionado);

	}

	public static void registrarContratoHorista() throws ParseException {

		System.out.println("** Registrar Contrato comissionado**");

		if (listaColaboradores.isEmpty()) {
			System.out.println("Não há colaborador na lista! ");
			return;
		}

		String matricula = "";
		int horaMes;
		float valorHora;

		SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Digite a data de início: ");
		System.out.println("Formato da data dd/MM/yyyy");
		String dataInformadaInicio = ler.next();
		Date dataInicio = formato1.parse(dataInformadaInicio);

		SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Digite a data de Encerramento: ");
		System.out.println("Formato da data dd/MM/yyyy");
		String dataInformadaEncerramento = ler.next();
		Date dataEncerramento = formato2.parse(dataInformadaEncerramento);

		try {
			System.out.println("Digite a matrícula do colaborador: ");
			matricula = ler.next();
		} catch (Exception e) {
			System.out.println("Digite um número válido! ");
			ler.next();
		}

		Colaborador colaboradorEncontrado = pesquisarColaborador(matricula);

		if (colaboradorEncontrado == null) {
			System.out.println("Colaborador não cadastrado!! ");
			return;
		}

		System.out.println("Digite o percentual de comissão: ");
		horaMes = ler.nextInt();

		System.out.println("Digite a ajuda de custo: ");
		valorHora = ler.nextFloat();

		ContratoHorista contratoHorista = (new ContratoHorista(dataInicio, dataEncerramento, colaboradorEncontrado,
				horaMes, valorHora));

		System.out.println("O id do contrato é: " + contratoHorista.getId());

		listaContratos.add(contratoHorista);

	}

}
