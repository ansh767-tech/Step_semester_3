package Object_Class.assignment_problems;

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

abstract class MembershipPlan {
    private String planName;
    private int durationMonths;

    public MembershipPlan(String planName, int durationMonths) {
        this.planName = planName;
        this.durationMonths = durationMonths;
    }

    public String getPlanName() { return planName; }
    public int getDurationMonths() { return durationMonths; }

    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() {
        super("Monthly", 1);
    }

    @Override
    public double calculateFee() {
        return 1000.0;
    }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() {
        super("Quarterly", 3);
    }

    @Override
    public double calculateFee() {
        return (1000.0 * 3) * 0.90; // 10% off
    }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() {
        super("Annual", 12);
    }

    @Override
    public double calculateFee() {
        return (1000.0 * 12) * 0.75; // 25% off
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;
    private double fee;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
        this.status = MembershipStatus.ACTIVE;

        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: Active.\n",
                plan.getPlanName(), member.getName(), fee);
    }

    public MembershipStatus getStatus() { return status; }
    public Member getMember() { return member; }

    public void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + status + ".");
        }
    }

    public void freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }
        status = MembershipStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
    }

    public void unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }
        status = MembershipStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
    }

    public void expire() {
        status = MembershipStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: Expired.");
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();

        raviMembership.expire();
        raviMembership.freeze();
    }
}