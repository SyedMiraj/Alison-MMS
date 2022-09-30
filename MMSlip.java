

public class MMSlip {

    private Membership membership;
    private Utils utils = new Utils();

    public MMSlip(Membership membership) {
        this.membership = membership;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public void printSlip() {
        this.utils.Totalslipheader();
        System.out.format(this.utils.slipFormat, this.membership.getName(), this.membership.getExpense(), this.membership.getTotalCredit(), this.membership.getDollarAvailable(), this.membership.getType());
        this.utils.TotalslipTableEnd();
    }
}

