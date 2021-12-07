package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.Colaborador;
import entidades.Contrato;
import entidades.ContratoAssalariado;
import entidades.ContratoComissionado;
import entidades.ContratoHorista;
import entidades.VendaComissionada;

public class App {

	static List<Colaborador> listaColaboradores = new ArrayList<>();
	static List<Contrato> listaContratos = new ArrayList<>();
	static List<VendaComissionada> listaVendasComissionadas = new ArrayList<>();
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
		System.out.println(" --**Inserir colaborador**-- ");


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

			Colaborador cpfColaborador = new Colaborador(cpf);

			if (cpfColaborador.validarCpf() == false) {
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
			System.out.println("--**Tipos de contratos**--\n");
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
				System.out.println("Registro finalizado!");
				return;
			default:
				System.out.println("Opção incorreta");
				break;
			}
		}
	}

	public static void consultarContrato() {

		System.out.println(" --**Consultar contrato**-- ");

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
		System.out.println(" --**Encerrar contrato**-- ");

		while (true) {

			int id = 0;
			;

			try {
				System.out.println("Digite o ID do contrato: ");
				id = ler.nextInt();
			} catch (Exception e) {
				System.out.println("Digite um número válido! ");
			}

			Contrato contratoEncontrado = pesquisarContrato(id);

			contratoEncontrado.getDataEncerramento();
			if (contratoEncontrado == null || contratoEncontrado.isAtivo() == false) {
				System.out.println("O contrato já está encerrado ou não existe");
			}

			System.out.println("Digite a data de Encerramento (DD/MM/YYYY): ");
			String dataInformadaEncerramento = ler.next();

			Date atual = new Date();
			Date dataEncerramento = new SimpleDateFormat("dd/MM/yyyy").parse(dataInformadaEncerramento);
			Calendar cVenc = Calendar.getInstance();
			Calendar cAtual = Calendar.getInstance();

			cVenc.setTime(dataEncerramento);
			cAtual.setTime(atual);

			if (cAtual.get(Calendar.DAY_OF_YEAR) < cVenc.get(Calendar.DAY_OF_YEAR)) {
				System.out.println("Data de encerramento inválida");
			} else {
				contratoEncontrado.encerrarContrato(dataEncerramento);

				System.out.println("Atribuido data de encerramento ao contrato e encerramento efetuado com sucesso!");
			}
		}
	}

	public static void listarColaborador() {
		System.out.println(" --**Listar colaboradores**-- ");
		for (Colaborador colaborador : listaColaboradores) {
			if (0 == listaColaboradores.size()) {
				System.out.println("Nenhum colaborador está ativo");
			} else if (colaborador.isSituacao() == true) {
					System.out.println(" **COLABORADOR** ");
					System.out.println("Matricula: " + colaborador.getMatricula());
					System.out.println("Nome: " + colaborador.getNome());
					System.out.println("CPF: " + colaborador.getCpf());
					
			}
		}
	}

	public static void consultarContratoColaborador() {
		System.out.println(" --**Consultar contrato colaborador**-- ");

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
		} else {
			System.out.println("Contrato inativo");
		}

	}

	public static void lancarVendasComissionadas() {
		System.out.println(" --**Lançar vendas comissionadas**-- ");
		while(true) {
			System.out.println("Digite o ID [0 para finalizar]: ");
			int id = ler.nextInt();
			if (id == 0) {
				return;
			}
			Contrato contratoEncontrado = pesquisarContrato(id);
			if (!(contratoEncontrado instanceof ContratoComissionado && contratoEncontrado.isAtivo() == true && contratoEncontrado != null)) {
				System.out.println("O contrato informado não existe, está encerrado ou não é um contrato comissionado");
			} else {
				System.out.println("informe o mes da filha de pagamento: ");
				int mes = ler.nextInt();
				if (mes == 0) {
					return;
				}
				System.out.println("informe o mes da filha de pagamento (yyyy): ");
				int ano = ler.nextInt();
				System.out.println("Valor total das vendas do mês: ");
				float valor = ler.nextFloat();
				
				//VendaComissionada vendaComissionada = new VendaComissionada(mes, ano, valor,(ContratoComissionado)contratoEncontrado);
				listaVendasComissionadas.add(new VendaComissionada(mes, ano, valor,(ContratoComissionado)contratoEncontrado));
				
				
			}
		}
		
	}

	public static void emitirFolhaPagamento() {
		System.out.println(" --**Emitir folha de pagamento**-- ");
		while (true) {
			System.out.println("informe o mes da filha de pagamento (de 1 a 12) [0 para finalizar]: ");
			int mes = ler.nextInt();
			if (mes == 0) {
				return;
			}
			System.out.println("informe o mes da filha de pagamento (yyyy): ");
			int ano = ler.nextInt();
			
			for (Contrato contrato : listaContratos) {
				if (contrato.isAtivo() == true) {
					System.out.println("ID: " + contrato.getId());
					System.out.println("Matricula do colaborador: " + contrato.getColaborador().getMatricula());
					System.out.println("Nome: " + contrato.getColaborador().getNome());
					if (contrato instanceof ContratoAssalariado) {
						System.out.println("Tipo de contrato: Contrato assalariado");
						System.out.println("Matricula: " + contrato.getColaborador().getMatricula());
						System.out.println("Nome: " + contrato.getColaborador().getNome());
						System.out.println("Salário: " + ((ContratoAssalariado) contrato).calcVencimento());
					}
					if (contrato instanceof ContratoComissionado) {
						float vlFaturam = 0;
						for (VendaComissionada vendaComissionada : listaVendasComissionadas) {
							if (vendaComissionada.getMes() == mes && vendaComissionada.getAno() == ano) {
								vlFaturam += vendaComissionada.getValor();
							}
							System.out.println("Tipo de contrato: Contrato comissionado");
							System.out.println("Matricula: " + contrato.getColaborador().getMatricula());
							System.out.println("Nome: " + contrato.getColaborador().getNome());
							System.out.println("Salário: " + ((ContratoComissionado) contrato).calcVencimento(vlFaturam));
						}
					}
					if (contrato instanceof ContratoHorista) {
						System.out.println("Tipo de contrato: Contrato horista");
						System.out.println("Matricula: " + contrato.getColaborador().getMatricula());
						System.out.println("Nome: " + contrato.getColaborador().getNome());
						System.out.println("Salário: " + ((ContratoHorista) contrato).calcVencimento());
						
					}
				}
			}
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

		Contrato contrato = new ContratoAssalariado(dataInicio, dataEncerramento,
				colaboradorEncontrado, salarioMensal, percInsalubridade, percPericulosidade);

		System.out.println("O id do contrato é: " + contrato.getId());

		listaContratos.add(contrato);

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

		Contrato contrato = (new ContratoComissionado(dataInicio, dataEncerramento,
				colaboradorEncontrado, percentualComissao, ajudaCusto));

		System.out.println("O id do contrato é: " + contrato.getId());

		listaContratos.add(contrato);

	}

	public static void registrarContratoHorista() throws ParseException {

		System.out.println("---** Registrar Contrato comissionado**---");

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

		Contrato contrato = (new ContratoHorista(dataInicio, dataEncerramento, colaboradorEncontrado, horaMes,
				valorHora));

		System.out.println("O id do contrato é: " + contrato.getId());

		listaContratos.add(contrato);

	}

}