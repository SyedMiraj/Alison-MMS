

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Memberships {

    private List<Membership> membershipList = new ArrayList<>();

    public Memberships() {
        membershipList.add(new Membership("13697480", "Thomas Muller", "thomas.muller@uts.com", "99991111", "3 Byern St. Sydney 2001", 2134.5));
        membershipList.add(new Membership("14517880", "Alice Stefan", "alice.stefan@uts.com", "88881111", "24 Pitt St. Sydney 2001", 4512.2));
        membershipList.add(new Membership("13267102", "Lucy Lu", "lucy.lu@uts.com", "98981100", "11 Hunter St. Sydney 2100", 158.4));
        membershipList.add(new Membership("13678020", "Andreas Brehme", "andreas.b@uts.com", "90001222", "91 Sussex St. Sydney 2100", 7596.3));
        membershipList.add(new Membership("13972870", "Ruddy Voller", "ruddy.v@uts.com", "98980000", "15 Stan St. Sydney 2100", 1100.0));
        membershipList.add(new Membership("13859610", "Monica Shwarz", "monica.s@uts.com", "92241188", "151 Jones St. Sydney 2100", 6741.2));
    }

    public void addMembership(Membership membership){
        this.membershipList.add(membership);
    }

    public void removeMembership(Membership membership){
        this.membershipList.remove(membership);
    }

    public List<Membership> searchMembership(String name){
        return this.membershipList
                .stream()
                .filter(x -> x.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Optional<Membership> findMember(String name){
        return this.membershipList
                .stream()
                .filter(x -> x.getName().equals(name))
                .findFirst();
    }

    public void updateMember(Membership membership, String newName, String newEmail, String newPhone, String newAddress, String newId, double newExpense){
        membership.setName(newName);
        membership.setEmail(newEmail);
        membership.setPhone(newPhone);
        membership.setAddress(newAddress);
        membership.setId(newId);
        membership.setExpense(newExpense);
    }

    public List<Membership> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<Membership> membershipList) {
        this.membershipList = membershipList;
    }
}

