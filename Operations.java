package ro.uvt.fi.dp;

public interface Operations {
	double getTotalAmount();

	double getInterest();

	void deposit(double amount);

	void retrieve(double amount);
}
