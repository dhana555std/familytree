package org.dhana.family;

import java.util.*;

import org.dhana.utils.MemberUtils;

/**
 * @author Dhanapathi Marepalli
 * Class representing the Family Member in the family tree. This class represents a Node in the Family Hierarchy.
 */
public class Member {
    private String name;
    private int age;
    private Character gender;
    private Member father;
    private Member mother;
    private List<Member> kids;
    private final String id;

    public Member() {
        this.id = UUID.randomUUID().toString();
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Member getFather() {
        return father;
    }

    public Member getMother() {
        return mother;
    }

    public List<Member> getKids() {
        return kids;
    }

    public Character getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    /**
     * Add a parent to the list of children.
     * @param parent the {@link Member} which is to be added as a parent to the member.
     */
    public void addParent(Member parent) {
        parent.kids = (parent.kids == null || parent.kids.isEmpty()) ? new ArrayList<>() : parent.getKids();

        parent.kids.add(this);
        if (parent.gender.equals('M')) {
            this.father = parent;
        } else {
            this.mother = parent;
        }

    }

    /**
     * Add a child to the list of children.
     * @param kid the {@link Member} which is to be added as a Child to the list of Children.
     */
    public void addKid(Member kid) {
        this.kids = (this.kids == null || this.kids.isEmpty()) ? new ArrayList<>() : this.getKids();
        this.kids.add(kid);
        if (this.gender.equals('M')) {
            kid.father = this;
        } else {
            kid.mother = this;
        }
    }

    /**
     * Display the family members in the sorted order.
     * @return {@link Set} of family members sorted based on the `sortedOrder` passed in.
     */
    List<Member> displayFamilyMembers() {
        List<Member> members = new LinkedList<>();
        MemberUtils.addMemberInSortedOrder(this, members);
        addAncestorsInSortedOrder(this, members);
        addDescendantsInSortedOrder(this, members);
        return members;
    }

    /**
     * Add the ancestors i.e. parent, grandparent and the great grandparents of the current `member`
     * to the `members` set in the sorted order.
     *
     * @param member the current member.
     * @param members the final set of sorted members in the family.
     */
    private void addAncestorsInSortedOrder(Member member, List<Member> members) {
        if (member.father != null) {
            MemberUtils.addMemberInSortedOrder(member.father, members);
            addAncestorsInSortedOrder(member.father, members);
        }

        if (member.mother != null) {
            MemberUtils.addMemberInSortedOrder(member.mother, members);
            addAncestorsInSortedOrder(member.mother, members);
        }
    }

    /**
     * Add the descendants i.e. the kids and the grand children of the current `member`
     * to the `members` set in the sorted order.
     *  @param member the current member.
     * @param members the final set of sorted members in the family.
     */
    private void addDescendantsInSortedOrder(Member member, List<Member> members) {
        MemberUtils.addMemberInSortedOrder(member, members);
        if (member.kids != null && !member.kids.isEmpty()) {
            for (Member kid : member.kids) {
                addDescendantsInSortedOrder(kid, members);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Member)) {return false;}
        Member mem = (Member) o;
        return mem.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", gender=" + gender +
               ", id='" + id + '\'' +
               '}';
    }
}
