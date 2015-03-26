package src.com.adform.academy.data.DAO;

import java.util.List;

public interface DAOClient {

    public void addScheme(String group, String name, String version);

    public String getScheme(String group, String name, String version);

    public String getScheme(String group, String name);

    public List getGroupOfScheme(String groupName);

    public void updateScheme(String group, String name, String version);

    public void deleteScheme(String group, String name, String version);

}
