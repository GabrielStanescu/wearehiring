package tema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, InvalidDatesException {

        Application app = Application.getInstance();
        String path = "src/tema/consumers.json";

        Object obj = new JSONParser().parse(new FileReader(path));

        JSONObject jo = (JSONObject) obj;

        String aux;
        long auxSalary;
        LocalDate auxDate;
        Education auxEd;
        JSONArray eds;
        Experience auxXp;
        JSONArray xps;
        JSONObject current;
        JSONArray langs;
        JSONArray langsLvl;
        JSONArray interested;
        JSONArray ja;
        int size;
        ArrayList<Employee> employees;

        // Parsarea consumers.json; am folosit cate un for pentru fiecare tip de Consumer
        //recruiters
        ja = (JSONArray) jo.get("recruiters");
        size = ja.size();
        ArrayList<Recruiter> recruiters = new ArrayList<Recruiter>();

        for (int i = 0; i < size; i++) {
            Recruiter r = new Recruiter();
            current = (JSONObject) ja.get(i);

            aux = (String) current.get("name");
            r.r.getInfo().setName(aux.substring(0, aux.lastIndexOf(' ')), aux.substring(aux.lastIndexOf(' ') + 1));

            aux = (String) current.get("email");
            r.r.getInfo().setEmail(aux);

            aux = (String) current.get("phone");
            r.r.getInfo().setPhone(aux);

            aux = (String) current.get("date_of_birth");
            auxDate = LocalDate.parse(aux, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            r.r.getInfo().setBirthDate(auxDate);

            aux = (String) current.get("genre");
            r.r.getInfo().setGender(aux);

            auxSalary = (long) current.get("salary");
            r.setSalary(auxSalary);

            langs = (JSONArray) current.get("languages");
            langsLvl = (JSONArray) current.get("languages_level");
            for (int j = 0; j < langs.size(); j++)
                r.r.getInfo().addLang(langs.get(j).toString(), langsLvl.get(j).toString());

            eds = (JSONArray) current.get("education");
            for (int j = 0; j < eds.size(); j++) {
                JSONObject currentEd = (JSONObject) eds.get(j);
                auxEd = new Education((String) currentEd.get("start_date"), (String) currentEd.get("end_date"), (String) currentEd.get("name"), (String) currentEd.get("level"), Double.parseDouble(currentEd.get("grade").toString()));
                r.add(auxEd);
            }

            xps = (JSONArray) current.get("experience");

            for (int j = 0; j < xps.size(); j++) {
                JSONObject currentXp = (JSONObject) xps.get(j);
                auxXp = new Experience((String) currentXp.get("start_date"), (String) currentXp.get("end_date"), (String) currentXp.get("position"), (String) currentXp.get("company"));
                r.add(auxXp);
            }

            r.company = r.r.getXp().get(xps.size() - 1).getCompName();
            recruiters.add(r);
        }

        //users
        ja = (JSONArray) jo.get("users");
        size = ja.size();
        ArrayList<User> users = new ArrayList<User>();

        for (int i = 0; i < size; i++) {
            User u = new User();
            current = (JSONObject) ja.get(i);

            aux = (String) current.get("name");
            u.r.getInfo().setName(aux.substring(0, aux.lastIndexOf(' ')), aux.substring(aux.lastIndexOf(' ') + 1));

            aux = (String) current.get("email");
            u.r.getInfo().setEmail(aux);

            aux = (String) current.get("phone");
            u.r.getInfo().setPhone(aux);

            aux = (String) current.get("date_of_birth");
            auxDate = LocalDate.parse(aux, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            u.r.getInfo().setBirthDate(auxDate);

            aux = (String) current.get("genre");
            u.r.getInfo().setGender(aux);

            langs = (JSONArray) current.get("languages");
            langsLvl = (JSONArray) current.get("languages_level");
            for (int j = 0; j < langs.size(); j++)
                u.r.getInfo().addLang(langs.get(j).toString(), langsLvl.get(j).toString());

            interested = (JSONArray) current.get("interested_companies");
            for (int j = 0; j < interested.size(); j++)
                u.interested_companies.add(interested.get(j).toString());

            eds = (JSONArray) current.get("education");
            for (int j = 0; j < eds.size(); j++) {
                JSONObject currentEd = (JSONObject) eds.get(j);
                auxEd = new Education((String) currentEd.get("start_date"), (String) currentEd.get("end_date"), (String) currentEd.get("name"), (String) currentEd.get("level"), Double.parseDouble(currentEd.get("grade").toString()));
                u.add(auxEd);
            }

            xps = (JSONArray) current.get("experience");

            for (int j = 0; j < xps.size(); j++) {
                JSONObject currentXp = (JSONObject) xps.get(j);
                auxXp = new Experience((String) currentXp.get("start_date"), (String) currentXp.get("end_date"), (String) currentXp.get("position"), (String) currentXp.get("company"));
                u.add(auxXp);
            }

            users.add(u);
        }

        //managers
        ja = (JSONArray) jo.get("managers");
        size = ja.size();
        ArrayList<Manager> managers = new ArrayList<Manager>();

        for (int i = 0; i < size; i++) {
            Manager m = new Manager();
            current = (JSONObject) ja.get(i);

            aux = (String) current.get("name");
            m.r.getInfo().setName(aux.substring(0, aux.lastIndexOf(' ')), aux.substring(aux.lastIndexOf(' ') + 1));

            aux = (String) current.get("email");
            m.r.getInfo().setEmail(aux);

            aux = (String) current.get("phone");
            m.r.getInfo().setPhone(aux);

            aux = (String) current.get("date_of_birth");
            auxDate = LocalDate.parse(aux, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            m.r.getInfo().setBirthDate(auxDate);

            aux = (String) current.get("genre");
            m.r.getInfo().setGender(aux);

            auxSalary = (long) current.get("salary");
            m.setSalary(auxSalary);

            langs = (JSONArray) current.get("languages");
            langsLvl = (JSONArray) current.get("languages_level");
            for (int j = 0; j < langs.size(); j++)
                m.r.getInfo().addLang(langs.get(j).toString(), langsLvl.get(j).toString());

            eds = (JSONArray) current.get("education");
            for (int j = 0; j < eds.size(); j++) {
                JSONObject currentEd = (JSONObject) eds.get(j);
                auxEd = new Education((String) currentEd.get("start_date"), (String) currentEd.get("end_date"), (String) currentEd.get("name"), (String) currentEd.get("level"), Double.parseDouble(currentEd.get("grade").toString()));
                m.add(auxEd);
            }

            xps = (JSONArray) current.get("experience");

            for (int j = 0; j < xps.size(); j++) {
                JSONObject currentXp = (JSONObject) xps.get(j);
                auxXp = new Experience((String) currentXp.get("start_date"), (String) currentXp.get("end_date"), (String) currentXp.get("position"), (String) currentXp.get("company"));
                m.add(auxXp);
            }

            m.company = m.r.getXp().get(xps.size() - 1).getCompName();
            managers.add(m);
        }

        //employees
        ArrayList<Job> jobs = new ArrayList<Job>();
        ja = (JSONArray) jo.get("employees");
        size = ja.size();
        employees = new ArrayList<Employee>();
        String end_aux, salary_aux, pos_aux = null, comp_aux = null;
        boolean lock = false;

        for (int i = 0; i < size; i++) {
            Employee e = new Employee();
            current = (JSONObject) ja.get(i);

            aux = (String) current.get("name");
            e.r.getInfo().setName(aux.substring(0, aux.lastIndexOf(' ')), aux.substring(aux.lastIndexOf(' ') + 1));

            aux = (String) current.get("email");
            e.r.getInfo().setEmail(aux);

            aux = (String) current.get("phone");
            e.r.getInfo().setPhone(aux);

            aux = (String) current.get("date_of_birth");
            auxDate = LocalDate.parse(aux, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            e.r.getInfo().setBirthDate(auxDate);

            aux = (String) current.get("genre");
            e.r.getInfo().setGender(aux);

            auxSalary = (long) current.get("salary");
            e.setSalary(auxSalary);

            langs = (JSONArray) current.get("languages");
            langsLvl = (JSONArray) current.get("languages_level");
            for (int j = 0; j < langs.size(); j++)
                e.r.getInfo().addLang(langs.get(j).toString(), langsLvl.get(j).toString());

            eds = (JSONArray) current.get("education");
            for (int j = 0; j < eds.size(); j++) {
                JSONObject currentEd = (JSONObject) eds.get(j);
                auxEd = new Education((String) currentEd.get("start_date"), (String) currentEd.get("end_date"), (String) currentEd.get("name"), (String) currentEd.get("level"), Double.parseDouble(currentEd.get("grade").toString()));
                e.add(auxEd);
            }

            xps = (JSONArray) current.get("experience");

            lock = false;
            for (int j = 0; j < xps.size(); j++) {
                JSONObject currentXp = (JSONObject) xps.get(j);
                if (lock == false) {
                    aux = (String) currentXp.get("department");
                    end_aux = (String) currentXp.get("end_date");
                    pos_aux = (String) currentXp.get("position");
                    comp_aux = (String) currentXp.get("company");
                    if (end_aux == "null")
                        lock = true;
                }
                auxXp = new Experience((String) currentXp.get("start_date"), (String) currentXp.get("end_date"), (String) currentXp.get("position"), (String) currentXp.get("company"));
                e.add(auxXp);
            }

            e.company = e.r.getXp().get(xps.size() - 1).getCompName();

            // instantierea job-ului la care este angajat employee
            Job j = new Job(pos_aux, comp_aux, Double.parseDouble(current.get("salary").toString()), 0, "null", "null", "null", "null", "null", "null");
            j.department = aux;
            jobs.add(j);

            employees.add(e);
        }

        // social graph
        File social = new File("src/tema/social.in");
        Scanner scanner = new Scanner(social);
        String s;
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            if (s.charAt(0) == 'u') {
                if (s.charAt(3) == 'u') {
                    users.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(users.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    users.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(users.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
                if (s.charAt(3) == 'e') {
                    users.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(employees.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    employees.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(users.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
                if (s.charAt(3) == 'r') {
                    users.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(recruiters.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    recruiters.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(users.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
            }

            if (s.charAt(0) == 'e') {
                if (s.charAt(3) == 'u') {
                    employees.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(users.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    users.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(employees.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
                if (s.charAt(3) == 'e') {
                    employees.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(employees.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    employees.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(employees.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
                if (s.charAt(3) == 'r') {
                    employees.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(recruiters.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    recruiters.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(employees.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
            }

            if (s.charAt(0) == 'r') {
                if (s.charAt(3) == 'u') {
                    recruiters.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(users.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    users.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(recruiters.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
                if (s.charAt(3) == 'e') {
                    recruiters.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(employees.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    employees.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(recruiters.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
                if (s.charAt(3) == 'r') {
                    recruiters.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1).friends.add(recruiters.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1));
                    recruiters.get(Integer.parseInt(Character.toString(s.charAt(4))) - 1).friends.add(recruiters.get(Integer.parseInt(Character.toString(s.charAt(1))) - 1));
                    continue;
                }
            }
        }

        for (int i = 0; i < users.size(); i++)
            app.add(users.get(i));

        managers.get(0).company = "Google";
        managers.get(1).company = "Amazon";

        // Construirea celor 2 companii si a departamentelor

        // Amazon
        Company Amazon = new Company();
        Amazon.name = "Amazon";
        Amazon.manager = managers.get(1);
        Amazon.add(recruiters.get(2));
        Amazon.add(recruiters.get(3));

        // departamentele Amazon-ului
        IT AIT = new IT();
        Management AManagement = new Management();
        Marketing AMarketing = new Marketing();
        Finance AFinance = new Finance();

        AIT.add(employees.get(0));
        AManagement.add(employees.get(1));
        AMarketing.add(employees.get(2));
        AFinance.add(employees.get(3));
        AMarketing.add(employees.get(4));

        // Google
        Company Google = new Company();
        Google.name = "Google";
        Google.manager = managers.get(0);
        Google.add(recruiters.get(0));
        Google.add(recruiters.get(1));

        // departamentele Google-ului
        IT GIT = new IT();
        Management GManagement = new Management();
        Marketing GMarketing = new Marketing();
        Finance GFinance = new Finance();

        GIT.add(employees.get(5));
        GManagement.add(employees.get(6));
        GMarketing.add(employees.get(7));
        GFinance.add(employees.get(8));
        GMarketing.add(employees.get(9));

        Amazon.add(AIT);
        Amazon.add(AManagement);
        Amazon.add(AMarketing);
        Amazon.add(AFinance);

        Google.add(GIT);
        Google.add(GManagement);
        Google.add(GMarketing);
        Google.add(GFinance);

        app.add(Amazon);
        app.add(Google);

        // jobs text file
        File jobsFile = new File("src/tema/jobs.in");
        Scanner readerJ = new Scanner(jobsFile);
        String jobName, compName, salary, no, miny, maxy, minxp, maxxp, ming, maxg;
        int lineIndex = 0;
        String[] split;

        while (readerJ.hasNextLine()) {
            compName = readerJ.nextLine();
            jobName = readerJ.nextLine();
            no = readerJ.nextLine();
            salary = readerJ.nextLine();
            s = readerJ.nextLine();
            split = s.split("\\s+");
            miny = split[0];
            maxy = split[1];
            s = readerJ.nextLine();
            split = s.split("\\s+");
            minxp = split[0];
            maxxp = split[1];
            s = readerJ.nextLine();
            split = s.split("\\s+");
            ming = split[0];
            maxg = split[1];
            Job job = new Job(jobName, compName, Double.parseDouble(salary), Integer.parseInt(no), (Object) miny, (Object) maxy, (Object) minxp, (Object) maxxp, (Object) ming, (Object) maxg);
            if (compName.equals("Amazon"))
                AIT.add(job);
            else if (compName.equals("Google")) {
                GIT.add(job);
            }
        }

        for (int i = 0; i < Amazon.recruiters.size(); i++)
            AIT.add(Amazon.recruiters.get(i));
        for (int i = 0; i < Google.recruiters.size(); i++)
            GIT.add(Google.recruiters.get(i));

        // adaugarea job-urilor din consumers.json in departamente
        for (Job j : jobs) {
            for (Department d : app.getCompany(j.companyName).departments) {
                if (d.getClass().getSimpleName().equals(j.department)) {
                    d.add(j);
                    break;
                }
            }
        }

        // afisarea aplicatiei inainte de aplicarea user-ilor
        System.out.println("Before\n" + app + "\n-----------------------------\n");

        // afisarea notificarilor inainte de apply
        for (User user : app.users)
            System.out.println(user.r.getInfo().getName() + ":\n" + user.getNotifications());

        // aplicarea userilor la job-uri
        for (User user : app.users) {
            for (Job job : app.getJobs(user.interested_companies)) {
                job.apply(user);
            }
        }

//        for (User user : app.users)
//            System.out.println(user.r.getInfo().getName() + ":\n" + user.getNotifications());

        // procesarea request-urilor
        for (Company company : app.getCompanies()) {
            for (Job job : company.getJobs()) {
                company.manager.process(job);
            }
        }

        // afisarea aplicatiei dupa angajarea user-ilor
        System.out.println("\nAfter\n" + app + "\n-----------------------------\n");

        // afisarea notificarilor dupa apply si process
        for (User user : app.users)
            System.out.println(user.r.getInfo().getName() + ":\n" + user.getNotifications());

        AdminPage page = new AdminPage();
    }
}
