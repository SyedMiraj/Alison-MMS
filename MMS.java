

public class MMS {

    private Supermarket supermarket;

    private Memberships memberships;
    private Utils utils;

    public MMS(Supermarket supermarket, Memberships memberships) {
        this.supermarket = supermarket;
        this.memberships = memberships;
        utils = new Utils();
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    public Memberships getMemberships() {
        return memberships;
    }

    public void setMemberships(Memberships memberships) {
        this.memberships = memberships;
    }

    public void printMMSReport(){
        this.utils.slipHeader( );
        for(Membership membership : this.memberships.getMembershipList()){
            System.out.format( this.utils.slipFormat, membership.getName(), membership.getExpense(), membership.getTotalCredit(), membership.getDollarAvailable(), membership.getType());
        }
        this.utils.slipTableEnd();
        System.out.println();
        this.utils.TotalSumHeader();
        System.out.format(this.utils.sumFormat, "Total expense", getTotalExpense());
        System.out.format(this.utils.sumFormat, "Total credits", getTotalCredit());
        System.out.format(this.utils.sumFormat, "Total dollars", getTotalDollar());
        System.out.format(this.utils.sumFormat, "Average pay per credit", getAveragePayPerCredit());
        System.out.format(this.utils.sumFormat, "Average deduction rate", getAverageDeductionRate());
        System.out.format(this.utils.sumFormat, "Average gas deduction rate", getAverageGasDeductionRate());
        this.utils.TotalSumTableEnd();

    }

    private Double getAverageGasDeductionRate() {
        return this.memberships.getMembershipList()
                .stream()
                .mapToDouble(Membership::getGasDeductionRate)
                .average()
                .orElse(0.0);
    }

    private Double getAverageDeductionRate() {
        return this.memberships.getMembershipList()
                .stream()
                .mapToDouble(Membership::getDeductionRate)
                .average()
                .orElse(0.0);
    }

    private Double getAveragePayPerCredit() {
        return this.memberships.getMembershipList()
                .stream()
                .mapToDouble(Membership::getPayPerCredit)
                .average()
                .orElse(0.0);
    }

    private Double getTotalDollar() {
        return this.memberships.getMembershipList()
                .stream()
                .mapToDouble(Membership::getDollarAvailable)
                .sum();
    }

    private Double getTotalCredit() {
        return this.memberships.getMembershipList()
                .stream()
                .mapToDouble(Membership::getTotalCredit)
                .sum();
    }

    private Double getTotalExpense() {
        return this.memberships.getMembershipList()
                .stream()
                .mapToDouble(Membership::getExpense)
                .sum();
    }
}
