package Bank;

import java.util.Scanner;

public class BancoApp {

	public static Scanner entrada;

	//mostrar as informações das contas existentes
	public static void mostrarInfo(BankAccount[] contas) {
		System.out.println("\nContas de todos os clientes:");
		for (int i=0; i<contas.length; i++) {
			System.out.println("[" + i + "]" + contas[i].toString());
		}
		System.out.println("");
	}
	
	//Método de sacar dinheiro da conta selecionada
	public static void interacaoSacar(BankAccount[] contas) {
		boolean clienteValido = false;
		int indiceConta = -1;
		while (!clienteValido) {
			mostrarInfo(contas);
			System.out.print("O saque será efetuado na conta de qual cliente? (0 a " + (contas.length-1) + "): ");
			indiceConta = entrada.nextInt();
			if (indiceConta >=0 && indiceConta < contas.length) {
				clienteValido = true;
			}
			else {
				System.out.println("Índice de cliente inválido!");
			}
		}

		System.out.print("Qual o valor do saque? ");
		double saque = entrada.nextDouble();
		contas[indiceConta].cpmfDesconto(saque);
		contas[indiceConta].withDraw(saque);
		System.out.println("Saque finalizado.\n");
	}

	//método de depositar dinheiro da conta selecionada
	public static void interacaoDepositar (BankAccount[] contas) {
		boolean clienteValido = false;
		int indiceConta = -1;
		while (!clienteValido) {
			mostrarInfo(contas);
			System.out.print("O deposito será efetuado na conta de qual cliente? (0 a "
			+ (contas.length-1) + "): ");
			indiceConta = entrada.nextInt();
			if (indiceConta >=0 && indiceConta < contas.length) {
				clienteValido = true;
			}
			else {
				System.out.println("Índice de cliente inválido!");
			}
		}
		
		System.out.print("Qual o valor do deposito? ");
		double deposito = entrada.nextDouble();
		contas[indiceConta].deposit(deposito);
		System.out.println("Deposito finalizado.\n");
	}
	
	//Método de transferir o dinheiro de uma conta para outra
	public static void interacaoTransferir (BankAccount[] contas) {
		boolean clienteValido = false;
		boolean clienteValido1 = false;
		int indiceConta = -1;
		int indiceConta1 = -1;
		while (!clienteValido) {
			mostrarInfo(contas);
			System.out.print("o dinheiro saira da conta de qual cliente? (0 a "
			+ (contas.length-1) + "): ");
			indiceConta = entrada.nextInt();
			if (indiceConta >=0 && indiceConta < contas.length) {
				clienteValido = true;
			}
			else {
				System.out.println("Índice de cliente inválido!");
			}
		}
		
		System.out.print("Qual o valor da transferência? ");
		double transfer = entrada.nextDouble();
		contas[indiceConta].withDraw(transfer);
		
		while (!clienteValido1) {
			System.out.print("a transferência vai para a conta de qual cliente? (0 a "
			+ (contas.length-1) + "): ");
			indiceConta1 = entrada.nextInt();
			if (indiceConta1 >=0 && indiceConta1 < contas.length) {
				clienteValido1 = true;
				contas[indiceConta1].transfer(transfer, contas[indiceConta1]);
			}
			else {
				System.out.println("Índice de cliente inválido!");
			}
		}
		
		System.out.println("Transferença finalizado.\n");
	}
	
	public static void main(String[] args) {
		BankAccount[] contas = new BankAccount[5];
		contas[0] = new BankAccount("Marcos1111{}[]", 1000.00);
		contas[1] = new BankAccount("Júlia", 250.00);
		contas[2] = new BankAccount("João", 2500.00);
		contas[3] = new BankAccount("Roberto", 3000.00);
		contas[4] = new BankAccount("Janaína", 4500.00);

		entrada = new Scanner(System.in);
		boolean sair = false;

		while(!sair) {
			System.out.println("Escolha uma operação:");
			System.out.println("(1) mostrar informações de todas as contas");
			System.out.println("(2) sacar");
			System.out.println("(3) depositar");
			System.out.println("(4) transferir");
			System.out.println("(5) sair");
			System.out.print("Opção escolhida: ");
			int escolha = entrada.nextInt();
			System.out.println();
			switch(escolha) {
				case 1: mostrarInfo(contas); break;
				case 2: interacaoSacar(contas); break;
				case 3: interacaoDepositar(contas); break;
				case 4: interacaoTransferir(contas); break;
				case 5: sair = true; break;
				default: System.out.println("Opção inválida!");
			}
			System.out.println();
		}
		System.out.println("Fim do programa!");
	}
}
