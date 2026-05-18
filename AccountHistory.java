package ro.uvt.fi.dp;

import java.util.ArrayList;
import java.util.List;

public class AccountHistory {

    private List<AccountMemento> history = new ArrayList<>();

    public void save(Account account) {
        AccountMemento memento = account.save();
        history.add(memento);
        System.out.println("[History] Saved: " + memento);
    }

    public AccountMemento getLatest() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1);
    }

    public List<AccountMemento> getAll() {
        return new ArrayList<>(history);
    }
}
