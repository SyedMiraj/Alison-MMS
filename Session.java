

import java.util.*;

public class Session {

    private Supermarkets supermarkets;
    private Supermarket supermarket;
    private Utils utils;

    public Session() {
        supermarkets = new Supermarkets();
        supermarkets.setSupermarkets(initializeSupermarketsData());
        utils = new Utils();
    }

    private Supermarket loginScreen(Scanner scanner) {
        Supermarket loggedInUser = null;
        while (loggedInUser == null) {
            System.out.print("Email: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            loggedInUser = this.supermarkets
                    .getSupermarkets()
                    .stream()
                    .filter(x -> x.getEmail().equals(username))
                    .findFirst()
                    .orElse(null);
            if (loggedInUser == null) {
                System.out.println("Incorrect SuperMarket details!");
                this.startMenuRoutes(scanner);
            }
            if (loggedInUser != null && !loggedInUser.getPassword().equals(password)) {
                System.out.println("Incorrect SuperMarket details!");
                this.startMenuRoutes(scanner);
            }
        }
        return loggedInUser;
    }

    private Map<String, String> adminMenu() {
        Map<String, String> adminMenus = new LinkedHashMap<>();
        adminMenus.put("C", "Add Membership");
        adminMenus.put("R", "View Membership");
        adminMenus.put("U", "Update Membership");
        adminMenus.put("D", "Delete Membership");
        adminMenus.put("V", "View Memberships");
        adminMenus.put("M", "MMS Menu");
        adminMenus.put("X", "Logout");
        return adminMenus;
    }

    private Map<String, String> mmsMenu() {
        Map<String, String> mmsMenus = new LinkedHashMap<>();
        mmsMenus.put("F", "Find Slip Details");
        mmsMenus.put("V", "View MMS Report");
        mmsMenus.put("A", "Archive MMS Report");
        mmsMenus.put("R", "Retrieve MMS Report");
        mmsMenus.put("S", "Show MMS Log");
        mmsMenus.put("X", "Close");
        return mmsMenus;
    }

    private Map<String, String> startMenu() {
        Map<String, String> startMenus = new LinkedHashMap<>();
        startMenus.put("L", "Login");
        startMenus.put("X", "Exit");
        return startMenus;
    }

    public void printAdminMenus() {
        System.out.println("Admin Menu: ");
        for (Map.Entry<String, String> entry : this.adminMenu().entrySet()) {
            System.out.println(entry.getKey() + "- " + entry.getValue());
        }
    }

    public void printMMSMenus() {
        System.out.println("MMS Menu: ");
        for (Map.Entry<String, String> entry : this.mmsMenu().entrySet()) {
            System.out.println(entry.getKey() + "- " + entry.getValue());
        }
    }

    public void printStartMenus() {
        for (Map.Entry<String, String> entry : this.startMenu().entrySet()) {
            System.out.println(entry.getKey() + "- " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Session session = new Session();
        Scanner scanner = new Scanner(System.in);
        Utils utils = session.getUtils();
        MMSLog mmsLog = new MMSLog();
        System.out.println("Membership Management System:");
        session.printStartMenus();
        Supermarket loggedInUser = session.startMenuRoutes(scanner);
        if (loggedInUser != null) {
            session.printAdminMenus();
            session.adminMenuRoutes(scanner, loggedInUser, mmsLog);
        }
    }

    private Supermarket startMenuRoutes(Scanner scanner) {
        System.out.print("Command (L/X): ");
        String menuId = scanner.nextLine();
        Supermarket supermarket = null;
        switch (menuId) {
            case "L":
                supermarket = this.loginScreen(scanner);
                break;
            case "X":
                this.terminate();
                break;
            default:
                System.out.println("Membership Management System:");
                this.startMenuRoutes(scanner);
                break;
        }
        return supermarket;
    }

    private void terminate() {
        System.out.println("");
        System.out.println("Session Terminated!");
    }

    private void adminMenuRoutes(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        System.out.print("Session Admin: " + loggedInUser.getName() + " - Menu Commands (C/R/U/D/V/M/X): ");
        String menuId = scanner.nextLine();
        if(menuId.isEmpty()){
            menuId = scanner.nextLine();
        }
        switch (menuId) {
            case "C":
                this.displayAddMembership(scanner, loggedInUser, mmsLog);
                break;
            case "R":
                this.displayMembership(scanner, loggedInUser, mmsLog);
                break;
            case "U":
                this.displayUpdateMembership(scanner, loggedInUser, mmsLog);
                break;
            case "D":
                this.displayDeleteMembership(scanner, loggedInUser, mmsLog);
                break;
            case "V":
                this.displayMemberships(scanner, loggedInUser, mmsLog);
                break;
            case "M":
                this.displaySlipReport(scanner, loggedInUser, mmsLog);
                break;
            case "X":
                this.displayLogout(scanner);
                break;
            default:
                this.adminMenuRoutes(scanner, loggedInUser, mmsLog);
                break;
        }
    }

    private void displaySlipReport(Scanner scanner, Supermarket loggedinuser, MMSLog mmsLog) {
        this.printMMSMenus();
        this.mmsMenuRoutes(scanner, loggedinuser, mmsLog, null);
    }

    private void mmsMenuRoutes(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog, MMS mms) {
        System.out.print("Session Admin: " + loggedInUser.getName() + " - Menu Commands (F/V/A/R/S/X): ");
        String menuId = scanner.nextLine();
        switch (menuId) {
            case "F":
                this.displaySlipDetails(scanner, loggedInUser, mmsLog);
                break;
            case "V":
                this.displayMMSReport(scanner, loggedInUser, mmsLog);
                break;
            case "A":
                this.displayArchiveMMSReport(scanner, loggedInUser, mmsLog, mms);
                break;
            case "R":
                this.displayRetrieveMMSReport(scanner, loggedInUser, mmsLog);
                break;
            case "S":
                this.displayShowMMSLog(scanner, loggedInUser, mmsLog);
                break;
            case "X":
                this.displayClose(scanner, loggedInUser, mmsLog);
                break;
            default:
                System.out.println("Invalid menu selected");
                this.mmsMenuRoutes(scanner, loggedInUser, mmsLog, mms);
                break;
        }
    }

    private void displayClose(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        this.adminMenuRoutes(scanner, loggedInUser, mmsLog);
    }

    private void displayShowMMSLog(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        mmsLog.printMMSArchive();
        this.mmsMenuRoutes(scanner, loggedInUser, mmsLog, null);
    }

    private void displayRetrieveMMSReport(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        System.out.print("RecordID: ");
        String recordId = scanner.nextLine();
        mmsLog.retrieveMMS(recordId);
        this.mmsMenuRoutes(scanner, loggedInUser, mmsLog, null);
    }

    private void displayArchiveMMSReport(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog, MMS mms) {
        if (mms == null) {
            mms = createNewMMS(loggedInUser);
        }
        String recordId = mmsLog.addMMS(loggedInUser, mms);
        System.out.println("MMS record is created as: " + recordId);
        this.mmsMenuRoutes(scanner, loggedInUser, mmsLog, null);
    }

    private MMS createNewMMS(Supermarket loggedInUser) {
        List<Membership> membershipList = new ArrayList<>(loggedInUser.getMemberships().getMembershipList());
        Memberships memberships = new Memberships();
        memberships.setMembershipList(membershipList);
        return new MMS(loggedInUser, memberships);
    }

    private void displayMMSReport(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        MMS mms = createNewMMS(loggedInUser);
        mms.printMMSReport();
        this.mmsMenuRoutes(scanner, loggedInUser, mmsLog, mms);
    }

    private void displaySlipDetails(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Optional<Membership> membership = loggedInUser.getMemberships().findMember(name);
        if (membership.isPresent()) {
            MMSlip mmSlip = new MMSlip(membership.get());
            mmSlip.printSlip();
        } else {
            System.out.println("Slip details does not exist for " + name);
        }
        this.mmsMenuRoutes(scanner, loggedInUser, mmsLog, null);
    }

    private void displayDeleteMembership(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Optional<Membership> membership = loggedInUser.getMemberships().findMember(name);
        if (membership.isPresent()) {
            loggedInUser.getMemberships().removeMembership(membership.get());
            System.out.println(name + " record has been deleted.");
        } else {
            System.out.println(name + " record does not exist!");
        }
        this.adminMenuRoutes(scanner, loggedInUser, mmsLog);
    }

    private void displayLogout(Scanner scanner) {
        this.terminate();
    }

    private void displayUpdateMembership(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Optional<Membership> membership = loggedInUser.getMemberships().findMember(name);
        if (membership.isPresent()) {
            Membership persisted = membership.get();
            System.out.println("Updating " + name + " record: ");
            System.out.print("Name: ");
            String newName = scanner.nextLine();
            System.out.print("Email: ");
            String newEmail = scanner.nextLine();
            System.out.print("Phone: ");
            String newPhone = scanner.nextLine();
            System.out.print("Address: ");
            String newAddress = scanner.nextLine();
            System.out.print("ID: ");
            String newId = scanner.nextLine();
            System.out.print("expense: ");
            double newExpense = scanner.nextDouble();
            loggedInUser.getMemberships().updateMember(persisted, newName, newEmail, newPhone, newAddress, newId, newExpense);
            System.out.println(name + " record has been updated.");
        } else {
            System.out.println(name + " record does not exist!");
        }
        this.adminMenuRoutes(scanner, loggedInUser, mmsLog);
    }

    private void displayMembership(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        List<Membership> filteredResult = loggedInUser.getMemberships().searchMembership(name);
        if (!filteredResult.isEmpty()) {
            this.utils.membershipHeader();
            for (Membership membership : filteredResult) {
                System.out.format(this.utils.membershipFormat, membership.getName(), membership.getEmail(), membership.getPhone(), membership.getType());
            }
            this.utils.membershipTableEnd();
        } else{
            System.out.println(name + " record does not exist!");
        }
        this.adminMenuRoutes(scanner, loggedInUser, mmsLog);
    }

    private void displayAddMembership(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        System.out.print("Name: ");
        String memberName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("expense: ");
        double expense = scanner.nextDouble();
        Membership newMembership = new Membership(id, memberName, email, phone, address, expense);
        loggedInUser.getMemberships().addMembership(newMembership);
        System.out.println(memberName + " record has been created.");
        adminMenuRoutes(scanner, loggedInUser, mmsLog);
    }

    public void displayMemberships(Scanner scanner, Supermarket loggedInUser, MMSLog mmsLog) {
        this.utils.membershipHeader();
        for (Membership membership : loggedInUser.getMemberships().getMembershipList()) {
            System.out.format(this.utils.membershipFormat, membership.getName(), membership.getEmail(), membership.getPhone(), membership.getType());
        }
        this.utils.membershipTableEnd();
        this.adminMenuRoutes(scanner, loggedInUser, mmsLog);
    }

    private List<Supermarket> initializeSupermarketsData() {
        List<Supermarket> supermarkets = new ArrayList<>();
        supermarkets.add(new Supermarket("John Smith", "john.smith@uts.com", "user222"));
        supermarkets.add(new Supermarket("Jane Tyler", "jane.tyler@uts.com", "super123"));
        return supermarkets;
    }

    public Supermarkets getSupermarkets() {
        return supermarkets;
    }

    public void setSupermarkets(Supermarkets supermarkets) {
        this.supermarkets = supermarkets;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }
}

