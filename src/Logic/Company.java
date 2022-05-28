package Logic;

import java.util.ArrayList;

public class Company  {
    private final ArrayList<Organization> organizations;

    public Company() {

        organizations = new ArrayList<>();
    }

    public void addOrganization(Organization orga){
        organizations.add(orga);
    }

    public ArrayList<Organization> getList(){
        return organizations;
    }

    public Company findid(int name) {
        Company a = new Company();
        for (Organization organization : organizations) {
            if (organization.getID() == name) {
                a.addOrganization(organization);
            }
        }
        return a;
    }

    public Company findYear(int name) {
        Company a = new Company();
        for (Organization organization : organizations) {
            if (organization.getYear() == name) {
                a.addOrganization(organization);
            }
        }
        return a;
    }

    public Company findname(String name) {
        Company a = new Company();
        for (Organization organization : organizations) {
            if (organization.getName().equals(name)) {
                a.addOrganization(organization);
            }
        }
        return a;
    }
    public Company findCompanytype(String name) {
        Company a = new Company();
        for (Organization organization : organizations) {
            if (organization.getCompany_type().equals(name)) {
                a.addOrganization(organization);
            }
        }
        return a;
    }
    public Company findType(String name) {
        Company a = new Company();
        for (Organization organization : organizations) {
            if (organization.getType().equals(name)) {
                a.addOrganization(organization);
            }
        }
        return a;
    }
    public void clear() {
        organizations.clear();
    }

}

