package ro.uvt.fi.dp;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
/*ISSUE 4
 * Both Client and Bank used fixed size arrays, meaning that if the requirement
 * changes, then the code must be modified
 * NOW SOLVED
 * Replaced fixed-size array of accounts with a dynamic List, so
   the limitation of a maximum number of accounts is removed and
   the extensibility of the system is improved.
 */


public class Client {
	public static final int MAX_ACCOUNTS_NO = 5;

	private String clientId;
	private String name;
	private String address;
	private List<Account> accounts;

	/* ISSUE 3
	Client directly instantiates Account, meaning that it depends on this class instead of
	an abstraction. This creates tight coupling, making the code less flexible
	*
	NOW SOLVED
	Removed direct instantiation of Account.
   	Client now uses AccountFactory to create accounts, reducing tight coupling.
	 */

	public Client(String clientId, String name, String address) {
		this.clientId = clientId;
		this.name = name;
		this.address = address;
		this.accounts = new ArrayList<>();
	}

	public String getClientId() {
		return clientId;
	}

	public void addAccount(String type, String accountCode, double amount) {
		accounts.add(AccountFactory.createAccount(type, accountCode, amount));
	}

	public Account getAccount(String accountCode) {
		for (Account acc : accounts) {
			if (acc.getAccountCode().equals(accountCode)) {
				return acc;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "\n\tClient [name=" + name + ", address=" + address + ", accounts=" + accounts + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
