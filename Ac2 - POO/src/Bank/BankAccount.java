package Bank;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class BankAccount {

	private static int lastAccountNumber = 1000;
    //Próximo número utilizado da conta
    
    private String owner;
    private String password;
    private int accountNumber;
    private double balance;
    private double cpmf;
     
    //Método privado para retirar assentos do nome dos clientes, e um complemento do metodo checkName
    private static String semAcento(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
    //Método para checar sem tem o números ou caracteres especiais nos nomes dos clientes
    //Caso exista ele irá remove-los
	private static String checkName(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("[!#@$%¨&*0-9].*");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
    
    //Criar uma conta com saldo igual 0.0
    public BankAccount(String owner){
        //chama outro construtor dessa classe com 2 parâmetros
        this(owner, 0.0);
    }
    
    //método para que se gere uma senha automática contendo 3 letras e quatro números de maneira aleatória
    private static String makePassword() {
    	int qtdeMaximaCaracteres = 3;
		int qtdeMaximaNumeros = 4;
		String[] caracteres = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
	                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
	                "x", "y", "z"};
	    
	    String[] numeros = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
        "9", "0", "1", "2", "3", "4", "5", "6", "7", "8",
        "9", "0", "1", "2", "2", "4", "5", "6", "7", "8",
        "9"};
	    
		StringBuilder senha = new StringBuilder();
		StringBuilder senha1 = new StringBuilder();
		
        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
       senha1 = senha;
        
        for (int i = 0; i < qtdeMaximaNumeros; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha1.append(numeros[posicao]);
        }
        
        return senha1.toString();
    }
    
    //método construtor
    public BankAccount(String owner,  double balance) {
        this.owner = semAcento(checkName(owner));
        this.accountNumber = lastAccountNumber++;
        this.balance = balance;
    }
    
    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return semAcento(checkName(owner));
    }
    
    public String getPassword() {
        return password = makePassword();
    }
    
    public static int getLastAccountNumber() {
        return lastAccountNumber;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public void setPassword(String password) {
    	password = makePassword();
        this.password = password;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    //Método de transferencia entre as contas
    public void transfer(double valor, BankAccount target) {
    	// instruções do método a ser desenvolvido
    	double newBalance = balance + valor;
        balance = newBalance;
  
    }
    
    /**
     * Deposita dinheiro nesta conta
     * (PÃ³s-condiÃ§Ã£o: getBalance() >= 0.0)
     * @param amount: a quantia de dinheiro a depositar
     * (PrÃ©-condiÃ§Ã£o: amount >=0)
     */
    //método de deposito 
    public void deposit(double amount){
        //Evitar efeitos colaterais
        double newBalance = balance + amount;
        balance = newBalance;
    }
    
    public double cpmfDesconto(double valor) {
    	balance = balance - (valor*0.0025);
    	cpmf = cpmf + (valor*0.0025);
    	return cpmf;
    }
    
   
    void withDraw(double amount) {
        //Evitar efeitos colaterais
        double newBalance = balance - amount;
         balance = newBalance;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "owner=" + owner + ", accountNumber=" + 
                accountNumber + ", balance=" + balance + '}';
    }
}
