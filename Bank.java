package ro.uvt.fi.dp;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Bank {


	private List<Client> clients;
	private String bankCode = null;

	public Bank(String codBanca) {
		this.bankCode = codBanca;
		clients = new ArrayList<>();
	}

	public void addClient(Client c) {
		clients.add(c);
	}

	/*ISSUE 7
	The fetching process is done by taking the name of the client. This is not
	ok because if two clients have the same name, this would lead to ambiguity
	NOW SOLVED
	The system now uses a unique clientId for client identification.
	* */
	public Client getClient(String id) {
		for (Client client : clients) {
			if (client.getClientId().equals(id)) {
				return client;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Bank [code=" + bankCode + ", clients=" + clients + "]";
	}

}
